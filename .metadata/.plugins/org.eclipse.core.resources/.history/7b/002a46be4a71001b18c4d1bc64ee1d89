/***
 * 
 * @author jackie
 * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:

Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
Example:

Input: root = [4,2,5,1,3], target = 3.714286

    4
   / \
  2   5
 / \
1   3

Output: 4
 */

public class Q270_Closest_Binary_Search_Tree_Value {
	// naive method is to use inorder travel, and find the node 
	// which is most close to the target node, time is O(n).
	
	// using binary search, time is O(logn)
	public int closestValue(TreeNode root, double target) {
        if (root == null)
        {
            return -1;
        }
        
        int result = 0;
        double diff = Double.MAX_VALUE;
        
        while (root != null)
        {
            if (diff > Math.abs(target - (double) root.val))
            {
                result = root.val;
                diff = Math.abs(target - (double) root.val);
            }
            
            if (target > (double) root.val)
            {
                root = root.right;
            }
            else if (target < (double) root.val)
            {
                root = root.left;
            }
            else
            {
                break;
            }
        }
        
        return result;
    }

	
	
	
	
	
	
	
	
	
	
	
	
	
	/*****************************************************************/
	// using binary search, time is O(logn)
	public int closestValue2(TreeNode root, double target) {
        double diff = Double.MAX_VALUE;
        int ans = 0;
        
        while(root != null){
            if(root.val > target){
                if(diff > Math.abs(root.val - target)){
                    diff = Math.abs(root.val - target);
                    ans = root.val;
                }
                root = root.left;
            } else if(root.val < target){
                if(diff > Math.abs(root.val - target)){
                    diff = Math.abs(root.val - target);
                    ans = root.val;
                }
                root = root.right;
            } else {
                return root.val;
            }
        }
        
        return ans;
    }
}
