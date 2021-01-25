import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/***
 * 
 * @author jackie
 *
 *Given three integer arrays arr1, arr2 and arr3 sorted in strictly increasing order, return a sorted array of only the integers that appeared in all three arrays.

 

Example 1:

Input: arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]
Output: [1,5]
Explanation: Only 1 and 5 appeared in the three arrays.
 

Constraints:

1 <= arr1.length, arr2.length, arr3.length <= 1000
1 <= arr1[i], arr2[i], arr3[i] <= 2000
 */
public class Q1213_Intersection_of_Three_Sorted_Arrays {
	public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) 
	{
        List<Integer> result = new LinkedList<>();
        
        if (arr1 == null || arr1.length == 0 || arr2 == null || arr2.length == 0 || arr3 == null || arr3.length == 0)
        {
            return result;
        }
        
        int index1 = 0, index2 = 0;
        int len1 = arr1.length, len2 = arr2.length;
        Set<Integer> set = new HashSet<>();
        
        while (index1 < len1 && index2 < len2)
        {
            if (arr1[index1] < arr2[index2])
            {
                index1++;
            }
            else if (arr1[index1] > arr2[index2])
            {
                index2++;
            }
            else
            {
                set.add(arr1[index1]);
                index1++;
                index2++;
            }
        }
        
        for (int num : arr3)
        {
            if (set.contains(num))
            {
                result.add(num);
            }
        }
        
        return result;
    }
}
