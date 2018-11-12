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
		//TODO NOW
		return null;
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
		//TODO WITH LISTCONTROLLER
		return null;
	}

	public Document convert(Card card)
	{
		//TODO WITH CARDCONTROLLER
		return null;
	}

	public Document convert(Comment comment)
	{
		//TODO WITH COMMENTCONTROLLER
		return null;
	}
}
