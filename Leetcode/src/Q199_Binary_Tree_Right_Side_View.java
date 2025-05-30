import java.util.*;

/******
 * 
Given a binary tree, imagine yourself standing on the right side of it, 
return the values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,
   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
You should return [1, 3, 4].

 * 
 * */

public class Q199_Binary_Tree_Right_Side_View 
{
	public List<Integer> rightSideView(TreeNode root) 
	{
        List<Integer> result = new LinkedList<>();
        
        if (root == null) 
        {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) 
        {
            int size = queue.size();
            
            for (int i = 0; i < size; i++)
            {
                TreeNode node = queue.poll();
                
                if (i == size-1)
                {
                    result.add(node.val);
                }
                
                if (node.left != null)
                {
                    queue.offer(node.left);
                }
                
                if (node.right != null)
                {
                    queue.offer(node.right);
                }
            }
        }
        
        return result;
    }
}
