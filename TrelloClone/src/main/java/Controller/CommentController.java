package Controller;

import javax.validation.Validator;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Representation.DTO.Card;

@Path("/boards/{id}/lists/{id}/cards/{id}/comment/{id}")
@Produces(MediaType.APPLICATION_JSON)
public class CommentController extends EndpointController
{
	public CommentController(final Validator validator)
	{
		super(validator);
	}

	@GET
	public Card getComment()
	{
		return new Card();
	}

	@POST
	public Card createComment()
	{
		return new Card();
	}

	@DELETE
	public Card removeCommentById()
	{
		return new Card();
	}
}
