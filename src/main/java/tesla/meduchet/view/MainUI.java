package tesla.meduchet.view;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

import tesla.meduchet.domain.User;

@SpringUI
@SpringComponent
@SuppressWarnings("serial")
public class MainUI extends UI{

	@Override
	protected void init(final VaadinRequest request) {
		updateContent();
	}

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
