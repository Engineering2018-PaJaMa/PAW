package Controller;

import static com.mongodb.client.model.Filters.eq;

import java.util.List;
import java.util.Optional;

import javax.annotation.security.PermitAll;
import javax.validation.Validator;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;

import Representation.Board;
import Representation.Card;
import Representation.Comment;
import Representation.Listing;

@Path("/boards/{id}")
@Produces(MediaType.APPLICATION_JSON)
public class BoardController extends EndpointController
{
	public BoardController(final Validator validator)
	{
		this.validator = validator;
	}

	@PermitAll
	@GET
	public Optional<Document> getBoard(@PathParam("id") final int id)
	{
		LOGGER.info("Returning info from database for board: {}", id);
		return Optional.ofNullable(databaseController.getCollection("boards").find(eq("boardId", id)).first());
	}

	@POST
	public Board createBoardById(@PathParam("id") final int id)
	{
		//I think we should get them from FormParam and use something along curl -X POST -d 'title=listTittle&etc..'
		LOGGER.info("Creating board with id: {}", id);
		Board board = new Board(id, "boardName", "boardState", "boardVisibility", 1, List.of(new Listing(
				1,
				"titleList",
				"descList",
				5,
				1,
				"stateList",
				"visibilityList", List.of(new Card(1, "titleCard", "descCard", 5, 1, List.of(new Comment(1, "titleComment", "messageComment", 1)))))));

		databaseController.getCollection("boards").insertOne(converter.convert(board));
		return board;
	}

	@DELETE
	public Document deleteBoardById(@PathParam("id") final int id)
	{
		LOGGER.info("Deleting board with id: {}", id);
		return databaseController.getCollection("boards").findOneAndDelete(eq("boardId", id));
	}
}
