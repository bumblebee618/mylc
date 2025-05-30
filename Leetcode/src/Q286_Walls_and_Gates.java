import java.util.LinkedList;
import java.util.Queue;

/************
 * 
You are given a m row n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the minValue 231 - 1 = 2147483647 
to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach 
a gate, it should be filled with INF.

For example, given the 2D grid:
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
  
 * 
 * */

public class Q286_Walls_and_Gates {
	// solution 1: bfs
	private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};
    
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) {
            return ;
        }
        
        int row = rooms.length, col = rooms[0].length;
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
            	if (rooms[i][j] == 0) {
            		queue.offer(new int[] {i, j});
            	}              
            }
        }
        
        int distance = 1;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                int[] index = queue.poll();
                
                for(int j = 0; j < dx.length; j++) {
                    int newX = index[0] + dx[j];
                    int newY = index[1] + dy[j];
            
                    if(newX >= 0 && newX < row && newY >= 0 && newY < col && rooms[newX][newY] == Integer.MAX_VALUE) {
            	        rooms[newX][newY] = distance;
                        queue.offer(new int[] {newX, newY});
                    }
                }
            }
            
            distance++;
        }
    }

    
    
	
	// solution 2:
	public void wallsAndGates2(int[][] rooms) {
        if(rooms == null || rooms.length == 0 || rooms[0].length == 0){
            return ;
        }
        
        int row = rooms.length;
        int col = rooms[0].length;

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
            	if(rooms[i][j] == 0){
            		DFS(rooms, i, j, 0);
            	}              
            }
        }
    }
    
    public void DFS(int[][] rooms, int x, int y, int distance){
        if (rooms[x][y] < distance){       // 注意这里不能有等号，否则"0"就过去不
        	return ;
        } else if (rooms[x][y] == distance && rooms[x][y] != 0) {
            return;
        }
        
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        rooms[x][y] = distance;              
        
        for(int i = 0; i < 4; i++){
            int newX = x + dx[i];
            int newY = y + dy[i];
            
            if(newX >= 0 && newX < rooms.length && newY >= 0 && newY < rooms[0].length){
            	DFS(rooms, newX, newY, distance + 1);
            }
        }
    }
    
    
    
    
    
    
    
    
    
    /*********************** main function *********************************/
    
    public static void main(String[] args){
    	Q286_Walls_and_Gates t = new Q286_Walls_and_Gates();
    	
    	int[][] rooms = {
    			{2147483647,-1,0,2147483647},
    			{2147483647,2147483647,2147483647,-1},
    			{2147483647,-1,2147483647,-1},
    			{0,-1,2147483647,2147483647}	
    	};
    	
    	t.wallsAndGates(rooms);
    	
    	for(int i = 0; i < rooms.length; i++){
    		for(int j = 0; j < rooms[0].length; j++){
    			System.out.print(rooms[i][j] + " ");
    		}
    		System.out.println();
    	}
    }
}
