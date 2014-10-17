package rain;

/**
 *  Application Listener, fetches Service objects from Spring
 *
 *  @author 517 Development Team
 */



import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;import org.springframework.web.context.*;
import org.springframework.web.context.support.*;


public class RainAppListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent event) {
		ServletContext application = event.getServletContext();


		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		
		RainForestService rainforestService = (RainForestService)context.getBean("rainForestService");
		
		// Add to application context
		application.setAttribute(Constants.RAINFOREST_SERVICE_KEY, rainforestService);


		log(application, "Application Context Initialized: " + new java.util.Date());

	}

	public void contextDestroyed(ServletContextEvent event) {
		ServletContext application = event.getServletContext();

		log(application, "Application Context Destroyed: " + new java.util.Date());
	}

	private void log(ServletContext application, Object message) {
		// contextName is the <display-name> for the web app defined in web.xml
		String applicationName = application.getServletContextName();

		// log data to server's event log and stdout
		application.log(applicationName + ": " + message.toString());
		System.out.println(applicationName + ": " + message);
	}
}