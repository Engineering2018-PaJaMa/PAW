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
	private String name;
	private String state;
	private String visibilty;
	private int userId;
}
