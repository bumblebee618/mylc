public class ElevatorFactory 
{
	public static ElevatorManagerImpl getElevatorManager()
	{
		return ElevatorManagerImpl.getInstance();
	}
	
	public static Elevator getElevator()
	{
		return new Elevator();
	}
}
