import java.util.UUID;

public class CarFactory 
{
	public static Car generateCar(CarType type)
	{
		String id = "Car_"+ UUID.randomUUID().toString();
		
		switch (type)
		{
			case SUV: return new Car_SUV(id);
			case VAN: return new Car_Van(id);
			case SEDAN: 
			default: return new Car_Sedan(id);
		}
	}
}
