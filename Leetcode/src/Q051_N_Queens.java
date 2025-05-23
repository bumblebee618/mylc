import java.util.LinkedList;
import java.util.List;


public class Q051_N_Queens {
	// backtrack(recursive)
	public List<List<String>> solveNQueens(int n) 
	{
        List<List<String>> result = new LinkedList<>();
        
        if (n <= 0)
        {
            return result;
        }
        
        int[] position = new int[n+1];
        backtrack(result, position, 1, n);
        return result;
    }
    
    private void backtrack(List<List<String>> result, int[] position, int curRow, int n)
    {
        if (curRow > n)
        {
            result.add(getSolution(position, n));
            return;
        }
        
        for (int i = 1; i <= n; i++)
        {
            position[curRow] = i;
            
            if (isValid(position, curRow))
            {
                backtrack(result, position, curRow+1, n);
            }
        }
    }
    
    private boolean isValid(int[] position, int curIndex)
    {
        for (int i = 1; i < curIndex; i++)
        {
            if (position[i] == position[curIndex] || Math.abs(i-curIndex) == Math.abs(position[i] - position[curIndex]))
            {
                return false;
            }
        }
        
        return true;
    }
    
    private List<String> getSolution(int[] position, int n)
    {
        List<String> list = new LinkedList<>();
        
        for (int i = 1; i < position.length; i++)
        {
            StringBuilder builder = new StringBuilder();
            
            for (int j = 1; j <= n; j++)
            {
                builder.append(j == position[i] ? "Q" : ".");
            }
            
            list.add(builder.toString());
        }
        
        return list;
    }
    
    
    
    
    
    /*************************************** main ***************************************/ 
    public static void main(String[] args)
    {
    	Q051_N_Queens t = new Q051_N_Queens();
    	List<List<String>> res = t.solveNQueens(8);
    	
    	for (int i = 0; i < res.size(); ++i)
    	{
    		for (int j = 0; j < res.get(i).size(); ++j)
    		{
    			System.out.print(res.get(i).get(j) + ", ");
    		}
    		
    		System.out.println();
    	} 
    	
    	System.out.println("size = " + res.size());
    }
}
