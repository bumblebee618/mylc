import java.util.Stack;
/******
 * 
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), 
the plus + or minus sign -, non-negative integers and empty spaces .

You may assume that the given expression is always valid.

Some examples:
	"1 + 1" = 2
	" 2-1 + 2 " = 3
	"(1+(4+5+2)-3)+(6+8)" = 23

Note: Do not use the eval built-in library function.

 * 
 * */

public class Q224_Basic_Calculator {
	// using recursion
	public int calculate(String s) {
        if (s == null)
        {
            return 0;
        }
        
        s = s.trim();
        
        if (s.length() == 0)
        {
            return 0;
        }
        
        Stack<Integer> stack = new Stack<>();
        char prevSign = ' ';
        int num = 0;
        int size = s.length();
        
        for (int i = 0; i < size; i++)
        {
            char c = s.charAt(i);
            
            if (c == ' ')
            {
                continue;
            }
            
            if (Character.isDigit(c))
            {
                num = num*10 + (c-'0');
            }
            
            if (c == '(')
            {
                int end = findClosePos(s, i);
                
                if (end == -1)
                {
                    return 0;
                }
                
                num = calculate(s.substring(i+1, end));
                i = end;
            }
            
            if (!Character.isDigit(c) || i == size-1)
            {
                if (prevSign == '+')
                {
                    stack.push(num);
                }
                else if (prevSign == '-')
                {
                    stack.push(-num);
                }
                else if (prevSign == ' ')
                {
                    stack.push(num);
                }
                
                prevSign = c;
                num = 0;
            }
        }
        
        int result = 0;
        
        while (!stack.isEmpty())
        {
            result += stack.pop();
        }
        
        return result;
    }
    
    private int findClosePos(String s, int start)
    {
        int count = 0;
        
        for (int i = start; i < s.length(); i++)
        {
            char c = s.charAt(i);
            
            if (c == '(')
            {
                count++;
            }
            else if (c == ')')
            {
                count--;
            }
            
            if (count == 0)
            {
                return i;
            }
        }
        
        return -1;
    }

    
    
	
	// follow up, + - * /
	public int calculate_follow_up(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        
        int len = s.length();
        Stack<Integer> stack = new Stack<Integer>();
        char preSign = ' ';
        int ans = 0;
        int num = 0;
        
        for(int i = 0; i < len; i++){
            char c = s.charAt(i);
            
            if(Character.isDigit(c)){
                num = num * 10 + (int)(c - '0');
            } 
            
            if(!Character.isDigit(c) && c != ' ' || i == len - 1){
                if(preSign == '+'){
                    stack.push(num);
                } else if(preSign == '-'){
                    stack.push(-num);
                } else if(preSign == '*'){
                    if(!stack.isEmpty()){
                        stack.push(stack.pop() * num);
                    } else {
                        break;
                    }
                } else if(preSign == '/'){
                    if(!stack.isEmpty()){
                        stack.push(stack.pop() / num);
                    } else {
                        break;
                    }
                } else {
                    stack.push(num);
                }
                
                preSign = c;
                num = 0;
            } 
        }
        
        while(!stack.isEmpty()){
            ans += stack.pop();
        }
        
        return ans;
    }
}
