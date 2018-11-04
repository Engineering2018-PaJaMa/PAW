package PawProject;

import Authentication.AppAuthorizer;
import Authentication.BasicAuthenticator;
import Controller.BoardController;
import Controller.CardController;
import Controller.ListingController;
import Representation.User;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

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
		environment.jersey().register(new BoardController(environment.getValidator()));
		environment.jersey().register(new CardController(environment.getValidator()));
		environment.jersey().register(new ListingController(environment.getValidator()));


        environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                .setAuthenticator(new BasicAuthenticator())
                .setAuthorizer(new AppAuthorizer())
                .setRealm("BASIC-AUTH-REALM")
                .buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
	}
}
