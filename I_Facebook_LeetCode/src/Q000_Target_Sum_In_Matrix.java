import java.util.HashSet;
import java.util.Set;

public class Q000_Target_Sum_In_Matrix 
{
	public boolean findTargetSum(int[][] matrix, int target)
	{
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0 || target <= 0)
		{
			return false;
		}
		
		int row = matrix.length, col = matrix[0].length;
		int[][] dp = new int[row][col];
		
		for (int i = 0; i < row; i++)
		{
			int rowSum = 0;
			
			for (int j = 0; j < col; j++)
			{
				rowSum += matrix[i][j];
				dp[i][j] += rowSum;
				
				if (i > 0)
				{
					dp[i][j] += dp[i-1][j];
				}
			}
		}
		
		for (int startRow = 0; startRow < row; startRow++)
		{
			for (int endRow = startRow; endRow < row; endRow++)
			{
				Set<Integer> set = new HashSet<>();
				set.add(0);
				
				for (int j = 0; j < col; j++)
				{
					int part1 = startRow > 0 ? dp[startRow-1][j] : 0;
					int sum = dp[endRow][j] - part1;
					
					if (set.contains(sum - target))
					{
						System.out.println(String.format("[%s][%s]: %s, %s", endRow, j, matrix[endRow][j], dp[endRow][j]));
						return true;
					}
					
					set.add(sum);
				}
			}
		}
		
		return false;
	}
	
	
	
	
	
	/****************************** main ******************************/
	
	public static void main(String[] args)
	{
		Q000_Target_Sum_In_Matrix test = new Q000_Target_Sum_In_Matrix();
		
		int[][] matrix1 = 
		{
			{1,  2,  3,  4,  5},
			{6,  7,  8,  9,  10},
			{11, 12, 13, 14, 15}
		};
		
		int target1 = 36;
		int target2 = 4;
		int target3 = 17;
		
		System.out.println(test.findTargetSum(matrix1, target1));
		System.out.println();
		System.out.println(test.findTargetSum(matrix1, target2));
		System.out.println();
		System.out.println(test.findTargetSum(matrix1, target3));
		System.out.println();
	}
}
