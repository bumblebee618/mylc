public interface PoolController 
{
	public void feedFish(int foodCount);
	
	public void checkFishesStatus();
	
	public void checkSingleFishStatus(int fishId);

	public void ModifyPoolTemperature(double template);
}
