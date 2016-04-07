package tesla.meduchet.gui.event;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;

@Component
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class DashboardEventBus implements SubscriberExceptionHandler {

	private final EventBus eventBus = new EventBus(this);

	public void post(final Object event) {
		eventBus.post(event);
	}

	public void register(final Object object) {
		eventBus.register(object);
	}

	public void unregister(final Object object) {
		eventBus.unregister(object);
	}

	@Override
	public final void handleException(final Throwable exception, final SubscriberExceptionContext context) {
		exception.printStackTrace();
	}
}
