import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/***
 * 
 * @author jackie
 * 
 * Given two binary search trees, return True if and only if there is a node in the first tree and a node in the second tree whose values sum up to a given integer target.

 

Example 1:



Input: root1 = [2,1,4], root2 = [1,0,3], target = 5
Output: true
Explanation: 2 and 3 sum up to 5.
Example 2:



Input: root1 = [0,-10,10], root2 = [5,1,7,0,2], target = 18
Output: false
 

Constraints:

Each tree has at most 5000 nodes.
-10^9 <= target, node.val <= 10^9
 */
public class Q1214_Two_Sum_BSTs {
	private Set<Integer> set;
    private int target = 0;
    
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        if (root1 == null || root2 == null)
        {
            return false;
        }
        
        set = new HashSet<>();
        this.target = target;
        dfs(root1, false);
        return dfs(root2, true);
    }
    
    private boolean dfs(TreeNode node, boolean isSearch)
    {
        Stack<TreeNode> stack = new Stack<>();
        
        while (node != null || !stack.isEmpty())
        {
            while (node != null)
            {
                if (!isSearch)
                {
                   set.add(node.val); 
                }
                else
                {
                    if (set.contains(target-node.val))
                    {
                        return true;
                    }
                }
                
                stack.push(node);
                node = node.left;
            }
            
            node = stack.pop();
            node = node.right;
        }
        
        return false;
    }
}
