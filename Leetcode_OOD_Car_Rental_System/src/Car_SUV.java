import java.util.HashSet;

public class Car_SUV extends Car
{
	public Car_SUV (String id)
	{
		this.id = id;
		rentalRate = 100;
		type = CarType.SUV;
		reserveDates = new HashSet<>();
	}
}
