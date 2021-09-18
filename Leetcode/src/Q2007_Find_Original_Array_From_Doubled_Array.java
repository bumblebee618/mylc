import java.util.*;

/***
 * 
 * @author jackie
 * An integer array original is transformed into a doubled array changed by appending twice the value of every element in original, and then randomly shuffling the resulting array.

Given an array changed, return original if changed is a doubled array. If changed is not a doubled array, return an empty array. The elements in original may be returned in any order.

 

Example 1:

Input: changed = [1,3,4,2,6,8]
Output: [1,3,4]
Explanation: One possible original array could be [1,3,4]:
- Twice the value of 1 is 1 * 2 = 2.
- Twice the value of 3 is 3 * 2 = 6.
- Twice the value of 4 is 4 * 2 = 8.
Other original arrays could be [4,3,1] or [3,1,4].
Example 2:

Input: changed = [6,3,0,1]
Output: []
Explanation: changed is not a doubled array.
Example 3:

Input: changed = [1]
Output: []
Explanation: changed is not a doubled array.
 

Constraints:

1 <= changed.length <= 105
0 <= changed[i] <= 105
 */
public class Q2007_Find_Original_Array_From_Doubled_Array {
	public int[] findOriginalArray(int[] changed) {
        if (changed.length % 2 != 0) {
			return new int[0];
		}
		
		Arrays.sort(changed);
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[changed.length/2];
        int index = 0;
        
        for (int num : changed) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        
        for (int i = 0; i < changed.length; i++) {
        	if (map.getOrDefault(changed[i], 0) == 0) {
        		continue;
        	}
        	
        	int target = changed[i] * 2;
        	
        	if (map.getOrDefault(target, 0) == 0 || (changed[i] == 0 && map.get(changed[i]) % 2 != 0)) {
        		return new int[0];
        	}
        	
        	int count = changed[i] == 0 ? map.get(changed[i]) / 2 : map.get(changed[i]);
        	
        	for (int j = 0; j < count && index < result.length; j++) {
        		result[index++] = changed[i];
        	}
        	
        	map.put(target, map.get(target) - map.get(changed[i]));
        	
        	while (i+1 < changed.length && changed[i] == changed[i+1]) {
        		i++;
        	}
        }
        
        return result;
    }
}
