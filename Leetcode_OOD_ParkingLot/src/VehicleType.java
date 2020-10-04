public enum VehicleType 
{
	MOTORCYCLE(1), CAR(2), BUS(3);
	
	private int size;
	
	private VehicleType(int size)
	{
		this.size = size;
	}
	
	public int getSize()
	{
		return size;
	}
}
