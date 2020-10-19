import java.util.HashSet;

public class Car_Van extends Car
{
	public Car_Van(String id)
	{
		this.id = id;
		rentalRate = 50;
		type = CarType.SUV;
		reserveDates = new HashSet<>();
	}
}
