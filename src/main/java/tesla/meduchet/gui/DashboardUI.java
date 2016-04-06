package tesla.meduchet.gui;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.eventbus.Subscribe;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

import tesla.meduchet.gui.data.DataProvider;
import tesla.meduchet.gui.domain.User;
import tesla.meduchet.gui.event.DashboardEvent.CloseOpenWindowsEvent;
import tesla.meduchet.gui.event.DashboardEvent.UserLoggedOutEvent;
import tesla.meduchet.gui.event.DashboardEvent.UserLoginRequestedEvent;
import tesla.meduchet.gui.event.DashboardEventBus;
import tesla.meduchet.gui.view.LoginView;
import tesla.meduchet.gui.view.MainView;


@SpringUI
@UIScope
@Title("Meduchet")
@Theme("dashboard")
@SuppressWarnings("serial")
public class DashboardUI extends UI {


	@Autowired
    private DashboardEventBus eventBus;
	
	
	@Autowired
	private DataProvider dataProvider;
	

	@Override
    protected void init(VaadinRequest request) {
		eventBus.register(this);
        Responsive.makeResponsive(this);
        addStyleName(ValoTheme.UI_WITH_MENU);
        updateContent();
    }
	
    private void updateContent() {
        User user = (User) VaadinSession.getCurrent().getAttribute(
                User.class.getName());
        if (user != null && "admin".equals(user.getRole())) {
			setContent(new MainView());
			removeStyleName("loginview");
			getNavigator().navigateTo(getNavigator().getState());
        } else {
            setContent(new LoginView());
            addStyleName("loginview");
        }
    }

    @Subscribe
    public void userLoginRequested(final UserLoginRequestedEvent event) {
    	User user = dataProvider.authenticate(event.getUserName(),event.getPassword());
    	VaadinSession.getCurrent().setAttribute(User.class, user);
        updateContent();
    }

    @Subscribe
    public void userLoggedOut(final UserLoggedOutEvent event) {
        VaadinSession.getCurrent().close();
        Page.getCurrent().reload();
    }

    @Subscribe
    public void closeOpenWindows(final CloseOpenWindowsEvent event) {
        for (Window window : getWindows()) {
            window.close();
        }
    }
    
    public static DataProvider getDataProvider() {
        return ((DashboardUI) getCurrent()).dataProvider;
    }
    
    public static DashboardEventBus getDashboardEventbus() {
        return ((DashboardUI) getCurrent()).eventBus;
    }

}
