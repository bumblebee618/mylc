import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
You need to find the largest value in each row of a binary tree.

Example:
Input: 

          1
         / \
        3   2
       / \   \  
      5   3   9 

Output: [1, 3, 9]
 *
 */
public class Le_515_Find_Largest_Value_in_Each_Tree_Row {
	public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int size = 1;
        int maxNum = Integer.MIN_VALUE;
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            maxNum = Math.max(maxNum, node.val);
            
            if (node.left != null) {
                queue.offer(node.left);
            }
            
            if (node.right != null) {
                queue.offer(node.right);
            }
            
            if (--size == 0) {
                size = queue.size();
                result.add(maxNum);
                maxNum = Integer.MIN_VALUE;
            }
        }
        
        return result;
    }
}
