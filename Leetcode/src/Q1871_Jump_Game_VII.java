import java.util.*;

/***
 * 
 * @author jackie
 * 
 * You are given a 0-indexed binary string s and two integers minJump and maxJump. In the beginning, you are standing at index 0, which is equal to '0'. You can move from index i to index j if the following conditions are fulfilled:

i + minJump <= j <= min(i + maxJump, s.length - 1), and
s[j] == '0'.
Return true if you can reach index s.length - 1 in s, or false otherwise.

 

Example 1:

Input: s = "011010", minJump = 2, maxJump = 3
Output: true
Explanation:
In the first step, move from index 0 to index 3. 
In the second step, move from index 3 to index 5.
Example 2:

Input: s = "01101110", minJump = 2, maxJump = 3
Output: false
 

Constraints:

2 <= s.length <= 105
s[i] is either '0' or '1'.
s[0] == '0'
1 <= minJump <= maxJump < s.length
 */
public class Q1871_Jump_Game_VII 
{
	// solution 1: sliding windows
	public boolean canReach(String s, int minJump, int maxJump) 
	{
		if (s == null || s.length() <= 1 || s.charAt(s.length()-1) == '1')
		{
			return false;
		}
		
		int size = s.length(), curIndex = 0;
	    List<Integer> zeros = new ArrayList<>();
		
		for (int i = 0; i < s.length(); i++)
		{
			if (s.charAt(i) == '0')
			{
				zeros.add(i);
			}
		}
		
		int left = 0, right = 0;
		
		while (curIndex < size)
		{
			while (left < zeros.size() && zeros.get(left) < curIndex + minJump)
			{
				left++;
			}
            
            if (left >= zeros.size())
			{
				return false;
			}
			
			while (right < zeros.size() && zeros.get(right) <= curIndex + maxJump)
			{
				right++;
			}
			
			right--;
			
			if (right == zeros.size()-1)
			{
				return true;
			}
			else if (left > right)
			{
				return false;
			}
			
			curIndex = zeros.get(left);
		}
		
		return true;
    }
    
    
	// solution 2: deque
    public boolean canReach2(String s, int minJump, int maxJump) 
	{
		if (s == null || s.length() <= 1 || s.charAt(s.length()-1) == '1')
		{
			return false;
		}
		
		int size = s.length(), curIndex = 0, prevFast = minJump-1;
        
		Set<Integer> visited = new HashSet<>();
		visited.add(0);
        
		Deque<Integer> dq = new LinkedList<>();
		dq.offer(0);
		
		while (curIndex < size && !dq.isEmpty())
		{
			while (!dq.isEmpty() && curIndex+minJump > dq.peekFirst())
			{
				dq.pollFirst();
			}
			
			for (int start = Math.max(curIndex+minJump, prevFast+1); start < size && start <= curIndex + maxJump; start++)
			{
				if (s.charAt(start) == '0' && !visited.contains(start))
				{
					dq.offer(start);
					visited.add(start);
					
					if (start == size-1)
					{
						return true;
					}
				}
			}
            
            prevFast = curIndex + maxJump;
			
			if (dq.isEmpty())
			{
				break;
			}
			else
			{
				curIndex = dq.peekFirst();
			}
		}
		
		return curIndex >= size-1;
    }
}
