/********
 * 
A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with fewer than two elements is trivially a wiggle sequence.

For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and negative. In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its first two differences are positive and the second because its last difference is zero.

Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence. A subsequence is obtained by deleting some number of elements (eventually, also zero) from the original sequence, leaving the remaining elements in their original order.

Examples:
	Input: [1,7,4,9,2,5]
	Output: 6
	The entire sequence is a wiggle sequence.

	Input: [1,17,5,10,13,15,10,5,16,8]
	Output: 7
	There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].

	Input: [1,2,3,4,5,6,7,8,9]
	Output: 2

Follow up:
	Can you do it in O(n) time?

 * 
 * */

public class Q376_Wiggle_Subsequence {
	// solution 1: using DP, time complexity O(n^2)
	public int wiggleMaxLength(int[] nums) 
    {
        if (nums == null || nums.length == 0)
        {
            return 0;
        }
        else if (nums.length == 1)
        {
            return 1;
        }
        
        int size = nums.length;
        int[] increase = new int[size];
        int[] decrease = new int[size];
        int result = 0;
        
        for (int i = 0; i < size; i++)
        {
            increase[i] = decrease[i] = 1;
            
            for (int j = 0; j < i; j++)
            {
                if (nums[i] > nums[j])
                {
                    increase[i] = Math.max(increase[i], decrease[j]+1);
                }
                else if (nums[i] < nums[j])
                {
                    decrease[i] = Math.max(decrease[i], increase[j]+1);
                }
            }
            
            result = Math.max(result, Math.max(increase[i], decrease[i]));
        }
        
        return result;
    }


	
	
	// solution 2: using DP, time complexity O(n), space O(n)
	public int wiggleMaxLength2(int[] nums) 
	{
		if (nums == null || nums.length == 0) 
		{
			return 0;
		} 
		else if (nums.length == 1) 
		{
			return 1;
		}

		int len = nums.length;
		int up = 1;
		int down = 1;

		for (int i = 1; i < len; i++) 
		{
			int flag = nums[i] - nums[i-1];

			if (flag > 0) 
			{
				up = down + 1;
			} 
			else if (flag < 0) 
			{
				down = up + 1;
			}
		}

		return Math.max(up, down);
	}

	
	
	// solution 3: using DP, time O(n), space O(n)
	public int wiggleMaxLength3(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		} else if (nums.length == 1) {
			return 1;
		}

		int len = nums.length;
		int[] up = new int[len];
		int[] down = new int[len];
		up[0] = down[0] = 1;

		for (int i = 1; i < len; i++) {
			int flag = nums[i] - nums[i - 1];

			if (flag > 0) {
				up[i] = down[i - 1] + 1;
				down[i] = down[i - 1];
			} else if (flag < 0) {
				up[i] = up[i - 1];
				down[i] = up[i - 1] + 1;
			} else {
				up[i] = up[i - 1];
				down[i] = down[i - 1];
			}
		}

		return Math.max(up[len - 1], down[len - 1]);
	}

	
	
	
	
	
	/****** main function *******/
	public static void main(String[] args) {
		Q376_Wiggle_Subsequence t = new Q376_Wiggle_Subsequence();
		int[] nums = { 1, 17, 5, 10, 13, 15, 10, 5, 16, 8 };
		System.out.println(t.wiggleMaxLength(nums));
		System.out.println(t.wiggleMaxLength2(nums));
	}
}
