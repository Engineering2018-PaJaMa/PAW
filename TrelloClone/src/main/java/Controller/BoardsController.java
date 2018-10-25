package Controller;

import java.net.URISyntaxException;

import javax.annotation.security.PermitAll;
import javax.validation.Validator;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/boards")
@Produces(MediaType.APPLICATION_JSON)
public class BoardsController
{
	private final Validator validator;

	public BoardsController(Validator validator)
	{
		this.validator = validator;
	}

	@PermitAll
	@GET
	public Response getBoards() {
		return Response.ok("Tablice").build();
	}

	@POST
	public Response createBoards() throws URISyntaxException
	{
		return Response.status(Response.Status.CREATED).build();
	}

	@PUT
	@Path("/{id}")
	public Response updateBoardsById() {
		return Response.status(Response.Status.ACCEPTED).build();
	}

	@DELETE
	@Path("/{id}")
	public Response removeBoardsById() {
		return Response.status(Response.Status.OK).build();
	}
}
