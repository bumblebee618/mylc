import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class Q241_Different_Ways_to_Add_Parentheses {
	private List<Integer> nums = new ArrayList<>();
    private List<Character> opers = new ArrayList<>();
    
    public List<Integer> diffWaysToCompute(String input) {
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
	
	
	public static void main(String[] args){
		Q241_Different_Ways_to_Add_Parentheses t = new Q241_Different_Ways_to_Add_Parentheses();
		t.diffWaysToCompute("2*3-4*5");
	}
}	
