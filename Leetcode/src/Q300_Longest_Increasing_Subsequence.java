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
	
	// solution 1: time O(nlogn), space O(n)
	public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int[] queue = new int[nums.length];
        int maxLen = 0;
        
        for (int num : nums) {
            int index = Arrays.binarySearch(queue, 0, maxLen, num);
            
            // return first num which is equal to or larger than target num
            // 1-indexed when index < 0 (cannot find any num in this array)
            index = index < 0 ? -index-1 : index;
            queue[index] = num;
            
            if (maxLen == index) {
                maxLen++;
            }
        }
        
        return maxLen;
    }
	
	
	// solution 2:
	public int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        List<Integer> list = new ArrayList<>();
        
        for (int i = 0; i < nums.length; i++) {            
            if (list.size() == 0 || nums[i] > list.get(list.size()-1)) {
                list.add(nums[i]);
            } else {
                int pos = findPos2(list, nums[i]);
                list.set(pos, nums[i]);
            }
        }
        
        return list.size();
    }
    
    private int findPos2(List<Integer> list, int target) {
        if (target <= list.get(0)) {
            return 0;
        }
        
        int left = 0, right = list.size()-1;
        
        while (left+1 < right) {
            int mid = left + (right-left)/2;
            
            if (list.get(mid) < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        return list.get(left) >= target ? left : right;
    }
	
    
	// solution 3: using customized binary search, time O(nlogn)
	public int lengthOfLIS3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int size = nums.length;
        int[] queue = new int[size];
        int curTail = -1;
        
        for (int num : nums) {
            if (curTail == -1 || num > queue[curTail]) {
                queue[++curTail] = num;
            } else {
                int pos = findPos3(queue, curTail, num);
                queue[pos] = num;
            }
        }
        
        return curTail+1;
    }

	// find the position of first num which is equal to or larger than target num
    private int findPos3(int[] queue, int curTail, int target) {
        int left = 0, right = curTail;
        
        while (left+1 < right) {
            int mid = left + (right-left)/2;
            
            if (queue[mid] < target) {
                left = mid;
            } else {   
                right = mid;
            }
        }
        
        return queue[left] >= target ? left : right;
    }


    
    
    
    
    
    
    
    
    /************************************** main **************************************/
    
    public static void main(String[] args)
    {
    	Q300_Longest_Increasing_Subsequence test = new Q300_Longest_Increasing_Subsequence();
    	int[] array1 = {1,2,3,4,5,6,7,8};
    	int[] array2 = {1,2,3,4,6,7,8};
    	
    	System.out.println(Arrays.binarySearch(array1, 5));
    	System.out.println(Arrays.binarySearch(array2, 5));
    	System.out.println(Arrays.binarySearch(array1, 0));
    	System.out.println(Arrays.binarySearch(array1, 9));
    }
    
    
	
	// solution 2: using ArrayList, time complexity O(nlogn), space O(n)
	private int[] array;
    private int arrayLen = 0;
    
    public int lengthOfLIS_2(int[] nums) 
    {
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
