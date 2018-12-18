package Controller;

import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.util.ArrayList;
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

import Representation.DTO.List;

@Path("/lists")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ListController implements EndpointController
{
	private Validator validator;
	private MongoCollection<Document> collection;
	private MongoCollection<Document> history;
	private Logger logger;

	public ListController(final Validator validator)
	{
		this.validator = validator;
		collection = databaseController.getCollection("lists");
		history = databaseController.getCollection("history");
		logger = LoggerFactory.getLogger(List.class);
	}

	@GET
	@Override
	public java.util.List<Document> getAll()
	{
		logger.info("Returning all lists");
		java.util.List<Document> lists = new ArrayList<>();

		for (Document d : collection.find())
		{
			lists.add(d);
		}
		return lists;
	}

	@GET
	@Path("/{name}")
	@Override
	public Optional<Document> get(@PathParam("name") final String name)
	{
		logger.info("Returning info from database for list {}", name);
		return Optional.ofNullable(collection.find(eq("name", name)).first());
	}

	@POST
	@Path("/create")
	@Override
	public List create(final String json) throws IOException
	{
		List list = objectMapper.readValue(json, List.class);
		logger.info("Creating list {}", list.getName());
		history.insertOne(new Document("change", "Created list with name" + list.getName()));
		collection.insertOne(converter.convert(list));
		return list;
	}

	@DELETE
	@Path("/{name}")
	@Override
	public Document delete(@PathParam("name") final String name)
	{
		logger.info("Deleting list {}", name);
		history.insertOne(new Document("change", "Deleted list with name" + name));
		return collection.findOneAndDelete(eq("name", name));
	}
}
