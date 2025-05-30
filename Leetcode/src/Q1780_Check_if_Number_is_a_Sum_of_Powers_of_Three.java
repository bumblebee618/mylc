import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Given an integer n, return true if it is possible to represent n as the sum of distinct powers of three. Otherwise, return false.

An integer y is a power of three if there exists an integer x such that y == 3x.

 

Example 1:

Input: n = 12
Output: true
Explanation: 12 = 31 + 32
Example 2:

Input: n = 91
Output: true
Explanation: 91 = 30 + 32 + 34
Example 3:

Input: n = 21
Output: false
 

Constraints:

1 <= n <= 107
 */
public class Q1780_Check_if_Number_is_a_Sum_of_Powers_of_Three 
{
	// solution 1: backtrack + binary search
	public boolean checkPowersOfThree(int n) 
    {
    	if (n < 1)
        {
            return false;
        }
    	
        List<Integer> powers = new ArrayList<>();
        int val = 1;
        
        while (val <= n) 
        {
        	powers.add(val);
            val *= 3;
        }
        
        while(n >= 1) 
        {
        	if (powers.size() == 0 || n < powers.get(0))
        	{
        		return false;
        	}
        	
        	int pos = binarySearch(powers, n);
        	n -= powers.get(pos);
        	powers.remove(pos);
        }
        
        return n == 0;
    }
    
    private int binarySearch(List<Integer> powers, int target)
    {
    	int left = 0, right = powers.size()-1;
    	
    	while (left+1 < right)
    	{
    		int mid = left+(right-left)/2;
    		int power = powers.get(mid);
    		
    		if (power < target)
    		{
    			left = mid;
    		}
    		else
    		{
    			right = mid;
    		}
    	}
    	
    	return powers.get(right) <= target ? right : left;
    }
	
	
	// solution 2: memo + backtrack
	private Map<String, Boolean> memo = new HashMap<>();
    private List<Integer> powers = new ArrayList<>();
    private int target = 0;
    
    public boolean checkPowersOfThree2(int n) 
    {
        if (n < 1)
        {
            return false;
        }
        
        target = n;
        
        int maxPower = getMaxPower(n);
        char[] status = new char[maxPower];
        Arrays.fill(status, '0');
        
        return search(status, 0, 0);
    }
    
    private boolean search(char[] status, int startIndex, long curSum)
    {
        if (curSum == (long) target)
        {
            return true;
        }
        
        String key = new String(status);
        
        if (memo.containsKey(key))
        {
            return memo.get(key);
        }
        
        for (int i = startIndex; i < status.length; i++)
        {
        	if (curSum + powers.get(i) > target)
        	{
        		break;
        	}
        	
            if (status[i] == '0')
            {
                status[i] = '1';
                
                if (curSum + powers.get(i) > target)
                {
                	break;
                }
                
                if (search(status, i+1, curSum + powers.get(i)))
                {
                    memo.put(key, true);
                    break;
                }
                
                status[i] = '0';
            }
        }
        
        if (!memo.containsKey(key))
        {
            memo.put(key, false);
        }
        
        return memo.get(key);
    }
    
    private int getMaxPower(int n)
    {
        long y = 1;
        
        while (y <= (long) n)
        {
            powers.add((int) y);
            y *= 3;
        }
        
        return powers.size();
    }
    
    
    
    // solution 3: Math
    public boolean checkPowersOfThree3(int n) 
    {
    	if (n < 1)
        {
            return false;
        }
    	
        while (n > 0) 
        {
            if (n % 3 == 2) 
            {
                return false;
            }
            
            n /= 3;
        }
        
        return true;
    }
    
    
    
    
    /************************************** main **************************************/
    
    public static void main(String[] args)
    {
    	Q1780_Check_if_Number_is_a_Sum_of_Powers_of_Three test = new Q1780_Check_if_Number_is_a_Sum_of_Powers_of_Three();
    	System.out.println(test.checkPowersOfThree2(85));
    }
}
