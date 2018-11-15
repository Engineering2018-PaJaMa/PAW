package Controller;

import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.util.Optional;

import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;

import Representation.DTO.User;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController extends EndpointController
{
	public UserController(final Validator validator)
	{
		super(validator);
	}

	@GET
	@Path("/{username}")
	public Optional<Document> getUser(@PathParam("username") final String username)
	{
		LOGGER.info("Returning info from database for user: {}", username);
		return Optional.ofNullable(databaseController.getCollection("users").find(eq("username", username)).first());
	}

	@POST
	@Path("/register")
	public User register(final String json) throws IOException
	{
		User user = objectMapper.readValue(json, User.class);
		LOGGER.info("Creating data for user: {}", user.getUsername());
		databaseController.getCollection("users").insertOne(converter.convert(user));
		return user;
	}

	@DELETE
	@Path("/{username}")
	public Document deleteUserByUsername(@PathParam("username") final String username)
	{
		LOGGER.info("Deleting from database user: {}", username);
		return databaseController.getCollection("users").findOneAndDelete(eq("username", username));
	}
}
