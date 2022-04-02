import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/***
 * 
 * @author jackie
 *
 *There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.

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

Output: 12

Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
             The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.

Example 2:

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: -1

Explanation: There is no way for the ball to stop at the destination.

 

Note:

There is only one ball and one destination in the maze.
Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
 */
public class Q505_The_Maze_II {
	private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};
    
    public int shortestDistance(int[][] maze, int[] start, int[] destination) 
    {
        if (maze == null || maze.length == 0 || maze[0].length == 0)
        {
            return -1;
        }
        else if (start == null || start.length != 2 || destination == null || destination.length != 2)
        {
            return -1;
        }
        
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        int row = maze.length;
        int col = maze[0].length;
        int[][] distance = new int[row][col];
        
        for (int[] distanceRow : distance)
        {
            Arrays.fill(distanceRow, Integer.MAX_VALUE);
        }
        
        distance[start[0]][start[1]] = 0;
        
        while (!queue.isEmpty()) 
        {
            int[] current = queue.poll();
            
            if (current[0] == destination[0] && current[1] == destination[1])
            {
                continue;
            }
            
            for (int i = 0; i < dx.length; i++) 
            {
                int newX = current[0] + dx[i];
                int newY = current[1] + dy[i];
                int count = 1;
                
                while (newX >= 0 && newY >= 0 && newX < row && newY < col && maze[newX][newY] == 0) 
                {
                    newX += dx[i];
                    newY += dy[i];
                    count++;
                }
                
                // remove back one step
                newX -= dx[i];
                newY -= dy[i];
                count--;
                
                // update distance
                if (distance[newX][newY] > distance[current[0]][current[1]] + count) 
                {
                    distance[newX][newY] = distance[current[0]][current[1]] + count;
                    queue.offer(new int[] {newX, newY});
                }
            }
        }
        
        return distance[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : distance[destination[0]][destination[1]];
    }
}
