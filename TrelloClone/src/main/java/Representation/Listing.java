package Representation;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Listing
{
	private int id;
	private String title;
	private String description;
	private int boardId;
	private int position;
	private String state;
	private String visibility;
	private List<Card> cards;
}
