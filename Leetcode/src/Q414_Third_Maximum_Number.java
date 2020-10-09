/*******
 * 
Given a non-empty array of integers, return the third maximum number in this array. 
If it does not exist, return the maximum number. The time complexity must be in O(n).

Example 1:
Input: [3, 2, 1]

Output: 1

Explanation: The third maximum is 1.
Example 2:
Input: [1, 2]

Output: 2

Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
Example 3:
Input: [2, 2, 3, 1]

Output: 1

Explanation: Note that the third maximum here means the third maximum distinct number.
Both numbers with value 2 are both considered as second maximum.

 * 
 * */

public class Q414_Third_Maximum_Number {
	// naive method is using sort, and time is O(nlogn)
	
	// solution 1: 分类讨论
	public int thirdMax(int[] nums) {
        if (nums == null || nums.length == 0) 
        {
            return -1;
        }

        // result[0] is max, result[2] is min
        Integer[] result = new Integer[3];

        for (int num : nums) 
        {
            if (result[0] == null) 
            {
                result[0] = num;
            } 
            else if (num >= result[0]) 
            {
                if (num == result[0]) 
                {
                    continue;
                }

                result[2] = result[1];
                result[1] = result[0];
                result[0] = num;
            } 
            else if (result[1] == null) 
            {
                result[1] = num;
            } 
            else if (num >= result[1]) 
            {
                if (num == result[1]) 
                {
                    continue;
                }

                result[2] = result[1];
                result[1] = num;
            } 
            else if (result[2] == null || num >= result[2]) 
            {
                result[2] = num;
            } 
        }

        return result[2] == null ? result[0] : result[2];
    }
	
	
	
	
	
	// this method: time is O(n), space is O(1)
	public int thirdMax2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        long[] max = new long[3];          // long型 ！！！
        max[0] = max[1] = max[2] = Long.MIN_VALUE;
        
        for (int num : nums) {
            if ((long) num >= max[0]) {    // 这里有等号 ！！！
                if ((long) num > max[0]) {
                    max[2] = max[1];
                    max[1] = max[0];
                    max[0] = (long) num;
                }
            } else if ((long) num >= max[1]) {
                if ((long) num > max[1]) {
                    max[2] = max[1];
                    max[1] = (long) num;
                }
            } else if ((long) num >= max[2]) {
                max[2] = (long) num;
            }
        }
        
        if (max[2] == Long.MIN_VALUE) {
            return (int) max[0];
        } else {
            return (int) max[2];
        }
    }
}
