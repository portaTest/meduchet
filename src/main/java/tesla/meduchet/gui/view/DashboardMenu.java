package tesla.meduchet.gui.view;

import com.google.common.eventbus.Subscribe;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

import tesla.meduchet.gui.DashboardUI;
import tesla.meduchet.gui.component.ProfilePreferencesWindow;
import tesla.meduchet.gui.domain.User;
import tesla.meduchet.gui.event.DashboardEvent.PostViewChangeEvent;
import tesla.meduchet.gui.event.DashboardEvent.ProfileUpdatedEvent;
import tesla.meduchet.gui.event.DashboardEvent.UserLoggedOutEvent;

@SuppressWarnings("serial")
public class DashboardMenu extends CustomComponent {

	public static final String ID = "dashboard-menu";
	public static final String REPORTS_BADGE_ID = "dashboard-menu-reports-badge";
	public static final String NOTIFICATIONS_BADGE_ID = "dashboard-menu-notifications-badge";
	private static final String STYLE_VISIBLE = "valo-menu-visible";
	private Label notificationsBadge;
	private Label reportsBadge;
	private MenuItem settingsItem;

	public DashboardMenu() {
		setPrimaryStyleName("valo-menu");
		setId(ID);
		setSizeUndefined();

		DashboardUI.getDashboardEventbus().register(this);

		setCompositionRoot(buildContent());
	}

	private Component buildContent() {
		final CssLayout menuContent = new CssLayout();
		menuContent.addStyleName("sidebar");
		menuContent.addStyleName(ValoTheme.MENU_PART);
		menuContent.addStyleName("no-vertical-drag-hints");
		menuContent.addStyleName("no-horizontal-drag-hints");
		menuContent.setWidth(null);
		menuContent.setHeight("100%");

		menuContent.addComponent(buildTitle());
		menuContent.addComponent(buildUserMenu());
		menuContent.addComponent(buildToggleButton());
        menuContent.addComponent(buildMenuItems());

		return menuContent;
	}

	private Component buildTitle() {
		Label logo = new Label("Tesla <strong>Dashboard</strong>", ContentMode.HTML);
		logo.setSizeUndefined();
		HorizontalLayout logoWrapper = new HorizontalLayout(logo);
		logoWrapper.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
		logoWrapper.addStyleName("valo-menu-title");
		return logoWrapper;
	}

	private User getCurrentUser() {
		return (User) VaadinSession.getCurrent().getAttribute(User.class.getName());
	}

	private Component buildUserMenu() {
		final MenuBar settings = new MenuBar();
		settings.addStyleName("user-menu");
		final User user = getCurrentUser();
		settingsItem = settings.addItem("", new ThemeResource("img/profile-pic-300px.jpg"), null);
		updateUserName(null);
		settingsItem.addItem("Edit Profile", new Command() {
			@Override
			public void menuSelected(final MenuItem selectedItem) {
				ProfilePreferencesWindow.open(user, false);
			}
		});
		settingsItem.addItem("Preferences", new Command() {
			@Override
			public void menuSelected(final MenuItem selectedItem) {
				ProfilePreferencesWindow.open(user, true);
			}
		});
		settingsItem.addSeparator();
		settingsItem.addItem("Sign Out", new Command() {
			@Override
			public void menuSelected(final MenuItem selectedItem) {
				DashboardUI.getDashboardEventbus().post(new UserLoggedOutEvent());
			}
		});
		return settings;
	}

	private Component buildToggleButton() {
		Button valoMenuToggleButton = new Button("Menu");
		valoMenuToggleButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(final ClickEvent event) {
				if (getCompositionRoot().getStyleName().contains(STYLE_VISIBLE)) {
					getCompositionRoot().removeStyleName(STYLE_VISIBLE);
				} else {
					getCompositionRoot().addStyleName(STYLE_VISIBLE);
				}
			}
		});
		valoMenuToggleButton.setIcon(FontAwesome.LIST);
		valoMenuToggleButton.addStyleName("valo-menu-toggle");
		valoMenuToggleButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
		valoMenuToggleButton.addStyleName(ValoTheme.BUTTON_SMALL);
		return valoMenuToggleButton;
	}

	private Component buildMenuItems() {
		CssLayout menuItemsLayout = new CssLayout();
		menuItemsLayout.addStyleName("valo-menuitems");

		for (final DashboardViewType view : DashboardViewType.values()) {
			Component menuItemComponent = new ValoMenuItemButton(view);

			if (view == DashboardViewType.DASHBOARD) {
				notificationsBadge = new Label();
				notificationsBadge.setId(NOTIFICATIONS_BADGE_ID);
				menuItemComponent = buildBadgeWrapper(menuItemComponent, notificationsBadge);
			}
			if (view == DashboardViewType.REPORTS) {
				reportsBadge = new Label();
				reportsBadge.setId(REPORTS_BADGE_ID);
				menuItemComponent = buildBadgeWrapper(menuItemComponent, reportsBadge);
			}

			menuItemsLayout.addComponent(menuItemComponent);
		}
		return menuItemsLayout;

	}
	

	private Component buildBadgeWrapper(final Component menuItemButton, final Component badgeLabel) {
		CssLayout dashboardWrapper = new CssLayout(menuItemButton);
		dashboardWrapper.addStyleName("badgewrapper");
		dashboardWrapper.addStyleName(ValoTheme.MENU_ITEM);
		badgeLabel.addStyleName(ValoTheme.MENU_BADGE);
		badgeLabel.setWidthUndefined();
		badgeLabel.setVisible(false);
		dashboardWrapper.addComponent(badgeLabel);
		return dashboardWrapper;
	}

	@Subscribe
	public void updateUserName(final ProfileUpdatedEvent event) {
		User user = getCurrentUser();
		settingsItem.setText(user.getFirstName() + " " + user.getLastName());
	}

	public final class ValoMenuItemButton extends Button {

		private static final String STYLE_SELECTED = "selected";

		private final DashboardViewType view;

		public ValoMenuItemButton(final DashboardViewType view) {
			this.view = view;
			setPrimaryStyleName("valo-menu-item");
			setIcon(view.getIcon());
			setCaption(view.getViewName().substring(0, 1).toUpperCase() + view.getViewName().substring(1));
			DashboardUI.getDashboardEventbus().register(this);
			addClickListener(new ClickListener() {
				@Override
				public void buttonClick(final ClickEvent event) {
					UI.getCurrent().getNavigator().navigateTo(view.getViewName());
				}
			});

		}

		@Subscribe
		public void postViewChange(final PostViewChangeEvent event) {
			removeStyleName(STYLE_SELECTED);
			if (event.getView() == view) {
				addStyleName(STYLE_SELECTED);
			}
		}
	}
}
