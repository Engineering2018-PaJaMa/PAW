package Representation.DTO;

import Representation.Visibility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class List
{
	private int id;
	private int boardId;
	private String name;
	private String description;
	private int position;
	private Visibility visibility;
}
