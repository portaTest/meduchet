package tesla.meduchet.gui.view;

import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;

import tesla.meduchet.gui.DashboardNavigator;

@SuppressWarnings("serial")
public class MainView extends HorizontalLayout {
	

	public MainView() {
        setSizeFull();
        addStyleName("mainview");

        addComponent(new DashboardMenu());

        ComponentContainer content = new CssLayout();
        content.addStyleName("view-content");
        content.setSizeFull();
        addComponent(content);
        setExpandRatio(content, 1.0f);
        new DashboardNavigator(content);
    }
}
