public class FishFactory 
{
	private static int globalId = 1;
	
	public static Fish generateFish(FishType type)
	{
		switch (type) 
		{
			case SHARK: return new Shark(globalId++);
			case GOLDENFISH: 
			default: return new GoldenFish(globalId++);
		}
	}
}
