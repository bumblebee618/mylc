import java.util.*;


public class Q316_Remove_Duplicate_Letters {
	// by other
	/*****************************************************************************************************
     * First loop: use an array hash[] to count the number of times each letter has appeared in s.
     * 
     * Second loop (Greedy): use a stack, pop() 
     * if previous character appears more than once, and current character is smaller than previous 
     * one in lexicographical order, the previous character should be removed;
     * 
     *****************************************************************************************************/
	// solution 1
	public String removeDuplicateLetters(String s) {
        if (s == null || s.length() <= 1)
        {
            return s;
        }
        
        Stack<Character> stack = new Stack<>();
        Set<Character> visited = new HashSet<>();
        int[] hash = new int[256];
        
        for (int i = 0; i < s.length(); i++)
        {
            hash[s.charAt(i)] = i;
        }

        for(int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);

            if (!visited.contains(c))
            {
                while(!stack.isEmpty() && c < stack.peek() && hash[stack.peek()] > i)
                {
                    visited.remove(stack.pop());
                }
                
                visited.add(c);
                stack.push(c);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (char c : stack) 
        {
            sb.append(c);
        }
        
        return sb.toString();
    }

	
	
	
	
	// solution 2
	public String removeDuplicateLetters2(String s) {
		if (s == null || s.isEmpty()) {
            return "";
		}
		
        Stack<Character> stack = new Stack<>();
        Map<Character, Integer> map = new HashMap<>();
        Set<Character> inStack = new HashSet<>();
        
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }        
        
        for (char c : s.toCharArray()) {
            if (inStack.contains(c)) {
                map.put(c, map.get(c) - 1); 
                continue;
            } 
            
            while (!stack.isEmpty()) {
                char top = stack.peek();
                
                if (map.get(top) > 1 && c < top) {
                    stack.pop();
                    inStack.remove(top);
                    map.put(top, map.get(top) - 1);
                } else {
                    break;
                }
            }
            
            stack.push(c);
            inStack.add(c);
        }
        
        String res = "";
        
        while (!stack.isEmpty()) {
            res = stack.pop() + res;
        }
        
        return res;
    }
	
	
	public static void main(String[] args){
		Q316_Remove_Duplicate_Letters t = new Q316_Remove_Duplicate_Letters();
		System.out.println(t.removeDuplicateLetters2("cbac"));	
	}
}
