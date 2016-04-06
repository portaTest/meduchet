package tesla.meduchet.services;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import tesla.meduchet.gui.domain.User;

@Component
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AuthenticationService {

	private User user;

	public void login(User user) {
		this.user = user;
	}

	public void logout() {
		user = null;
	}

	public boolean isLogin() {
		boolean retval = false;
		if (user == null) {
			retval = true;
		}
		return retval;
	}
}
