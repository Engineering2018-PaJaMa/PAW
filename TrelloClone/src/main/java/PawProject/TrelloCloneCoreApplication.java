package PawProject;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.Registration;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import Controller.BoardController;
import Controller.CardController;
import Controller.CommentController;
import Controller.HistoryController;
import Controller.ListController;
import Controller.UserController;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TrelloCloneCoreApplication extends Application<TrelloCloneCoreConfiguration>
{
	public static void main(final String[] args) throws Exception
	{
		new TrelloCloneCoreApplication().run(args);
	}

	@Override
	public String getName()
	{
		return "TrelloCloneCore";
	}

	@Override
	public void initialize(final Bootstrap<TrelloCloneCoreConfiguration> bootstrap)
	{
		// TODO: application initialization
	}

	@Override
	public void run(final TrelloCloneCoreConfiguration configuration, final Environment environment)
	{
		environment.jersey().register(new UserController(environment.getValidator()));
		environment.jersey().register(new BoardController(environment.getValidator()));
		environment.jersey().register(new ListController(environment.getValidator()));
		environment.jersey().register(new CardController(environment.getValidator()));
		environment.jersey().register(new CommentController(environment.getValidator()));
		environment.jersey().register(new HistoryController(environment.getValidator()));
		configureCors(environment);
	}

	// among other imports for Dropwizard, you'll need these specific ones:
	private void configureCors(Environment environment)
	{
		Registration.Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
		((FilterRegistration.Dynamic) filter).addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
		filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
		filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
		filter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
		filter.setInitParameter("allowedHeaders", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
		filter.setInitParameter("allowCredentials", "true");
	}
}
