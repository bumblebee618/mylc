
public class Z_PizzaOrderClient 
{
	public static void main(String[] args)
	{
		System.out.println(PizzaFlavor.PINEAPPLE.toString() + ": " + PizzaShop.getInstance().checkPrice(PizzaFlavor.PINEAPPLE));
		
		String userName = "Jackie";
		CustomerOrder order = PizzaShop.getInstance().placeOrder(userName, PizzaFlavor.PINEAPPLE);
		System.out.println(order);
		
		System.out.println(PizzaShop.getInstance().checkOrder(order.getOrderId()));
		
		if (PizzaShop.getInstance().checkOrder("123") == null)
		{
			System.out.println("order doesn't exist");
		}
	}
}
