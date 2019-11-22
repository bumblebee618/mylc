import java.util.*;
/*******
 * 
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. 
The algorithm should run in linear time and in O(1) space. 

 * 
 * */

public class Q229_Majority_Element_II {
	/*********************************************************************************************************
	 * The basic idea is based on Moore's Voting Algorithm, we need two candidates with top 2 frequency. 
	 * If meeting different number from the candidate, then decrease 1 from its count, 
	 * or increase 1 on the opposite condition. Once count equals 0, then switch the candidate to the current number.
	 * The trick is that we need to count again for the two candidates after the first loop. 
	 * Finally, output the numbers appearing more than n/3 times.
	 *********************************************************************************************************/
	// using Moore's Voting Algorithm
	public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new LinkedList<>();
        
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        int majorNum1 = nums[0];
        int majorNum2 = nums[0];
        int count1 = 0;
        int count2 = 0;
        int len = nums.length;
        
        for (int num : nums) {
            if (num == majorNum1) {
                count1++;
            } else if (num == majorNum2) {
                count2++;
            } else if (count1 == 0) {
                majorNum1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                majorNum2 = num;
                count2 = 1; 
            } else {         // count--放在 count == 0之后，防止第一次进入的时候不会出错！！！
                count1--;
                count2--;
            }
        }
        
        count1 = count2 = 0;     // Count again for x1, x2
        
        for (int num : nums) {
            if (num == majorNum1) {
                count1++;
            } else if (num == majorNum2) {
                count2++;
            } 
        }
        
        if (count1 > len/3) {
            result.add(majorNum1);
        }
        
        if (count2 > len/3) {
            result.add(majorNum2);
        }
        
        return result;
    }
	
	
	
	
	
	
	
	
	/************************** main function ***************************/
	// by other, slow	
	public List<Integer> majorityElement2(int[] nums) {
		List<Integer> list = new ArrayList<Integer>();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (!map.containsKey(nums[i]))
				map.put(nums[i], 1);
			else
				map.put(nums[i], map.get(nums[i]) + 1);
		}

		for (Integer in : map.keySet()) { // there is at most 2 numbers that
											// appear more than 1/3 times.
			if (map.get(in) > nums.length / 3)
				list.add(in);
		}

		return list;
	}
	
	
	public static void main(String[] args){
		Q229_Majority_Element_II t = new Q229_Majority_Element_II();
		int[] nums = {5, 5, 5, 4, 3, 2, 1, 6};
		List<Integer> res = t.majorityElement(nums);
		for(int i = 0; i < res.size(); ++i)
			System.out.print(res.get(i) + ", ");
	}
}
