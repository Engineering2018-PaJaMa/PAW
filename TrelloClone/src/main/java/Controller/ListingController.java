package Controller;

import javax.annotation.security.PermitAll;
import javax.validation.Validator;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Representation.Listing;

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
	public Listing getBoards()
	{
		return new Listing(1, "Lista zadan", "Get Lista zadan 1");
	}

	@POST
	public Listing updateBoardsById()
	{
		return new Listing(1, "Lista zadan", "Post Lista zadan 1");
	}

	@DELETE
	public Listing removeBoardsById()
	{
		return new Listing(1, "Lista zadan", "Delete Lista zadan 1");
	}
}
