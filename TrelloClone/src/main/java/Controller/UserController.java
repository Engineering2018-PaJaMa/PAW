package Controller;

import javax.validation.Validator;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserController
{
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	private final Validator validator;

	public UserController(final Validator validator)
	{
		this.validator = validator;
	}

	@GET
	@Path("/{id}")
	public String getUser()
	{
		LOGGER.info("get");
		return "User ";
	}

	@POST
	@Path("/register")
	public String register(@FormParam("username") String username, @FormParam("password") String password)
	{
		LOGGER.info("Receiving data");

		MongoClient mongoClient = MongoClients.create();
		MongoDatabase database = mongoClient.getDatabase("TrelloClone");
		MongoCollection<Document> collection = database.getCollection("user");

		Document document = new Document("username", username).append("password", password).append("registerDate", "08.11.2018");

		collection.insertOne(document);

		return "User " + username + ", Password: " + password;
	}

	@POST
	@Path("/postparam")
	public String postParam(@FormParam("message") String message)
	{
		return "You posted " + message;
	}

}
