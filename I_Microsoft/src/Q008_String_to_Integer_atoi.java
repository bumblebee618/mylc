/*****
 * Implement atoi to convert a string to an integer.
 * Hint: Carefully consider all possible input cases. 
 * If you want a challenge, please do not see below and ask yourself what are the possible input cases.
 * 
 * Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). 
 * You are responsible to gather all the input requirements up front.
 * 
 * */

public class Q008_String_to_Integer_atoi {
	/****************************************************
	 * 遇到非数字的字符串，只返回但前有效的数字
	 * 
	 ****************************************************/
	public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        
        str = str.trim();
        long sum = 0;
        int signFlag = 1;
        boolean hasSign = false, hasDigit = false;
        
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
                sum = sum * 10 + (c - '0');
                
                if (sum >= Integer.MAX_VALUE) {   // 防止越界
                    break;
                }
            } else if (c == '+' || c == '-') {
                if (!hasSign && !hasDigit) {
                    signFlag = (c == '-') ? -1 : 1; 
                    hasSign = true;
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        
        sum *= signFlag;
        
        if (sum >= Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (sum <= Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else {
            return (int) sum;
        }
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**************************** main function ****************************/
	
	public static void main(String[] args){
		Q008_String_to_Integer_atoi t = new Q008_String_to_Integer_atoi();
		System.out.println(t.myAtoi("9223372036854775809"));   // test case: "+1", "-1", "  -0012a42", "2147483648", "-2147483648", "      -11919730356x"
	}
}
