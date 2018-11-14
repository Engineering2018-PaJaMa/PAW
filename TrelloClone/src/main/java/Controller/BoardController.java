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

import com.mongodb.client.MongoCollection;

import Representation.Board;
import Representation.Card;
import Representation.Comment;
import Representation.Listing;

@Path("/boards/{id}")
@Produces(MediaType.APPLICATION_JSON)
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
	public Board createBoardById(@PathParam("id") final int id)
	{
		//I think we should get them from FormParam and use something along curl -X POST -d 'title=listTittle&etc..'
		//Another idea which I like better right now is to use ObjectMapper and convert whole json to Board.class but who teh fuck cares
		//No one reads what i type there so I'll decide
		//I like banananas
		//Second one is B E T T E R
		LOGGER.info("Creating board with id: {}", id);
		Board board = new Board(id, "boardName", "boardState", "boardVisibility", 1, List.of(new Listing(
				1,
				"titleList",
				"descList",
				5,
				1,
				"stateList",
				"visibilityList",
				List.of(new Card(1, "titleCard", "descCard", 5, 1, List.of(new Comment(1, "titleComment", "messageComment", 1)))))));

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
