package Controller;

import javax.annotation.security.PermitAll;
import javax.validation.Validator;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Representation.List;

@Path("/boards/{id}/lists/{id}")
@Produces(MediaType.APPLICATION_JSON)
public class ListingController
{
	private final Validator validator;

	public ListingController(final Validator validator)
	{
		this.validator = validator;
	}

	@PermitAll
	@GET
	public List getBoards()
	{
		return new List(1, "Lista zadan", "Get Lista zadan 1");
	}

	@POST
	public List updateBoardsById()
	{
		return new List(1, "Lista zadan", "Post Lista zadan 1");
	}

	@DELETE
	public List removeBoardsById()
	{
		return new List(1, "Lista zadan", "Delete Lista zadan 1");
	}
}
