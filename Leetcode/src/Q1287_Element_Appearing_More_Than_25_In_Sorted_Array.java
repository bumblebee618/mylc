/***
 * 
 * @author jackie
 * Given an integer array sorted in non-decreasing order, there is exactly one integer in the array that occurs more than 25% of the time.

Return that integer.

 

Example 1:

Input: arr = [1,2,2,6,6,6,6,7,10]
Output: 6
 

Constraints:

1 <= arr.length <= 10^4
0 <= arr[i] <= 10^5
 *
 */
public class Q1287_Element_Appearing_More_Than_25_In_Sorted_Array {
	public int findSpecialInteger(int[] arr) {
        if (arr == null || arr.length == 0)
        {
            return -1;
        }
        
        int size = arr.length;
        int target = size/4;
        
        for (int i = 0; i+target < size; i++)
        {
            if (arr[i] == arr[i+target])
            {
                return arr[i];
            }
        }
        
        return -1;
    }
}
