import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/***
 * 
 * @author jackie
 *
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

You may return the answer in any order.

 

Example 1:

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
Example 2:

Input: n = 1, k = 1
Output: [[1]]
 

Constraints:

1 <= n <= 20
1 <= k <= n
 */

public class Q077_Combinations {
	/*************************************************************/
	public List<List<Integer>> combine(int n, int k) 
	{
        List<List<Integer>> result = new LinkedList<>();
        
        if (n <= 0 || k <= 0 || n < k)
        {
            return result;
        }
        
        List<Integer> solution = new LinkedList<>();
        
        backtrack(result, solution, n, 1, k);
        return result;
    }
    
    private void backtrack(List<List<Integer>> result, List<Integer> solution, int n, int start, int k)
    {
        if (solution.size() == k)
        {
            result.add(new ArrayList<Integer>(solution));
            return;
        }
        
        for (int i = start; i <= n; i++)
        {
            solution.add(i);
            backtrack(result, solution, n, i + 1, k);
            solution.remove(solution.size() - 1);
        }
    }
    
    
    
    
    
    
    
    /********************************* main *********************************/
    
    public static void main(String[] args)
    {
    	Q077_Combinations t = new Q077_Combinations();
    	List<List<Integer>> res = t.combine(4, 2);
    	
    	for (int i = 0; i < res.size(); ++i)
    	{
    		for (int j = 0; j < res.get(i).size(); ++j)
    		{
    			System.out.print(res.get(i).get(j) + ", ");
    		}
    		
    		System.out.println();
    	}
    }
}
