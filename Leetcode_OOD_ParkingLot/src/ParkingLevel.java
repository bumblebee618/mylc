import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import Exception.ResourceNotFoundException;

public class ParkingLevel 
{
	private String levelId;
	private int totalAvailableSlotNum;
	
	private Map<String, ParkingSlot> slotIdToSlots;
	private Map<Integer, Queue<String>> availableSlotMap;
	private Set<String> unavailableSlots;
	
	public ParkingLevel(String levelId, Map<Integer, Integer> slotCapacityMap)
	{
		this.levelId = levelId;
		availableSlotMap = new HashMap<>();
		unavailableSlots = new HashSet<>();
		slotIdToSlots = new HashMap<>();
		int index = 0;
		
		for (int slotSize : slotCapacityMap.keySet())
		{
			String slotId = Integer.toString(index);
			int count = slotCapacityMap.get(slotSize);
			
			for (int j = 0; j < count; j++)
			{
				ParkingSlot slot = new ParkingSlot(levelId, slotId, slotSize);
				availableSlotMap.computeIfAbsent(slotSize, x -> new LinkedList<String>()).add(slotId);
				slotIdToSlots.put(slotId, slot);
			}
			
			totalAvailableSlotNum += count;
		}
	}
	
	public int findAvailableSpotNumForGivenVehicleSize(int vehicleSize)
	{
		int count = 0;
		
		for (int slotSize : availableSlotMap.keySet())
		{
			if (slotSize < vehicleSize)
			{
				continue;
			}
		
			count += availableSlotMap.get(slotSize).size();
		}
		
		return count;
	}

	public ParkingSlot parkVehicle(int vehicleSize)
	{
		for (int slotSize : availableSlotMap.keySet())
		{
			if (slotSize < vehicleSize)
			{
				continue;
			}
		
			if (availableSlotMap.get(slotSize).size() > 0)
			{
				synchronized (this) 
				{
					ParkingSlot slot = slotIdToSlots.get(availableSlotMap.get(slotSize).poll());
					
					if (slot.tryParkVehicle(vehicleSize))
					{
						unavailableSlots.add(slot.getSlotId());
						totalAvailableSlotNum--;
						return slot;
					}
				}
			}
		}
		
		return null;
	}
	
	public void unparkVehicle(ParkingSlot slot) throws ResourceNotFoundException 
	{
		if (!unavailableSlots.contains(slot.getSlotId()))
		{
			throw new ResourceNotFoundException(String.format("Cannot find the slot id %s in the target level", slot.getSlotId()));
		}
		
		synchronized (this) 
		{
			slot.removeVehicle();
			availableSlotMap.get(slot.getSlotSize()).offer(slot.getSlotId());
			unavailableSlots.remove(slot.getSlotId());
			totalAvailableSlotNum++;
		}
	}
	
	public String getLevelId() 
	{
		return levelId;
	}

	public int getAvailableSpotNum() 
	{
		return totalAvailableSlotNum;
	}
}
