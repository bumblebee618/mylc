import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CustomerOrder 
{
	private String orderId;
	private String userName;
	private Map<Pizza, Integer> pizzaInventory;
	
	public CustomerOrder(String orderId, String userName)
	{
		this.orderId = orderId;
		this.userName = userName;
		pizzaInventory = new HashMap<>();
	}
	
	public void addPizzaToOrder(Pizza pizza, int count)
	{
		pizzaInventory.put(pizza, pizzaInventory.getOrDefault(pizza, 0) + count);
	}
	
	public void removePizzaFromOrder(Pizza pizza, int count)
	{
		if (!pizzaInventory.containsKey(pizza))
		{
			return;
		}
		
		int curCount = pizzaInventory.get(pizza);
		
		if (curCount <= count)
		{
			pizzaInventory.remove(pizza);
		}
		else
		{
			pizzaInventory.put(pizza, curCount-count);
		}
	}

	public String getOrderId() 
	{
		return orderId;
	}

	public String getUserName() 
	{
		return userName;
	}

	public List<Pizza> getPizzas() 
	{
		List<Pizza> pizzaList = new LinkedList<>();
		
		for (Pizza pizza : pizzaInventory.keySet())
		{
			for (int i = 0; i < pizzaInventory.get(pizza); i++)
			{
				pizzaList.add(pizza);
			}
		}
		
		return pizzaList;
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		
		for (Pizza pizza : pizzaInventory.keySet())
		{
			builder.append(pizza.toString()).append("-").append(pizzaInventory.get(pizza)).append(", ");
		}
		
		return String.format("Order Id: %s, User Name: %s, Pizza: %s", orderId, userName, builder.toString());
	}
}
