import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * 
 * 
Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes.

Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.

If no such second minimum value exists, output -1 instead.

Example 1:
Input: 
    2
   / \
  2   5
     / \
    5   7

Output: 5
Explanation: The smallest value is 2, the second smallest value is 5.
Example 2:
Input: 
    2
   / \
  2   2

Output: -1
Explanation: The smallest value is 2, but there isn't any second smallest value.
 *
 */
public class Q671_Second_Minimum_Node_In_a_Binary_Tree {
	// solution 1: DFS
	public int findSecondMinimumValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        
        int firstMinimum = root.val;
        int secondMinimum = Integer.MAX_VALUE;
        Stack<TreeNode> stack = new Stack();
        
        while(root != null || !stack.isEmpty()) {
            while(root != null) {
                if (root.val > firstMinimum && root.val < secondMinimum) {
                    secondMinimum = root.val;
                } 
                
                stack.push(root);
                root = root.left;
            }
            
            root = stack.pop();
            root = root.right;
        }
        
        return secondMinimum == Integer.MAX_VALUE ? -1 : secondMinimum;
    }
	
	// solution 2: priority queue and can apply to Kth minimun element
	public int findSecondMinimumValue2(TreeNode root) {
        if (root == null) {
            return -1;
        }
        
        Stack<TreeNode> stack = new Stack();
        Set<Integer> set = new HashSet<>();
        Queue<Integer> heap = new PriorityQueue<Integer>(3, new Comparator<Integer>() {
            public int compare(Integer num1, Integer num2) {
                return num2 - num1;
            }
        });
        
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                if (!set.contains(root.val)) {
                    heap.offer(root.val);
                    set.add(root.val);
                
                    if (heap.size() > 2) {
                        heap.poll();
                    }
                }
                
                stack.push(root);
                root = root.left;
            }
            
            root = stack.pop();
            root = root.right;
        }
        
        return heap.size() == 2 ? heap.poll() : -1;
    }
	
	// solution 3: divide and conquer, faster
	public int findSecondMinimumValue3(TreeNode root) {
        if (root == null) {
            return -1;
        } else if (root.left == null && root.right == null) {
            return -1;
        } 
        
        int leftValue = root.left.val;
        int rightValue = root.right.val;
        
        if (leftValue == root.val) {
            leftValue = findSecondMinimumValue(root.left);
        }
        if (rightValue == root.val) {
            rightValue = findSecondMinimumValue(root.right);
        }
        
        if (leftValue != -1 && rightValue != -1) {
            return Math.min(leftValue, rightValue);
        } else if (leftValue != -1) {
            return leftValue;
        } else {
            return rightValue;
        }
    }
}
