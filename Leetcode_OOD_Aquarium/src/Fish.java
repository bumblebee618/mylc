import java.util.Timer;

public abstract class Fish
{
	protected int fishId;
	protected FishType type;
	protected volatile int foodInStomach;
	protected int stomachCapacity;
	protected Timer timer;
	
	public int feed(int food)
	{
		int eat = Math.min(food, stomachCapacity - foodInStomach);
		
		synchronized (this) 
		{
			foodInStomach += eat;
		}
		
		return eat;
	}
	
	public boolean isHealthy()
	{
		return foodInStomach > 0;
	}
	
	public int getFishId() 
	{
		return fishId;
	}
	
	public FishType getType() 
	{
		return type;
	}
}

enum FishType
{
	GOLDENFISH(), SHARK();
}
