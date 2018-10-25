package Controller;

import javax.annotation.security.PermitAll;
import javax.validation.Validator;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/boards")
@Produces(MediaType.APPLICATION_JSON)
public class Boards
{
	private final Validator validator;

	public Boards(Validator validator)
	{
		this.validator = validator;
	}

	@PermitAll
	@GET
	public Response getBoards() {
		return Response.ok("Test").build();
	}
}
