package Representation.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board
{
	private int id;
	private int userId;
	private String name;
	private String description;
	private String state;
	private String visibility;
}
