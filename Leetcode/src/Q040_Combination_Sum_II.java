import java.util.*;
/********
 * 
Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, 
A solution set is: 
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
 * 
 * */

public class Q040_Combination_Sum_II 
{
	public List<List<Integer>> combinationSum2(int[] candidates, int target) 
	{
        List<List<Integer>> result = new LinkedList<>();
        
        if(candidates == null || candidates.length == 0)
        {
            return result;
        }
        
        List<Integer> list = new ArrayList<Integer>();
        Arrays.sort(candidates);
        backtrack(result, list, candidates, 0, target);
        return result;
    }
    
    private void backtrack(
    		List<List<Integer>> result, 
    		List<Integer> solution, 
    		int[] candidates, int start, int target)
    {
        if (target == 0)
        {
            result.add(new ArrayList<Integer>(solution));
            return;
        } 
        
        for (int i = start; i < candidates.length; i++)
        {
            if (candidates[i] > target)
            {
                return;
            }
            
            solution.add(candidates[i]);
            backtrack(result, solution, candidates, i + 1, target - candidates[i]);
            solution.remove(solution.size() - 1);
            
            while (i + 1 < candidates.length && candidates[i] == candidates[i + 1])
            {
                i++;
            }
        }
    }
}
