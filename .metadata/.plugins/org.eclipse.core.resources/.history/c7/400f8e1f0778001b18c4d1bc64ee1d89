import java.util.HashSet;
import java.util.Set;

/***
 * 
 * @author jackie
 * 
 * If the depth of a tree is smaller than 5, then this tree can be represented by a list of three-digits integers.
For each integer in this list:
	1. The hundreds digit represents the depth D of this node, 1 <= D <= 4.
	2. The tens digit represents the position P of this node in the level it belongs to, 1 <= P <= 8. The position is the same as that in a full binary tree.
	3. The units digit represents the minValue V of this node, 0 <= V <= 9.
Given a list of ascending three-digits integers representing a binary tree with the depth smaller than 5, you need to return the sum of all paths from the root towards the leaves.
It's guaranteed that the given list represents a valid connected binary tree.
Example 1:
Input: [113, 215, 221]
Output: 12
Explanation: 
The tree that the list represents is:
    3
   / \
  5   1
The path sum is (3 + 5) + (3 + 1) = 12.
 
Example 2:
Input: [113, 221]
Output: 4
Explanation: 
The tree that the list represents is: 
    3
     \
      1
The path sum is (3 + 1) = 4.

 */
public class Q666_Path_Sum_IV 
{
	private int result = 0;
	
	public int pathSum(int[] nums) 
    {
        if (nums == null || nums.length == 0)
        {
            return result;
        }
        
        int[][] tree = new int[5][9];
        Set<Integer> nodes = new HashSet<>();
        
        for (int num : nums)
        {
            int row = num / 100;
            int col = (num / 10) - row * 10;
            int value = num % 10;
            tree[row][col] = value;
            nodes.add(row * tree[0].length + col);
        }
    
        dfs(tree, 1, 1, 0, nodes);
        return result;
    }
	
	private void dfs(int[][] tree, int row, int col, int solution, Set<Integer> nodes)
	{
		solution += tree[row][col];
		int leftRow = row+1, rightRow = row+1;
        int leftCol = col * 2 - 1;
        int rightCol = col * 2;
        boolean leftNull = false, rightNull = false;
        
        if (nodes.contains(leftRow * tree[0].length + leftCol))
        {
            dfs(tree, leftRow, leftCol, solution, nodes);
        }
        else
        {
        	leftNull = true;
        }
        
        if (nodes.contains(rightRow * tree[0].length + rightCol))
        {
        	dfs(tree, rightRow, rightCol, solution, nodes); 
        }
        else
        {
        	rightNull = true;
        }
        
        if (leftNull && rightNull)
        {
        	result += solution;
        }
	}

	
	
	
	/***************************** main *****************************/ 
	
	public static void main(String[] args)
	{
		Q666_Path_Sum_IV test = new Q666_Path_Sum_IV();
		int[] nums1 = {113, 215, 221};
		int[] nums2 = {113, 221};
		int[] nums3 = {111, 217, 221, 315, 415};
		int[] nums4 = {113, 229, 330, 466};
		// System.out.println(test.pathSum(nums1));
		System.out.println(test.pathSum(nums4));
	}
}
