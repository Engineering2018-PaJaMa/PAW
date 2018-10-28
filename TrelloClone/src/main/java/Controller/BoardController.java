package Controller;

import javax.annotation.security.PermitAll;
import javax.validation.Validator;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Representation.Board;

@Path("/boards/{id}")
@Produces(MediaType.APPLICATION_JSON)
public class BoardController
{
	private final Validator validator;

	public BoardController(final Validator validator)
	{
		this.validator = validator;
	}

	@PermitAll
	@GET
	public Board getBoards()
	{
		return new Board(1, "Get Tablica 1");
	}

	@POST
	public Board updateBoardsById()
	{
		return new Board(1, "Post Tablica 1");
	}

	@DELETE
	public Board removeBoardsById()
	{
		return new Board(1, "Delete Tablica 1");
	}
}
