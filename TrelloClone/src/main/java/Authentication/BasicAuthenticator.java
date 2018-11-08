package Authentication;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import Representation.User;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

public class BasicAuthenticator implements Authenticator<BasicCredentials, User>
{
	private static final Map<String, Set<String>> VALID_USERS = ImmutableMap.of("guest",
			ImmutableSet.of(),
			"user",
			ImmutableSet.of("USER"),
			"admin",
			ImmutableSet.of("ADMIN", "USER"));

	@Override
	public Optional<User> authenticate(BasicCredentials credentials)
	{
		if (VALID_USERS.containsKey(credentials.getUsername()) && "password".equals(credentials.getPassword()))
		{
			return Optional.of(new User(credentials.getUsername(), VALID_USERS.get(credentials.getUsername()), credentials.getPassword()));
		}
		return Optional.empty();
	}
}