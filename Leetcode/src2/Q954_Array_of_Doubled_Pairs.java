import java.util.TreeMap;

/***
 * 
 * @author jackie
 * 
 * Given an integer array of even length arr, return true if it is possible to reorder arr such that arr[2 * i + 1] = 2 * arr[2 * i] for every 0 <= i < len(arr) / 2, or false otherwise.

 

Example 1:

Input: arr = [3,1,3,6]
Output: false
Example 2:

Input: arr = [2,1,2,6]
Output: false
Example 3:

Input: arr = [4,-2,2,-4]
Output: true
Explanation: We can take two groups, [-2,-4] and [2,4] to form [-2,-4,2,4] or [2,4,-2,-4].
Example 4:

Input: arr = [1,2,4,16,8,4]
Output: false
 

Constraints:

2 <= arr.length <= 3 * 104
arr.length is even.
-105 <= arr[i] <= 105
 * 
 */
public class Q954_Array_of_Doubled_Pairs {
	public boolean canReorderDoubled(int[] arr) {
        TreeMap<Integer, Integer> positives = new TreeMap<>();
        TreeMap<Integer, Integer> negatives = new TreeMap<>();
        int zeroCount = 0;
        
        for (int num : arr) {
            if (num > 0) {
                positives.put(num, positives.getOrDefault(num, 0)+1);
            } else if (num < 0) {
                negatives.put(-num, negatives.getOrDefault(-num, 0)+1);
            } else {
                zeroCount++;
            }
        }
        
        if (zeroCount % 2 > 0 || !isValid(positives) || !isValid(negatives)) {
            return false;
        }
        
        return true;
    }
    
    private boolean isValid(TreeMap<Integer, Integer> treeset) {
        for (int num : treeset.keySet()) {
            int count = treeset.getOrDefault(num, 0);
            
            if (count == 0) {
                continue;
            }
            
            if (treeset.containsKey(num*2) && treeset.get(num*2) >= count) {
                treeset.put(num*2, treeset.get(num*2)-count);
            } else {
                return false;
            }
        }
        
        return true;
    }
}
