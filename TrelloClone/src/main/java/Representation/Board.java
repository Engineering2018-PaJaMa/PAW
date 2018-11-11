package Representation;

import javax.validation.constraints.NotNull;

import org.bson.Document;
import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Board extends Document
{
	@NotNull
	private int id;

	@NotBlank
	private String name;

	private String state;
	private String visibilty;
	private int userId;
	private List list;
}
