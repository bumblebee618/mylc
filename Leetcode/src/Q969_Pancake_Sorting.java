import java.util.LinkedList;
import java.util.List;

/***
 * 
 * @author jackie
 * 
 * Given an array of integers arr, sort the array by performing a series of pancake flips.

In one pancake flip we do the following steps:

Choose an integer k where 1 <= k <= arr.length.
Reverse the sub-array arr[1...k].
For example, if arr = [3,2,1,4] and we performed a pancake flip choosing k = 3, we reverse the sub-array [3,2,1], so arr = [1,2,3,4] after the pancake flip at k = 3.

Return the k-values corresponding to a sequence of pancake flips that sort arr. Any valid answer that sorts the array within 10 * arr.length flips will be judged as correct.

 

Example 1:

Input: arr = [3,2,4,1]
Output: [4,2,4,3]
Explanation: 
We perform 4 pancake flips, with k values 4, 2, 4, and 3.
Starting state: arr = [3, 2, 4, 1]
After 1st flip (k = 4): arr = [1, 4, 2, 3]
After 2nd flip (k = 2): arr = [4, 1, 2, 3]
After 3rd flip (k = 4): arr = [3, 2, 1, 4]
After 4th flip (k = 3): arr = [1, 2, 3, 4], which is sorted.
Notice that we return an array of the chosen k values of the pancake flips.
Example 2:

Input: arr = [1,2,3]
Output: []
Explanation: The input is already sorted, so there is no need to flip anything.
Note that other answers, such as [3, 3], would also be accepted.
 

Constraints:

1 <= arr.length <= 100
1 <= arr[i] <= arr.length
All integers in arr are unique (i.e. arr is a permutation of the integers from 1 to arr.length).
 */
public class Q969_Pancake_Sorting 
{
	public List<Integer> pancakeSort(int[] arr) 
    {
        List<Integer> result = new LinkedList<>();
        
        if (arr == null || arr.length <= 1)
        {
            return result;
        }
        
        for (int value = arr.length; value > 0; value--)
        {
            int targetPos = value-1;
            int index = findPos(arr, targetPos, value);
            
            if (index == targetPos)
            {
                continue;
            }
            else if (index == -1)
            {
                break;
            }
            
            if (index != 0)
            {
            	// (1). flip the minValue to the head if necessary
                swap(arr, index);
                result.add(index+1);
            }
            
            // (2). now that the minValue is at the head, flip it to the bottom
            swap(arr, targetPos);
            result.add(targetPos+1);
        }
        
        return result;
    }
    
    private int findPos(int[] arr, int end, int target)
    {
        for (int i = 0; i <= end; i++)
        {
            if (arr[i] == target)
            {
                return i;
            }
        }
        
        return -1;
    }
    
    private void swap(int[] arr, int end)
    {
        int start = 0;
        
        while (start < end)
        {
            int tmp = arr[start];
            arr[start] = arr[end];
            arr[end] = tmp;
            start++;
            end--;
        }
    }
}
