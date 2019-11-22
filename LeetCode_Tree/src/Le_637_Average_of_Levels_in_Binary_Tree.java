import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
Example 1:
Input:
    3
   / \
  9  20
    /  \
   15   7
Output: [3, 14.5, 11]
Explanation:
The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
Note:
The range of node's value is in the range of 32-bit signed integer.
 *
 */
public class Le_637_Average_of_Levels_in_Binary_Tree {
	public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) {
            return new LinkedList<Double>();
        }
        
        List<Double> result = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int size = 1;
        double sum = 0;
        int count = 1;
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            sum += node.val;
            
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (--size == 0) {
                result.add(sum / count);
                size = queue.size();
                count = size;
                sum = 0;
            }
        }
        
        return result;
    }
}
