import java.util.*;
/********
 * 
 contains: +, -, *, /, (, )
 * 
 * */

public class Q224_Basic_Calculator_III 
{	
	public int calculate(String s) {
        if (s == null) {
            return 0;
        }
        
        s = s.replaceAll(" ", "");
        
        if (s.length() == 0) {
            return 0;
        }
        
        Stack<Integer> stack = new Stack<>();
        int num = 0, result = 0;
        char preOper = ' ';
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else if (c == '(') {
                int endPos = findEndPos(s, i);
                
                if (endPos == -1) {
                    return -1;
                }
                
                num = calculate(s.substring(i+1, endPos));
                i = endPos;
            }
            
            if (!Character.isDigit(c) || i == s.length()-1) {
                switch (preOper) {
                    case ' ': stack.push(num); break;
                    case '+': stack.push(num); break;
                    case '-': stack.push(-num); break;
                    case '*': {
                                if (stack.isEmpty()) {
                                    return -1;
                                } else {
                                    stack.push(stack.pop() * num);
                                    break;
                                }
                            }
                    case '/': {
                                if (stack.isEmpty()) {
                                    return -1;
                                } else {
                                    stack.push(stack.pop() / num);
                                    break;
                                }
                            }
                    default: break;
                }
                
                num = 0;
                preOper = c;
            }
        }
        
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        
        return result;
    }
    
    private int findEndPos(String s, int start) {
        int count = 0;
        
        while (start < s.length()) {
            char c = s.charAt(start);
            
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
            }
            
            if (count == 0) {
                return start;
            } else if (count < 0) {
                return -1;
            }
            
            start++;
        }
        
        return -1;
    }
	
	
	
    
    
    /****************************** main ******************************/ 
	
	public static void main(String[] args) 
	{
		Q224_Basic_Calculator_III t = new Q224_Basic_Calculator_III();
		String str = "3 * (3/(1+2))";
		System.out.println(t.calculate(str));
	}
}
