package Representation;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board
{
	@NotNull
	private int id;

	@NotBlank
	private String name;

	private String state;
	private String visibilty;
	private int userId;
	private List<Listing> lists;
}
