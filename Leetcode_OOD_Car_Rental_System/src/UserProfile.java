public class UserProfile 
{
	private String userId;
	private String driverLicense;
	private String creditCard;
	
	public UserProfile(String userId, String driverLicense, String creditCard)
	{
		this.userId = userId;
		this.driverLicense = driverLicense;
		this.creditCard = creditCard;
	}

	public String getUserId() 
	{
		return userId;
	}

	public String getDriverLicense() 
	{
		return driverLicense;
	}

	public String getCreditCard() 
	{
		return creditCard;
	}
	
	public void setCreditCard(String creditCard) 
	{
		this.creditCard = creditCard;
	}
}
