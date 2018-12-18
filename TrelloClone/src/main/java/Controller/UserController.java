package Controller;

import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.client.MongoCollection;

import Representation.DTO.User;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController implements EndpointController
{
	private Validator validator;
	private MongoCollection<Document> collection;
	private MongoCollection<Document> history;
	private Logger logger;

	public UserController(final Validator validator)
	{
		this.validator = validator;
		collection = databaseController.getCollection("users");
		history = databaseController.getCollection("history");
		logger = LoggerFactory.getLogger(UserController.class);
	}

	@GET
	@Override
	public List<Document> getAll()
	{
		logger.info("Returning all users");
		List<Document> users = new ArrayList<>();

		for (Document d : collection.find())
		{
			users.add(d);
		}
		return users;
	}

	@GET
	@Path("/userParent/{userrrrr}")
	@Override
	public List<Document> getByParentId(@PathParam("userrrrr") String parentID) {
		return null;
	}

	@GET
	@Path("/{username}")
	public Optional<Document> get(@PathParam("username") final String username)
	{
		logger.info("Returning info from database for user: {}", username);
		return Optional.ofNullable(collection.find(eq("username", username)).first());
	}

	@POST
	@Path("/register")
	public User create(final String json) throws IOException
	{
		User user = objectMapper.readValue(json, User.class);
		logger.info("Creating data for user: {}", user.getUsername());
		history.insertOne(new Document("change", "Created user with name" + user.getUsername()));
		collection.insertOne(converter.convert(user));
		return user;
	}

	@DELETE
	@Path("/{username}")
	public Document delete(@PathParam("username") final String username)
	{
		logger.info("Deleting from database user: {}", username);
		history.insertOne(new Document("change", "Deleted user with name" + username));
		return collection.findOneAndDelete(eq("username", username));
	}
}
