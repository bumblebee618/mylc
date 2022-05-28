import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/***
 * 
 * 
 * 
 * Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.
 

For example:
Given BST [1,null,2,2],

   1
    \
     2
    /
   2
 

return [2].

Note: If a tree has more than one mode, you can return them in any order.

Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
 *
 */
public class Q501_Find_Mode_In_Binary_Search_Tree {
	public int[] findMode(TreeNode root) {
		if (root == null)
        {
            return new int[] {};
        }
        
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new LinkedList<>();
        Integer preValue = null;
        int count = 0;
        int mode = 0;
        
        while (root != null || !stack.isEmpty())
        {
            while (root != null)
            {
                stack.push(root);
                root = root.left;
            }
            
            root = stack.pop();
            
            if (preValue != null && preValue == root.val)
            {
                count++;
            }
            else
            {
                count = 1;
            }
            
            if (mode < count)
            {
                mode = count;
                list.clear();
                list.add(root.val);
            }
            else if (mode == count)
            {
                list.add(root.val);
            }
            
            preValue = root.val;
            root = root.right;
        }
        
        return list.stream().mapToInt(i->i).toArray();
    }
	
	
	private Integer preNum = null;
    private int mode = 0;
    private int count = 1;
    
    public int[] findMode2(TreeNode root) {
        if (root == null)
        {
            return new int[0];
        }
        
        List<Integer> list = new LinkedList<>();
        inorder(root, list);
        return list.stream().mapToInt(i->i).toArray();
    }
    
    private void inorder(TreeNode node, List<Integer> list)
    {
        if (node == null)
        {
            return;
        }
        
        inorder(node.left, list);
        
        if (preNum != null)
        {
            if (preNum == node.val)
            {
                count++;
            }
            else
            {
                count = 1;
            }
        }
        
        if (mode < count)
        {
            mode = count;
            list.clear();
            list.add(node.val);
        }
        else if (mode == count)
        {
            list.add(node.val);
        }
        
        preNum = node.val;
        inorder(node.right, list);
    }
}
