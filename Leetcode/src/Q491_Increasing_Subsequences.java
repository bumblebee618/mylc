import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 
Given an integer array, your task is to find all the different possible increasing subsequences of the given array, and the length of an increasing subsequence should be at least 2 .

Example:
Input: [4, 6, 7, 7]
Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
Note:
The length of the given array will not exceed 15.
The range of integer in the given array is [-100,100].
The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.

 *
 */
public class Q491_Increasing_Subsequences {
	// solution 1
		public List<List<Integer>> findSubsequences(int[] nums) 
		{
	        if (nums == null || nums.length == 0) 
	        {
	            return new LinkedList<>();
	        }
	        
	        Set<List<Integer>> results = new HashSet<>();
	        backtrack(nums, 0, new LinkedList<Integer>(), results);
	        return new LinkedList<>(results);
	    }
	    
	    private void backtrack(int[] nums, int start, List<Integer> list, Set<List<Integer>> results) 
	    {
	        if (list.size() >= 2) 
	        {
	           results.add(new ArrayList<>(list));
	        }
	        
	        for (int i = start; i < nums.length; i++) 
	        {
	            if (list.size() == 0 || nums[i] >= list.get(list.size() - 1)) 
	            {
	                list.add(nums[i]);
	                backtrack(nums, i+1, list, results);
	                list.remove(list.size() - 1);
	            }
	        }
	    }
	
	
	    
	    
	
	// solution 2
	private List<List<Integer>> result = new LinkedList<>();
    
    public List<List<Integer>> findSubsequences2(int[] nums) {
        if (nums == null || nums.length <= 1)
        {
            return result;
        }

        backtrack(new LinkedList<Integer>(), nums, 0);
        return result;
    }
    
    private void backtrack(List<Integer> list, int[] nums, int start)
    {
        if (list.size() >= 2)
        {
            result.add(new LinkedList<Integer>(list));
        }
        
        Set<Integer> visited = new HashSet<>();
        
        for (int i = start; i < nums.length; i++)
        {
            if (visited.contains(nums[i]))
            {
                continue;
            }
            
            if (list.size() == 0 || nums[i] >= list.get(list.size()-1))
            {
                visited.add(nums[i]);
                list.add(nums[i]); 
                backtrack(list, nums, i+1);
                list.remove(list.size()-1);
            }
        }
    }
}
