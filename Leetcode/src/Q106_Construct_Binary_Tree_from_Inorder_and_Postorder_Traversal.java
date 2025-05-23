import java.util.HashMap;
import java.util.Map;
/*****
 * 
Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
	You may assume that duplicates do not exist in the tree.
 * 
 * */

public class Q106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal 
{
	private int[] postorder;
    private Map<Integer, Integer> inMap;
    
    public TreeNode buildTree(int[] inorder, int[] postorder) 
    {
        if (postorder == null || postorder.length == 0 || inorder == null || inorder.length != postorder.length)
        {
            return null;
        }
        
        this.postorder = postorder;
        inMap = new HashMap<>();
        
        for (int i = 0; i < inorder.length; i++)
        {
            inMap.put(inorder[i], i);
        }
        
        return buildTree(0, postorder.length-1, 0, inorder.length-1);
    }
    
    private TreeNode buildTree(int postStart, int postEnd, int inStart, int inEnd)
    {
        if (postStart > postEnd || inStart > inEnd)
        {
            return null;
        }
        
        int inRootPos = inMap.getOrDefault(postorder[postEnd], -1);
        
        if (inRootPos == -1)
        {
            return null;
        }
        
        int leftCount = inRootPos - inStart;
        
        TreeNode root = new TreeNode(postorder[postEnd]);
        root.left = buildTree(postStart, postStart+leftCount-1, inStart, inRootPos-1);
        root.right = buildTree(postStart+leftCount, postEnd-1, inRootPos+1, inEnd);
        
        return root;
    }
    
    
    
    
    
    
    /**************************************** main ****************************************/
    
    public static void main(String[] args){
    	Q106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal t = new Q106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal();
    	int[] inorder = {1,2,3,4,5};
    	int[] postorder = {2,3,1,5,4};
    	
    	TreeNode root = t.buildTree(inorder, postorder);   	
    }
}
