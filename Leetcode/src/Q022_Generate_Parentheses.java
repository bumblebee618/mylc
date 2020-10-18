import java.util.*;
/*******
 * 
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]

 * 
 * */


public class Q022_Generate_Parentheses {
	// solution 1: using backtrack
	public List<String> generateParenthesis(int n) 
	{
        List<String> result = new LinkedList<>();
        
        if (n <= 0)
        {
            return result;
        }
        
        backtrack(result, "", 0, 0, n);
        return result;
    }
    
    private void backtrack(List<String> result, String solution, int open, int close, int n)
    {
        if (solution.length() == n*2)
        {
            result.add(solution);
            return;
        }
        
        if (open < n)
        {
            backtrack(result, solution + "(", open+1, close, n);
        }
        
        // 注意不能用 else
        if (close < open)  
        {
            backtrack(result, solution + ")", open, close+1, n);
        }
    }

    
	
	// solution 2: using backtrack, but exceed time limit
	private List<String> res = new LinkedList<String>();
	private HashSet<String> set = new HashSet<String>();
	
    public List<String> generateParenthesis3(int n) {
        if(n <= 0){
            return res;
        }
        char[] array = new char[n*2];
        for(int i = 0; i < n*2; ++i){
            if(i % 2 == 0)
                array[i] = '(';
            else
                array[i] = ')';
        }
        permutation(array, n*2-1);
        return res;
    }
    
    public void permutation(char[] array, int len){
        if(len == 0){
            if(isValid(array) == true){
            	String str = new String(array);
            	if(!set.contains(str)){
            		res.add(str);
            		set.add(str);
            	}
            }
            return;
        }
        
        for(int i = 0; i <= len; ++i){
            swap(array, i, len);
            permutation(array, len-1);
            swap(array, i, len);
        }
    }
    
    public void swap(char[] array, int x, int y){
        char temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }
    
    public boolean isValid(char[] array){
        Stack<Character> s = new Stack<Character>();
        for(int i = 0, len = array.length; i < len; ++i){
            if(array[i] == '('){
                s.push(array[i]);
            } else{
                if(s.size() == 0){
                    return false;
                }
                s.pop();  
            }
        }
        return (s.size() == 0);
    }
    
    
    
    
    
    
    
    
    
    /********************************* main function ***************************************/
    
    public static void main(String[] args){
    	Q022_Generate_Parentheses t = new Q022_Generate_Parentheses();
    	List<String> res = t.generateParenthesis(3);
    	for(int i = 0; i < res.size(); ++i)
    		System.out.println(res.get(i));
    	System.out.println(res.size());
    }
}
