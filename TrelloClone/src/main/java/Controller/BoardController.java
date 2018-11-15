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
	private Logger logger;

	public BoardController(final Validator validator)
	{
		this.validator = validator;
		collection = databaseController.getCollection("boards");
		logger = LoggerFactory.getLogger(BoardController.class);
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
		collection.insertOne(converter.convert(board));
		return board;
	}

	@DELETE
	@Path("/{name}")
	@Override
	public Document delete(@PathParam("name") final String name)
	{
		logger.info("Deleting board {}", name);
		return collection.findOneAndDelete(eq("name", name));
	}
}
