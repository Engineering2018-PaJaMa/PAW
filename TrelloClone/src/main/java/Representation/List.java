package Representation;

public class List
{
	private int id;
	private int listId;
	private String title;
	private String description;

	public List(int id, int listId, String title, String description)
	{
		this.id = id;
		this.listId = listId;
		this.title = title;
		this.description = description;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getListId()
	{
		return listId;
	}

	public void setListId(int listId)
	{
		this.listId = listId;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
}
