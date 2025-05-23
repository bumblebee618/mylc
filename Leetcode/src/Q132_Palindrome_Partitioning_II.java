/********
 * 
Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needNum for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.

 * 
 * */


public class Q132_Palindrome_Partitioning_II {
	/**************************************************************************************************************************************************
	 * 双序列动态规划 - (String类)
	 * 		(1). 运用memo[i][j]记录从i到j是否可以划分，减少重复计算；
	 * 		(2). 运用dp[i]记录前i字符的最少切割次数
	 * 
	 **************************************************************************************************************************************************/
	// solution 1: using memo + dp, time is O(n^2)
	public int minCut(String s) 
	{
        if (s == null || s.length() == 0)
        {
	    	return 0;
	    }
	    
	    int n = s.length();
	    boolean[][] memo = getMemo(s);
	    int[] cut = new int[n];
	    
	    for(int i = 0; i < n; ++i)
	    {
	        cut[i] = i;
	    }
	    
	    for (int end = 0; end < n; end++)
	    {
	        for (int start = 0; start <= end; start++)
	        {  
	            if (memo[start][end]) 
	            {   
	                if (start == 0)
	                {
	                    cut[end] = 0;  // 注意切割代表的意思，这里代表当前i个字符可以构成一个Palindrome时，此时的切割次数为0
	                } 
	                else 
	                {
	                    cut[end] = Math.min(cut[end], cut[start - 1] + 1);    
	                }
	            }
	        }
	    }
	    
	    return cut[n - 1];
    }
    
    private boolean[][] getMemo(String s)
    {
        int n = s.length();
        boolean[][] memo = new boolean[n][n];
        
        for (int i = 0; i < n; ++i)
        {
            memo[i][i] = true;
        }
        
        for(int i = 0; i < n - 1; ++i)
        {
            memo[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
        }
        
        for (int length = 3; length <= n; ++length)
        {
            for (int start = 0; start + length <= n; ++start)
            {
            	int end = start + length - 1;
                memo[start][end] = memo[start + 1][end-1] && s.charAt(start) == s.charAt(end);
            }
        }
        
        return memo;
    }
    
    
    
    // solution 2: using 区间DP, O(n^3)
    public int minCut2(String s) 
    {
        if (s == null || s.length() == 0)
        {
            return 0;
        }
        
        int size = s.length();
        int[][] minCut = new int[size][size];
        
        for (int i = 0; i < size-1; i++)
        {
            minCut[i][i+1] = s.charAt(i) == s.charAt(i+1) ? 0 : 1;
        }
        
        for (int length = 3; length <= size; length++)
        {
            for (int start = 0; start+length <= size; start++)
            {
                int end = start+length-1;
                minCut[start][end] = Integer.MAX_VALUE;
                
                if (s.charAt(start) == s.charAt(end) && minCut[start+1][end-1] == 0)
                {
                    minCut[start][end] = 0;      
                }
                else
                {
                    for (int k = start; k < end; k++)
                    {
                        minCut[start][end] = Math.min(minCut[start][end], minCut[start][k] + minCut[k+1][end] + 1);
                    }
                }
            }
        }
        
        return minCut[0][size-1];       
    }
    
    
    
    
    
    
    /****************************************** main ******************************************/
    
    public static void main(String[] args)
    {
    	Q132_Palindrome_Partitioning_II t = new Q132_Palindrome_Partitioning_II();
    	String s = "ewewqewqeqwewqeqe";
    	System.out.println(t.minCut(s));
    	System.out.println(t.minCut2(s));
    }
}
