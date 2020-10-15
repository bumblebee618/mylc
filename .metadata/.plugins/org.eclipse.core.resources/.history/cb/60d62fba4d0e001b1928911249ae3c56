import java.util.List;
import java.util.UUID;

public class UserOrder 
{
	private String orderId;
	private String userId;
	private UserProfile userProfile;
	private String carId;
	private List<Integer> reserveDates;
	
	public UserOrder(String userId, String carId, List<Integer> reserveDates)
	{
		this.userId = userId;
		this.carId = carId;
		this.reserveDates = reserveDates;
		this.orderId = "Order_"+ UUID.randomUUID().toString();
	}

	public String getOrderId() 
	{
		return orderId;
	}

	public String getUserId() 
	{
		return userId;
	}

	public String getCarId() 
	{
		return carId;
	}

	public List<Integer> getReserveDates() 
	{
		return reserveDates;
	}

	public UserProfile getUserProfile() 
	{
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) 
	{
		this.userProfile = userProfile;
	}
}
