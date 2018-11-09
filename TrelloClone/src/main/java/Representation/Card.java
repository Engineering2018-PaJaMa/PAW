package Representation;

import org.bson.types.ObjectId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card
{
	private ObjectId id;
	private String title;
	private String description;
	private ObjectId listId;
	private int postion;
	private Comment comment;

}
