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
public class CommentController extends EndpointController
{
	public CommentController(final Validator validator)
	{
		this.validator = validator;
	}

	@GET
	public Card getCOmment()
	{
		return new Card();
	}

	@POST
	public Card createCommenr()
	{
		return new Card();
	}

	@DELETE
	public Card removeCommentById()
	{
		return new Card();
	}
}
