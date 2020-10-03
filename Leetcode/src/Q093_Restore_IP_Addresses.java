import java.util.*;
/*****
 * 
 * @author jackie
 *
 * Given a string s containing only digits, return all possible valid IP addresses that can be obtained from s. You can return them in any order.

A valid IP address consists of exactly four integers, each integer is between 0 and 255, separated by single dots and cannot have leading zeros. For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses and "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses. 

 

Example 1:

Input: s = "25525511135"
Output: ["255.255.11.135","255.255.111.35"]
Example 2:

Input: s = "0000"
Output: ["0.0.0.0"]
Example 3:

Input: s = "1111"
Output: ["1.1.1.1"]
Example 4:

Input: s = "010010"
Output: ["0.10.0.10","0.100.1.0"]
Example 5:

Input: s = "101023"
Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 */

public class Q093_Restore_IP_Addresses {
	// test case = "0000"
	public List<String> restoreIpAddresses(String s) {
        List<String> result = new LinkedList<>();
        
        if (s == null || s.length() == 0) 
        {
            return result;
        }
        
        backtrack(result, new ArrayList<String>(), s, 0);
        return result;
    }
    
    private void backtrack(List<String> result, List<String> solution, String s, int start)
    {
        if (solution.size() == 4 || start == s.length()) 
        {
            if (solution.size() == 4 && start == s.length()) 
            {
                StringBuilder builder = new StringBuilder();
                
                for (int i = 0; i < 3; i++) 
                {
                    builder.append(solution.get(i)).append(".");
                }
                
                builder.append(solution.get(3));
                result.add(builder.toString());
            }
            
            return;
        } 
        
        for (int i = start; i < s.length() && i < start + 3; i++) 
        {
        	if(s.charAt(start) == '0' && i > start) 
        	{
                return;
            }
        	
            String str = s.substring(start, i + 1);
            int num = Integer.parseInt(str);
            
            if(num >= 0 && num <= 255) 
            {
                solution.add(str);
                backtrack(result, solution, s, i + 1);
                solution.remove(solution.size() - 1);
            }
        }
    }
}
