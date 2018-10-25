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

import Representation.Card;

@Path("/board{boardId}/cards")
@Produces(MediaType.APPLICATION_JSON)
public class CardController
{
	private final Validator validator;

	public CardController(Validator validator)
	{
		this.validator = validator;
	}

	@GET
	public Card getCards() {
		return new Card(2,1,"Karta 1","Pierwsza Karta");
	}

	@POST
	public Response createCard() throws URISyntaxException
	{
		return Response.ok("createCard").build();	}

	@PUT
	@Path("{boards}/{id}")
	public Response updateCardById() {
		return Response.ok("updateCardById").build();	}

	@DELETE
	@Path("{boards}/{id}")
	public Response removeCardById() {
		return Response.ok("removeCardById").build();	}
}
