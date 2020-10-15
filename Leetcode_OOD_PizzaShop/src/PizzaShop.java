import java.util.Map;

public interface PizzaShop 
{
	public CustomerOrder placeOrder(String userName, Map<PizzaFlavor, Integer> pizzaInventory);
	public double checkPrice(CustomerOrder order, CasherType type);
	public CustomerOrder checkOrder(String orderId);
}
