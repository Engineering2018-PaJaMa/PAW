package Controller;

import javax.validation.Validator;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/boards/{id}/lists/{id}")
@Produces(MediaType.APPLICATION_JSON)
public class ListController extends EndpointController
{
	public ListController(final Validator validator)
	{
		this.validator = validator;
	}

	//	@PermitAll
	//	@GET
	//	public Optional<Document> getListById(@PathParam("id") final String id)
	//	{
	//		LOGGER.info("Returning info from database of list with id: {}", id);
	//		return Optional.ofNullable(databaseController.getCollection("lists").find(eq("listId", id)).first());
	//	}

	//	@POST
	//	public List createListById(@PathParam("id") final String id)
	//	{
	//		I think we should get them from FormParam and use something along curl -X POST -d 'title=listTittle&etc..'
	//		LOGGER.info("Creating list for id: {}", id);
	//		Document document = new Document("listId", id).append("title", "listTittle")
	//				.append("description", "listDesc")
	//				.append("boardId", "1")
	//				.append("position", "10")
	//				.append("state", "listState")
	//				.append("visibility", "listVisibility")
	//				.append("cards", null);
	//		databaseController.getCollection("lists").insertOne(document);
	//		return new List(Integer.valueOf(id), "listTittle", "listDesc", 1, 10, "listState", "listVisibility", new Card());
	//	}
	//
	//	@DELETE
	//	public Document removeListById(@PathParam("id") final String id)
	//	{
	//		LOGGER.info("Deleting from database list with id: {}", id);
	//		return databaseController.getCollection("lists").findOneAndDelete(eq("listId", id));
	//	}
}
