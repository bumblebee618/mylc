import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
/*******
 * 
Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

Examples: 
	"123", 6 -> ["1+2+3", "1*2*3"] 
	"232", 8 -> ["2*3+2", "2+3*2"]
	"105", 5 -> ["1*0+5","10-5"]
	"00", 0 -> ["0+0", "0-0", "0*0"]
	"3456237490", 9191 -> []

 * 
 * */



public class Q282_Expression_Add_Operators {
	// test case: ["0"], ["01"], ["101"]
	public List<String> addOperators(String num, int target) {
        List<String> result = new LinkedList<>();
        
        if (num == null || num.length() == 0)
        {
            return result;
        }
        
        backtrack(result, num, 0, "", target, 0, 0);
        return result;
    }
    
    private void backtrack(List<String> result, String num, int start, String solution, int target, long sum, long prevSum)
    {
    	// prevNum is used to store the previous valid number 
        if (start == num.length())
        {
            if (sum == target)
            {
                result.add(solution);
            }
            
            return;
        }
        
        for (int end = start; end < num.length(); end++)
        {
            if (num.charAt(start) == '0' && end > start)         // 注意这里是start，而不是 i  ！！！
            {                                                    // 可以是 "0", 但不可以是"01"之类的 ！！！
                return;
            }
            
            long curNum = Long.parseLong(num.substring(start, end+1));
            
            if (solution.length() == 0)
            {
                backtrack(result, num, end+1, num.substring(start, end+1), target, curNum, curNum);
            }
            else
            {
                backtrack(result, num, end+1, String.format("%s+%s", solution, curNum), target, sum+curNum, curNum);
                backtrack(result, num, end+1, String.format("%s-%s", solution, curNum), target, sum-curNum, -curNum);
                backtrack(result, num, end+1, String.format("%s*%s", solution, curNum), target, sum-prevSum + prevSum*curNum, prevSum*curNum);
            }
        }
    }
}
