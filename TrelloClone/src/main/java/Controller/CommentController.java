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

import Representation.DTO.Comment;

@Path("/comments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentController implements EndpointController
{
	private Validator validator;
	private MongoCollection<Document> collection;
	private MongoCollection<Document> history;
	private Logger logger;

	public CommentController(final Validator validator)
	{
		this.validator = validator;
		collection = databaseController.getCollection("comments");
		history = databaseController.getCollection("history");
		logger = LoggerFactory.getLogger(CommentController.class);
	}

	@GET
	@Override
	public List<Document> getAll()
	{
		logger.info("Returning all comments");
		List<Document> comments = new ArrayList<>();

		for (Document d : collection.find())
		{
			comments.add(d);
		}
		return comments;
	}
	@GET
	@Path("/cardParent/{cardId}")
	@Override
	public List<Document> getByParentId(@PathParam("cardId") Integer parentID) {
		List<Document> comments = new ArrayList<>();

		for (Document d : collection.find(eq("cardId", parentID)))
		{
			comments.add(d);
		}
		return comments;
	}


	@GET
	@Path("/{name}")
	@Override
	public Optional<Document> get(@PathParam("name") final String name)
	{
		logger.info("Returning info from database for comment {}", name);
		return Optional.ofNullable(collection.find(eq("name", name)).first());
	}

	@POST
	@Path("/create")
	@Override
	public Comment create(final String json) throws IOException
	{
		Comment comment = objectMapper.readValue(json, Comment.class);
		logger.info("Creating comment {}", comment.getName());
		history.insertOne(new Document("change", "Created comment with name" + comment.getName()));
		collection.insertOne(converter.convert(comment));
		return comment;
	}

	@DELETE
	@Path("/{name}")
	@Override
	public Document delete(@PathParam("name") final String name)
	{
		logger.info("Deleting comment {}", name);
		history.insertOne(new Document("change", "Deleted comment with name" + name));
		return collection.findOneAndDelete(eq("name", name));
	}
}
