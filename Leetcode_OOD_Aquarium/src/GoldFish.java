import java.util.Timer;
import java.util.TimerTask;

public class GoldFish extends Fish
{
	public GoldFish(int fishId)
	{
		this.fishId = fishId;
		type = FishType.GOLDFISH;
		stomachCapacity = 10;
		foodInStomach = 2;
		timer = new Timer();
		
		timer.schedule(new TimerTask() {
			@Override
			public synchronized void run() 
			{
				while (foodInStomach > 0)
				{
					foodInStomach--;
				}
			}
		}, 0, 1000);
	}
}