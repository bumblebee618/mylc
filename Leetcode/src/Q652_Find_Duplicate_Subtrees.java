import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/***
 * 
 * @author jackie
 * 
 * Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with same node values.

Example 1:

        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
The following are two duplicate subtrees:

      2
     /
    4
and

    4
Therefore, you need to return above trees' root in the form of a list.
 */
public class Q652_Find_Duplicate_Subtrees {
	private List<TreeNode> result = new LinkedList<>();
    private Map<String, Integer> map;
    
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        if (root == null)
        {
            return result;
        }
        
        map = new HashMap<>();
        dfs(root);
        return result;
    }
    
    private String dfs(TreeNode node)
    {
        if (node == null)
        {
            return "#";
        }
        
        StringBuilder builder = new StringBuilder();
        builder.append(node.val).append(dfs(node.left)).append(dfs(node.right));
        String str = builder.toString();
        map.put(str, map.getOrDefault(str, 0)+1);
        
        if (map.get(str) == 2)
        {
            result.add(node);
        }
        
        return str;
    }
}
