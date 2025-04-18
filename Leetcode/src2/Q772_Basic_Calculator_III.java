import java.util.Stack;

/***
 * 
 * @author jackie
 * 
 * Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647].

Some examples:

"1 + 1" = 2
" 6-4 / 2 " = 4
"2*(5+5*2)/3+(6/2+8)" = 21
"(2+6* 3+5- (3*14/7+2)*5)+3"=-12

 */
public class Q772_Basic_Calculator_III 
{
	public int calculate(String s) 
	{
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
        char prevOper = ' ';
        int num = 0;
        int result = 0;
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
                switch (prevOper)
                {
                	case ' ': stack.push(num); break;
                    case '+': stack.push(num); break;
                    case '-': stack.push(-num); break;
                    case '*': 
                        {
                            if (stack.isEmpty())
                            {
                                return -1;
                            }
                    
                            stack.push(stack.pop() * num);
                            break;
                        }
                    case '/': 
                        {
                            if (stack.isEmpty())
                            {
                                return -1;
                            }
                    
                            stack.push(stack.pop() / num);
                            break;
                        }
                    default: break;
                }
                
                num = 0;
                prevOper = c;
            }
        }
        
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
            int c = s.charAt(i);
                
            if (c == '(')
            {
                count++;
            }
            else if (c == ')')
            {
                count--;
            }
            
            if (count < 0)
            {
            	return -1;
            }
            else if (count == 0)
            {
                return i;
            }
        }
            
        return -1;
    }

    
    
    
    
    
    

    
    public static void main(String[] args)
    {
    	String str = "2*(5+5*2)/3+(6/2+8)";
    	Q772_Basic_Calculator_III test = new Q772_Basic_Calculator_III();
    	System.out.print(test.calculate(str));
    }
}
