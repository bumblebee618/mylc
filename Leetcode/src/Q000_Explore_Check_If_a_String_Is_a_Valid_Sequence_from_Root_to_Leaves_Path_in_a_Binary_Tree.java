/***
 * 
 * @author jackie
 * 
 * Given a binary tree where each path going from the root to any leaf form a valid sequence, check if a given string is a valid sequence in such binary tree. 
We get the given string from the concatenation of an array of integers arr and the concatenation of all values of the nodes along a path results in a sequence in the given binary tree.
 
Example 1:

Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,1,0,1]
Output: true
Explanation: 
The path 0 -> 1 -> 0 -> 1 is a valid sequence (green color in the figure). 
Other valid sequences are: 
0 -> 1 -> 1 -> 0 
0 -> 0 -> 0
Example 2:

Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,0,1]
Output: false 
Explanation: The path 0 -> 0 -> 1 does not exist, therefore it is not even a sequence.
Example 3:

Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,1,1]
Output: false
Explanation: The path 0 -> 1 -> 1 is a sequence, but it is not a valid sequence.
 
Constraints:
	• 1 <= arr.length <= 5000
	• 0 <= arr[i] <= 9
	• Each node's minValue is between [0 - 9].
 */
public class Q000_Explore_Check_If_a_String_Is_a_Valid_Sequence_from_Root_to_Leaves_Path_in_a_Binary_Tree {
	public boolean isValidSequence(TreeNode root, int[] arr) {
        if (root == null || arr == null || arr.length == 0)
        {
            return false;
        }
        
        return isValid(root, arr, 0);
    }
    
    private boolean isValid(TreeNode node, int[] arr, int index)
    {
        if (node == null)
        {
            return false;
        }
        else if (index >= arr.length || arr[index] != node.val)
        {
            return false;
        }
        else if (index == arr.length-1)
        {
            return (node.left == null && node.right == null);
        }
        
        return isValid(node.left, arr, index+1) || isValid(node.right, arr, index+1);
    }
}
