package Representation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card
{
	private long id;
	private String title;
	private String description;
	private String listId;
	private int postion;

}
