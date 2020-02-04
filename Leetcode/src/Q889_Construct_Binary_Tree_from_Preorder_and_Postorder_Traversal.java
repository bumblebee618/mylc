import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Return any binary tree that matches the given preorder and postorder traversals.

Values in the traversals pre and post are distinct positive integers.

 

Example 1:

Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
Output: [1,2,3,4,5,6,7]
 

Note:

1 <= pre.length == post.length <= 30
pre[] and post[] are both permutations of 1, 2, ..., pre.length.
It is guaranteed an answer exists. If there exists multiple answers, you can return any of them.
 */
public class Q889_Construct_Binary_Tree_from_Preorder_and_Postorder_Traversal {
	private Map<Integer, Integer> postMap;
    
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        if (pre == null || post == null || pre.length == 0 || pre.length != post.length)
        {
            return null;
        }
        
        postMap = new HashMap<>();
        
        for (int i = 0; i < post.length; i++)
        {
            postMap.put(post[i], i);
        }
        
        return buildTree(pre, 0, pre.length-1, post, 0, post.length-1);
    }
    
    private TreeNode buildTree(int[] pre, int preStart, int preEnd, int[] post, int postStart, int postEnd)
    {
        if (preStart > preEnd || postStart > postEnd)
        {
            return null;
        }
        else if (preStart == preEnd || postStart == postEnd)
        {
            return new TreeNode(pre[preStart]);
        }
        
        TreeNode root = new TreeNode(pre[preStart]);
        int leftRootPos = postMap.get(pre[preStart+1]);
        int leftLen = leftRootPos-postStart+1;
        root.left = buildTree(pre, preStart+1, preStart+leftLen, post, postStart, leftRootPos);
        root.right = buildTree(pre, preStart+leftLen+1, preEnd, post, leftRootPos+1, postEnd-1);
        return root;
    }
}
