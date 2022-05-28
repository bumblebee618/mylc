import java.util.*;
/**
 * 
Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

The root is the maximum number in the array.
The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
Construct the maximum tree by the given array and output the root node of this tree.

Example 1:
Input: [3,2,1,6,0,5]
Output: return the tree root node representing the following tree:

      6
    /   \
   3     5
    \    / 
     2  0   
       \
        1
Note:
The size of the given array will be in the range [1,1000].
 *
 */
public class Q654_Maximum_Binary_Tree {
	// solution 1: time complexity is O(nlogn), worse case is O(n^2)
	// logn level and each level it will traverse all n elements
	public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        
        return treeBuilder(nums, 0, nums.length - 1);
    }
    
    private TreeNode treeBuilder(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        
        int index = -1;
        int maxValue = Integer.MIN_VALUE;
        
        for (int i = start; i <= end; i++) {
            if (maxValue < nums[i]) {
                maxValue = nums[i];
                index = i;
            }
        }
        
        TreeNode root = new TreeNode(maxValue);
        root.left = treeBuilder(nums, start, index-1);
        root.right = treeBuilder(nums, index+1, end);
        return root;
    }
    
    
    
    
    // solution 2: time complexity is O(n)
    public TreeNode constructMaximumBinaryTree2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        
        Stack<TreeNode> stack = new Stack();
        
        for (int num : nums) {
            TreeNode node = new TreeNode(num);
            
            while (!stack.isEmpty() && stack.peek().val < num) {
                node.left = stack.pop();
            }
            
            if (!stack.isEmpty()) {
                stack.peek().right = node;
            }
            
            stack.push(node);
        }
        
        while (stack.size() > 1) {
            stack.pop();
        }
        
        return !stack.isEmpty() ? stack.pop() : null;
    }
}
