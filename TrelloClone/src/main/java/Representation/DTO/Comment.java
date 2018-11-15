package Representation.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment
{
	private int id;
	private int cardId;
	private String name;
	private String description;
}
