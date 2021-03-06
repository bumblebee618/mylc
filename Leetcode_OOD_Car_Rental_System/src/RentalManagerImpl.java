import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RentalManagerImpl implements RentalManager
{
	private Map<String, UserProfile> userIdToUser;
	private Map<String, Car> carIdToCar;
	private Map<String, UserOrder> orderIdToOrder;
	private Map<CarType, List<String>> carPool;
	private static RentalManagerImpl instance;
	
	private final int capacity = 1;
	
	private RentalManagerImpl()
	{
		init();
	}
	
	public static RentalManagerImpl getInstance()
	{
		if (instance == null)
		{
			synchronized (RentalManagerImpl.class) 
			{
				if (instance == null)
				{
					instance = new RentalManagerImpl();
				}
			}
		}
		
		return instance;
	}
	
	public RentalResponse<String> reserveCarOnline(
			String userId, 
			String driverLicense, 
			String creditCard, 
			CarType type, 
			List<Integer> reserveDates)
	{
		RentalResponse<UserProfile> response = findOrCreateUser(userId, driverLicense, creditCard);
		
		if (response.getStatus() == Status.FAIL)
		{
			return new RentalResponse<String>(null, Status.FAIL, "Cannot register the user");
		}
		
		Car car = reserveCar(type, reserveDates);
		
		if (car == null)
		{
			return new RentalResponse<String>(null, Status.FAIL, "Cannot find any available car for the given type");
		}

		UserOrder order = createOrder((UserProfile) response.getResult(), car.getId(), reserveDates);
		return new RentalResponse<String>(order.getOrderId(), Status.SUCCEED, "");
	}
	
	public RentalResponse<String> rentCarOndemand(
			String userId, 
			String driverLicense, 
			String creditCard, 
			CarType type, 
			List<Integer> reserveDates)
	{
		RentalResponse<UserProfile> response = findOrCreateUser(userId, driverLicense, creditCard);
		
		if (response.getStatus() == Status.FAIL)
		{
			return new RentalResponse<String>(null, Status.FAIL, "Cannot register the user");
		}
		
		Car car = reserveCar(type, reserveDates);
		
		if (car == null)
		{
			return new RentalResponse<String>(null, Status.FAIL, "Cannot find any available car for the given type");
		}
		
		UserOrder order = createOrder((UserProfile) response.getResult(), car.getId(), reserveDates);
		return new RentalResponse<String>(order.getOrderId(), Status.SUCCEED, "");
	}
	
	public RentalResponse<Car> pickupCar(String orderId)
	{
		if (!orderIdToOrder.containsKey(orderId))
		{
			return new RentalResponse<Car>(null, Status.FAIL, "Cannot find the order");
		}
		
		UserOrder order = orderIdToOrder.get(orderId);
		
		if (!carIdToCar.containsKey(order.getCarId()))
		{
			return new RentalResponse<Car>(null, Status.FAIL, "Cannot find the car in the order");
		}
		
		return new RentalResponse<Car>(carIdToCar.get(order.getCarId()), Status.SUCCEED, "");
	}
	
	public RentalResponse<Double> dropOffCar(String orderId)
	{
		if (!orderIdToOrder.containsKey(orderId))
		{
			return new RentalResponse<Double>(0d, Status.FAIL, "Cannot find the order");
		}
		
		UserOrder order = orderIdToOrder.get(orderId);
		Car car = carIdToCar.get(order.getCarId());
		
		if (!car.unReserveCar(order.getReserveDates()))
		{
			return new RentalResponse<Double>(0d, Status.FAIL, "Cannot drop off the car");
		}
		else
		{
			orderIdToOrder.remove(orderId);
			double totalCharge = order.getReserveDates().size() * car.getRentalRate();
			return new RentalResponse<Double>(totalCharge, Status.SUCCEED, "");
		}
	}
	
	public RentalResponse<UserProfile> findOrCreateUser(String userId, String driverLicense, String creditCard)
	{
		userIdToUser.computeIfAbsent(userId, x -> new UserProfile(userId, driverLicense, creditCard));
		UserProfile user = userIdToUser.get(userId);
		
		if (!user.getDriverLicense().equals(driverLicense))
		{
			return new RentalResponse<UserProfile>(null, Status.FAIL, "Incorrect driver license has been input");
		}
		else
		{
			if (!user.getCreditCard().equals(creditCard))
			{
				user.setCreditCard(creditCard);
			}
			
			return new RentalResponse<UserProfile>(user, Status.SUCCEED, "");
		}
	}
	
	private void init()
	{
		userIdToUser = new HashMap<>();
		carIdToCar = new HashMap<>();
		orderIdToOrder = new HashMap<>();
		carPool = new HashMap<>();
		
		for (CarType type : CarType.values())
		{
			carPool.put(type, new LinkedList<String>());
			
			for (int i = 0; i < capacity; i++)
			{
				Car car = CarFactory.generateCar(type);
				carIdToCar.put(car.getId(), car);
				carPool.get(type).add(car.getId());
			}
		}
	}
	
	private Car reserveCar(CarType type, List<Integer> reserveDates)
	{
		if (!carPool.containsKey(type) || carPool.get(type).size() == 0)
		{
			return null;
		}
		
		for (String carId : carPool.get(type))
		{
			if (carIdToCar.get(carId).reserveCar(reserveDates))
			{
				return carIdToCar.get(carId);
			}
		}
		
		return null;
	}
	
	private UserOrder createOrder(UserProfile user, String carId, List<Integer> reserveDates)
	{
		String orderId = "Order_"+ UUID.randomUUID().toString();
		UserOrder order = new UserOrder(user, carId, reserveDates, orderId);
		orderIdToOrder.put(orderId, order);
		return order;
	}
}
