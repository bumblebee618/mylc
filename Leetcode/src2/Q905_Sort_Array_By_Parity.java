/***
Given an array A of non-negative integers, return an array consisting of all the even elements of A, followed by all the odd elements of A.

You may return any answer array that satisfies this condition.

 

Example 1:

Input: [3,1,2,4]
Output: [2,4,3,1]
The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
 

Note:

1 <= A.length <= 5000
0 <= A[i] <= 5000
***/

public class Q905_Sort_Array_By_Parity {
	
	// Time complexity O(n), space complexity is o(1)s
	public int[] sortArrayByParity(int[] A) {
        if (A == null || A.length <= 1)
        {
            return A;
        }
        
        int even = 0, odd = 0;
        
        while (even < A.length)
        {
            while (even < A.length && A[even] % 2 != 0)
            {
                even++;
            }
            
            if (even < A.length)
            {
                swap(A, odd++, even++);
            }
        }
        
        return A;
    }
    
    private void swap(int[] nums, int a, int b)
    {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
