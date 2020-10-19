import java.util.List;

public class CasherFactory 
{
	public static CasherBase getCasher(CasherType type, List<Pizza> pizzas)
	{
		switch (type)
		{
			case DISCOUNT: return new CasherDiscount(pizzas);
			case NORMAL: 
			default: return new CasherNormal(pizzas);
		}
	}
}
