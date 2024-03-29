import java.util.HashMap;
import java.util.Map;

/***
 * 
 * @author jackie
 * 
 * A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

For example, these are arithmetic sequences:

1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9
The following sequence is not arithmetic.

1, 1, 2, 5, 7
 
A zero-indexed array A consisting of N numbers is given. A subsequence slice of that array is any sequence of integers (P0, P1, ..., Pk) such that 0 ≤ P0 < P1 < ... < Pk < N.

A subsequence slice (P0, P1, ..., Pk) of array A is called arithmetic if the sequence A[P0], A[P1], ..., A[Pk-1], A[Pk] is arithmetic. In particular, this means that k ≥ 2.

The function should return the number of arithmetic subsequence slices in the array A.

The input contains N integers. Every integer is in the range of -231 and 231-1 and 0 ≤ N ≤ 1000. The output is guaranteed to be less than 231-1.

 
Example:

Input: [2, 4, 6, 8, 10]

Output: 7

Explanation:
All arithmetic subsequence slices are:
[2,4,6]
[4,6,8]
[6,8,10]
[2,4,6,8]
[4,6,8,10]
[2,4,6,8,10]
[2,6,10]
 */
public class Q446_Arithmetic_Slices_II_Subsequence 
{
	public int numberOfArithmeticSlices(int[] nums) 
    {
        if (nums == null || nums.length < 3) 
        {
            return 0;
        } 
        
        int result = 0;
        Map<Integer, Integer>[] counts = new Map[nums.length];
        
        for (int i = 0; i < nums.length; i++) 
        {
            counts[i] = new HashMap<>(i);
            
            for (int j = 0; j < i; j++) 
            {
                long gap = (long) nums[i] - (long) nums[j];
                
                if (gap < Integer.MIN_VALUE || gap > Integer.MAX_VALUE) 
                {
                    continue;
                }
                
                int diff = (int) gap;
                int prevCount = counts[j].getOrDefault(diff, 0);
                
                // this means current subarray's len is 2
                // each time add 1 to previous result:  
                // 		[1,2] = 1 and add 3 ==>  [1,2] and [1,2,3] (new one)
                if (prevCount == 0)
                {
                    counts[i].put(diff, counts[i].getOrDefault(diff, 0) + 1);
                }
                else
                {
                    counts[i].put(diff, counts[i].getOrDefault(diff, 0) + prevCount + 1);
                    result += prevCount; // add prevCount since this is the increasing number
                }
            }
        }
        
        return result;     
    }
	
	public int numberOfArithmeticSlices2(int[] nums) 
    {
        if (nums == null || nums.length < 3) 
        {
            return 0;
        } 
        
        int result = 0;
        Map<Integer, Integer>[] counts = new Map[nums.length];
        
        for (int i = 0; i < nums.length; i++) 
        {
            counts[i] = new HashMap<>(i);
            
            for (int j = 0; j < i; j++) 
            {
                long gap = (long) nums[i] - (long) nums[j];
                
                if (gap < Integer.MIN_VALUE || gap > Integer.MAX_VALUE) 
                {
                    continue;
                }
                
                int diff = (int) gap;
                
                // count比实际要多一个1，用来区别数组长度为2的情况和为1的情况
                int count = counts[j].getOrDefault(diff, 0); 
                int origin = counts[i].getOrDefault(diff, 0);
                counts[i].put(diff, origin + count + 1);
                
                // 这里加的是j的sum, 防止subarray长度只有2的情况；
                // 因为count的值比实际多1，所以相当于已经包含了新增数组[i-1, i, i+1]的情况
                result += count;
            }
        }
        
        return result; 
    }
}
