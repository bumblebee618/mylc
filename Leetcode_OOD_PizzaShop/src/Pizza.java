public class Pizza 
{
	private PizzaFlavor flavor;
	private double price;
	
	public Pizza(PizzaFlavor flavor, double price)
	{
		this.flavor = flavor;
		this.price = price;
	}

	public PizzaFlavor getFlavor() 
	{
		return flavor;
	}

	public double getPrice() 
	{
		return price;
	}
}