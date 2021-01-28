import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Given a sorted array, two integers k and row, find the k closest elements to row in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.

Example 1:
Input: [1,2,3,4,5], k=4, row=3
Output: [1,2,3,4]
Example 2:
Input: [1,2,3,4,5], k=4, row=-1
Output: [1,2,3,4]
Note:
The value k is positive and will always be smaller than the length of the sorted array.
Length of the given array is positive and will not exceed 104
Absolute value of elements in the array and row will not exceed 104
 */
public class Q658_Find_K_Closest_Elements {
	// Time complexity O(logn)
	public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<>();
        
        if (arr == null || arr.length == 0 || k <= 0 || k > arr.length)
        {
            return result;            
        }
        
        int left = 0, right = arr.length-k;
        
        while (left < right)
        {
            int mid = left + (right - left) / 2;
            
            if (x - arr[mid] > arr[mid + k] - x)
            {
                left = mid + 1;
            }
            else
            {
                 right = mid;
            }
        }
        
        for (int i = 0; i < k; i++)
        {
            result.add(arr[left+i]);
        }
        
        return result;
    }

	
	
	
    
	
	// Time complexity O(n-k)
    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
        List<Integer> result = new LinkedList<>();
        
        if (arr == null || k <= 0 || arr.length < k)
        {
            return result;
        }
        
        int left = 0, right = arr.length-1;
        int deleteCount = 0;
        
        while (left < right && deleteCount < arr.length-k)
        {
            int dist1 = Math.abs(arr[left]-x);
            int dist2 = Math.abs(arr[right]-x);
            
            if (dist1 > dist2)
            {
                left++;
            }
            else
            {
                right--;
            }
            
            deleteCount++;
        }
        
        for (int i = left; i <= right; i++)
        {
            result.add(arr[i]);
        }
        
        return result;
    }
}
