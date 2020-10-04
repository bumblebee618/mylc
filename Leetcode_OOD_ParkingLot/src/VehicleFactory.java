
public class VehicleFactory {
	public static Vehicle createVehicle(String type, String plateNum)
	{
		switch (type)
		{
			case "Motorcycle": return new Vehicle(plateNum, VehicleType.MOTORCYCLE); 
			case "Car": return new Vehicle(plateNum, VehicleType.CAR); 
			case "Bus": return new Vehicle(plateNum, VehicleType.BUS); 
			default: return null;
		}
	}
}
