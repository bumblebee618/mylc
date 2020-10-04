
public class ParkingSystemClient {
	private ParkingManager parkingManager = new ParkingManager();
	
	public String getParkingTicket(Vehicle vehicle)
	{
		return parkingManager.generateParkingTicketId(vehicle);
	}
	
	public boolean withdrawParkingTicket(String ticketId)
	{
		return parkingManager.withdrawParkingTicket(ticketId);
	}
	
	public static void  main(String[] args)
	{
		ParkingSystemClient client = new ParkingSystemClient();
		
		Vehicle motorcycle1 = VehicleFactory.createVehicle("Motorcycle", "Motorcycle_1");
		Vehicle motorcycle2 = VehicleFactory.createVehicle("Motorcycle", "Motorcycle_2");
		
		Vehicle car1 = VehicleFactory.createVehicle("Car", "Car_1");
		Vehicle car2 = VehicleFactory.createVehicle("Car", "Car_2");
		
		Vehicle bus1 = VehicleFactory.createVehicle("Bus", "Bus_1");
		Vehicle bus2 = VehicleFactory.createVehicle("Bus", "Bus_2");
		
		String car1_ticketId = client.getParkingTicket(car1);
		String car2_ticketId = client.getParkingTicket(car2);
		
		String bus1_ticketId = client.getParkingTicket(bus1);
		String bus2_ticketId = client.getParkingTicket(bus2);
		
		String motorcycle1_ticketId = client.getParkingTicket(motorcycle1);
		String motorcycle2_ticketId = client.getParkingTicket(motorcycle2);
		
		System.out.println("car1: " + car1_ticketId);
		System.out.println("car2: " + car2_ticketId);
		System.out.println("bus1: " + bus1_ticketId);
		System.out.println("bus2: " + bus2_ticketId);
		System.out.println("motorcycle1: " + motorcycle1_ticketId);
		System.out.println("motorcycle2: " + motorcycle2_ticketId);
		
		
		System.out.println("car1: " + client.withdrawParkingTicket(car1_ticketId));
		System.out.println("car2: " + client.withdrawParkingTicket(car2_ticketId));
		System.out.println("bus1: " + client.withdrawParkingTicket(bus1_ticketId));
		System.out.println("bus2: " + client.withdrawParkingTicket(bus2_ticketId));
		System.out.println("motorcycle1: " + client.withdrawParkingTicket(motorcycle1_ticketId));
		System.out.println("motorcycle2: " + client.withdrawParkingTicket(motorcycle2_ticketId));
		
		System.out.println("car1: " + client.withdrawParkingTicket(car1_ticketId));
	}
}
