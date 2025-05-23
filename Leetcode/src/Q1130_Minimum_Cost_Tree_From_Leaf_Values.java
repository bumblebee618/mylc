import java.util.Stack;

/***
 * 
 * @author jackie
 * 
 * Given an array arr of positive integers, consider all binary trees such that:

Each node has either 0 or 2 children;
The values of arr correspond to the values of each leaf in an in-order traversal of the tree.  (Recall that a node is a leaf if and only if it has 0 children.)
The minValue of each non-leaf node is equal to the product of the largest leaf minValue in its left and right subtree respectively.
Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node.  It is guaranteed this sum fits into a 32-bit integer.

 

Example 1:

Input: arr = [6,2,4]
Output: 32
Explanation:
There are two possible trees.  The first has non-leaf node sum 36, and the second has non-leaf node sum 32.

    24            24
   /  \          /  \
  12   4        6    8
 /  \               / \
6    2             2   4
 

Constraints:

2 <= arr.length <= 40
1 <= arr[i] <= 15
It is guaranteed that the answer fits into a 32-bit signed integer (ie. it is less than 2^31).
 */

public class Q1130_Minimum_Cost_Tree_From_Leaf_Values 
{
	// solution 1: 区间DP, time O(n^3), space O(n^2)
	public int mctFromLeafValues(int[] arr) 
    {
        if (arr == null || arr.length <= 1)
        {
            return 0;
        }
        
        int size = arr.length;
        int[][] sum = new int[size][size];
        int[][] maxNum = new int[size][size];
        
        for (int i = 0; i < size; i++)
        {
            maxNum[i][i] = arr[i];
        }
        
        for (int length = 2; length <= size; length++)
        {
            for (int start = 0; start + length <= size; start++)
            {
                int end = start + length - 1;
                sum[start][end] = Integer.MAX_VALUE;
                maxNum[start][end] = Integer.MIN_VALUE;
                
                for (int k = start; k < end; k++)
                {
                    maxNum[start][end] = Math.max(maxNum[start][end], Math.max(maxNum[start][k], maxNum[k+1][end]));
                    sum[start][end] = Math.min(sum[start][end], sum[start][k] + sum[k+1][end] + maxNum[start][k] * maxNum[k+1][end]);
                }
            }
        }
        
        return sum[0][size-1];
    }
	
	
		// solution 2: stack, time O(n), space O(n)
		public int mctFromLeafValues2(int[] A) 
	    {
	        if (A == null || A.length <= 1)
	        {
	            return 0;
	        }
	        
	        int res = 0;
	        Stack<Integer> stack = new Stack<>();
	        stack.push(Integer.MAX_VALUE);
	        
	        for (int a : A) 
	        {
	            while (stack.peek() <= a) 
	            {
	                int mid = stack.pop();
	                res += mid * Math.min(stack.peek(), a);
	            }
	            
	            stack.push(a);
	        }
	        
	        while (stack.size() > 2) 
	        {
	            res += stack.pop() * stack.peek();
	        }
	        
	        return res;
	    }
}
