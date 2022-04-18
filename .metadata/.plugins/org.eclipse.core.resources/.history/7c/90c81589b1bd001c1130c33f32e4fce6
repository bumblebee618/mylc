import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Given an array of integers arr, you are initially positioned at the first index of the array.

In one step you can jump from index i to index:

i + 1 where: i + 1 < arr.length.
i - 1 where: i - 1 >= 0.
j where: arr[i] == arr[j] and i != j.
Return the minimum number of steps to reach the last index of the array.

Notice that you can not jump outside of the array at any time.

 

Example 1:

Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
Output: 3
Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.
Example 2:

Input: arr = [7]
Output: 0
Explanation: Start index is the last index. You don't need to jump.
Example 3:

Input: arr = [7,6,9,6,9,6,9,7]
Output: 1
Explanation: You can jump directly from index 0 to index 7 which is last index of the array.
Example 4:

Input: arr = [6,1,9]
Output: 2
Example 5:

Input: arr = [11,22,7,7,7,7,7,7,7,22,13]
Output: 3
 

Constraints:

1 <= arr.length <= 5 * 104
-108 <= arr[i] <= 108
 */

public class Q1345_Jump_Game_IV 
{
	public int minJumps(int[] arr) 
    {
        if (arr == null || arr.length == 0)
        {
            return 0;
        }
        
        Map<Integer, List<Integer>> graph = new HashMap<>();
        
        for (int i = 0; i < arr.length; i++)
        {
            graph.computeIfAbsent(arr[i], x -> new LinkedList<>()).add(i);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        int step = 0;
        
        while (!queue.isEmpty())
        {
            int size = queue.size();
            
            for (int i = 0; i < size; i++)
            {
                int curIndex = queue.poll();
                
                if (curIndex >= arr.length-1)
                {
                    return step;
                }
                
                if (curIndex-1 >= 0 && !visited.contains(curIndex-1))
                {
                    queue.offer(curIndex-1);
                    visited.add(curIndex-1);
                }
                
                if (curIndex+1 < arr.length && !visited.contains(curIndex+1))
                {
                    queue.offer(curIndex+1);
                    visited.add(curIndex+1);
                }
                
                for (int nextIndex : graph.get(arr[curIndex]))
                {
                    if (!visited.contains(nextIndex))
                    {
                        queue.offer(nextIndex);
                        visited.add(nextIndex);
                    }
                }
                
                graph.get(arr[curIndex]).clear();
            }
            
            step++;
        }
        
        return -1;
    }
}
