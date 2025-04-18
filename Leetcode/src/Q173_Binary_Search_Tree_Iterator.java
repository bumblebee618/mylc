import java.util.Stack; 
/********
 * 
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.

 * 
 * */

public class Q173_Binary_Search_Tree_Iterator {
	/*****************************************************
	 * 此题可以看作为前序遍历的拆分版： next()每次返回当前最小的值，
	 * 							 即最左边的叶子结点
	 *      
	 *****************************************************/
	
	private Stack<TreeNode> stack;
    private TreeNode root;
    
    public Q173_Binary_Search_Tree_Iterator(TreeNode root) 
    {
        stack = new Stack<>();
        this.root = root;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() 
    {
        return root != null || stack.size() != 0;
    }

    /** @return the next smallest number */
    public int next() 
    {
        while (root != null)
        {
            stack.push(root);
            root = root.left;
        }
        
        root = stack.pop();
        int val = root.val;
        root = root.right;
        return val;
    }
}
