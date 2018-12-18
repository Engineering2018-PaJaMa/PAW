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

import Representation.DTO.Board;

@Path("/boards")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BoardController implements EndpointController
{
	private Validator validator;
	private MongoCollection<Document> collection;
	private MongoCollection<Document> history;
	private Logger logger;

	public BoardController(final Validator validator)
	{
		this.validator = validator;
		collection = databaseController.getCollection("boards");
		history = databaseController.getCollection("history");
		logger = LoggerFactory.getLogger(BoardController.class);
	}

	@GET
	@Override
	public List<Document> getAll()
	{
		logger.info("Returning all boards");
		List<Document> boards = new ArrayList<>();

		for (Document d : collection.find())
		{
			boards.add(d);
		}
		return boards;
	}

	@GET
	@Path("/{all}")
	@Override
	public List<Document> getByParentId(@PathParam("all") String parentID) {
		return null;
	}


	@GET
	@Path("/{name}")
	@Override
	public Optional<Document> get(@PathParam("name") final String name)
	{
		logger.info("Returning info from database for board {}", name);
		return Optional.ofNullable(collection.find(eq("name", name)).first());
	}

	@POST
	@Path("/create")
	@Override
	public Board create(final String json) throws IOException
	{
		Board board = objectMapper.readValue(json, Board.class);
		logger.info("Creating board {}", board.getName());
		history.insertOne(new Document("change", "Created board with name" + board.getName()));
		collection.insertOne(converter.convert(board));
		return board;
	}

	@DELETE
	@Path("/{name}")
	@Override
	public Document delete(@PathParam("name") final String name)
	{
		logger.info("Deleting board {}", name);
		history.insertOne(new Document("change", "Deleted board with name" + name));
		return collection.findOneAndDelete(eq("name", name));
	}
}
