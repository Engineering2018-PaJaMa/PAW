package Controller;

import java.net.URISyntaxException;

import javax.validation.Validator;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/boards/cards")
@Produces(MediaType.APPLICATION_JSON)
public class CardsController
{
	private final Validator validator;

	public CardsController(Validator validator)
	{
		this.validator = validator;
	}

	@GET
	public Response getCards() {
		return Response.ok("Karta").build();
	}

	@POST
	public Response createCard() throws URISyntaxException
	{
		return Response.status(Response.Status.CREATED).build();
	}

	@PUT
	@Path("{boards}/{id}")
	public Response updateCardById() {
		return Response.status(Response.Status.ACCEPTED).build();
	}

	@DELETE
	@Path("{boards}/{id}")
	public Response removeCardById() {
		return Response.status(Response.Status.OK).build();
	}
}
