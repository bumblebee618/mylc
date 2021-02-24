import java.util.*;
/******
 * 
Given a collection of integers that might contain duplicates, nums, return all possible subsets.
Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]

 * 
 * */

public class Q090_Subsets_II 
{
	// using backtrack
	public List<List<Integer>> subsetsWithDup(int[] nums) 
	{
        List<List<Integer>> result = new ArrayList<>();
        
        if (nums == null || nums.length == 0) 
        {
            return result;
        }
        
        Arrays.sort(nums);
        backtrack(result, new ArrayList<Integer>(), nums, 0);
        return result;
    }
    
    private void backtrack(List<List<Integer>> result, List<Integer> list, int[] nums, int start) 
    {
        result.add(new ArrayList<Integer>(list));
        
        for (int i = start; i < nums.length; i++) 
        {
            list.add(nums[i]);
            backtrack(result, list, nums, i + 1);
            list.remove(list.size() - 1);
            
            while (i + 1 < nums.length && nums[i] == nums[i + 1]) 
            {
                i++;
            }
        }
    }
}
