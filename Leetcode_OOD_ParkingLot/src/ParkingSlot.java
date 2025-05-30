import Exception.ResourceNotFoundException;

public class ParkingSlot 
{
	private String levelId;
	private String slotId;
	private int slotSize;
	private boolean isAvailable;
	
	public ParkingSlot(String levelId, String slotId, int slotSize)
	{
		this.levelId = levelId;
		this.slotId = slotId;
		this.slotSize = slotSize;
		isAvailable = true;
	}
	
	public boolean canFitInSlot(int vehicleSize)
	{
		return vehicleSize <= slotSize;
	}
	
	public boolean tryParkVehicle(int vehicleSize)
	{
		if (!canFitInSlot(vehicleSize))
		{
			return false;
		}
		
		isAvailable = false;
		return true;
	}
	
	public void removeVehicle() throws ResourceNotFoundException
	{
		if (isAvailable == true)
		{
			throw new ResourceNotFoundException("Cannot find any vehicle parking on the slot");
		} 
		else
		{
			isAvailable = true;
		}
	}
	
	public String getLevelId() 
	{
		return levelId;
	}
	
	public String getSlotId() 
	{
		return slotId;
	}

	public int getSlotSize() 
	{
		return slotSize;
	}

	public boolean isAvailable() 
	{
		return isAvailable;
	}
}
