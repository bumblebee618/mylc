
public class ElevatorClient {
	public static void main(String[] args)
	{
		ElevatorManager eManager = ElevatorFactory.getElevatorManager();
		Thread thread = new Thread(eManager);
		thread.start();
		
		eManager.pressButton(1, 5);
		eManager.pressButton(2, 3);
		
		sleepTime(1500);
		
		eManager.pressButton(7, 5);
		eManager.pressButton(3, 1);
		
		sleepTime(1500);
		
		eManager.pressButton(6, 10);
		eManager.pressButton(2, 11);
	}
	
	
	private static void sleepTime(int time)
	{
		try 
		{
			Thread.sleep(time);
		} 
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
