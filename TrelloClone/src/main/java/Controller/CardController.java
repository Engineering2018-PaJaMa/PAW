package Controller;

import javax.validation.Validator;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Representation.Card;

@Path("/boards/{id}/lists/{id}/cards/{id}")
@Produces(MediaType.APPLICATION_JSON)
public class CardController
{
	private final Validator validator;

	public CardController(final Validator validator)
	{
		this.validator = validator;
	}

	@GET
	public Card getCards()
	{
		return new Card(2, "Karta 1", "Get Pierwsza Karta");
	}

	@POST
	public Card createCard()
	{
		return new Card(2, "Karta 1", "Post Pierwsza Karta");
	}

	@DELETE
	public Card removeCardById()
	{
		return new Card(2, "Karta 1", "Delete Pierwsza Karta");
	}
}
