package PawProject;

import Controller.BoardController;
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
		//		environment.jersey().register(new CardController(environment.getValidator()));
		//		environment.jersey().register(new ListController(environment.getValidator()));
	}
}
