import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PizzaShop 
{
	private Map<PizzaFlavor, Double> flavorToprice; 
	private Map<String, CustomerOrder> orderIdToOrder;
	
	private static PizzaShop instance;
	
	private PizzaShop()
	{
		orderIdToOrder = new HashMap<>();
		
		flavorToprice = new HashMap<PizzaFlavor, Double>();
		flavorToprice.put(PizzaFlavor.PINEAPPLE, 5.99);
		flavorToprice.put(PizzaFlavor.PEPPER, 6.99);
		flavorToprice.put(PizzaFlavor.BEEF, 7.99);
	}
	
	public static PizzaShop getInstance()
	{
		if (instance == null)
		{
			synchronized (PizzaShop.class) 
			{
				if (instance == null)
				{
					instance = new PizzaShop(); 
				}
			}
		}
		
		return instance;
	}
	
	public CustomerOrder placeOrder(String userName, PizzaFlavor flavor)
	{
		String orderId = UUID.randomUUID().toString();
		Pizza pizza = PizzaFactory.createPizza(flavor, flavorToprice.get(flavor));
		CustomerOrder order = new CustomerOrder(orderId, userName, pizza);
		orderIdToOrder.put(orderId, order);
		return order;
	}
	
	public double checkPrice(PizzaFlavor flavor)
	{
		return flavorToprice.get(flavor);
	} 
	
	public CustomerOrder checkOrder(String orderId)
	{
		return orderIdToOrder.getOrDefault(orderId, null);
	}
}