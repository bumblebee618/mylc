import java.util.*;

/******
 * 
Find the kth largest element in an unsorted array. 
Note that it is the kth largest element in the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.

 * 
 * */

public class Q215_Kth_Largest_Element_in_an_Array {
	// solution 1: use priority queue, O(nlogk)
	public int findKthLargest(int[] nums, int k) 
    {
        if (nums == null || nums.length == 0)
        {
            return 0;
        }
        else if (k <= 0 || nums.length < k)
        {
            return 0;
        }
        
        Queue<Integer> heap = new PriorityQueue<>();
        
        for (int num : nums)
        {
            heap.offer(num);
            
            if (heap.size() > k)
            {
                heap.poll();
            }
        }
        
        return heap.poll();
    }
	
	
	// Solution 2: using quickselect, time complexity is O(n), worst case is O(n2)
	public int findKthLargest2(int[] nums, int k) 
	{
        if (nums == null || nums.length == 0)
        {
            return 0;
        }
        
        int n = nums.length;
        int left = 0, right = n - 1;
        
        while (left < right)       	      // 注意这里用的是 left < right
        {
            int index = partition(nums, left, right);
            int count = index + 1;
            
            if (k > count)
            {
                left = index + 1;         // 注意这里用的是left = index + 1，有偏移，防止死循环 ！！！
            } 
            else if (k < count)
            {
                right = index - 1;        // 注意这里用的是right = index - 1 ！！！
            } 
            else 
            {
                return nums[index];
            }
        }

        return nums[left];
    }
    
    private int partition(int[] nums, int left, int right)
    {
    	if (left == right)
    	{
    		return left;
    	}
    	
        int index = left;
        int pivot = nums[right];
        int temp = 0;
        
        for (int j = left; j < right; ++j)
        {
            if (nums[j] >= pivot)
            {
                temp = nums[j];
                nums[j] = nums[index];
                nums[index] = temp;
                index++;
            }
        }
        
        temp = nums[index];
        nums[index] = nums[right];
        nums[right] = temp;
        return index;
    }
    
    
    
    
    
    
    
    
    
    /************************* main function ****************************/
    
    public static void main(String[] args){
    	Q215_Kth_Largest_Element_in_an_Array t = new Q215_Kth_Largest_Element_in_an_Array();
//    	int[] nums = {2,1};
    	int[] nums = {3,2,1,5,6,12,4};
    	System.out.println(t.findKthLargest(nums, 2));
    }
}
