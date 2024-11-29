package api.payload;

public class Pet {

	int id;
	PetCategory category;
	String name;
	String status;
	
	public int getId() 
	{
		return id;
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public PetCategory getCategory() 
	{
		return category;
	}
	
	public void setCategory(PetCategory category) 
	{
		this.category = category;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getStatus() 
	{
		return status;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}
	
}
