import java.util.*;
/*******
 * 
A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

For example, given three people living at (0,0), (0,4), and (2,2):

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.

 * 
 * */

public class Q296_Best_Meeting_Point 
{
	public int minTotalDistance(int[][] grid) 
    {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
        {
            return 0;
        }
        
        List<Integer> list_x = new ArrayList<Integer>();
        List<Integer> list_y = new ArrayList<Integer>();
        int row = grid.length, col = grid[0].length;
        
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                if (grid[i][j] == 1)
                {
                    list_x.add(i);
                    list_y.add(j);
                }
            }
        }
        
        return getDistance(list_x) + getDistance(list_y);
    }
    
    private int getDistance(List<Integer> list)
    {
        int left = 0, right = list.size() - 1;
        int totalDistance = 0;
        Collections.sort(list);
        
        while (left < right)
        {
            totalDistance += list.get(right) - list.get(left);
            left++;
            right--;
        }
        
        return totalDistance;
    }
	
    
    
    

    // solution 2: time exceed limit
    private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};
    
    public int minTotalDistance2(int[][] grid) 
    {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
        {
            return 0;
        }
        
        int row = grid.length, col = grid[0].length;
        int[][] distance = new int[row][col];
        int minDistance = Integer.MAX_VALUE;
        
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                if (grid[i][j] == 1)
                {
                    bfs(grid, distance, i, j);
                }
            }
        }
        
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                minDistance = Math.min(minDistance, distance[i][j]);
            }
        }
        
        return minDistance;
    }
    
    private void bfs(int[][] grid, int[][] distance, int x, int y)
    {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {x, y});
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        visited[x][y] = true;
        int step = 0;
        
        while (!queue.isEmpty())
        {
            int size = queue.size();
            
            for (int i = 0; i < size; i++)
            {
                int[] curNode = queue.poll();
                distance[curNode[0]][curNode[1]] += step;
            
                for (int j = 0; j < dx.length; j++)
                {
                    int newX = curNode[0] + dx[j];
                    int newY = curNode[1] + dy[j];
                
                    if (newX >= 0 && newX < grid.length
                        && newY >= 0 && newY < grid[0].length
                        && !visited[newX][newY])
                    {
                        queue.offer(new int[] {newX, newY});
                        visited[newX][newY] = true;
                    }
                }
            }
            
            step++;
        }
    }
	
	
	
	
	
	
	
	
	/************************** main function ********************************/
	
	public static void main(String[] args){
		Q296_Best_Meeting_Point t = new Q296_Best_Meeting_Point();
		int[][] grid = {
				{1,0,0,0,1},
				{0,0,0,0,0},
				{0,0,1,0,0}
		};
		
		System.out.println(t.minTotalDistance(grid));
	}
}
