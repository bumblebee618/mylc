public interface ParkingManager 
{
	public int getAvailableSlotNum(int vehicleSize);
	public String generateParkingTicketId(Vehicle vehicle);
	public void withdrawParkingTicket(String ticketId);
}
