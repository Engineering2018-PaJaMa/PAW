package Representation;

import org.bson.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Listing extends Document
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
