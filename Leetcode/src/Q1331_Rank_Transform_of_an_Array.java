import java.util.*;

/***
 * 
 * @author jackie
 * Given an array of integers arr, replace each element with its rank.

The rank represents how large the element is. The rank has the following rules:

Rank is an integer starting from 1.
The larger the element, the larger the rank. If two elements are equal, their rank must be the same.
Rank should be as small as possible.
 

Example 1:

Input: arr = [40,10,20,30]
Output: [4,1,2,3]
Explanation: 40 is the largest element. 10 is the smallest. 20 is the second smallest. 30 is the third smallest.
Example 2:

Input: arr = [100,100,100]
Output: [1,1,1]
Explanation: Same elements share the same rank.
Example 3:

Input: arr = [37,12,28,9,100,56,80,5,12]
Output: [5,3,4,2,8,6,7,1,3]
 

Constraints:

0 <= arr.length <= 105
-109 <= arr[i] <= 109

 *
 */
public class Q1331_Rank_Transform_of_an_Array {
	public int[] arrayRankTransform(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int[] temp = nums.clone();
        Arrays.sort(temp);
        int[] result = new int[nums.length];
        Map<Integer, Integer> map = new HashMap<>();
        int rank = 1;

        for (int i = 0; i < temp.length; i++) {
            if (map.putIfAbsent(temp[i], rank) == null) {
                rank++;
            }
        }
        
        for (int i = 0; i < result.length; i++) {
            result[i] = map.get(nums[i]);
        }

        return result;
    }
}
