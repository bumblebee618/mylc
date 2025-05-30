/***
 * Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.

Example 1:

Input: "2-1-1"
Output: [0, 2]
Explanation: 
((2-1)-1) = 0 
(2-(1-1)) = 2
Example 2:

Input: "2*3-4*5"
Output: [-34, -14, -10, -10, 10]
Explanation: 
(2*(3-(4*5))) = -34 
((2*3)-(4*5)) = -14 
((2*(3-4))*5) = -10 
(2*((3-4)*5)) = -10 
(((2*3)-4)*5) = 10

 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Q241_Different_Ways_to_Add_Parentheses {
	private List<Integer> result = new LinkedList<>();
    private List<Character> opers = new ArrayList<>();
    private List<Integer> nums = new ArrayList<>();
    private List<Integer>[][] memo;
    
    public List<Integer> diffWaysToCompute(String expression) {
        if (expression == null || expression.length() == 0) {
            return result;
        }
        
        expression = expression.replaceAll(" ", "");
        initialization(expression);
        
        memo = new List[nums.size()][nums.size()];
        return search(0, nums.size()-1);
    }
    
    private void initialization(String expression) {
        int num = 0;
        
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            
            if (Character.isDigit(c)) {
                num = num * 10 + (int) (c-'0');
            } else {
                opers.add(c);
                nums.add(num);
                num = 0;
            }
        }
        
        nums.add(num);
    }
    
    private List<Integer> search(int start, int end) {
        if (memo[start][end] != null) {
            return memo[start][end];
        }
        
        memo[start][end] = new LinkedList<>();
        
        if (start == end) {
            memo[start][end].add(nums.get(start));
            return memo[start][end];
        }
        
        for (int mid = start; mid < end; mid++) {
            List<Integer> leftResult = search(start, mid);
            List<Integer> rightResult = search(mid+1, end);
            
            for (int left : leftResult) {
                for (int right : rightResult) {
                    char oper = opers.get(mid);
                    
                    switch (oper) {
                        case '+': memo[start][end].add(left+right); break;
                        case '-': memo[start][end].add(left-right); break;
                        case '*': memo[start][end].add(left*right); break;
                        case '/': memo[start][end].add(left/right); break;
                        default: break;
                    }
                }
            }
        }
        
        return memo[start][end];
    }
	
	
	/***
	private List<Integer> nums = new ArrayList<>();
    private List<Character> opers = new ArrayList<>();
    
    public List<Integer> diffWaysToCompute(String input) 
    {
        if (input == null || input.length() == 0)
        {
            return new LinkedList<Integer>();
        }
        
        initLists(input);
        return backtrack(0, nums.size()-1);
    }
    
    private void initLists(String input)
    {
        int num = 0;
        
        for (int i = 0; i < input.length(); i++)
        {
            char c = input.charAt(i);
            
            if (Character.isDigit(c))
            {
                num = num*10 + (int)(c-'0');
            }
            else
            {
                opers.add(c);
                nums.add(num);
                num = 0;
            }
        }
        
        nums.add(num);
    }
    
    private List<Integer> backtrack(int start, int end)
    {
        List<Integer> result = new LinkedList<>();
        
        if (start == end)
        {
            result.add(nums.get(start));
            return result;
        }
        
        for (int i = start; i < end; i++)
        {
            List<Integer> leftResult = backtrack(start, i);
            List<Integer> rightResult = backtrack(i+1, end);
            
            for (int left : leftResult)
            {
                for (int right : rightResult)
                {
                    switch(opers.get(i))
                    {
                        case '+': result.add(left+right); break;
                        case '-': result.add(left-right); break;
                        case '*': result.add(left*right); break;
                        case '/': result.add(left/right); break;
                    }
                }
            }
        }
        
        return result;
    }
    ***/
    
	
	
	public static void main(String[] args){
		Q241_Different_Ways_to_Add_Parentheses t = new Q241_Different_Ways_to_Add_Parentheses();
		t.diffWaysToCompute("2*3-4*5");
	}
}	
