/******
 *
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a 
constant space solution?

 * 
 * */

public class Q099_Recover_Binary_Search_Tree {
	/***********************************************
	 * 递归版的前序遍历的结构：
	 * 	travel(node.left)
	 * 		...一些列操作
	 * 	travel(node.right);
	 * 
	 ***********************************************/	
	
    private TreeNode firstElement = null;   // 给定初始值null, 用于后续判断
    private TreeNode secondElement = null;  // 给定初始值null, 用于后续判断
    private TreeNode preElement = null;
    
	public void recoverTree(TreeNode root) 
	{
		inorder(root);
        
        if (firstElement != null && secondElement != null) 
        {
            int temp = firstElement.val;
            firstElement.val = secondElement.val;
            secondElement.val = temp;
        }
    }
    
    private void inorder(TreeNode node)
    {
        if (node == null)
        {
            return;
        }
        
        inorder(node.left);
        
        // 找到第一个元素
        if (firstElement == null && preElement != null && node.val < preElement.val)  
        {
        	firstElement = preElement;
        }   
        
        // 注意，这里用firstElement != null
        // 找到第二个元素, 此处不可以用return，因为不用else，因此两个if有可能在同一次递归里被调用, 例如[3,2]的情况
        if (firstElement != null && node.val < preElement.val)
        {  
            secondElement = node;     
        }
        
        preElement = node;    // 执行完后才进行preElement的update， 等同于操作之后，进行node = node.next
        inorder(node.right);
    }
}
