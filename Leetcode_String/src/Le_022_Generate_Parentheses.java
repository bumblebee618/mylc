import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

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


public class Le_022_Generate_Parentheses {
	// solution 1: using backtrack
	public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        
        if (n <= 0) {
            return ans;
        }
        
        helper(ans, "", 0, 0, n);
        return ans;
    }
    
    public void helper(List<String> ans, String solution, int openNum, int closeNum, int n) {
        if (n * 2 == solution.length()) {
            ans.add(solution);
            return ;
        }
        
        if (openNum < n) {
            helper(ans, solution + "(", openNum + 1, closeNum, n);
        } 
        
        if (closeNum < openNum) {
            helper(ans, solution + ")", openNum, closeNum + 1, n);
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
    	Le_022_Generate_Parentheses t = new Le_022_Generate_Parentheses();
    	List<String> res = t.generateParenthesis(3);
    	for(int i = 0; i < res.size(); ++i)
    		System.out.println(res.get(i));
    	System.out.println(res.size());
    }
}
