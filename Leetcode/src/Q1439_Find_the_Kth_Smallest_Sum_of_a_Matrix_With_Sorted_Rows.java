import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/***
 * 
 * @author jackie
 * 
 * You are given an m * n matrix, mat, and an integer k, which has its rows sorted in non-decreasing order.

You are allowed to choose exactly 1 element from each row to form an array. Return the Kth smallest array sum among all possible arrays.

 

Example 1:

Input: mat = [[1,3,11],[2,4,6]], k = 5
Output: 7
Explanation: Choosing one element from each row, the first k smallest sum are:
[1,2], [1,4], [3,2], [3,4], [1,6]. Where the 5th sum is 7.  
Example 2:

Input: mat = [[1,3,11],[2,4,6]], k = 9
Output: 17
Example 3:

Input: mat = [[1,10,10],[1,4,5],[2,3,6]], k = 7
Output: 9
Explanation: Choosing one element from each row, the first k smallest sum are:
[1,1,2], [1,1,3], [1,4,2], [1,4,3], [1,1,6], [1,5,2], [1,5,3]. Where the 7th sum is 9.  
Example 4:

Input: mat = [[1,1,10],[2,2,9]], k = 7
Output: 12
 

Constraints:

m == mat.length
n == mat.length[i]
1 <= m, n <= 40
1 <= k <= min(200, n ^ m)
1 <= mat[i][j] <= 5000
mat[i] is a non decreasing array.
 */
public class Q1439_Find_the_Kth_Smallest_Sum_of_a_Matrix_With_Sorted_Rows {
	public int kthSmallest(int[][] mat, int k) 
    {
        if (mat == null || mat.length == 0 || mat[0].length == 0 || k <= 0)
        {
            return 0;
        }
        
        int col = Math.min(mat[0].length, k);
	    Queue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
	    heap.add(0);
        
	    for (int[] row : mat) 
        {
		    // max priority queue for the i-th row
		    Queue<Integer> nextHeap = new PriorityQueue<>(Collections.reverseOrder());
            
		    for (int sum : heap) 
            {
			    for (int c = 0; c < col; c++) 
                {
				    nextHeap.add(sum + row[c]);
				
                    // keep heap size <= k
				    if (nextHeap.size() > k) 
                    {
					    nextHeap.poll();
                    }
			    }
		    }
            
		    heap = nextHeap;
	    }
	    
        return heap.poll();
    }
}
