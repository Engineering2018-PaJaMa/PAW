package Controller;

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

import Representation.Board;

@Path("/boards")
@Produces(MediaType.APPLICATION_JSON)
public class BoardController
{
	private final Validator validator;

	public BoardController(Validator validator)
	{
		this.validator = validator;
	}

	@PermitAll
	@GET
	public Board getBoards()
	{
		return new Board(1, "Tablica 1");
	}

	@POST
	public Response createBoards()
	{
		return Response.ok("createBoards").build();
	}

	@PUT
	@Path("/{id}")
	public Response updateBoardsById()
	{
		return Response.ok("updateBoardsById").build();
	}

	@DELETE
	@Path("/{id}")
	public Response removeBoardsById()
	{
		return Response.ok("removeBoardsById").build();
	}
}
