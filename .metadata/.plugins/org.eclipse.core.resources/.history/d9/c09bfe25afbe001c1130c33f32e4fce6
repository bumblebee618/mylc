import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
/*******
 * 
Given a string that contains only digits 0-9 and a target minValue, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target minValue.

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
private List<String> result = new LinkedList<>();
    
    public List<String> addOperators(String num, int target) 
    {
        if (num == null || num.length() == 0)
        {
            return result;
        }
        
        search(num, 0, "", target, 0, 0);
        return result;
    }
    
    private void search(String num, int start, String solution, int target, long sum, long prevNum)
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
        	// 注意这里是start，而不是 end, str可以是 "0", 但不可以是"01"之类的 ！！！
            if (num.charAt(start) == '0' && end > start)         
            {                                                   
                return;
            }
            
            String str = num.substring(start, end+1);
            long curNum = Long.parseLong(str);
            
            if (solution.length() == 0)
            {
                search(num, end+1, str, target, curNum, curNum);
            }
            else
            {
                search(num, end+1, String.format("%s+%s", solution, str), target, sum+curNum, curNum);
                search(num, end+1, String.format("%s-%s", solution, str), target, sum-curNum, -curNum);
                search(num, end+1, String.format("%s*%s", solution, str), target, sum-prevNum + prevNum*curNum, prevNum*curNum);
            }
        }
    }
}
