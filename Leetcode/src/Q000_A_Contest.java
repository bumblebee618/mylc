import java.util.*;





public class Q000_A_Contest 
{	  
	public boolean findRotation(int[][] mat, int[][] target) 
    {
        if (mat == null || mat.length == 0 || mat[0].length == 0)
        {
            return false;
        }
        else if (target == null || target.length == 0 || target[0].length == 0)
        {
            return false;
        }
        else if (mat.length != target.length)
        {
            return false;
        }
        
        int n = mat.length;
        int[] status1 = new int[n];
        int[] status2 = new int[n];
        
        for (int row = 0; row < n; row++)
        {
        	status1[row] = 0;
        	status2[row] = 0;
        	
        	for (int col = 0; col < n; col++)
        	{
        		if (mat[row][col] == 1)
        		{
        			status1[row] |= (1 << col);
        		}
        		
        		if (target[row][col] == 1)
        		{
        			status2[row] |= (1 << col);
        		}
        	}
        }
        
        if (isEqual(status1, status2))
        {
        	return true;
        }
        
        int[] status3 = new int[n];
        
        for (int col = n-1; col >= 0; col--)
        {
        	status3[n-1-col] = 0;
        	
        	for (int row = 0; row < n; row++)
        	{
        		if (target[row][col] == 1)
        		{
        			status3[n-1-col] |= (1 << row);
        		}
        	}
        }
        
        if (isEqual(status1, status3))
        {
        	return true;
        }
        
        int[] status4 = new int[n];
        
        for (int row = n-1; row >= 0; row--)
        {
        	status4[n-1-row] = 0;
        	
        	for (int col = n-1; col >= 0; col--)
        	{
        		if (target[row][col] == 1)
        		{
        			status4[n-1-row] |= (1 << (n-1-col));
        		}
        	}
        }
        
        if (isEqual(status1, status4))
        {
        	return true;
        }
        
        int[] status5 = new int[n];
        
        for (int col = 0; col < n; col++)
        {
        	status5[col] = 0;
        	
        	for (int row = n-1; row >= 0; row--)
        	{
        		if (target[row][col] == 1)
        		{
        			status5[col] |= (1 << (n-1-row));
        		}
        	}
        }
        
        if (isEqual(status1, status5))
        {
        	return true;
        }
        
        return false;
    }		
	
	private boolean isEqual(int[] status1, int[] status2)
	{
		for (int i = 0; i < status1.length; i++)
		{
			if (status1[i] != status2[i])
			{
				return false;
			}
		}
		
		return true;
	}
	
	
	public int reductionOperations(int[] nums) 
	{
		if (nums == null || nums.length <= 1)
		{
			return 0;
		}
		
		int min = Integer.MAX_VALUE;
		Map<Integer, Integer> map = new HashMap<>();
		List<Integer> list = new ArrayList<>();
		
		for (int num : nums)
		{
			min = Math.min(min, num);
			
			if (!map.containsKey(num))
			{
				map.put(num, 1);
				list.add(num);
			}
			else
			{
				map.put(num, map.get(num)+1);
			}
		}
		
		Collections.sort(list, (a, b) -> b-a);
		int result = 0, curSum = 0;
		
		for (int i = 0; i < list.size()-1; i++)
		{
			curSum += map.get(list.get(i));
			result += curSum;
		}
		
		return result;
    }
	
	public int minFlips(String s) 
	{
		if (s == null || s.length() == 0)
		{
			return 0;
		}
		
		int n = s.length();
        int[][] left = new int[n][2];
        int result = 0;
        
        left[0][0] = (s.charAt(0) == '0') ? 0 : 1;
        left[0][1] = (s.charAt(0) == '1') ? 0 : 1;
        
        for (int i=1; i<s.length(); i++) 
        {
            left[i][0] = ((s.charAt(i) == '0') ? 0 : 1) + left[i - 1][1];
            left[i][1] = ((s.charAt(i) == '1') ? 0 : 1) + left[i - 1][0];
        }
        
        int[][] right = new int[n][2];
        right[n - 1][0] = (s.charAt(n - 1) == '0') ? 0 : 1;
        right[n - 1][1] = (s.charAt(n - 1) == '1') ? 0 : 1;
        
        for (int i=n - 2; i>=0; i--) 
        {
            right[i][0] = ((s.charAt(i) == '0') ? 0 : 1) + right[i + 1][1];
            right[i][1] = ((s.charAt(i) == '1') ? 0 : 1) + right[i + 1][0];
        }
        
        result = Math.min(left[n - 1][0], left[n - 1][1]);
        
        for (int i=1; i<n; i++) 
        {
            if ((n - i) % 2 == 0) 
            {
                // 0101 0101 ? 1010 1010
            	result = Math.min(result, right[i][0] + ((i % 2 == 0) ? left[i - 1][1] : left[i - 1][0]));
            	result = Math.min(result, right[i][1] + ((i % 2 == 0) ? left[i - 1][0] : left[i - 1][1]));
            } 
            else 
            {
                // 101 0101
            	result = Math.min(result, right[i][0] + ((i % 2 == 0) ? left[i - 1][0] : left[i - 1][1]));
            	result = Math.min(result, right[i][1] + ((i % 2 == 0) ? left[i - 1][1] : left[i - 1][0]));
            } 
        }
        
        return result;
    }
									
	
    public static void main(String[] args)
    {
    	Q000_A_Contest test = new Q000_A_Contest();
    	
    	/****************************************************/
    	
    	int[] nums1 = {5,1,3};
    	int[] nums2 = {1,1,1};
    	int[] nums3 = {1,1,2,2,3};
    	
    	System.out.println(test.reductionOperations(nums1));
    	System.out.println(test.reductionOperations(nums2));
    	System.out.println(test.reductionOperations(nums3));
    }
}
