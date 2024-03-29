/******
 * 
Divide two integers without using multiplication, division and mod operator.
If it is overflow, return MAX_INT.

 * 
 * */

public class Q029_Divide_Two_Integers 
{	
	// solution 1: binary search
	public int divide(int dividend, int divisor) 
	{
        if (dividend == Integer.MIN_VALUE && divisor == -1)
        {
            return Integer.MAX_VALUE;
        }
        else if (divisor == 0)
        {
            return Integer.MAX_VALUE;
        }
        
        int flag = (long) dividend * divisor > 0 ? 1 : -1;
        
        // 注意(long) 在Math.abs里，防止溢出，例如test case [-2147483648, 2]
        long divd = Math.abs((long) dividend);  
        long divs = Math.abs((long) divisor);
        long result = binarySearch(divd, divs) * flag;
        return result > Integer.MAX_VALUE || result < Integer.MIN_VALUE ? Integer.MAX_VALUE : (int) result;   
    }
    
    private long binarySearch(long divd, long divs)
    {
        long left = 1, right = divd;
        
        // 这里使用left < right，简洁
        while (left < right)
        {
            long mid = left + (right - left) / 2;
            long result = mid * divs;
            
            if (result > divd)
            {
                right = mid - 1;
            }
            else if (result < divd)
            {
                left = mid + 1;
            }
            else
            {
                return mid;
            }
        }
        
        return left * divs > divd ? left - 1 : left;
    }

	
    
	
	// solution 2:
	public int divide2(int dividend, int divisor) {
		if (dividend == Integer.MIN_VALUE && divisor == -1){
			return Integer.MAX_VALUE;
		}

		long divd = Math.abs((long) dividend);
		long divs = Math.abs((long) divisor);
		int ret = 0;
		
		while (divd >= divs) {
			int counter = 0;
			
			while (divd >= (divs << counter)) { // keep multiply by 2 until divs > divd
				counter++;
			}
			
			counter--;                // rollback counter so that (divs<<counter) <= divd
			ret += 1 << counter;      // quotient
			divd -= divs << counter;
		}

		if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)){
			return ret;
		} else{
			return -ret;
		}
	}
	
	
	
	
	
	
	
	
	/****************************** main function *************************************/
	
	public static void main(String[] args) {
		Q029_Divide_Two_Integers t = new Q029_Divide_Two_Integers();
		System.out.println(t.divide(-2147483648, -1));
//		System.out.println(t.divide(-1010369383, -2147483648));
	}
}
