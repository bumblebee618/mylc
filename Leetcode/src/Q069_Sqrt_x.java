
/*********
 * 
 * Implement int sqrt(int row). Compute and return the square root of row.
 * 
 */

public class Q069_Sqrt_x {
	/************************************************
	 * 二分法，查找一个mid,使得mid * mid 大于或等于x
	 * 
	 ************************************************/

	public int mySqrt(int x) {
        if (x <= 0) {
            return 0;
        }
        
        long left = 1, right = x;
        
        while (left+1 < right) {
            long mid = left + (right - left) / 2;
            long product = mid * mid;
            
            if (product > (long) x) {
                right = mid;
            } else if (product < (long) x) {
                left = mid;
            } else {
                return (int) mid;
            }
        }
        
        return left * left <= x ? (int) left : (int) right;
    }

	

	/********************************
	 * main function
	 *******************************/

	public static void main(String[] args) {
		Q069_Sqrt_x t = new Q069_Sqrt_x();
		System.out.println(t.mySqrt(2147395599));
	}
}
