import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PizzaShopImpl 
{
	private Map<PizzaFlavor, Double> flavorToprice; 
	private Map<String, CustomerOrder> orderIdToOrder;
	
	private static PizzaShopImpl instance;
	
	private PizzaShopImpl()
	{
		orderIdToOrder = new HashMap<>();
		initPriceInfo();
	}
	
	public static PizzaShopImpl getInstance()
	{
		if (instance == null)
		{
			synchronized (PizzaShopImpl.class) 
			{
				if (instance == null)
				{
					instance = new PizzaShopImpl(); 
				}
			}
		}
		
		return instance;
	}
	
	public CustomerOrder placeOrder(String userName, Map<PizzaFlavor, Integer> pizzaInventory)
	{
		String orderId = UUID.randomUUID().toString();
		CustomerOrder order = new CustomerOrder(orderId, userName);
		
		for (PizzaFlavor flavor : pizzaInventory.keySet())
		{
			Pizza pizza = PizzaFactory.createPizza(flavor, flavorToprice.get(flavor));
			order.addPizzaToOrder(pizza, pizzaInventory.get(flavor));
		}
		
		orderIdToOrder.put(orderId, order);
		return order;
	}
	
	public double checkPrice(CustomerOrder order, CasherType type)
	{
		return CasherFactory.getBalance(type, order.getPizzas());
	} 
	
	public CustomerOrder checkOrder(String orderId)
	{
		return orderIdToOrder.getOrDefault(orderId, null);
	}
	
	private void initPriceInfo()
	{
		flavorToprice = new HashMap<PizzaFlavor, Double>();
		flavorToprice.put(PizzaFlavor.PINEAPPLE, 5.99);
		flavorToprice.put(PizzaFlavor.PEPPER, 6.99);
		flavorToprice.put(PizzaFlavor.BEEF, 7.99);
	}
}
