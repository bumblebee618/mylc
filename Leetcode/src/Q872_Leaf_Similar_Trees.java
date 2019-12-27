import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/***
 * 
 * @author jackie
 * 
 * Consider all the leaves of a binary tree.  From left to right order, the values of those leaves form a leaf value sequence.



For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).

Two binary trees are considered leaf-similar if their leaf value sequence is the same.

Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.

 

Note:

Both of the given trees will have between 1 and 100 nodes.
 */
public class Q872_Leaf_Similar_Trees {
	public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = dfs(root1);
        List<Integer> list2 = dfs(root2);
        
        if (list1.size() != list2.size())
        {
            return false;
        }
        
        for (int i = 0; i < list1.size(); i++)
        {
            if (list1.get(i) != list2.get(i))
            {
                return false;
            }
        }
        
        return true;
    }
    
    private List<Integer> dfs(TreeNode root)
    {
        List<Integer> list = new LinkedList<>();
        
        if (root == null)
        {
            return list;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        
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
                list.add(root.val);
            }
            
            root = root.right;
        }
        
        return list;
    }
}
