import java.util.HashSet;
import java.util.Set;

/***
 * 
 * @author jackie
 * 
 * Given a robot cleaner in a room modeled as a grid.

Each cell in the grid can be empty or blocked.

The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.

When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.

Design an algorithm to clean the entire room using only the 4 given APIs shown below.

interface Robot {
  // returns true if next cell is open and robot moves into the cell.
  // returns false if next cell is obstacle and robot stays on the current cell.
  boolean move();

  // Robot will stay on the same cell after calling turnLeft/turnRight.
  // Each turn will be 90 degrees.
  void turnLeft();
  void turnRight();

  // Clean the current cell.
  void clean();
}
Example:

Input:
room = [
  [1,1,1,1,1,0,1,1],
  [1,1,1,1,1,0,1,1],
  [1,0,1,1,1,1,1,1],
  [0,0,0,1,0,0,0,0],
  [1,1,1,1,1,1,1,1]
],
row = 1,
col = 3

Explanation:
All grids in the room are marked by either 0 or 1.
0 means the cell is blocked, while 1 means the cell is accessible.
The robot initially starts at the position of row=1, col=3.
From the top left corner, its position is one row below and three columns right.
Notes:

The input is only given to initialize the room and the robot's position internally. You must solve this problem "blindfolded". In other words, you must control the robot using only the mentioned 4 APIs, without knowing the room layout and the initial robot's position.
The robot's initial position will always be in an accessible cell.
The initial direction of the robot will be facing up.
All accessible cells are connected, which means the all cells marked as 1 will be accessible by the robot.
Assume all four edges of the grid are all surrounded by wall.
 */
public class Q489_Robot_Room_Cleaner 
{
	private int[] dx = {-1, 0, 1, 0};
    private int[] dy = {0, 1, 0, -1};
    private Set<String> visited = new HashSet<>();
    private Robot robot;

    public void cleanRoom(Robot robot) 
    {
        this.robot = robot;
        backtrack(0, 0, 0);
    }

    private void backtrack(int row, int col, int direction) 
    {
        visited.add(row + "+" + col);
        robot.clean();
    
        // going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
        for (int i = 0; i < 4; ++i) 
        {
            int newDirection = (direction + i) % 4;            
            int newRow = row + dx[newDirection];
            int newCol = col + dy[newDirection];

            if (!visited.contains(newRow + "+" + newCol)) 
            {
                if (robot.move())
                {
                    backtrack(newRow, newCol, newDirection);
                    goBack();  // backtracking思想，恢复原样
                }
            }
            
            // turn the robot following chosen direction : clockwise
            robot.turnRight();
        }
    }

    private void goBack() 
    {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }
    
    
    
    
    
    
    
/***************************************************************/    
    class Robot
    {
    	public void turnRight() {}
    	public boolean move() { return true; }
    	public void clean() {}
    }
}
