public interface PoolController 
{
	public void feedFish(int foodCount);
	
	public void checkFishesStatus();

	public void heatPool(int template);
	
	public void coolPool(int template);
}