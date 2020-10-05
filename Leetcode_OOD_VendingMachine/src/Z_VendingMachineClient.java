import java.util.List;

public class Z_VendingMachineClient {
	public static void main(String[] args)
	{
		VendingMachine vm = VendingMachineFactory.createVendingMachine();
		System.out.println("Price need to pay:" + vm.selectItemAndGetPrice(Item.COKE));
		System.out.println("Current balance: " + vm.insertCoin(Coin.QUARTER));
		System.out.println("Current balance: " + vm.insertCoin(Coin.QUARTER));
		
		try
		{
			Bucket<Item, List<Coin>> bucket = vm.collectItemAndChange();
			System.out.println("Item: " + bucket.getFirst());
			
			for (Coin coin : bucket.getSecond())
			{
				System.out.println("Coin: " + coin);
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
