/***
 * 
 * @author jackie
 * 
 * You are given an m x n integer matrix grid​​​, where m and n are both even integers, and an integer k.

The matrix is composed of several layers, which is shown in the below image, where each color is its own layer:



A cyclic rotation of the matrix is done by cyclically rotating each layer in the matrix. To cyclically rotate a layer once, each element in the layer will take the place of the adjacent element in the counter-clockwise direction. An example rotation is shown below:


Return the matrix after applying k cyclic rotations to it.

 

Example 1:


Input: grid = [[40,10],[30,20]], k = 1
Output: [[10,20],[40,30]]
Explanation: The figures above represent the grid at every state.
Example 2:

  
Input: grid = [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]], k = 2
Output: [[3,4,8,12],[2,11,10,16],[1,7,6,15],[5,9,13,14]]
Explanation: The figures above represent the grid at every state.
 

Constraints:

m == grid.length
n == grid[i].length
2 <= m, n <= 50
Both m and n are even integers.
1 <= grid[i][j] <= 5000
1 <= k <= 109
 */
public class Q1914_Cyclically_Rotating_a_Grid 
{
	public int[][] rotateGrid(int[][] grid, int k) 
    {
        if (grid == null || grid.length == 0 || grid[0].length == 0 || k <= 0)
        {
            return grid;
        }
        
        int row = grid.length, col = grid[0].length;
        int level = Math.min(row, col) / 2;
        int[][] result = new int[row][col];
        int startX = 0, endX = row-1, startY = 0, endY = col-1;
        
        for (int i = 0; i < level; i++)
        {
            int x = startX, y = startY;
            int diffX = endX - startX;
            int diffY = endY - startY;
            
            for (int j = 0; j < 2 * (diffX + diffY); j++)
            {
                int[] updateIndex = findNext(x, y, startX, endX, startY, endY, k);
                result[updateIndex[0]][updateIndex[1]] = grid[x][y];
                int[] next = findNext(x, y, startX, endX, startY, endY, 1);
                x = next[0];
                y = next[1];
            }
            
            startX++;
            endX--;
            startY++;
            endY--;
        }
        
        return result;
    }
    
    private int[] findNext(int x, int y, int startX, int endX, int startY, int endY, int k)
    {
        int diffX = endX - startX;
        int diffY = endY - startY;
        k %= (diffX + diffY) * 2;
        
        while (k > 0)
        {
            if (y == startY && x < endX)
            {
                int step = Math.min(endX - x, k);
                k -= step;
                x += step;
            }
            else if (x == endX && y < endY)
            {
                int step = Math.min(endY - y, k);
                k -= step;
                y += step;
            }
            else if (y == endY && x > startX)
            {
                int step = Math.min(x - startX, k);
                k -= step;
                x -= step;
            }
            else if (x == startX && y > startY)
            {
                int step = Math.min(y - startY, k);
                k -= step;
                y -= step;
            }
        }
        
        return new int[] {x, y};
    }
    
    
    
    
    /******************************** main ********************************/
    public static void main(String[] args)
    {
    	Q1914_Cyclically_Rotating_a_Grid test = new Q1914_Cyclically_Rotating_a_Grid();
    	int[][] grid = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}}; 
    	int k = 2;
    	
    	int[][] result = test.rotateGrid(grid, k);
    }
    
}
