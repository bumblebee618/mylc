public class Request 
{
	private int fromFloor;
	private int toFloor;
	private boolean isUp;
	
	public Request(int fromFloor, int toFloor)
	{
		this.fromFloor = fromFloor;
		this.toFloor = toFloor;
		isUp = fromFloor - toFloor < 0 ? true : false;
	}

	public int getFromFloor() 
	{
		return fromFloor;
	}

	public int getToFloor() 
	{
		return toFloor;
	}
	
	public boolean isUp() {
		return isUp;
	}
}
