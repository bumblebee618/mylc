/********
 * Given two arrays, write a function to compute their intersection.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]
Note:

Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.
Follow up:

What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 * 
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Q350_Intersection_of_Two_Arrays_II {
	// solution 1: using two pointers, time O(nlogn), space O(n)
	public int[] intersect(int[] nums1, int[] nums2) 
	{
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0)
        {
            return new int[0];
        }
        
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int index1 = 0, index2 = 0;
        List<Integer> list = new LinkedList<>();
        
        while (index1 < nums1.length && index2 < nums2.length)
        {
            if (nums1[index1] < nums2[index2])
            {
                index1++;
            } 
            else if (nums1[index1] > nums2[index2])
            {
                index2++;
            } 
            else 
            {
                list.add(nums1[index1]);
                index1++;
                index2++;
            }
        }
        
        int[] result = new int[list.size()];
        int index = 0;
        
        for (int num : list)
        {
            result[index++] = num;
        }
        
        return result;
    }
	
	
	
	// solution 2: using two map, time O(n), space O(n)
	public int[] intersect2(int[] nums1, int[] nums2) {
        if(nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        
        Map<Integer, Integer> map = new HashMap();
        
        for(int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        List<Integer> list = new LinkedList();
        
        for(int num : nums2) {
            if(map.containsKey(num)) {
                list.add(num);
                int count = map.get(num);
                
                if(count == 1) {
                    map.remove(num);    
                } else {
                    map.put(num, count - 1);
                }
            }
        }
        
        int[] ans = new int[list.size()];
        int index = 0;
        
        for(int num : list) {
            ans[index++] = num;           
        }
        
        return ans;
    }
}
