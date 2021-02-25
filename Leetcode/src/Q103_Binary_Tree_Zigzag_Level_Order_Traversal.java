import java.util.*;

/******
 * 
Given a binary tree, return the zigzag level order traversal of its nodes' values. 
(ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]

 * 
 * */

public class Q103_Binary_Tree_Zigzag_Level_Order_Traversal 
{
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) 
    {
        List<List<Integer>> result = new LinkedList<>();
        
        if (root == null)
        {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 1;
        
        while (!queue.isEmpty())
        {
            int size = queue.size();
            List<Integer> list = new LinkedList<>();
            
            for (int i = 0; i < size; i++)
            {
                TreeNode node = queue.poll();
                
                if (level % 2 == 1)
                {
                    list.add(node.val);
                }
                else
                {
                    list.add(0, node.val);
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
            
            level++;
            result.add(list);
        }
        
        return result;
    }
}
