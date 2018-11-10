package Representation;

import javax.validation.constraints.NotNull;

import org.bson.Document;
import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Board extends Document
{
	@NotNull
	@Getter
	@Setter
	private int id;

	@NotBlank
	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	private String state;
	@Getter
	@Setter
	private String visibilty;
	@Getter
	@Setter
	private int userId;
	private List list;
}
