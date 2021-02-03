import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Exception.ResourceNotFoundException;

public class ParkingManagerImpl implements ParkingManager
{
	private ParkingLot parkingLot;
	private Map<String, ParkingTicket> idToTickets;
	private Map<String, Vehicle> plateNumToVehicles;
	
	public static ParkingManagerImpl instance;
	
	private ParkingManagerImpl()
	{		
		parkingLot = new ParkingLot();
		idToTickets = new HashMap<>();
		plateNumToVehicles = new HashMap<>();
	}
	
	public static ParkingManagerImpl getInstance()
	{
		if (instance == null)
		{
			synchronized (ParkingManagerImpl.class)
			{
				if (instance == null)
				{
					instance = new ParkingManagerImpl();
				}
			}
		}
		
		return instance;
	}
	
	public int getAvailableSlotNum(int vehicleSize)
	{
		return parkingLot.findAvailableSlotNumForGivenVehicleSize(vehicleSize);
	}
	
	public String generateParkingTicketId(Vehicle vehicle)
	{
		if (getAvailableSlotNum(vehicle.getSize()) == 0)
		{
			return null;
		}
		
		ParkingSlot slot= parkingLot.parkVehicle(vehicle.getSize());
		
		if (slot == null)
		{
			return null;
		}
		
		ParkingTicket ticket = new ParkingTicket(slot, vehicle.getPlateNum());
		idToTickets.put(ticket.getTicketId(), ticket);
		plateNumToVehicles.put(vehicle.getPlateNum(), vehicle);
		System.out.println(ticket.printTicket());
		return ticket.getTicketId();
	}
	
	public void withdrawParkingTicket(String ticketId)
	{
		if (!idToTickets.containsKey(ticketId))
		{
			throw new ResourceNotFoundException(String.format("Cannot find ticket %s", ticketId));
		}
		
		ParkingTicket ticket = idToTickets.get(ticketId);
		parkingLot.unparkVehicle(ticket.getSlot());
		plateNumToVehicles.remove(ticket.getVehiclePlateNum());
		idToTickets.remove(ticket.getTicketId());		
	}
}
