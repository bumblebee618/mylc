import java.util.List;

public abstract class CasherBase 
{
	protected List<Pizza> pizzas;
	
	public CasherBase(List<Pizza> pizzas)
	{
		this.pizzas = pizzas;
	}
	
	public abstract double getTotalPrice();
	
	protected double caculatePrice(List<Pizza> pizzas)
	{
		double totalPrice = 0;
		
		for (Pizza pizza : pizzas)
		{
			totalPrice += pizza.getPrice();
		}
		
		return totalPrice;
	}
}
