import java.util.*;

/***
 * 
 * @author jackie
 * There is a 1 million by 1 million grid on an XY-plane, and the coordinates of each grid square are (x, y).

We start at the source = [sx, sy] square and want to reach the target = [tx, ty] square. There is also an array of blocked squares, where each blocked[i] = [xi, yi] represents a blocked square with coordinates (xi, yi).

Each move, we can walk one square north, east, south, or west if the square is not in the array of blocked squares. We are also not allowed to walk outside of the grid.

Return true if and only if it is possible to reach the target square from the source square through a sequence of valid moves.

 

Example 1:

Input: blocked = [[0,1],[1,0]], source = [0,0], target = [0,2]
Output: false
Explanation: The target square is inaccessible starting from the source square because we cannot move.
We cannot move north or east because those squares are blocked.
We cannot move south or west because we cannot go outside of the grid.
Example 2:

Input: blocked = [], source = [0,0], target = [999999,999999]
Output: true
Explanation: Because there are no blocked cells, it is possible to reach the target square.
 

Constraints:

0 <= blocked.length <= 200
blocked[i].length == 2
0 <= xi, yi < 106
source.length == target.length == 2
0 <= sx, sy, tx, ty < 106
source != target
It is guaranteed that source and target are not blocked.
 */
public class Q1036_Escape_a_Large_Maze {
	public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        if (blocked == null || source == null || source.length != 2 || target == null || target.length != 2) {
            return true;
        }

        Set<String> blocks = new HashSet<>();

        for(int[] block : blocked) {
            blocks.add(block[0] + "," + block[1]);
        }

        return bfs(blocks, source, target) && bfs(blocks, target, source);
    }

    private boolean bfs(Set<String> blocks, int[] source, int[] target) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(source);

        Set<String> visited = new HashSet<>();
        visited.add(source[0] + "," + source[1]);

        int boundary = 1_000_000;
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        
        while (!queue.isEmpty()) {
            int[] curPos = queue.poll();

            if (curPos[0] == target[0] && curPos[1] == target[1]) {
                return true;
            }

            if (visited.size() > 19900){
                return true;
            }

            for (int i = 0; i < dx.length; i++) {
                int nextX = curPos[0] + dx[i];
                int nextY = curPos[1] + dy[i];
                String key = nextX + "," + nextY;

                if (nextX >= 0 && nextY >= 0 && nextX < boundary && nextY < boundary && !visited.contains(key) && !blocks.contains(key)) {
                    queue.offer(new int[]{nextX, nextY});
                    visited.add(key);
                }
            } 
        }

        return false;
    }
}
