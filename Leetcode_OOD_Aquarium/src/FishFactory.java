public class FishFactory 
{
	private static int id = 1;
	
	public static Fish generateFish(FishType type)
	{
		switch (type) 
		{
			case SHARK: return new Shark(id++);
			case GOLDFISH: 
			default: return new GoldFish(id++);
		}
	}
}