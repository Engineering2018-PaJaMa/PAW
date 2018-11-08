package Controller;

import javax.validation.Validator;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Representation.Card;

@Path("/boards/{id}/lists/{id}/cards/{id}/comment/{id}")
@Produces(MediaType.APPLICATION_JSON)
public class CommentController
{
	private final Validator validator;

	public CommentController(final Validator validator)
	{
		this.validator = validator;
	}

	@GET
	public Card getCOmment()
	{
		return new Card(2, "Komentarz 1", "Get Pierwsza Karta");
	}

	@POST
	public Card createCommenr()
	{
		return new Card(2, "Komentarz 1", "Post Pierwsza Karta");
	}

	@DELETE
	public Card removeCommentById()
	{
		return new Card(2, "Komentarz 1", "Delete Pierwsza Karta");
	}
}
