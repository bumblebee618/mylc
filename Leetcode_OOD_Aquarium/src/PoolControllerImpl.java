import java.util.HashMap;
import java.util.Map;

public class PoolControllerImpl implements PoolController
{
	private Pool pool;
	private static PoolControllerImpl instance; 
	private Map<FishType, Integer> fishCapacity;
	
	private PoolControllerImpl()
	{
		pool = new Pool("pool_1");
		fishCapacity = new HashMap<>();
		fishCapacity.put(FishType.GOLDFISH, 5);
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
	
	public void heatPool(int template)
	{
		pool.setTemperature(template, true);
	}
	
	public void coolPool(int template)
	{
		pool.setTemperature(template, false);
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