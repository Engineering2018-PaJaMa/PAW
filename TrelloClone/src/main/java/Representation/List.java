package Representation;

import org.bson.types.ObjectId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class List
{
	private ObjectId id;
	private String title;
	private String description;
	private ObjectId boardId;
	private int position;
	private String state;
	private String visibility;
	private Card card;

}
