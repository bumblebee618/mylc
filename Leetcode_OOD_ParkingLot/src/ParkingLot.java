import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Exception.ResourceNotFoundException;

public class ParkingLot 
{
	private Map<String, ParkingLevel> levelIdToLevel;
	private Map<Integer, Integer> slotCapacityMap;
	
	private int levelNum = 2;
	
	public ParkingLot()
	{
		slotCapacityMap = new HashMap<>();
		slotCapacityMap.put(1, 1);
		slotCapacityMap.put(2, 1);
		slotCapacityMap.put(3, 1);
		
		levelIdToLevel = new HashMap<>();
		
		for (int i = 0; i < levelNum; i++)
		{
			String levelId = Integer.toString(i);
			levelIdToLevel.put(levelId, new ParkingLevel(levelId, slotCapacityMap));
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
