/***
 * 
 * @author jackie
 *
 *Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, also in sorted non-decreasing order.

 

Example 1:

Input: [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Example 2:

Input: [-7,-3,2,3,11]
Output: [4,9,9,49,121]
 

Note:

1 <= A.length <= 10000
-10000 <= A[i] <= 10000
A is sorted in non-decreasing order.
 */
public class Q977_Squares_of_a_Sorted_Array {
	public int[] sortedSquares(int[] nums) {
        if (nums == null || nums.length == 0)
        {
            return nums;
        }
        
        int size = nums.length;
        int[] result = new int[size];
        int posStart = 0;
        
        while (posStart < size && nums[posStart] < 0)
        {
            posStart++;
        }

        int negStart = posStart-1;
        int num1 = 0, num2 = 0;
        
        for (int i = 0; i < size; i++)
        {
            num1 = posStart < size ? nums[posStart]*nums[posStart] : Integer.MAX_VALUE;
            num2 = negStart >= 0 ? nums[negStart]*nums[negStart] : Integer.MAX_VALUE;
   
            if (num1 < num2)
            {
                result[i] = num1;
                posStart++;
            }
            else
            {
                result[i] = num2;
                negStart--;
            }
        }
        
        return result;
    }

}
