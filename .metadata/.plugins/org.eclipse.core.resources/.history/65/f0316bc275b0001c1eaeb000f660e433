import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/***
 * 
 * There is an integer array nums that consists of n unique elements, but you have forgotten it. However, you do remember every pair of adjacent elements in nums.

You are given a 2D integer array adjacentPairs of size n - 1 where each adjacentPairs[i] = [ui, vi] indicates that the elements ui and vi are adjacent in nums.

It is guaranteed that every adjacent pair of elements nums[i] and nums[i+1] will exist in adjacentPairs, either as [nums[i], nums[i+1]] or [nums[i+1], nums[i]]. The pairs can appear in any order.

Return the original array nums. If there are multiple solutions, return any of them.

 

Example 1:

Input: adjacentPairs = [[2,1],[3,4],[3,2]]
Output: [1,2,3,4]
Explanation: This array has all its adjacent pairs in adjacentPairs.
Notice that adjacentPairs[i] may not be in left-to-right order.
Example 2:

Input: adjacentPairs = [[4,-2],[1,4],[-3,1]]
Output: [-2,4,1,-3]
Explanation: There can be negative numbers.
Another solution is [-3,1,4,-2], which would also be accepted.
Example 3:

Input: adjacentPairs = [[100000,-100000]]
Output: [100000,-100000]
 

Constraints:

nums.length == n
adjacentPairs.length == n - 1
adjacentPairs[i].length == 2
2 <= n <= 105
-105 <= nums[i], ui, vi <= 105
There exists some nums that has adjacentPairs as its pairs.
 *
 */

public class Q1743_Restore_the_Array_From_Adjacent_Pairs {
	public int[] restoreArray(int[][] adjacentPairs) {
        if (adjacentPairs == null || adjacentPairs.length == 0) {
            return new int[0];
        }
        
        Map<Integer, Set<Integer>> map = new HashMap<>();
        
        for (int[] adjacentPair : adjacentPairs) {
            map.computeIfAbsent(adjacentPair[0], x -> new HashSet<>()).add(adjacentPair[1]);
            map.computeIfAbsent(adjacentPair[1], x -> new HashSet<>()).add(adjacentPair[0]);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        
        for (Map.Entry<Integer, Set<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() == 1) {
                queue.offer(entry.getKey());
                visited.add(entry.getKey());
                break;
            }
        }
        
        int[] result = new int[map.size()];
        int index = 0;
        
        
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            result[index++] = cur;
            
            for (int next : map.get(cur)) {
                if (!visited.contains(next)) {
                    queue.offer(next);
                    visited.add(next);
                }
            }
        }
        
        return visited.size() == map.size() ? result : new int[0];
    }
}
