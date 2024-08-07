import java.util.List;

public class UserOrder 
{
	private String orderId;
	private UserProfile user;
	private String carId;
	private List<Integer> reserveDates;
	
	public UserOrder(UserProfile user, String carId, List<Integer> reserveDates, String orderId)
	{
		this.user = user;
		this.carId = carId;
		this.reserveDates = reserveDates;
		this.orderId = orderId;
	}

	public String getOrderId() 
	{
		return orderId;
	}

	public UserProfile getUser() 
	{
		return user;
	}

	public String getCarId() 
	{
		return carId;
	}

	public List<Integer> getReserveDates() 
	{
		return reserveDates;
	}
}
