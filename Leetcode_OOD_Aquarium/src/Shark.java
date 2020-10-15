import java.util.Timer;
import java.util.TimerTask;

public class Shark extends Fish
{
	public Shark(int fishId)
	{
		this.fishId = fishId;
		type = FishType.SHARK;
		stomachCapacity = 20;
		foodInStomach = 2;
		timer = new Timer();
		
		timer.schedule(new TimerTask() {
			@Override
			public void run() 
			{
				while (foodInStomach > 0)
				{
					foodInStomach--;
				}
			}
		}, 0, 1000);
	}
}
