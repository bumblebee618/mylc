import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.

An integer a is closer to x than an integer b if:

|a - x| < |b - x|, or
|a - x| == |b - x| and a < b
 

Example 1:

Input: arr = [1,2,3,4,5], k = 4, x = 3
Output: [1,2,3,4]
Example 2:

Input: arr = [1,2,3,4,5], k = 4, x = -1
Output: [1,2,3,4]
 

Constraints:

1 <= k <= arr.length
1 <= arr.length <= 104
arr is sorted in ascending order.
-104 <= arr[i], x <= 104
 */
public class Q658_Find_K_Closest_Elements {
	// Solution 1: time complexity O(logn)
	public List<Integer> findClosestElements(int[] arr, int k, int x) 
	{
        List<Integer> result = new ArrayList<>();
        
        if (arr == null || arr.length == 0 || k <= 0 || k > arr.length)
        {
            return result;            
        }
        
        int left = 0, right = arr.length-k;
        
        while (left < right)
        {
            int mid = left + (right - left) / 2;
            
            if (x - arr[mid] > arr[mid + k] - x)    // 不可以用绝对值
            {
                left = mid + 1;
            }
            else
            {
                 right = mid;  // 尽量往左靠，因为答案需要满足|a - x| == |b - x| and a < b
            }
        }
        
        for (int i = 0; i < k; i++)
        {
            result.add(arr[left+i]);
        }
        
        return result;
    }

	
	// Solution 2: binary search + two pointer, time O(logn + k + klogk)
	public List<Integer> findClosestElements2(int[] arr, int k, int x) 
    {
        List<Integer> result = new ArrayList<>();
        
        if (arr == null || arr.length == 0 || k <= 0 || k > arr.length)
        {
            return result;            
        }
        
        int right = Arrays.binarySearch(arr, x);
        right = right < 0 ? -right-1 : right;
        int left = right-1;
            
        for (int i = 0; i < k; i++)
        {
            int elem = 0;
            
            if (left >= 0 && right < arr.length)
            {
                elem = (Math.abs(x-arr[left]) <= Math.abs(x-arr[right])) ? arr[left--] : arr[right++];
            }
            else if (left >= 0)
            {
                elem = arr[left--];
            }
            else
            {
                elem = arr[right++];
            }
            
            result.add(elem);
        }
        
        Collections.sort(result);
        return result;
    }
	
    
	
	// Solution 3: time complexity O(n-k)
    public List<Integer> findClosestElements3(int[] arr, int k, int x) {
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
