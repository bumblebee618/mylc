/***
 * 
 * @author jackie
 * 
 * Given the root of a binary search tree and the lowest and highest boundaries as low and high, trim the tree so that all its elements lies in [low, high]. You might need to change the root of the tree, so the result should return the new root of the trimmed binary search tree.

 

Example 1:


Input: root = [1,0,2], low = 1, high = 2
Output: [1,null,2]
Example 2:


Input: root = [3,0,4,null,2,null,null,1], low = 1, high = 3
Output: [3,2,null,1]
Example 3:

Input: root = [1], low = 1, high = 2
Output: [1]
Example 4:

Input: root = [1,null,2], low = 1, high = 3
Output: [1,null,2]
Example 5:

Input: root = [1,null,2], low = 2, high = 4
Output: [2]
 

Constraints:

The number of nodes in the tree in the range [1, 104].
0 <= Node.val <= 104
The minValue of each node in the tree is unique.
root is guaranteed to be a valid binary search tree.
0 <= l <= r <= 104
 */
public class Q669_Trim_a_Binary_Search_Tree {
	public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null)
        {
            return null;
        }
        
        if (root.val > high)
        {
            return trimBST(root.left, low, high);
        }
        else if (root.val < low)
        {
            return trimBST(root.right, low, high);
        }
        else
        {
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
            return root; 
        }
    }
}
