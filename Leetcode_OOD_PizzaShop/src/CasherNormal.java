import java.util.List;

public class CasherNormal extends CasherBase
{
	public CasherNormal(List<Pizza> pizzas) 
	{
		super(pizzas);
	}
	
	@Override
	public double getFinalPrice()
	{
		return caculatePrice();
	}
}
