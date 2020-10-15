public class Z_Client 
{
	public static void main(String[] args) throws InterruptedException
	{
		PoolControllerImpl controller = PoolControllerImpl.getInstance();
		controller.feedFish(10);
		controller.checkFishesStatus();
		
		Thread.sleep(5000);
		
		controller.checkFishesStatus();
		controller.feedFish(100);
		controller.checkFishesStatus();
	}
}
