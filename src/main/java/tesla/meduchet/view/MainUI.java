package tesla.meduchet.view;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

import tesla.meduchet.domain.User;


@SuppressWarnings("serial")
public class MainUI extends UI{

	  protected void init(final VaadinRequest request) {
	        addStyleName(ValoTheme.UI_WITH_MENU);

	        updateContent();
	    }

	    /**
	     * Updates the correct content for this UI based on the current user status.
	     * If the user is logged in with appropriate privileges, main view is shown.
	     * Otherwise login view is shown.
	     */
	    private void updateContent() {
	        User user = (User) VaadinSession.getCurrent().getAttribute(
	                User.class.getName());
	        if (user != null && "admin".equals(user.getRole())) {
	       
	            
	        } else {
	            setContent(new LoginView());
	            addStyleName("loginview");
	        }
	    }

}
