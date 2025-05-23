import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
/********
 * 
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

[
  ["aa","b"],
  ["a","a","b"]
]
 * 
 * */



public class Q131_Palindrome_Partitioning {
	// solution 1: 自顶向下，backtrack + memoSearch
	private List<List<String>> result = new LinkedList<>();
    private boolean[][] canSplit;
    
    public List<List<String>> partition(String s) 
	{
        if (s == null || s.length() == 0)
        {
            return result;
        }
        
        initMemo(s);
        List<String> solution = new LinkedList<String>();
        
        backtrack(solution, s, 0);
        return result;
    }
    
    private void backtrack(List<String> solution, String s, int start)
    {
        if (start == s.length())
        {
            result.add(new ArrayList<String>(solution));
            return;
        }
        
        for (int i = start; i < s.length(); i++)
        {
            if (canSplit[start][i] == true)
            {
                String newStr = s.substring(start, i + 1);
                solution.add(newStr);
                backtrack(solution, s, i + 1);
                solution.remove(solution.size() - 1);
            }
        }
    }
    
    private void initMemo(String s)
    {
        int n = s.length();
        canSplit = new boolean[n][n];
        
        for (int i = 0; i < n; i++)
        {
            canSplit[i][i] = true;
        }
        
        for (int i = 0; i < n - 1; i++)
        {
            canSplit[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
        }
        
        for (int length = 3; length <= n; length++)
        {
            for (int start = 0; start + length <= n; start++)
            {
                int end = start + length - 1;
                canSplit[start][end] = canSplit[start + 1][end - 1] && s.charAt(start) == s.charAt(end);
            }
        }
    }
	
	
    
	// solution 2: 自底向上，memo search
    private List<List<String>>[] memo;
    
    public List<List<String>> partition2(String s) 
    {
        if (s == null || s.length() == 0)
        {
            return new LinkedList<List<String>>();
        }
        
        memo = new List[s.length()];
        return search(s, 0);
    }
    
    private List<List<String>> search(String s, int start)
    {
        if (start == s.length())
        {
            List<String> list = new LinkedList<>();
            List<List<String>> result = new LinkedList<>();
            result.add(list);
            return result;
        }
        else if (memo[start] != null)
        {
            return memo[start];
        }
        
        memo[start] = new LinkedList<>();
        
        for (int end = start; end < s.length(); end++)
        {
            if (!isPalindrome(s, start, end))
            {
                continue;
            }
            
            List<List<String>> prevResult = search(s, end+1);
            
            for (List<String> list : prevResult)
            {
                List<String> curList = new LinkedList<>(list);
                curList.add(0, s.substring(start, end+1));
                memo[start].add(curList);
            }
        }
        
        return memo[start];
    }
    
    private boolean isPalindrome(String s, int start, int end)
    {
        while (start < end)
        {
            if (s.charAt(start) != s.charAt(end))
            {
                return false;
            }
            
            start++;
            end--;
        }
        
        return true;
    }
	
	
	
    /***************************** main ***************************/
    
	public static void main(String[] args)
	{
		Q131_Palindrome_Partitioning t = new Q131_Palindrome_Partitioning();
		String s = "aab";
		List<List<String>> res = t.partition(s);
		
		for(int i = 0; i < res.size(); ++i){
    		for(int j = 0; j < res.get(i).size(); ++j){
    			System.out.print(res.get(i).get(j) + ", ");
    		}
    		System.out.println();
    	}		
	}
}
