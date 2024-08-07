import java.util.HashMap;
import java.util.Map;
/*********
 * 
Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
	You may assume that duplicates do not exist in the tree.

 * 
 * */

public class Q105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal 
{
	private int[] preorder;
    private Map<Integer, Integer> inMap;
    
    public TreeNode buildTree(int[] preorder, int[] inorder) 
    {
        if (preorder == null || preorder.length == 0 || inorder == null || inorder.length != preorder.length)
        {
            return null;
        }
        
        this.preorder = preorder;
        inMap = new HashMap<>();
        
        for (int i = 0; i < inorder.length; i++)
        {
            inMap.put(inorder[i], i);
        }
        
        return buildTree(0, preorder.length-1, 0, inorder.length-1);
    }
    
    private TreeNode buildTree(int preStart, int preEnd, int inStart, int inEnd)
    {
        if (preStart > preEnd || inStart > inEnd)
        {
            return null;
        }
        
        int inRootPos = inMap.getOrDefault(preorder[preStart], -1);
        
        if (inRootPos == -1)
        {
            return null;
        }
        
        int leftCount = inRootPos - inStart;
        
        TreeNode root = new TreeNode(preorder[preStart]);
        root.left = buildTree(preStart+1, preStart+leftCount, inStart, inRootPos-1);
        root.right = buildTree(preStart+leftCount+1, preEnd, inRootPos+1, inEnd);
        
        return root;
    }
	
	
	
	
	
	/********** main function **************/
	public static void main(String[] args)
	{
		Q105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal t = new Q105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal();
    	int[] preorder = {1,2,3};
		int[] inorder = {1,2,3};
    	
    	TreeNode root = t.buildTree(preorder, inorder);    	
    }

}
