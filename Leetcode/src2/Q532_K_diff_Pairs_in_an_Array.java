import java.awt.Dialog.ModalityType;
import java.awt.event.MouseWheelEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/***
 * 
 * @author jackie
 *	Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array. Here a k-diff pair is defined as an integer pair (i, j), where i and j are both numbers in the array and their absolute difference is k.

Example 1:
Input: [3, 1, 4, 1, 5], k = 2
Output: 2
Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
Although we have two 1s in the input, we should only return the number of unique pairs.
Example 2:
Input:[1, 2, 3, 4, 5], k = 1
Output: 4
Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
Example 3:
Input: [1, 3, 1, 5, 4], k = 0
Output: 1
Explanation: There is one 0-diff pair in the array, (1, 1).
Note:
The pairs (i, j) and (j, i) count as the same pair.
The length of the array won't exceed 10,000.
All the integers in the given input belong to the range: [-1e7, 1e7].
 *
 *
 */
public class Q532_K_diff_Pairs_in_an_Array {
	public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        
        int result = 0; 
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (map.containsKey(entry.getKey() + k)) {
                result += (k != 0 || entry.getValue() > 1) ? 1 : 0;
            }
        }
        
        
        return result;
    }
	
	public int findPairs2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Map<Integer, Set<Integer>> map = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            map.computeIfAbsent(nums[i], x -> new HashSet<>()).add(i);
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited.contains(nums[i])) {
                continue;
            }

            if (!map.containsKey(nums[i] + k)) {
                continue;
            } 
            
            visited.add(nums[i]);
            Set<Integer> indexSet = map.get(nums[i] + k);

            if (!indexSet.contains(i) || indexSet.size() > 1) {
                result++;
            }
        }

        return result;
    }
	
	
	
	
	/************************************ main ************************************/
	
	public static void main(String[] args)
	{
		Q532_K_diff_Pairs_in_an_Array test = new Q532_K_diff_Pairs_in_an_Array();
		int[] array = {1,3,1,5,4};
		int k = 0;
		System.out.println(test.findPairs(array, k));
	}
}
