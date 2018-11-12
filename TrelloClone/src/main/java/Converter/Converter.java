package Converter;

import org.bson.Document;

import Representation.Board;
import Representation.Card;
import Representation.Comment;
import Representation.List;
import Representation.User;

/**
 * Created by Paweł Szopa on 11/11/2018
 */
public class Converter
{
	public Document convert(User user)
	{
		return new Document("name", user.getName()).append("password", user.getPassword()).append("roles", user.getRoles());
	}

	public Document convert(Board board)
	{
		return new Document("boardId", board.getId()).append("name", board.getName())
				.append("state", board.getState())
				.append("visibility", board.getVisibilty())
				.append("userId", board.getUserId())
				.append("lists", board.getLists());
	}

	public Document convert(List list)
	{
		return new Document("listId", list.getId()).append("title", list.getTitle())
				.append("description", list.getDescription())
				.append("boardId", list.getBoardId())
				.append("position", list.getPosition())
				.append("state", list.getState())
				.append("visibility", list.getVisibility())
				.append("card", list.getCard());
	}

	public Document convert(Card card)
	{
		return new Document("cardId", card.getId()).append("title", card.getTitle())
				.append("description", card.getDescription())
				.append("listId", card.getListId())
				.append("position", card.getPostion())
				.append("comment", card.getComment());
	}

	public Document convert(Comment comment)
	{
		return new Document("id", comment.getId()).append("title", comment.getTitle())
				.append("message", comment.getMessage())
				.append("position", comment.getPossition());
	}
}
