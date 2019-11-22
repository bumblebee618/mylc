/**
 * 
 We are given two strings, A and B.

A shift on A consists of taking string A and moving the leftmost character to the rightmost position. For example, if A = 'abcde', then it will be 'bcdea' after one shift on A. Return True if and only if A can become B after some number of shifts on A.

Example 1:
Input: A = 'abcde', B = 'cdeab'
Output: true

Example 2:
Input: A = 'abcde', B = 'abced'
Output: false
Note:

A and B will have length at most 100.
 *
 */
public class Q796_Rotate_String {
	// solution 1:
	public boolean rotateString(String A, String B) {
	    if(A.length() != B.length()) {
	    	return false;
	    }
	    
	    int[] starts = new int[A.length()];
	    char c = A.charAt(0);
	    int index = 0;
	    
	    for (int i = 0; i < A.length(); i++) {
	        if (B.charAt(i) == c) {
	        	starts[index++] = i;
	        }
	    }
	    
	    for (int i = 0; i < index; i++){
	        if (helper(A, B, starts[i]) == true) {
	        	return true;
	        }
	    }
	    
	    return false;
	}
	
	private boolean helper(String A, String B, int start) {
        int len = A.length();
        String str1 = A.substring(0, len-start);
        String str2 = A.substring(len-start, len);
        String str3 = B.substring(start, len);
        String str4 = B.substring(0, start);
        System.out.println(start + ":" + str1 + " " + str2 + " " + str3 + " " + str4);
        return str1.equals(str3) && str2.equals(str4);
    }
	
	// solution 2: faster
	public boolean rotateString2(String A, String B) {
        return A.length() == B.length() && (A + A).contains(B);
    }
	
	public static void main(String[] args) {
		String str1 = "abcde";
		String str2 = "cdeab";
		Q796_Rotate_String test = new Q796_Rotate_String();
		System.out.println(test.rotateString(str1, str2));
	}
}
