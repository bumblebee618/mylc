import java.util.LinkedList;
import java.util.List;

public class Z_TestClient 
{
	public static void main(String[] args)
	{
		RentalManagerImpl rentalManager = RentalManagerImpl.getInstance();
		
		String user1 = "user1";
		CarType type1 = CarType.SUV;
		String driverLicense1 = "driverLicense1";
		String creditCard1 = "creditCard1";
		List<Integer> reserveDates1 = new LinkedList<Integer>();
		reserveDates1.add(1);
		reserveDates1.add(2);
		reserveDates1.add(3);
		
		String user2 = "user2";
		CarType type2 = CarType.SUV;
		String driverLicense2 = "driverLicense2";
		String creditCard2 = "creditCard2";
		List<Integer> reserveDates2 = new LinkedList<Integer>();
		reserveDates2.add(2);
		reserveDates2.add(3);
		
		String user3 = "user3";
		CarType type3 = CarType.SEDAN;
		String driverLicense3 = "driverLicense3";
		String creditCard3 = "creditCard3";
		List<Integer> reserveDates3 = new LinkedList<Integer>();
		reserveDates3.add(1);
		reserveDates3.add(2);
		
		RentalResponse response1_1 = rentalManager.reserveCarOnline(user1, driverLicense1, creditCard1, type1, reserveDates1);
		System.out.println("response1_1: orderId = " + (String) response1_1.getResult() + ", status = " + response1_1.getStatus());
		RentalResponse response1_2 = rentalManager.pickupCar((String) response1_1.getResult());
		System.out.println("response1_2: CarId = " + ((Car) response1_2.getResult()).getId() + ", status = " + response1_1.getStatus());
		
		RentalResponse response2_1 = rentalManager.reserveCarOnline(user2, driverLicense2, creditCard2, type2, reserveDates2);
		System.out.println("response2_1: status = " + response2_1.getStatus() + ", error message: " + response2_1.getErrMsg());
		
		RentalResponse response3_1 = rentalManager.reserveCarOnline(user3, driverLicense3, creditCard3, type3, reserveDates3);
		System.out.println("response3_1: orderId = " + (String) response3_1.getResult() + ", status = " + response3_1.getStatus());
		RentalResponse response3_2 = rentalManager.pickupCar((String) response3_1.getResult());
		System.out.println("response3_2: CarId = " + ((Car) response3_2.getResult()).getId() + ", status = " + response3_1.getStatus());
		RentalResponse response3_3 = rentalManager.dropOffCar((String) response3_1.getResult());
		System.out.println("response3_3: total charge = $" + (Double) response3_3.getResult() + ", status = " + response3_3.getStatus());
		
		RentalResponse response4_1 = rentalManager.dropOffCar((String) response1_1.getResult());
		System.out.println("response1_1: total charge = $" + (Double) response4_1.getResult() + ", status = " + response4_1.getStatus());
		
		RentalResponse response5_1 = rentalManager.reserveCarOnline(user2, driverLicense2, creditCard2, type2, reserveDates2);
		System.out.println("response5_1: orderId = " + (String) response5_1.getResult() + ", status = " + response5_1.getStatus());
		RentalResponse response5_2 = rentalManager.pickupCar((String) response5_1.getResult());
		System.out.println("response5_2: CarId = " + ((Car) response5_2.getResult()).getId() + ", status = " + response5_1.getStatus());
	}
}
