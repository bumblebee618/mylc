import java.util.List;

public interface VendingMachine 
{
	public long selectItemAndGetPrice(Item item); 
	public long insertCoin(Coin coin); 
	public Bucket<Item, List<Coin>> collectItemAndChange(); 
	public List<Coin> refund(); 
	public void reset();
}
