public class CustomerOrder 
{
	private String orderId;
	private String userName;
	private Pizza pizza;
	
	public CustomerOrder(String orderId, String userName, Pizza pizza)
	{
		this.orderId = orderId;
		this.userName = userName;
		this.pizza = pizza;
	}

	public String getOrderId() 
	{
		return orderId;
	}

	public String getUserName() 
	{
		return userName;
	}

	public Pizza getPizza() 
	{
		return pizza;
	}
	
	@Override
	public String toString()
	{
		return String.format("Order Id: %s, User Name: %s, Pizza: %s", orderId, userName, pizza.getFlavor().toString());
	}
}
