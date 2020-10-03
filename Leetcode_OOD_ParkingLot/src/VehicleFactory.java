
public class VehicleFactory {
	public static Vehicle createVehicle(String type, String plateNum)
	{
		switch (type)
		{
			case "Motorcycle": return new Vehicle_Motorcycle(plateNum); 
			case "Car": return new Vehicle_Car(plateNum); 
			case "Bus": return new Vehicle_Bus(plateNum); 
			default: return null;
		}
	}
}
