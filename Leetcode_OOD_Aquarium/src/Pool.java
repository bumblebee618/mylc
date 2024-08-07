import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Pool 
{
	private String poolId;
	private double temperature;
	private Map<Integer, Fish> fishPool;
	private List<Fish> fishList;
	private Random rand;
	
	public Pool(String poolId)
	{
		this.poolId = poolId;
		rand = new Random();
		fishPool = new HashMap<>();
		fishList = new LinkedList<>();
		temperature = 10;
	}
	
	public void feedFish(int foodCount)
	{
		if (foodCount < 0)
		{
			return;
		}
		
		while (foodCount > 0)
		{
			int feedCount = rand.nextInt(foodCount+1);
			
			if (feedCount == 0)
			{
				continue;
			}
			
			Fish fish = pickupFish();
			int actualEatNum = fish.feed(feedCount);
			foodCount -= actualEatNum;
			System.out.println(String.format("%s-%s: eat %d", fish.getType(), fish.getFishId(), actualEatNum));
		}
	}
	
	public boolean isFishHealthy(int fishId)
	{
		if (!fishPool.containsKey(fishId))
		{
			return false;
		}
		
		return fishPool.get(fishId).isHealthy();
	}
	
	public String checkFishesStatus()
	{
		StringBuilder builder = new StringBuilder();
		
		for (Fish fish : fishList)
		{
			builder
			.append(fish.getType())
			.append("-")
			.append(fish.getFishId())
			.append(":")
			.append(fish.isHealthy() ? "Healthy" : "Unhealthy")
			.append("\n");
		}
		
		return builder.toString();
	}
	
	public void addFishToPool(Fish fish)
	{
		if (!fishPool.containsKey(fish.getFishId()))
		{
			fishPool.put(fish.getFishId(), fish);
			fishList.add(fish);
		}
	}
	
	public boolean removeFishFromPool(int fishId)
	{
		if (fishPool.containsKey(fishId))
		{
			fishPool.remove(fishId);
			fishList.remove(fishId);
			return true;
		}
		
		return false;
	}
	
	public String getPoolId() 
	{
		return poolId;
	}
	
	public double getTemperature() 
	{
		return temperature;
	}
	
	public void setTemperature(double temp) 
	{
		temperature = temp;
	}

	private Fish pickupFish()
	{
		int index = rand.nextInt(fishList.size());
		return fishList.get(index);
	}
}
