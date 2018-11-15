package Controller;

import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.util.Optional;

import javax.annotation.security.PermitAll;
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

import com.mongodb.client.MongoCollection;

import Representation.DTO.Board;

@Path("/boards/{id}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BoardController extends EndpointController
{
	private MongoCollection<Document> collection;

	public BoardController(final Validator validator)
	{
		super(validator);
		collection = databaseController.getCollection("boards");
	}

	@PermitAll
	@GET
	public Optional<Document> getBoard(@PathParam("id") final int id)
	{
		LOGGER.info("Returning info from database for board: {}", id);
		return Optional.ofNullable(collection.find(eq("boardId", id)).first());
	}

	@POST
	public Board createBoardById(final String json) throws IOException
	{
		Board board = objectMapper.readValue(json, Board.class);
		LOGGER.info("Creating board with id: {}", board.getId());
		collection.insertOne(converter.convert(board));
		return board;
	}

	@DELETE
	public Document deleteBoardById(@PathParam("id") final int id)
	{
		LOGGER.info("Deleting board with id: {}", id);
		return collection.findOneAndDelete(eq("boardId", id));
	}
}
