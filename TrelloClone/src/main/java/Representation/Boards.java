package Representation;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class Boards
{
	@NotNull
	private int id;
	@NotBlank
	@Length(min=2, max=255)
	private String name;

	public Boards(Integer id, String name)
	{
		this.id = id;
		this.name = name;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
