public enum PizzaFlavor
{
	PINEAPPLE(1), PEPPER(2), BEEF(3);
	
	private int id;
	
	PizzaFlavor(int id)
	{
		this.id = id;
	}
	
	public int getId()
	{
		return id;
	}
}