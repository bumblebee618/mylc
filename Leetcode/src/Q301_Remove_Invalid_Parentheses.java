import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
/*****
 * 
Remove the minimum number of invalid parentheses in order to make the input string valid. 
Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Examples:
"()())()" -> ["()()()", "(())()"]
"(a)())()" -> ["(a)()()", "(a())()"]
")(" -> [""]
 * 
 * */

public class Q301_Remove_Invalid_Parentheses {
	/*******************************************************************
	 * 此题使用bfs，层序遍历
	 * 设置一个found标志位，当找到一个后，只统计这一层的符合的解
	 * 此例中判断是否是合法的Parentheses方法很简单！
	 *  
	 *******************************************************************/
	
	public List<String> removeInvalidParentheses(String s) {
		List<String> result = new ArrayList<String>();
		
        if (s == null || s.length() == 0) {
            return result;
        }
        
        Queue<String> queue = new LinkedList<>();
        queue.offer(s);
        
        Set<String> visited = new HashSet<>();
        visited.add(s);
        
        boolean found = false;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                String tempStr = queue.poll();
                
                if (isValid(tempStr)) {
                    result.add(tempStr);
                    found = true;
                }
                
                // 只要找到一个解，则不再往下拆分，只统计当前层里的解
                if (found == true) {  
                    continue;
                }
            
                for (int j = 0; j < tempStr.length(); ++j) {
                    char c = tempStr.charAt(j);  // 需要剔除无效字符
                
                    if (c != '(' && c != ')') {
                        continue;
                    }
                
                    String newStr = tempStr.substring(0, j) + tempStr.substring(j+1);
                
                    if (!visited.contains(newStr)) {
                        visited.add(newStr);
                        queue.offer(newStr);
                    }
                }
            }
            
            if (found) {
                break;
            }
        }
        
        return result;
    }
    
	// 此法验证是否是有效的Parentheses很巧 ！！！
    private boolean isValid(String str) {   
        int count = 0;
        
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
            }
            
            if (count < 0) {
                return false;
            }
        }
        
        return count == 0;
    }
}
