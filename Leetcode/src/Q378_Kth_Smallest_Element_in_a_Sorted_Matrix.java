import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
/******
 * 
Given a n row n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.

Note: 
	You may assume k is always valid, 1 ≤ k ≤ n2.
 * 
 * */

public class Q378_Kth_Smallest_Element_in_a_Sorted_Matrix {
	// solution 1: time is O(col*logcol + klogk)
	public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
        {
            return 0;
        }
        else if (k <= 0 || k > matrix.length * matrix[0].length)
        {
            return 0;
        }
        
        int row = matrix.length, col = matrix[0].length;
        Queue<Tuple> heap = new PriorityQueue<>(col+1, (a, b) -> a.value - b.value);
        
        for (int i = 0; i < col; i++)
        {
            heap.offer(new Tuple(0, i, matrix[0][i]));
        }
        
        for (int index = 0; index < k-1; index++)
        {
            Tuple cur = heap.poll();
            
            if (cur.x + 1 < row)
            {
                heap.offer(new Tuple(cur.x+1, cur.y, matrix[cur.x+1][cur.y]));
            }
        }
        
        return heap.peek().value;
    }
    
    class Tuple
    {
        public int x;
        public int y;
        public int value;
        
        public Tuple(int x, int y, int value)
        {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
	
    
    
	
    
	// solution 2: Time is O(row*col*klogk)
	public int kthSmallest2(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        
        int row = matrix.length;
        int col = matrix[0].length;
        
        Queue<Integer> heap = new PriorityQueue<Integer>(k + 1, new Comparator<Integer>() {
            @Override
            public int compare (Integer val1, Integer val2) {
                return val2 - val1;
            }
        });
        
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                heap.offer(matrix[x][y]);
                
                if (heap.size() > k) {
                    heap.poll();
                }
            }
        }
        
        return heap.poll();
    }
}
