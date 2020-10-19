import java.util.HashMap;
import java.util.Map;

public class PoolControllerImpl implements PoolController
{
	private Pool pool;
	private Map<FishType, Integer> fishCapacity;
	
	private static PoolControllerImpl instance; 
	
	private PoolControllerImpl()
	{
		pool = new Pool("pool_1");
		fishCapacity = new HashMap<>();
		fishCapacity.put(FishType.GOLDENFISH, 5);
		fishCapacity.put(FishType.SHARK, 2);
		init();
	}
	
	public static PoolControllerImpl getInstance()
	{
		if (instance == null)
		{
			synchronized (PoolControllerImpl.class) 
			{
				if (instance == null)
				{
					instance = new PoolControllerImpl();
				}
			}
		}
		
		return instance;
	}
	
	public void feedFish(int foodCount)
	{
		pool.feedFish(foodCount);
	}
	
	public void checkFishesStatus()
	{
		String status = pool.checkFishesStatus();
		System.out.println(status);
	}
	
	public void checkSingleFishStatus(int fishId)
	{
		System.out.println(pool.isFishHealthy(fishId));
	}
	
	public void ModifyPoolTemperature(double template)
	{
		pool.setTemperature(template);
	}
	
	private void init()
	{
		for (FishType type : fishCapacity.keySet())
		{
			for (int i = 0; i < fishCapacity.get(type); i++)
			{
				Fish fish = FishFactory.generateFish(type);
				pool.addFishToPool(fish);
			}
		}
	}
}
