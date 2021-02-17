import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/***
 * 
 * @author jackie
 * 
 * Consider all the leaves of a binary tree.  From left to right order, the values of those leaves form a leaf minValue sequence.



For example, in the given tree above, the leaf minValue sequence is (6, 7, 4, 9, 8).

Two binary trees are considered leaf-similar if their leaf minValue sequence is the same.

Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.

 

Note:

Both of the given trees will have between 1 and 100 nodes.
 */
public class Q872_Leaf_Similar_Trees {
	public boolean leafSimilar(TreeNode root1, TreeNode root2) 
    {
        if (root1 == null || root2 == null)
        {
            return root1 == root2;
        }
        
        String leafs1 = dfs(root1);
        String leafs2 = dfs(root2);
        return leafs1.equals(leafs2);
    }
    
    private String dfs(TreeNode root)
    {
        if (root == null)
        {
            return "";
        }
        
        Stack<TreeNode> stack = new Stack<>();
        StringBuilder builder = new StringBuilder();
        
        while (root != null || !stack.isEmpty())
        {
            while (root != null)
            {
                stack.push(root);
                root = root.left;
            }
            
            root = stack.pop();
            
            if (root.left == null && root.right == null)
            {
                builder.append(root.val).append(",");
            }
            
            root = root.right;
        }
        
        return builder.toString();
    }
}
