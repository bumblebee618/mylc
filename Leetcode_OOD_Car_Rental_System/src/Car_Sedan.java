import java.util.HashSet;

public class Car_Sedan extends Car
{
	public Car_Sedan(String id)
	{
		this.id = id;
		rate = 100;
		type = CarType.SEDAN;
		reserveDates = new HashSet<>();
	}
}