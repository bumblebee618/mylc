import java.util.LinkedList;
import java.util.List;

public class Robot_Rodeo {
	public static List<String> doesCircleExist(List<String> commands)
	{
		List<String> result = new LinkedList<String>();
		
		if (commands == null || commands.size() == 0)
		{
			return result;
		}
		
		int x = 0, y = 0, direction = 0;
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		
		for (String command : commands)
		{
			int originalX = x;
			int originalY = y;
			boolean isMoved = false;
			
			for (char c : command.toCharArray())
			{
				switch (c) 
				{
					case 'R': direction = (direction+1) % 4; break;
					case 'L': direction = (direction-1) % 4; break;
					case 'G': x += dx[direction]; 
							  y += dy[direction]; 
							  isMoved = true; 
							  break;
					default: break;
				}
			}
			
			if (isMoved && x == originalX & y == originalY)
			{
				result.add("YES");
			}
			else
			{
				result.add("NO");
			}
		}
		
		return result;
	}
	
	
	
	
	
	
	
	public static void main(String[] args)
	{
		List<String> commands = new LinkedList<>();
		commands.add("RGRGRGRG"); 
		commands.add("RGRGRGRG"); 
		commands.add("RLRRRRL");
		List<String> results = doesCircleExist(commands);
		
		for (String result : results)
		{
			System.out.println(result);
		}
	}
}
