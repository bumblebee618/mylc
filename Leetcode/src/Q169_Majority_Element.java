import java.util.Arrays;
/********
 * 
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
You may assume that the array is non-empty and the majority element always exist in the array.
 * 
 * */

public class Q169_Majority_Element {
	// test case:
    // 		nums is empty
    // 		does not contain major element
	
	// solution 1: time complexity is O(n);
	public int majorityElement(int[] nums) 
    {
        if (nums == null || nums.length == 0)
        {
            return 0;
        }
        
        int len = nums.length;
        int recordNum = nums[0];
        int count = 0;
        
        for (int i = 1; i < len; i++)
        {
            if (recordNum == nums[i])
            {
                count++;
            }
            else if (count == 0)
            {
                recordNum = nums[i];
                count = 1;
            }
            else    // count--放在 count == 0之后，防止第一次进入的时候不会出错！！！
            {
                count--;
            }
        }
        
        return recordNum;
    }
	
	
	
	
	// solution 2: using sort, time complexity is O(nlogn)
	public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }
    
	
	
	
	
	
	
	
	
	/************************** main function ********************************/
	
    public static void main(String[] args){
    	Q169_Majority_Element t = new Q169_Majority_Element();
    	int[] array = {3,6,1,2,7,3,3,3,3};
    	System.out.println(t.majorityElement(array));
    }
}
