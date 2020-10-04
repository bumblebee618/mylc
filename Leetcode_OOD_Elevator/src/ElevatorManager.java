import java.util.PriorityQueue;
import java.util.Queue;

public class ElevatorManager implements Runnable
{
	private Queue<Integer> upRequestProcessingQueue;
	private Queue<Integer> upRequestWaitingQueue;
	
	private Queue<Integer> downRequestProcessingQueue;
	private Queue<Integer> downRequestWaitingQueue;
	
	private static ElevatorManager instance;
	private Elevator elevator;
	private volatile boolean isUp;
	
	private final int maxFloor = 10;
	private final int minFloor = 1;
	
	private ElevatorManager()
	{
		upRequestProcessingQueue = new PriorityQueue<Integer>(1, (a, b) -> a - b);
		upRequestWaitingQueue = new PriorityQueue<Integer>(1, (a, b) -> a - b);
		
		downRequestProcessingQueue = new PriorityQueue<Integer>(1, (a, b) -> b - a);
		downRequestWaitingQueue = new PriorityQueue<Integer>(1, (a, b) -> b - a);
		
		elevator = ElevatorFactory.getElevator();
		isUp = true;
	}
	
	public static ElevatorManager getInstance()
	{
		if (instance == null)
		{
			synchronized (Elevator.class) 
			{
				if (instance == null)
				{
					instance = new ElevatorManager();
				}
			}
		}
		
		return instance;
	}
	
	public void pressButton(int fromFloor, int toFloor)
	{
		if (!validateInput(fromFloor, toFloor))
		{
			System.out.println("Invalid input: " + "from " + fromFloor + " to " + toFloor);
			return;
		}
		
		System.out.println("Receive request: " + "from " + fromFloor + " to " + toFloor);
		addRequest(new Request(fromFloor, toFloor));
	}
	
	@Override
	public void run() 
	{
		while (true) 
		{
			processRequest(upRequestProcessingQueue, true);
			processRequest(downRequestProcessingQueue, false);
			refreshProcessingQueue();
		}
	}
	
	private void refreshProcessingQueue()
	{
		while (upRequestWaitingQueue.isEmpty() && downRequestWaitingQueue.isEmpty())
		{
			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		if (!upRequestWaitingQueue.isEmpty())
		{
			synchronized (upRequestWaitingQueue)
			{
				upRequestProcessingQueue = upRequestWaitingQueue;
				upRequestWaitingQueue = new PriorityQueue<Integer>(1, (a, b) -> b - a);
			}
		}
		
		if (!downRequestWaitingQueue.isEmpty())
		{
			synchronized (downRequestWaitingQueue)
			{
				downRequestProcessingQueue = downRequestWaitingQueue;
				downRequestWaitingQueue = new PriorityQueue<Integer>(1, (a, b) -> b - a);
			}
		}
	}
	
	private void processRequest(Queue<Integer> requestQueue, boolean isUp)
	{
		this.isUp = isUp;
		
		while (!requestQueue.isEmpty())
		{
			Integer targetFloor = null;
			
			synchronized (requestQueue)
			{
				if (!requestQueue.isEmpty())
				{
					targetFloor = requestQueue.poll();
				}
			}
			
			if (targetFloor != null)
			{
				elevator.moveToTargetFloor(targetFloor);
				processingTime(1000);
			}
		}
	}
	
	private void addRequest(Request request)
	{
		if (request.isUp())
		{
			synchronized (upRequestWaitingQueue)
			{
				upRequestWaitingQueue.offer(request.getFromFloor());
				upRequestWaitingQueue.offer(request.getToFloor());
			}
		}
		else
		{
			synchronized (downRequestWaitingQueue)
			{
				downRequestWaitingQueue.add(request.getFromFloor());
				downRequestWaitingQueue.add(request.getToFloor());
			}
		}
	}	
	
	
	/*** on-demand insert new requests
	private void addRequest(Request request)
		if (request.isUp())
		{
			if (request.getFromFloor() > elevator.getCurrentFloor())
			{
				synchronized(upRequestProcessingQueue)
				{
					upRequestProcessingQueue.add(request.getFromFloor());
					upRequestProcessingQueue.add(request.getToFloor());
				}
			}
			else
			{
				synchronized(upRequestWaitingQueue)
				{
					upRequestWaitingQueue.add(request.getFromFloor());
					upRequestWaitingQueue.add(request.getToFloor());
				}
			}
		}
		else
		{
			if (request.getFromFloor() < elevator.getCurrentFloor())
			{
				synchronized(downRequestProcessingQueue)
				{
					downRequestProcessingQueue.add(request.getFromFloor());
					downRequestProcessingQueue.add(request.getToFloor());
				}
			}
			else
			{
				synchronized(downRequestWaitingQueue)
				{
					downRequestWaitingQueue.add(request.getFromFloor());
					downRequestWaitingQueue.add(request.getToFloor());
				}
			}
		}
	}
	***/
	
	private boolean validateInput(int fromFloor, int toFloor)
	{
		if (fromFloor < minFloor || fromFloor > maxFloor)
		{
			return false;
		}
		else if (toFloor < minFloor || toFloor > maxFloor)
		{
			return false;
		}
		else if (fromFloor == toFloor)
		{
			return false;
		}
		else 
		{
			return true;
		}
	}
	
	
	/*****************************************************/
	private void processingTime(int time)
	{
		try 
		{
			Thread.sleep(time);
			System.out.println("Move " + (isUp ? "up" : "down") + " to " + elevator.getCurrentFloor() + " floor");
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
}
