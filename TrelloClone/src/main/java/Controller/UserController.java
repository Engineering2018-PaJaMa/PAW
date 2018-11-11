package Controller;

import static com.mongodb.client.model.Filters.eq;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import javax.validation.Validator;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;

import Representation.User;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserController extends EndpointController
{
	public UserController(final Validator validator)
	{
		this.validator = validator;
	}

	@GET
	@Path("/{username}")
	public Optional<Document> getUser(@PathParam("username") final String username)
	{
		LOGGER.info("Returning info from database for user: {}", username);
		return Optional.ofNullable(databaseController.getCollection("user").find(eq("username", username)).first());
	}

	@POST
	@Path("/register")
	public User register(@FormParam("username") final String username, @FormParam("password") final String password)
	{
		LOGGER.info("Creating data for user: {}", username);
		Document document = new Document("username", username).append("password", password).append("registerDate", LocalDate.now().toString());
		databaseController.getCollection("user").insertOne(document);

		return new User(username, password, Set.of("READ_ROLE"));
	}

	@DELETE
	@Path("/{username}")
	public Document deleteUserByUsername(@PathParam("username") final String username)
	{
		LOGGER.info("Deleting from database user: {}", username);
		return databaseController.getCollection("user").findOneAndDelete(eq("username", username));
	}
}
