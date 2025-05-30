import java.util.*;

/******
 * 
You are given a binary tree in which each node contains an integer minValue.

Find the number of paths that sum to a given minValue.

The path does not need to start or end at the root or a leaf, 
but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11

 * 
 * */

public class Q437_Path_Sum_III {
	public int pathSum(TreeNode root, int sum) 
	{
        if (root == null) 
        {
            return 0;
        }
        
        int totalCount = dfs(root, 0, sum);
        return totalCount + pathSum(root.left, sum) + pathSum(root.right, sum);  // 更换起始点
    }
    
    private int dfs(TreeNode node, int curSum, int sum) 
    {
        if (node == null) 
        {
            return 0;
        } 
        
        int count = 0;
        
        if (curSum + node.val == sum) 
        {
            count++;
        }
        
        count += dfs(node.left, curSum + node.val, sum);
        count += dfs(node.right, curSum + node.val, sum);
        return count;
    }
    
    public int pathSum2(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }

        Map<Long, Integer> map = new HashMap<>();
        map.put(0l, 1);
        return dfs(root, 0, targetSum, map);
    }

    private int dfs(TreeNode node, long sum, int targetSum, Map<Long, Integer> map) {
        if (node == null) {
            return 0;
        }

        sum += node.val;
        int count = map.getOrDefault(sum - targetSum, 0);
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        count += dfs(node.left, sum, targetSum, map);
        count += dfs(node.right, sum, targetSum, map);
        map.put(sum, map.getOrDefault(sum, 0) - 1);

        if (map.get(sum) == 0) {
            map.remove(sum);
        } 

        return count;
    }
}
