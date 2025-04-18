import java.util.*;

/***
 * 
 * @author jackie
 *
 * Given an array nums of positive integers, return the longest possible length of an array prefix of nums, such that it is possible to remove exactly one element from this prefix so that every number that has appeared in it will have the same number of occurrences.

If after removing one element there are no remaining elements, it's still considered that every appeared number has the same number of ocurrences (0).

 

Example 1:

Input: nums = [2,2,1,1,5,3,3,5]
Output: 7
Explanation: For the subarray [2,2,1,1,5,3,3] of length 7, if we remove nums[4]=5, we will get [2,2,1,1,3,3], so that each number will appear exactly twice.
Example 2:

Input: nums = [1,1,1,2,2,2,3,3,3,4,4,4,5]
Output: 13
Example 3:

Input: nums = [1,1,1,2,2,2]
Output: 5
Example 4:

Input: nums = [10,2,8,9,3,8,1,5,2,3,7,6]
Output: 8
 

Constraints:

2 <= nums.length <= 10^5
1 <= nums[i] <= 10^5
 */

public class Q1224_Maximum_Equal_Frequency 
{
	// time is O(n), space is O(n)
	public int maxEqualFreq(int[] nums) 
    {
		if (nums == null || nums.length == 0)
        {
            return 0;
        }
        
        int maxLen = 0;
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Set<Integer>> countMap = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++)
        {
        	int count = map.getOrDefault(nums[i], 0);
        	
        	if (count > 0)
            {
                if (countMap.get(count).size() == 1)
                {
                	countMap.remove(count);
                }
                else 
                {
                	countMap.get(count).remove(nums[i]);
				}
            }
        
            map.put(nums[i], count+1);
            countMap.computeIfAbsent(count+1, x -> new HashSet<>()).add(nums[i]);
            
            if (maxLen < i+1 && isValid(countMap))
            {
                maxLen = i+1;
            }
        }
        
        return maxLen;
    }
	
	// time O(1)
	private boolean isValid(Map<Integer, Set<Integer>> countMap)
    {
    	if (countMap.size() == 1)
    	{
    		// all elems' count == 1
    		if (countMap.containsKey(1)) 
    		{
    			return true;
    		}
    		
    		// only contains one elem
    		for (Map.Entry<Integer, Set<Integer>> entry : countMap.entrySet())
    		{
    			return entry.getValue().size() == 1;
    		}
    	}
    	
    	if (countMap.size() == 2)
    	{
    		if (countMap.getOrDefault(1, new HashSet<>()).size() == 1)
    		{
    			return true;
    		}
    		
    		for (Map.Entry<Integer, Set<Integer>> entry : countMap.entrySet())
        	{
        		if (entry.getValue().size() == 1 && countMap.containsKey(entry.getKey()-1))
        		{
        			return true;
        		}
        	}
    	}
    	
    	return false;
    }
	
	
	
	// follow up: if it is not prefix, time is O(uniqueNum * n), space is O(n)
	public int maxEqualFreq2(int[] nums) 
    {
        if (nums == null || nums.length == 0)
        {
            return 0;
        }
        
        int size = nums.length;
        int totalUniqueNum = getUniqueNum(nums);
        
        if (totalUniqueNum == size)
        {
        	return size;
        }
        
        int maxLen = 0;
        
        for (int uniqueNum = 1; uniqueNum <= totalUniqueNum; uniqueNum++)
        {
            int curUnique = 0;
            int front = 0, back = 0;
            Map<Integer, Integer> map = new HashMap<>();
            Map<Integer, Set<Integer>> countMap = new HashMap<>();
            
            while (front < size)
            {
                if (curUnique <= uniqueNum)
                {
                    int count = map.getOrDefault(nums[front], 0);
                
                    if (count == 0)
                    {
                        curUnique++;
                    }
                    else
                    {
                        if (countMap.get(count).size() == 1)
                        {
                        	countMap.remove(count);
                        }
                        else 
                        {
                        	countMap.get(count).remove(nums[front]);
						}
                    }
                
                    map.put(nums[front], count+1);
                    countMap.computeIfAbsent(count+1, x -> new HashSet<>()).add(nums[front]);
                    
                    front++;
                }
                else
                {
                    int count = map.getOrDefault(nums[back], 0);
                    
                    if (countMap.get(count).size() == 1)
                    {
                    	countMap.remove(count);
                    }
                    else 
                    {
                    	countMap.get(count).remove(nums[back]);
					}
                
                    if (count == 1)
                    {
                        curUnique--;
                        map.remove(nums[back]);
                    }
                    else
                    {
                        map.put(nums[back], count-1);
                        countMap.computeIfAbsent(count-1, x -> new HashSet<>()).add(nums[back]);
                    }
                
                    back++;
                }
                
                if (curUnique == uniqueNum && maxLen < front-back && isValid2(countMap))
                {
                    maxLen = front-back;
                }
            }
        }
        
        return maxLen;
    }
    
    private int getUniqueNum(int[] nums)
    {
        Set<Integer> set = new HashSet<>();
        
        for (int num : nums)
        {
            set.add(num);
        }
        
        return set.size();
    }
    
    private boolean isValid2(Map<Integer, Set<Integer>> countMap)
    {
    	if (countMap.size() == 1)
    	{
    		// all elems' count == 1
    		if (countMap.containsKey(1)) 
    		{
    			return true;
    		}
    		
    		// only contains one elem
    		for (Map.Entry<Integer, Set<Integer>> entry : countMap.entrySet())
    		{
    			return entry.getValue().size() == 1;
    		}
    	}
    	
    	if (countMap.size() == 2)
    	{
    		if (countMap.getOrDefault(1, new HashSet<>()).size() == 1)
    		{
    			return true;
    		}
    		
    		for (Map.Entry<Integer, Set<Integer>> entry : countMap.entrySet())
        	{
        		if (entry.getValue().size() == 1 && countMap.containsKey(entry.getKey()-1))
        		{
        			return true;
        		}
        	}
    	}
    	
    	return false;
    }
    
    
    
    
    
    /***************************************** main *****************************************/
    
    public static void main(String[] args)
    {
    	Q1224_Maximum_Equal_Frequency test = new Q1224_Maximum_Equal_Frequency();
    	int[] nums1 = {2,2,1,1,5,3,3,5};
    	int[] nums2 = {1,1,1,2,2,2,3,3,3,4,4,4,5};
    	int[] nums3 = {1,1,1,2,2,2};
    	int[] nums4 = {10,2,8,9,3,8,1,5,2,3,7,6};
    	int[] nums5 = {1,2};
    	int[] nums6 = {1,1};
    	int[] nums7 = {1,1,2,2,3,4,5,6,6};
    	
    	System.out.println(test.maxEqualFreq(nums1));
    	System.out.println(test.maxEqualFreq(nums2));
    	System.out.println(test.maxEqualFreq(nums3));
    	System.out.println(test.maxEqualFreq(nums4));
    	System.out.println(test.maxEqualFreq(nums5));
    	System.out.println(test.maxEqualFreq(nums6));
    	System.out.println(test.maxEqualFreq(nums7));
    }
}
