import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.

Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2.  Elements that don't appear in arr2 should be placed at the end of arr1 in ascending order.

 

Example 1:

Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
Output: [2,2,2,1,4,3,3,9,6,7,19]
 

Constraints:

arr1.length, arr2.length <= 1000
0 <= arr1[i], arr2[i] <= 1000
Each arr2[i] is distinct.
Each arr2[i] is in arr1.
 */
public class Q1122_Relative_Sort_Array {
	public int[] relativeSortArray(int[] arr1, int[] arr2) {
        if (arr1 == null || arr1.length == 0 || arr2 == null || arr2.length == 0)
        {
            return new int[0];
        }
        
        int[] bucket = new int[1001];
        
        for (int num : arr1)
        {
            bucket[num]++;
        }
        
        int index = 0;
        
        for (int num : arr2)
        {
            while (bucket[num]-- > 0)
            {
                arr1[index++] = num;
            }
        }
        
        for (int num = 0; num < 1001; num++)
        {
            while (bucket[num]-- > 0)
            {
                arr1[index++] = num;
            }
        }
        
        return arr1;
    }
}
