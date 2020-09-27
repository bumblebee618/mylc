import java.util.LinkedList;
import java.util.Queue;

/***
 * 
 * @author jackie
 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

 

Example 1:

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: true

Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.

Example 2:

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: false

Explanation: There is no way for the ball to stop at the destination.

 

Note:

There is only one ball and one destination in the maze.
Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
 *
 */
public class Q490_The_Maze {
	private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};
        
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0 || maze[0].length == 0)
        {
            return false;
        }
        else if (start == null || start.length != 2 || destination == null || destination.length != 2)
        {
            return false;
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        visited[start[0]][start[1]] = true;
        
        while (!queue.isEmpty()) 
        {
            int[] current = queue.poll();
            
            if (current[0] == destination[0] && current[1] == destination[1])
            {
                return true;
            }
            
            for (int i = 0; i < dx.length; i++) 
            {
                int newX = current[0] + dx[i];
                int newY = current[1] + dy[i];
                
                while (newX >= 0 && newY >= 0 && newX < maze.length && newY < maze[0].length && maze[newX][newY] == 0) 
                {
                    newX += dx[i];
                    newY += dy[i];
                }
                
                // remove back one step
                newX -= dx[i];
                newY -= dy[i];
                
                if (!visited[newX][newY]) 
                {
                    queue.add(new int[] {newX, newY});
                    visited[newX][newY] = true;
                }
            }
        }
        
        return false;
    }
}
