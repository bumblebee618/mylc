public class Elevator
{
	private int currentFloor;
	private volatile boolean isUp;
	
	public Elevator() 
	{
		currentFloor = 1;
		isUp = true;
	}

	public int getCurrentFloor() 
	{
		return currentFloor;
	}
	
	public void moveToTargetFloor(int floor)
	{
		currentFloor = floor;
	}

	public boolean isUp() 
	{
		return isUp;
	}

	public void setUp(boolean isUp) 
	{
		this.isUp = isUp;
	}
}
