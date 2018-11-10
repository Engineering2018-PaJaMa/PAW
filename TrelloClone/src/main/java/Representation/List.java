package Representation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class List
{
	private int id;
	private String title;
	private String description;
	private int boardId;
	private int position;
	private String state;
	private String visibility;
	private Card card;

}
