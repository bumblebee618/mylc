/***
 * 
 * Given a matrix of direction with L, R, U, D, at any point you can move to the direction which is written over the cell [i, j]. We have to tell minimum number of modifications to reach from [0, 0] to [N - 1, M - 1] .
Example :-
R R D
L L L
U U R
Answer is 1, we can modify cell [1, 2] from L To D.
 * 
 */

import java.util.HashMap;
import java.util.Map;

public class Q_Find_A_Shortest_Path_In_Matrix {
	private int[] dx = {-1, 1, 0, 0};
	private int[] dy = {0, 0, -1, 1};
	
	public int findMinModify(char[][] directs)
	{
		if (directs == null || directs.length == 0 || directs[0].length == 0)
		{
			return 0;
		}
		
		int row = directs.length;
		int col = directs[0].length;
		int[][] steps = new int[row][col];
		
		Map<Character, Integer> map = new HashMap<>();
		map.put('U', 0);
		map.put('D', 1);
		map.put('L', 2);
		map.put('R', 3);
		
		for (int i = 0; i < row; i++)
		{
			for (int j = 0; j < row; j++)
			{
				steps[i][j] = Integer.MAX_VALUE; 
			}
		}
		
		steps[0][0] = 0;
		dfs(directs, steps, map, 0, 0);
		
		printMatrix(steps);
		
		return steps[row-1][col-1];
	}
	
	private void dfs(char[][] directs, int[][] steps, Map<Character, Integer> map , int x, int y)
	{
		int currentStep = steps[x][y];
		int directIndex = map.get(directs[x][y]);
		
		for (int i = 0; i < dx.length; i++)
		{
			int newX = x + dx[i];
			int newY = y + dy[i];
			
			if (newX >= 0 && newX < directs.length && newY >= 0 && newY < directs[0].length)
			{
				int cost = directIndex == i ? 0 : 1;
				int nextStep = currentStep + cost;
				
				if (steps[newX][newY] > nextStep)
				{
					steps[newX][newY] = nextStep;
					dfs(directs, steps, map, newX, newY);
				}
			}
		}
	}
	
	private void printMatrix(int[][] steps)
	{
		for (int i = 0; i < steps.length; i++)
		{
			for (int j = 0; j < steps[0].length; j++)
			{
				System.out.print(steps[i][j] + ", ");
			}
			
			System.out.println();
		}
	}
	
	
	public static void main(String[] args)
	{
		Q_Find_A_Shortest_Path_In_Matrix test = new Q_Find_A_Shortest_Path_In_Matrix();
		char[][] directs = {{'R', 'R', 'D'}, {'L', 'L', 'L'}, {'U', 'U', 'R'}};
		System.out.println(test.findMinModify(directs));
	}
}
