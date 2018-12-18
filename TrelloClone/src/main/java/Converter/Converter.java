package Converter;

import org.bson.Document;

import Representation.DTO.Board;
import Representation.DTO.Card;
import Representation.DTO.Comment;
import Representation.DTO.List;
import Representation.DTO.User;

/**
 * Created by Paweł Szopa on 11/11/2018
 */
public class Converter
{
	public Document convert(User user)
	{
		return new Document("username", user.getUsername()).append("password", user.getPassword());
	}

	public Document convert(Board board)
	{
		return new Document("id", board.getId()).append("userId", board.getUserId())
				.append("name", board.getName())
				.append("description", board.getDescription()).append("visibility", board.getVisibility().toString());
	}

	public Document convert(List list)
	{
		return new Document("id", list.getId()).append("boardId", list.getBoardId())
				.append("name", list.getName())
				.append("description", list.getDescription())
				.append("position", list.getPosition()).append("visibility", list.getVisibility().toString());
	}

	public Document convert(Card card)
	{
		return new Document("id", card.getId()).append("listId", card.getListId())
				.append("name", card.getName())
				.append("description", card.getDescription())
				.append("position", card.getPosition()).append("visibility", card.getVisibility().toString());
	}

	public Document convert(Comment comment)
	{
		return new Document("id", comment.getId()).append("cardId", comment.getCardId())
				.append("name", comment.getName())
				.append("description", comment.getDescription());
	}
}
