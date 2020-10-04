public class Vehicle 
{
	private String plateNum;
	private VehicleType type;
	
	public Vehicle(String plateNum, VehicleType type)
	{
		this.plateNum = plateNum;
		this.type = type;
	}

	public String getPlateNum() 
	{
		return plateNum;
	}

	public int getSize() 
	{
		return type.getSize();
	}
}
