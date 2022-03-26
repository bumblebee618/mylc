/***
 * 
 * Given an integer n, add a dot (".") as the thousands separator and return it in string format.

 

Example 1:

Input: n = 987
Output: "987"
Example 2:

Input: n = 1234
Output: "1.234"
 

Constraints:

0 <= n <= 231 - 1
 *
 */

public class Q1556_Thousand_Separator {
	public String thousandSeparator(int n) {
        if (n < 0) {
            return "";
        } else if (n == 0) {
            return "0";
        }
        
        StringBuilder builder = new StringBuilder();
        int count = 0;
        
        while (n > 0) {
            char digit = (char) (n % 10 + '0');
            builder.insert(0, digit);
            n /= 10;
            count++;
            
            if (count % 3 == 0 && n > 0) {
                builder.insert(0, ".");
            }
        }

        return builder.toString();
    }
}
