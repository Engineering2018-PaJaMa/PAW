package Controller;

import static com.mongodb.client.model.Filters.eq;

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

import Representation.DTO.List;

@Path("/boards/{boardId}/lists/{id}")
@Produces(MediaType.APPLICATION_JSON)
public class ListController extends EndpointController
{
	public ListController(final Validator validator)
	{
		super(validator);
	}

	@PermitAll
	@GET
	public Optional<Document> getListById(@PathParam("boardId") final int boardId, @PathParam("listId") final int listId)
	{
		LOGGER.info("Returning info for boardId: {} for listId: {}", boardId, listId);
		return Optional.ofNullable(databaseController.getCollection("lists").find(eq("listId", listId)).first());
	}

	@POST
	public List createListById(@PathParam("boardId") final int boardId, @PathParam("listId") final int listId)
	{
		//I think we should get them from FormParam and use something along curl -X POST -d 'title=listTittle&etc..'
		//getboardFromDatabase
		//Add list to that board
		List list = new List();
		LOGGER.info("Creating list for boardId: {}, listId: {}", boardId, listId);
		return list;
	}

	@DELETE
	public Document removeListById(@PathParam("boardId") final int boardId, @PathParam("listId") final int listId)
	{
		LOGGER.info("Deleting from database list for boardId: {}, with listId: {}", boardId, listId);
		return databaseController.getCollection("lists").findOneAndDelete(eq("listId", listId));
	}
}
