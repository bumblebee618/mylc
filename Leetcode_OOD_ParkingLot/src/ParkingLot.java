import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Exception.ResourceNotFoundException;

public class ParkingLot 
{
	private Map<String, ParkingLevel> levelIdToLevel;
	
	private int levelNum = 2;
	private List<Integer> slotSizes;
	private List<Integer> capacities;
	
	public ParkingLot()
	{
		slotSizes = new LinkedList<>();
		slotSizes.add(1);
		slotSizes.add(2);
		slotSizes.add(3);
		
		capacities = new LinkedList<>();
		capacities.add(1);
		capacities.add(1);
		capacities.add(1);
		
		levelIdToLevel = new HashMap<>();
		
		for (int i = 0; i < levelNum; i++)
		{
			String levelId = Integer.toString(i);
			levelIdToLevel.put(levelId, new ParkingLevel(levelId, slotSizes, capacities));
		}
	}
	
	public int findAvailableSlotNumForGivenVehicleSize(int vehicleSize)
	{
		int count = 0;
		
		for (String levelId : levelIdToLevel.keySet())
		{
			count += levelIdToLevel.get(levelId).findAvailableSpotNumForGivenVehicleSize(vehicleSize);
		}
		
		return count;
	}
	
	public ParkingSlot parkVehicle(int vehicleSize)
	{
		for (String levelId : levelIdToLevel.keySet())
		{
			ParkingSlot slot = levelIdToLevel.get(levelId).parkVehicle(vehicleSize);
			
			if (slot != null)
			{
				return slot;
			}
		}
		
		return null;
	}
	
	public void unparkVehicle(ParkingSlot slot)
	{
		levelIdToLevel.get(slot.getLevelId()).unparkVehicle(slot);
	}
}