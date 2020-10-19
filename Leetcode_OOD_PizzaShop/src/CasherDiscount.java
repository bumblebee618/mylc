import java.util.List;

public class CasherDiscount extends CasherBase
{
	private final double discountRate = 0.8;
	
	public CasherDiscount(List<Pizza> pizzas) 
	{
		super(pizzas);
	}
	
	@Override
	public double getFinalPrice()
	{
		return caculatePrice() * discountRate;
	}
}
