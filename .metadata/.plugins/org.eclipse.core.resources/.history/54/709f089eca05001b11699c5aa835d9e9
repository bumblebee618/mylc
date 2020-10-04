
public class ParkingSlot 
{
	private String slotId;
	private int spotSize;
	private String levelId;
	private boolean isAvailable;
	
	public ParkingSlot(String slotId, int spotSize, String levelId)
	{
		this.slotId = slotId;
		this.spotSize = spotSize;
		this.levelId = levelId;
		isAvailable = true;
	}
	
	public boolean canFitInSpot(int vehicleSize)
	{
		return vehicleSize <= spotSize;
	}
	
	public boolean park(int vehicleSize)
	{
		if (!canFitInSpot(vehicleSize))
		{
			return false;
		}
		
		isAvailable = false;
		return true;
	}
	
	public boolean removeVehicle()
	{
		if (isAvailable == true)
		{
			return false;
		} 
		else
		{
			isAvailable = true;
			return true;
		}
	}
	
	public String getSlotId() {
		return slotId;
	}

	public int getSpotSize() {
		return spotSize;
	}

	public String getLevelId() {
		return levelId;
	}

	public boolean isAvailable() {
		return isAvailable;
	}
}
