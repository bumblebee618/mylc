import java.util.*;

/***
 * 
 * @author jackie
 * 
 * You are given a string s and an integer k. You can choose one of the first k letters of s and append it at the end of the string..

Return the lexicographically smallest string you could have after applying the mentioned step any number of moves.

 

Example 1:

Input: s = "cba", k = 1
Output: "acb"
Explanation: 
In the first move, we move the 1st character 'c' to the end, obtaining the string "bac".
In the second move, we move the 1st character 'b' to the end, obtaining the final result "acb".
Example 2:

Input: s = "baaca", k = 3
Output: "aaabc"
Explanation: 
In the first move, we move the 1st character 'b' to the end, obtaining the string "aacab".
In the second move, we move the 3rd character 'c' to the end, obtaining the final result "aaabc".
 

Constraints:

1 <= k <= s.length <= 1000
s consist of lowercase English letters.
 */
public class Q899_Orderly_Queue {
	public String orderlyQueue(String s, int k) {
        if (k > 1) {
			char[] letters = s.toCharArray();
			Arrays.sort(letters);
			return new String(letters);
		}
		
        String result = s;
            
        for (int i = 0; i < s.length(); ++i) {
            String temp = s.substring(i) + s.substring(0, i);
            
            if (temp.compareTo(result) < 0) {
                result = temp;
            }
        }
            
        return result;
    }
	
	
	
	
	
	
	
	/************************************** main **************************************/
	
	public static void main(String[] args) {
		Q899_Orderly_Queue test = new Q899_Orderly_Queue();
		
		String s1 = "cba";
		int k1 = 1;
		
		String s2 = "baaca";
		int k2 = 3;
		
		String s3 = "nhtq";
		int k3 = 1;
		
		String s4 = "mclpx";
		int k4 = 5;
		
		String s5 = "xxqjzq";
		int k5 = 2;
		
		System.out.println(test.orderlyQueue(s1, k1));
		System.out.println(test.orderlyQueue(s2, k2));
		System.out.println(test.orderlyQueue(s3, k3));
		System.out.println(test.orderlyQueue(s4, k4));
		System.out.println(test.orderlyQueue(s5, k5));
	}
}
