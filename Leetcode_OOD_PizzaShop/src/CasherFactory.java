import java.util.List;

public class CasherFactory 
{
	public static double getBalance(CasherType type, List<Pizza> pizzas)
	{
		switch (type)
		{
			case Discount: return new CasherDiscount(pizzas).getTotalPrice();
			case Normal: 
			default: return new CasherNormal(pizzas).getTotalPrice();
		}
	}
}