import java.util.List;
import java.util.Set;

public abstract class Car 
{
	protected String id;
	protected CarType type;
	protected double rate;
	protected Set<Integer> reserveDates;
	
	public boolean reserveCar(List<Integer> dates)
	{
		for (Integer date : dates)
		{
			if (date < 0 || reserveDates.contains(date))
			{
				return false;
			}
		}
		
		updateCarStatus(dates, true);
		return true;
	}
	
	public boolean unReserveCar(List<Integer> dates)
	{
		for (Integer date : dates)
		{
			if (date < 0)
			{
				return false;
			}
		}
		
		updateCarStatus(dates, false);
		return true;
	}
	
	public String getId() 
	{
		return id;
	}
	
	public CarType getType()
	{
		return type;
	}
	
	public double getRate() 
	{
		return rate;
	}
	
	private void updateCarStatus(List<Integer> dates, boolean isReserveRequest)
	{
		for (Integer date : dates)
		{
			if (isReserveRequest)
			{
				reserveDates.add(date);
			}
			else
			{
				reserveDates.remove(date);
			}
		}
	}
}
