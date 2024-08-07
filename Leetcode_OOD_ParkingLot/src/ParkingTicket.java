import java.sql.Timestamp;

public class ParkingTicket {
	private String ticketId;
	private ParkingSlot slot;
	private String vehiclePlateNum;
	private Timestamp enterTime;
	
	public ParkingTicket(ParkingSlot slot, String vehiclePlateNum)
	{
		ticketId = Utils.generateGuid();
		this.slot = slot;
		this.vehiclePlateNum = vehiclePlateNum;
		enterTime = new Timestamp(System.currentTimeMillis());
	}
	
	public String printTicket()
	{
		return String.format("TicketId: {%s}, Vehicle Plate {%s}, Slot {level%s-slot%s}", ticketId, vehiclePlateNum, slot.getLevelId(), slot.getSlotId());
	}
	
	public String getTicketId() {
		return ticketId;
	}

	public ParkingSlot getSlot() {
		return slot;
	}

	public String getVehiclePlateNum() {
		return vehiclePlateNum;
	}

	public String getEnterTime() {
		return enterTime.toString();
	}
}
