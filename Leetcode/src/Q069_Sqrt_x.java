
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

	public int mySqrt(int x) 
	{
		if (x <= 0) 
		{
			return 0;
		} 
		else if (x <= 3) 
		{
			return 1;
		}

		long left = 1, right = x;

		while (left + 1 < right) 
		{
			long mid = left + (right - left) / 2;
			long product = mid * mid;

			if (product < x) 
			{
				left = mid;
			} 
			else if (product > x) 
			{
				right = mid;
			} 
			else 
			{
				return (int) mid;
			}
		}

		if (left * left <= x) 
		{
			return (int) left;
		} 
		else 
		{
			return (int) right;
		}
	}

	/********************************
	 * main function
	 *******************************/

	public static void main(String[] args) {
		Q069_Sqrt_x t = new Q069_Sqrt_x();
		System.out.println(t.mySqrt(2147395599));
	}
}
