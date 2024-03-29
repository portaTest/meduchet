package tesla.meduchet.gui;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

import tesla.meduchet.gui.event.DashboardEvent.BrowserResizeEvent;
import tesla.meduchet.gui.event.DashboardEvent.CloseOpenWindowsEvent;
import tesla.meduchet.gui.event.DashboardEvent.PostViewChangeEvent;
import tesla.meduchet.gui.view.DashboardViewType;

@SuppressWarnings("serial")
public class DashboardNavigator extends Navigator {

    
    private static final DashboardViewType ERROR_VIEW = DashboardViewType.DASHBOARD;
    private ViewProvider errorViewProvider;

    public DashboardNavigator(final ComponentContainer container) {
        super(UI.getCurrent(), container);
       
        initViewChangeListener();
        initViewProviders();

    }

 
    private void initViewChangeListener() {
        addViewChangeListener(new ViewChangeListener() {

            @Override
            public boolean beforeViewChange(final ViewChangeEvent event) {
                // Since there's no conditions in switching between the views
                // we can always return true.
                return true;
            }

            @Override
            public void afterViewChange(final ViewChangeEvent event) {
                DashboardViewType view = DashboardViewType.getByViewName(event
                        .getViewName());
                // Appropriate events get fired after the view is changed.
                DashboardUI.getDashboardEventbus().post(new PostViewChangeEvent(view));
                DashboardUI.getDashboardEventbus().post(new BrowserResizeEvent());
                DashboardUI.getDashboardEventbus().post(new CloseOpenWindowsEvent());

            }
        });
    }

    private void initViewProviders() {
        // A dedicated view provider is added for each separate view type
        for (final DashboardViewType viewType : DashboardViewType.values()) {
            ViewProvider viewProvider = new ClassBasedViewProvider(
                    viewType.getViewName(), viewType.getViewClass()) {

                private View cachedInstance;

                @Override
                public View getView(final String viewName) {
                    View result = null;
                    if (viewType.getViewName().equals(viewName)) {
                        if (viewType.isStateful()) {
                            // Stateful views get lazily instantiated
                            if (cachedInstance == null) {
                                cachedInstance = super.getView(viewType
                                        .getViewName());
                            }
                            result = cachedInstance;
                        } else {
                            // Non-stateful views get instantiated every time
                            // they're navigated to
                            result = super.getView(viewType.getViewName());
                        }
                    }
                    return result;
                }
            };

            if (viewType == ERROR_VIEW) {
                errorViewProvider = viewProvider;
            }

            addProvider(viewProvider);
        }

        setErrorProvider(new ViewProvider() {
            @Override
            public String getViewName(final String viewAndParameters) {
                return ERROR_VIEW.getViewName();
            }

            @Override
            public View getView(final String viewName) {
                return errorViewProvider.getView(ERROR_VIEW.getViewName());
            }
        });
    }
}