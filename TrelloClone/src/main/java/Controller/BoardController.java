package Controller;

import static com.mongodb.client.model.Filters.eq;

import java.util.Optional;
import java.util.Set;

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
import Representation.List;

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
	public Optional<Document> getBoard(@PathParam("id") final String id)
	{
		LOGGER.info("Returning info from database for board: {}", id);
		return Optional.ofNullable(databaseController.getCollection("boards").find(eq("boardId", id)).first());
	}

	@POST
	public Board createBoardById(@PathParam("id") final String id)
	{
		//I think we should get them from FormParam and use something along curl -X POST -d 'title=listTittle&etc..'
		LOGGER.info("Creating board with id: {}", id);
		Document document = new Document("boardId", id).append("name", "boardName")
				.append("state", "boardState")
				.append("visibility", "boardVisibility")
				.append("userId", "1")
				.append("lists", null);
		databaseController.getCollection("boards").insertOne(document);
		return new Board(Integer.valueOf(id), "boardName", "boardState", "boardVisibility", 1, Set.of(new List()));
	}

	@DELETE
	public Document deleteBoardById(@PathParam("id") final String id)
	{
		LOGGER.info("Deleting board with id: {}", id);
		return databaseController.getCollection("boardId").findOneAndDelete(eq("boardId", id));
	}
}
