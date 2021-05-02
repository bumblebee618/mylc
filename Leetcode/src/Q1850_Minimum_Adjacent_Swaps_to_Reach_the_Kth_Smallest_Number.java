/***
 * 
 * @author jackie
 * 
 * You are given a string num, representing a large integer, and an integer k.

We call some integer wonderful if it is a permutation of the digits in num and is greater in value than num. There can be many wonderful integers. However, we only care about the smallest-valued ones.

For example, when num = "5489355142":
The 1st smallest wonderful integer is "5489355214".
The 2nd smallest wonderful integer is "5489355241".
The 3rd smallest wonderful integer is "5489355412".
The 4th smallest wonderful integer is "5489355421".
Return the minimum number of adjacent digit swaps that needs to be applied to num to reach the kth smallest wonderful integer.

The tests are generated in such a way that kth smallest wonderful integer exists.

 

Example 1:

Input: num = "5489355142", k = 4
Output: 2
Explanation: The 4th smallest wonderful number is "5489355421". To get this number:
- Swap index 7 with index 8: "5489355142" -> "5489355412"
- Swap index 8 with index 9: "5489355412" -> "5489355421"
Example 2:

Input: num = "11112", k = 4
Output: 4
Explanation: The 4th smallest wonderful number is "21111". To get this number:
- Swap index 3 with index 4: "11112" -> "11121"
- Swap index 2 with index 3: "11121" -> "11211"
- Swap index 1 with index 2: "11211" -> "12111"
- Swap index 0 with index 1: "12111" -> "21111"
Example 3:

Input: num = "00123", k = 1
Output: 1
Explanation: The 1st smallest wonderful number is "00132". To get this number:
- Swap index 3 with index 4: "00123" -> "00132"
 

Constraints:

2 <= num.length <= 1000
1 <= k <= 1000
num only consists of digits.
 */
public class Q1850_Minimum_Adjacent_Swaps_to_Reach_the_Kth_Smallest_Number 
{
	public int getMinSwaps(String num, int k)
	{
		char[] originDigits = num.toCharArray();
		char[] digits = num.toCharArray();
		
		for (int i = 0; i < k; i++)
		{
			nextPermutation(digits);
		}
		
		int start = 0, size = digits.length;
		int step = 0;
		
		while (start < size)
		{
			if (digits[start] != originDigits[start])
			{
				step += findTarget(digits, originDigits[start], start);
			}
			
			start++;
		}
		
		return step;
	}
	
	private int findTarget(char[] digits, char target, int start)
	{
		int end = start;
		
		while (end < digits.length && digits[end] != target)
		{
			end++;
		}
		
		for (int i = end; i-1 >= start; i--)
		{
			char tmp = digits[i];
			digits[i] = digits[i-1];
			digits[i-1] = tmp;
		}
		
		return end-start;
	}
	
	private void nextPermutation(char[] nums) 
    {
        if (nums == null || nums.length == 0)
        {
            return;
        }
        
        int size = nums.length;
        int index1 = size - 2;
        
        while (index1 >= 0 && nums[index1] >= nums[index1+1])
        {
            index1--;
        }
        
        if (index1 >= 0)
        {
            int index2 = size-1;
            
            while (nums[index2] <= nums[index1])
            {
                index2--;
            }
            
            char tmp = nums[index1];
            nums[index1] = nums[index2];
            nums[index2] = tmp;
        }
        
        reverse(nums, index1+1, size-1);
    }
    
    private void reverse(char[] nums, int left, int right)
    {
        while (left < right)
        {
            char tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
            left++;
            right--;
        }
    }
}
