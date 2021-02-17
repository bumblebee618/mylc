import javax.imageio.ImageTypeSpecifier;

/********
 * 
Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
 * 
 * */

public class Q034_Search_for_a_Range {
	// test case:
    // 		nums is empty
    // 		nums contains only one element
    // 		nums do not contain target element
	
	// using binary search
	public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0)
        {
            return new int[] {-1, -1};
        }
        
        int[] result = new int[] {-1, -1};
        result[0] = binarySearch(nums, target, 0, nums.length - 1, true);
        
        if (result[0] == -1)
        {
            return result;
        }
        
        result[1] = binarySearch(nums, target, result[0], nums.length - 1, false);
        return result;
    }
    
    private int binarySearch(int[] nums, int target, int start, int end, boolean findLeftBound)
    {
        while (start + 1 < end)
        {
            int mid = start + (end - start) / 2;
            
            if (findLeftBound)
            {
                if (nums[mid] < target)
                {
                    start = mid;
                }
                else
                {
                    end = mid;
                }
            }
            else
            {
                if (nums[mid] > target)
                {
                    end = mid;
                }
                else
                {
                    start = mid;
                }
            }
        }
        
        if (nums[start] == target || nums[end] == target)
        {
            if (findLeftBound)
            {
                return nums[start] == target ? start : end;
            }
            else
            {
                return nums[end] == target ? end : start;
            }
        }
        else
        {
            return -1;
        }
    }
    
   
    
    
    
    
    
    
    
    /********************************** main function **************************************/
    
    public static void main(String[] args){
    	Q034_Search_for_a_Range test = new Q034_Search_for_a_Range();
    	int target = 4;
    	int[] array = {1, 5};
    	int[] res = test.searchRange(array, target);
    	for(int i = 0; i < 2; ++i)
    		System.out.println(res[i]);
    }
}
