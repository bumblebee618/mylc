import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/***
 * 
 * @author jackie
 * 
 * There is a donuts shop that bakes donuts in batches of batchSize. They have a rule where they must serve all of the donuts of a batch before serving any donuts of the next batch. You are given an integer batchSize and an integer array groups, where groups[i] denotes that there is a group of groups[i] customers that will visit the shop. Each customer will get exactly one donut.

When a group visits the shop, all customers of the group must be served before serving any of the following groups. A group will be happy if they all get fresh donuts. That is, the first customer of the group does not receive a donut that was left over from the previous group.

You can freely rearrange the ordering of the groups. Return the maximum possible number of happy groups after rearranging the groups.

 

Example 1:

Input: batchSize = 3, groups = [1,2,3,4,5,6]
Output: 4
Explanation: You can arrange the groups as [6,2,4,5,1,3]. Then the 1st, 2nd, 4th, and 6th groups will be happy.
Example 2:

Input: batchSize = 4, groups = [1,3,2,5,2,2,1,6]
Output: 4
 

Constraints:

1 <= batchSize <= 9
1 <= groups.length <= 30
1 <= groups[i] <= 109
 */
public class Q1815_Maximum_Number_of_Groups_Getting_Fresh_Donuts 
{
	// solution 1: greedy + memo search;
	private Map<String, Integer> memo;
	
	public int maxHappyGroups(int batchSize, int[] groups) 
	{	
		if (batchSize <= 0 || groups == null || groups.length == 0)
		{
			return 0;
		}
		
		memo = new HashMap<>();
		
        int countMap[] = new int[batchSize+1];
        int result = 0;
        
        for (int group : groups) 
        {
            countMap[group % batchSize]++;
        }
        
        result += countMap[0];
        countMap[0] = 0;
        
        for (int num = 1; num < batchSize / 2; num++) 
        {
            int min = Math.min(countMap[num], countMap[batchSize-num]);
            countMap[num] -= min;
            countMap[batchSize - num] -= min;
            result += min;
        }
        
        result += search(countMap, 0);
        return result;
    }
    
    private int search(int countMap[], int sumLeft) 
    {
        int n = countMap.length-1;
        String key = Arrays.toString(countMap);
        
        if (memo.containsKey(key)) 
        {
        	return memo.get(key);
        }
        
        int result = 0;
        
        // greedy and short cut when we can finish the current round
        if (sumLeft > 0 && countMap[n-sumLeft] > 0) 
        {
            countMap[n-sumLeft]--;
            result = search(countMap, 0);
            countMap[n-sumLeft]++;
        } 
        else 
        {
        	// or just backtrack to find the better solution
            for (int i = 1; i < countMap.length; i++) 
            {
                if (countMap[i] > 0) 
                {
                    countMap[i]--;
                    result = Math.max(result, search(countMap, (sumLeft+i) % n) + (sumLeft == 0 ? 1 : 0));
                    countMap[i]++;
                }
            }
        }
        
        memo.put(key, result);
        return result;
    }
}
