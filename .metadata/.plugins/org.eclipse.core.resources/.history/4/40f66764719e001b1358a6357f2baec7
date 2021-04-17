import java.util.*;




public class Q000_A_Contest 
{
	public int arraySign(int[] nums) 
    {
        if (nums == null || nums.length == 0)
        {
            return 0;
        }
        
        int result = 1;
        
        for (int num : nums)
        {
            if (num < 0)
            {
                result *= -1;
            }
            else if (num == 0)
            {
                return 0;
            }
        }
        
        return result;
    }
	
	
	public int findTheWinner(int n, int k) 
    {
        if (n <= 0 || k <= 0 || k > n)
        {
            return -1;
        }
        
        int index = 0, count = 0;
        boolean[] removed = new boolean[n];
        
        while (count < n-1)
        {
        	int step = 1;
            
            while (step < k)
            {
                while (removed[index])
                {
                    index = (index+1) % n;
                }
                
                step++;
                index = (index+1) % n;
            }
        	
            while (removed[index])
            {
                index = (index+1) % n;
            }
            
            removed[index] = true;
            count++;
        }
        
        while (removed[index])
        {
            index = (index+1) % n;
        }
        
        return index+1;
    }

	
	
	public int minSideJumps(int[] obstacles) 
    {
        if (obstacles == null || obstacles.length == 0)
        {
            return 0;
        }
        
        int size = obstacles.length;
        int[][] dp = new int[size][3];
        dp[0][0] = dp[0][2] = 1;
        dp[0][1] = 0;
        
        for (int i = 1; i < size; i++)
        {
        	int minJump = Integer.MAX_VALUE;
        	
        	for (int lane = 0; lane < 3; lane++)
        	{
        		dp[i][lane] = obstacles[i]-1 == lane ? Integer.MAX_VALUE : dp[i-1][lane];
        		minJump = Math.min(minJump, dp[i][lane]);
        	}
        	
        	for (int lane = 0; lane < 3; lane++)
        	{
        		if (obstacles[i]-1 == lane)
        		{
        			continue;
        		}
        		
        		dp[i][lane] = dp[i][lane] == minJump ? dp[i][lane] : minJump + 1;
        	}
        }
        
        return Math.min(dp[size-1][0], Math.min(dp[size-1][1], dp[size-1][2]));
    }


    
    public static void main(String[] args)
    {
    	Q000_A_Contest test = new Q000_A_Contest();
    	
    	/****************************************************/
    	
    	int[] obstacles1 = {0,1,2,3,0};
    	int[] obstacles2 = {0,1,1,3,3,0};
    	int[] obstacles3 = {0,2,1,0,3,0};
    	
    	System.out.println(test.minSideJumps(obstacles1));
    	System.out.println(test.minSideJumps(obstacles2));
    	System.out.println(test.minSideJumps(obstacles3));
    	
    	
    	
    }
}
