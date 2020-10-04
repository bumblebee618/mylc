public class Elevator
{
	private int currentFloor;
	
	public Elevator() 
	{
		currentFloor = 1;
	}

	public int getCurrentFloor() 
	{
		return currentFloor;
	}
	
	public void moveToTargetFloor(int floor)
	{
		currentFloor = floor;
	}
}
