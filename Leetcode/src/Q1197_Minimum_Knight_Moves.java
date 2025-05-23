import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/***
 * 
 * @author jackie
 * 
 * In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].

A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.



Return the minimum number of steps needNum to move the knight to the square [row, col].  It is guaranteed the answer exists.

 

Example 1:

Input: row = 2, col = 1
Output: 1
Explanation: [0, 0] → [2, 1]
Example 2:

Input: row = 5, col = 5
Output: 4
Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]
 

Constraints:

|row| + |col| <= 300
 */
public class Q1197_Minimum_Knight_Moves {
    private int[] dx = {-2, -1, 1, 2,  2,  1, -1, -2};
    private int[] dy = { 1,  2, 2, 1, -1, -2, -2, -1};
    
    public int minKnightMoves(int m, int n) {
        if (m == 0 && n == 0) {
            return 0;
        }
        
        m = Math.abs(m);
        n = Math.abs(n);
        
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0});
        
        Set<String> visited = new HashSet<>();
        visited.add("0,0");
        
        int steps = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                int[] index = queue.poll();
                
                if (index[0] == m && index[1] == n) {
                    return steps;
                }
                
                for (int j = 0; j < dx.length; j++) {
                    int newX = index[0] + dx[j];
                    int newY = index[1] + dy[j];
                    String newIndex = newX+","+newY;
                    
                    if (newX >= -2 && newY >= -2 && !visited.contains(newIndex)) {
                        queue.offer(new int[]{newX, newY});
                        visited.add(newIndex);
                    }
                }
            }
            
            steps++;
        }
        
        return -1;
    }
    
    
    
    
    
    
    
    
    /******************************* main *******************************/
	
	public static void main(String[] args)
	{
		Q1197_Minimum_Knight_Moves test = new Q1197_Minimum_Knight_Moves();
		System.out.println(test.minKnightMoves(0, -300));
	}
}
