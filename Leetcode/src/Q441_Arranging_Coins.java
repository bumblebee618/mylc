/***
 * You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.

Given n, find the total number of full staircase rows that can be formed.

n is a non-negative integer and fits within the range of a 32-bit signed integer.

Example 1:

n = 5

The coins can form the following rows:
¤
¤ ¤
¤ ¤

Because the 3rd row is incomplete, we return 2.
Example 2:

n = 8

The coins can form the following rows:
¤
¤ ¤
¤ ¤ ¤
¤ ¤

Because the 4th row is incomplete, we return 3.
 * @author jackie
 *
 */
public class Q441_Arranging_Coins {
	// time complexity is log(n)
	public int arrangeCoins(int n)
	{
		if (n <= 0)
		{
			return n;
		}
		else if (n == 1)
		{
			return 1;
		}
		
		long left = 1, right = n;
		long p = 0;
		long sum1 = 0, sum2 = 0;
		
		while (!(sum1 < n && sum2 > n))
		{
			p = left + (right - left) / 2;
			sum1 = getSum(p);
			sum2 = getSum(p+1);
			
			if (sum1 == n)
			{
				return (int) p;
			}
			else if (left > right)
			{
				return 0;
			}
			else if (sum1 > n)
			{
				right = p-1;
			}
			else 
			{
				left = p+1;
			}
		}
		
		return (int) p;
	}
	
	private long getSum(long p)
	{
		return (1 + p) * p / 2;
	}
	
	// solution 2, time complexity is O(sqrt(n))
	public int arrangeCoins2(int n)
	{
		if (n <= 0)
		{
			return n;
		}
		else if (n == 1)
		{
			return 1;
		}
		
		int i = 1;
		
		while (n >= i)
		{
			n -= i;
			i++;
		}
		
		return i-1;
	}
	
	public static void main(String[] args)
	{
		Q441_Arranging_Coins test = new Q441_Arranging_Coins();
		int result = test.arrangeCoins(1804289383);
		System.out.print(result);
	}
}
