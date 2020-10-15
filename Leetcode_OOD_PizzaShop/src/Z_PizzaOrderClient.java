import java.util.HashMap;
import java.util.Map;

public class Z_PizzaOrderClient 
{
	public static void main(String[] args)
	{
		Map<PizzaFlavor, Integer> pizzaMap = new HashMap<>();
		pizzaMap.put(PizzaFlavor.PINEAPPLE, 1);
		pizzaMap.put(PizzaFlavor.BEEF, 2);
		
		String userName = "Jackie";
		CustomerOrder order = PizzaShopImpl.getInstance().placeOrder(userName, pizzaMap);
		
		System.out.println(order);
		System.out.println("Total price: " + PizzaShopImpl.getInstance().checkPrice(order, CasherType.Normal));
		System.out.println("Discount price: " + PizzaShopImpl.getInstance().checkPrice(order, CasherType.Discount));
		System.out.println(PizzaShopImpl.getInstance().checkOrder(order.getOrderId()));
		
		if (PizzaShopImpl.getInstance().checkOrder("123") == null)
		{
			System.out.println("order doesn't exist");
		}
	}
}
