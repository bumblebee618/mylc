import java.util.List;

public interface RentalManager 
{
	// return orderId
	public RentalResponse<String> reserveCarOnline(String userId, String driverLicense, String creditCard, CarType type, List<Integer> reserveDates);
	
	// return orderId
	public RentalResponse<String> rentCarOndemand(String userId, String driverLicense, String creditCard, CarType type, List<Integer> reserveDates);
	
	// return a car from an existing order
	public RentalResponse<Car> pickupCar(String orderId);
	
	// return a total rental charge
	public RentalResponse<Double> dropOffCar(String orderId);
	
	// return a user profile
	public RentalResponse<UserProfile> findOrCreateUser(String userId, String driverLicense, String creditCard);
}
