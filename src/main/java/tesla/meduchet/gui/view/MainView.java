package tesla.meduchet.gui.view;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

import tesla.meduchet.gui.DashboardNavigator;


public class MainView extends HorizontalLayout {
	
    @Autowired
	public MainView(DashboardMenu dashboardMenu) {
        setSizeFull();
        addStyleName("mainview");

        addComponent(dashboardMenu);

        ComponentContainer content = new CssLayout();
        content.addStyleName("view-content");
        content.setSizeFull();
        addComponent(content);
        setExpandRatio(content, 1.0f);

        if (UI.getCurrent()==null){
        	System.err.println("UI is null");
        }else{
        	System.err.println("navigator created");
        	new DashboardNavigator(content);
        }
    }
}
