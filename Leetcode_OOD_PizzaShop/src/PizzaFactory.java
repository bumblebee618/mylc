public class PizzaFactory 
{	
	public static Pizza createPizza(PizzaFlavor flavor, double price)
	{
		switch (flavor)
		{
			case PINEAPPLE: return new Pizza(flavor, price);
			case PEPPER: return new Pizza(flavor, price);
			case BEEF: return new Pizza(flavor, price);
			default: return null;
		}
	}
}
