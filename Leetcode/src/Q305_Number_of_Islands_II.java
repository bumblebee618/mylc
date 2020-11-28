import java.util.LinkedList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/******
 * 
A 2d grid map of m rows and n columns is initially filled with water. 
We may perform an addLand operation which turns the water at position (row, col) into a land. 
Given a list of positions to operate, count the number of islands after each addLand operation. 
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
You may assume all four edges of the grid are all surrounded by water.

Example:

Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
We return the result as an array: [1, 1, 2, 3]

Challenge:

Can you do it in time complexity O(k log mn), where k is the length of the positions?

 * 
 * 
 * */


public class Q305_Number_of_Islands_II {
	// using Union Find time complexity is O(n*m + k), space O(n*m)
	private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};
    
    public List<Integer> numIslands2(int m, int n, int[][] positions) 
    {
        List<Integer> result = new LinkedList<>();
        
        if (m <= 0 || n <= 0 || positions == null || positions.length == 0 || positions[0].length != 2) 
        {
            return result;
        }
        
        UnionFind uf = new UnionFind(m, n);
        int count = 0;
        int[][] island = new int[m][n];
        
        for (int[] position : positions)
        {
            int x = position[0];
            int y = position[1];
            
            if (island[x][y] == 0)
            {
                count++;
                island[x][y] = 1;
                int currentId = getId(x, y, n);
            
                for (int i = 0; i < dx.length; i++)
                {
                    int newX = x + dx[i];
                    int newY = y + dy[i];
                
                    if (newX >= 0 && newX < m && newY >= 0 && newY < n && island[newX][newY] == 1)
                    {
                        int newId = getId(newX, newY, n);
                        int parent = uf.find(currentId);
                        int newParent = uf.find(newId);
                    
                        if (parent != newParent)
                        {
                            uf.union(currentId, newId);
                            count--;
                        }
                    }
                }
            }
            
            result.add(count);
        }
        
        return result;
    }
    
    private int getId(int x, int y, int col)
    {
        return x * col + y;
    }
    
    class UnionFind
    {
        private Map<Integer, Integer> father;
        
        public UnionFind(int row, int col)
        {
            father = new HashMap<>();
            
            for (int i = 0; i < row * col; i++)
            {
                father.put(i, i);
            }
        }
        
        public int find(int id)
        {
        	if (!father.containsKey(id))
        	{
        		return -1;
        	}
        	
            int parent = father.get(id);
            
            while (parent != father.get(parent))
            {
                parent = father.get(parent);
            }
            
            while (id != father.get(id))
            {
                int temp = father.get(id);
                father.put(id, parent);
                id = temp;
            }
            
            return parent;
        }
        
        public void union(int id1, int id2)
        {
            int parent1 = find(id1);
            int parent2 = find(id2);
            
            if (parent1 != parent2)
            {
                father.put(parent1, parent2);
            }
        }
    }
}
