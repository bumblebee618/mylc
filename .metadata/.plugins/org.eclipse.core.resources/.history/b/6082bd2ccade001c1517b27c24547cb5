import java.util.LinkedList;
import java.util.List;

/***
 * 
 * @author jackie
 * A full binary tree is a binary tree where each node has exactly 0 or 2 children.

Return a list of all possible full binary trees with N nodes.  Each element of the answer is the root node of one possible tree.

Each node of each tree in the answer must have node.val = 0.

You may return the final list of trees in any order.

 

Example 1:

Input: 7
Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
Explanation:

 

Note:

1 <= N <= 20
 * 
 */
public class Q894_All_Possible_Full_Binary_Trees 
{
	public List<TreeNode> allPossibleFBT(int N) 
	{
        if (N <= 0)
        {
            return new LinkedList<TreeNode>();
        }
        
        List<TreeNode>[] memo = new List[N];
        return buildTree(memo, 0, N-1);
    }
    
    private List<TreeNode> buildTree(List<TreeNode>[] memo, int start, int end)
    {
        if (start > end)
        {
            List<TreeNode> result = new LinkedList<>();
            result.add(null);
            return result;
        }
        else if (memo[end-start] != null)
        {
            return memo[end-start];
        }
        
        memo[end-start] = new LinkedList<>();
        
        for (int i = start; i <= end; i++)
        {
            List<TreeNode> lefts = buildTree(memo, start, i-1);
            List<TreeNode> rights = buildTree(memo, i+1, end);
            
            for (TreeNode left : lefts)
            {
                for (TreeNode right : rights)
                {
                    if ((left == null && right == null) ||  (left != null && right != null))
                    {
                        TreeNode root = new TreeNode(0);
                        root.left = left;
                        root.right = right;
                        memo[end-start].add(root);
                    }
                }
            }
        }
        
        return memo[end-start];
    }
}
