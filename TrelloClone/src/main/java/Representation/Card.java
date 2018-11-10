package Representation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card
{
	private int id;
	private String title;
	private String description;
	private int listId;
	private int postion;
	private Comment comment;

}
