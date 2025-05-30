import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

/*******************************************
Given a list of non-negative integers nums, arrange them such that they form the largest number.

Note: The result may be very large, so you need to return a string instead of an integer.

 

Example 1:

Input: nums = [10,2]
Output: "210"
Example 2:

Input: nums = [3,30,34,5,9]
Output: "9534330"
Example 3:

Input: nums = [1]
Output: "1"
Example 4:

Input: nums = [10]
Output: "10"
 

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 109              
/*******************************************/

public class Q179_Largest_Number 
{   // 注意test case： 0，0，0，0
	public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        
        String[] strs = new String[nums.length];
        
        for (int i = 0; i < nums.length; i++) {
            strs[i] = Integer.toString(nums[i]);
        }
        
        Arrays.sort(strs, (a, b) -> b.concat(a).compareTo(a.concat(b)));
        StringBuilder builder = new StringBuilder();
        
        for (String str : strs) {
            builder.append(str);
        }
        
        String result = builder.toString();        
        int index = 0;
        
        // remove front 0, for example "000123" -> "123"
        while (index < result.length() && result.charAt(index) == '0') {
            index++;
        }
        
        // test case [0, 0]
        return index == result.length() ? "0" : result.substring(index);
    }
	    
	
	
	
	
	/**************************************************************/
    // by Jackie using priorityqueue
	public String largestNumber2(int[] nums) {
        if(nums == null || nums.length == 0){
            return new String();
        }
        
        Queue<String> heap = new PriorityQueue<String>(1, new Comparator<String>(){
            public int compare(String left, String right){
                String str1 = left + right;
                String str2 = right + left;
                return str2.compareTo(str1);
            }
        });
        
        StringBuilder builder= new StringBuilder();
        boolean zeroFlag = true;
        
        for(int num : nums){
            if(num != 0){
                zeroFlag = false;
            }
            heap.offer(Integer.toString(num));
        }
        
        if(zeroFlag == true){  // 需要作是否全是0的判断 ！！！
            return "0";
        }
        
        while(!heap.isEmpty()){
            builder.append(heap.poll());
        }

        return builder.toString();
    }
	
	
	
	public static void main(String[] args){
		Q179_Largest_Number l = new Q179_Largest_Number();
		int[] nums = {0, 0, 0};
//		int[] nums = {999999998,999999997,999999999};
		System.out.println(l.largestNumber(nums));
		System.out.println(l.largestNumber2(nums));
		
		System.out.println(Objects.hashCode(1212));
	}
}
