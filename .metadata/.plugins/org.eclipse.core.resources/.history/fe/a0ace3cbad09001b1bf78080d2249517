import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/******
 * 
Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. 
Note that there may be more than one LIS combination, it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?
 * 
 * */

public class Q300_Longest_Increasing_Subsequence {
	/***************************************************************
	 * nlogn方法很巧
	 * 
	 * 
	 ***************************************************************/
	// solution 1: using binary, time O(nlogn)
	public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0)
        {
            return 0;
        }
        
        int totalLen = 0;

        for (int element : nums)
        {
            if (totalLen == 0 || element > nums[totalLen - 1])
            {
                nums[totalLen++] = element;
            } 
            else 
            {
                int index = findPos(nums, 0, totalLen, element);
                nums[index] = element;
            }
        }

        return totalLen;
    }
    
    private int findPos(int[] nums, int left, int right, int target)
    {
        while (left+1 < right)
        {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] < target)
            {
                left = mid;
            }
            else
            {
                right = mid;
            }
        }
        
        return nums[left] >= target ? left : right;
    }

    
    
    
	
	// solution 2: using ArrayList, time complexity O(nlogn), space O(n)
	private int[] array;
    private int arrayLen = 0;
    
    public int lengthOfLIS_2(int[] nums) {
        if (nums == null || nums.length == 0)
        {
            return 0;
        }
        
        array = new int[nums.length];
        
        for (int num : nums)
        {
            updateArray(num);
        }
        
        return arrayLen;
    }
    
    private void updateArray(int num)
    {
        if (arrayLen == 0 || num > array[arrayLen - 1])
        {
            array[arrayLen++] = num;
        }
        else
        {
            int pos = findLeftBound(num);
            array[pos] = num;
        }
    }
    
    private int findLeftBound(int target)
    {
        if (arrayLen == 0)
        {
            return 0;
        }
        
        int left = 0, right = arrayLen - 1;
        
        while (left + 1 < right)
        {
            int mid = left + (right - left) / 2;
            
            if (target > array[mid])
            {
                left = mid;   
            }
            else
            {
                right = mid;
            }
        }
        
        if (array[left] >= target)
        {
            return left;
        }
        else
        {
            return right;
        }
    }

	
	
	// solution 3: using ArrayList, time complexity O(nlogn), space O(n)
	public int lengthOfLIS_3(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        
        List<Integer> list = new ArrayList<>();
        
        for(int num : nums){
            updateList(list, num);
        }
        
        return list.size();
    }
    
	private void updateList(List<Integer> list, int target){
        if(list.size() == 0 || target > list.get(list.size() - 1)){    
            list.add(target);
        } else {
            int pos = findPos(list, target);
            list.set(pos, target);    // 注意是set
        }
    }
        
    private int findPos(List<Integer> list, int target){
    	if(list.size() == 0){
    		return 0;
    	}
    	
        int left = 0, right = list.size() - 1;
        
        while(left + 1 < right){
            int mid = left + (right - left) / 2;
            int midNum = list.get(mid); 
            
            if(midNum < target){
                left = mid;
            } else {
                right = mid;
            }
        }

        if (list.get(left) >= target){
            return left;
        } else {
            return right;
        } 
    }
    
    
    

    
    // solution 4: using DP, time complexity O(n^2), space O(n)
    public int lengthOfLIS_4(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        
        int[] dp = new int[nums.length];
        int maxLen = 1;
        int n = nums.length;
        
        for(int i = 1; i < n; ++i){
        	dp[i] = 1;
        	
            for(int j = 0; j < i; ++j){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1); 
                    maxLen = Math.max(maxLen, dp[i]);
                }
            }
        }
        
        return maxLen;
    }
}
