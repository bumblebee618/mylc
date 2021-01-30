import java.util.*;
/*******
 * 
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
 * 
 * */

public class Q047_Permutations_II {
	
	public List<List<Integer>> permuteUnique(int[] nums) 
	{
        List<List<Integer>> result = new LinkedList<>();
        
        if (nums == null || nums.length == 0) 
        {
            return result;
        }
        
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        List<Integer> solution = new LinkedList<>();
        backtrack(result, solution, nums, visited);
        return result;
    }
    
    private void backtrack(List<List<Integer>> result, List<Integer> solution, int[] nums, boolean[] visited)
    {
        if (solution.size() == nums.length) 
        {
            result.add(new LinkedList<Integer>(solution));
            return ;
        }
        
        for (int i = 0; i < nums.length; i++) 
        {
            if (visited[i] == false) 
            {
                visited[i] = true;
                solution.add(nums[i]);
                backtrack(result, solution, nums, visited);
                solution.remove(solution.size() - 1);
                visited[i] = false;
                
                // 重复部分需要写在 visited[i] == false里
                // 表示当此element被选上时，才跳过相同的element
                while (i + 1 < nums.length && nums[i] == nums[i + 1]) 
                {
                    i++;
                }
            }
        }
    }
    
    
    
    
    
    
    
    
    
    /**************************** main function ***********************************/
    
    public static void main(String[] args){
    	Q047_Permutations_II t = new Q047_Permutations_II();
    	int[] nums = {0,1,0,0,9};
    	List<List<Integer>> res = t.permuteUnique(nums);
    	for(int i = 0; i < res.size(); ++i){
    		for(int j = 0; j < res.get(i).size(); ++j){
    			System.out.print(res.get(i).get(j) + ", ");
    		}
    		System.out.println();
    	}
    }
}
