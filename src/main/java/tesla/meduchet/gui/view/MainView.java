package tesla.meduchet.gui.view;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;

@SpringComponent
@UIScope
@SuppressWarnings("serial")
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

        //new DashboardNavigator(content);
    }
}
