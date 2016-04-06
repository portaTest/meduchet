package tesla.meduchet.gui.view;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.themes.ValoTheme;

import tesla.meduchet.gui.component.ProfilePreferencesWindow;
import tesla.meduchet.gui.domain.User;
import tesla.meduchet.gui.event.DashboardEventBus;

/**
 * A responsive menu component providing user information and the controls for
 * primary navigation between the views.
 */


@SpringComponent
@UIScope
@SuppressWarnings("serial")
public class DashboardMenu extends CustomComponent {

    public static final String ID = "dashboard-menu";
    public static final String REPORTS_BADGE_ID = "dashboard-menu-reports-badge";
    public static final String NOTIFICATIONS_BADGE_ID = "dashboard-menu-notifications-badge";
    
    private DashboardEventBus eventBus;

    @Autowired
    public DashboardMenu(DashboardEventBus eventBus) {
    	this.eventBus=eventBus;
        setPrimaryStyleName("valo-menu");
        setId(ID);
        setSizeUndefined();
        
        eventBus.register(this);

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


        return menuContent;
    }

    private Component buildTitle() {
    	 User user = (User) VaadinSession.getCurrent().getAttribute(User.class);
    	 if (user==null){
    		 //TODO Find user!
    		 System.err.println("where is my user?");
    	 }
        Label logo = new Label("<strong>Dashboard</strong>",
                ContentMode.HTML);
        logo.setSizeUndefined();
        HorizontalLayout logoWrapper = new HorizontalLayout(logo);
        logoWrapper.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
        logoWrapper.addStyleName("valo-menu-title");
        return logoWrapper;
    }
    
    
}
