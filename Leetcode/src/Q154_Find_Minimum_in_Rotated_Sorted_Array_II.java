
public class Q154_Find_Minimum_in_Rotated_Sorted_Array_II {
	/*********************************************************
	 * rotated array 类模版： 
	 * 		（1）分类讨论，已经排序的在mid的左边或右边
	 * 		（2）最小值必在未排序的那一边
	 * 
	 *********************************************************/
	// using binary search
	public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int left = 0, right = nums.length-1;
        int min = Integer.MAX_VALUE;
        
        while (left+1 < right) {
            int mid = left + (right-left)/2;
            min = Math.min(min, Math.min(nums[mid], Math.min(nums[left], nums[right])));
            
            if (nums[mid] > nums[left] || nums[mid] > nums[right]) {         // 这里没等号 ！！！
                left = mid;
            } else if (nums[mid] < nums[left] || nums[mid] < nums[right]) {  // 这里没等号 ！！！
                right = mid;
            } else {
                right--;
            }
        }
        
        return Math.min(min, Math.min(nums[left], nums[right]));
    }
	
	
	
	
	
	
	
	/***************************** main *****************************/
	
	public static void main(String[] args)
	{
		Q154_Find_Minimum_in_Rotated_Sorted_Array_II t = new Q154_Find_Minimum_in_Rotated_Sorted_Array_II();
		int[] nums = {1,3};
		System.out.println(t.findMin(nums));
	}
}
