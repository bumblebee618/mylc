import java.util.*;
/*******
 * 
Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
	All numbers (including target) will be positive integers.
	The solution set must not contain duplicate combinations.
	For example, given candidate set [2, 3, 6, 7] and target 7, 
	A solution set is: 
	[
  		[7],
  		[2, 2, 3]
	]
	
 * 
 * */

public class Q039_Combination_Sum 
{
	public List<List<Integer>> combinationSum(int[] candidates, int target) 
	{
        List<List<Integer>> result = new LinkedList<>();
        
        if (candidates == null || candidates.length == 0) 
        {
            return result;
        }
        
        Arrays.sort(candidates);
        backtrack(result, new LinkedList<Integer>(), target, candidates, 0);
        return result;
    }
    
    private void backtrack(
    		List<List<Integer>> result, 
    		List<Integer> solution, 
    		int target, 
    		int[] candidates, 
    		int start) 
    {
        if (target == 0) 
        {
        	result.add(new ArrayList<Integer>(solution));
            return;
        }
        
        for (int i = start; i < candidates.length; i++) 
        {
            if (target < candidates[i]) 
            {
                return;
            }
            
            solution.add(candidates[i]);
            backtrack(result, solution, target - candidates[i], candidates, i);
            solution.remove(solution.size() - 1);
        }
    }
	
    
    
    
    
    
    
    
    
    
    
    /******************************* main function *************************************/
	
	public static void main(String[] args){
		Q039_Combination_Sum t = new Q039_Combination_Sum();
		int[] nums = {2,3,6,7};
		List<List<Integer>> res = t.combinationSum(nums, 7);
		for(int i = 0; i < res.size(); ++i){
    		for(int j = 0; j < res.get(i).size(); ++j){
    			System.out.print(res.get(i).get(j) + ", ");
    		}
    		System.out.println();
    	}
	}
}
