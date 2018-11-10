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
import Representation.User;
import io.dropwizard.auth.Auth;

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
	public Board getBoards(@Auth User user)
	{
		return new Board();
	}

	@POST
	public Board updateBoardsById()
	{
		return new Board();
	}

	@DELETE
	public Board removeBoardsById()
	{
		return new Board();
	}
}
