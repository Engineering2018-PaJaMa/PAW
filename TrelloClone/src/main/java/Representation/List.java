package Representation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class List
{
	private long id;
	private String title;
	private String description;
	private long boardId;
	private int position;
	private String state;
	private String visibility;
}
