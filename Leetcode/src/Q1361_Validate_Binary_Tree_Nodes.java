import java.util.*;

/***
 * 
 * @author jackie
 * 
 * You have n binary tree nodes numbered from 0 to n - 1 where node i has two children leftChild[i] and rightChild[i], return true if and only if all the given nodes form exactly one valid binary tree.

If node i has no left child then leftChild[i] will equal -1, similarly for the right child.

Note that the nodes have no values and that we only use the node numbers in this problem.

 

Example 1:



Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
Output: true
Example 2:



Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
Output: false
Example 3:



Input: n = 2, leftChild = [1,0], rightChild = [-1,-1]
Output: false
Example 4:



Input: n = 6, leftChild = [1,-1,-1,4,-1,-1], rightChild = [2,-1,-1,5,-1,-1]
Output: false
 

Constraints:

1 <= n <= 10^4
leftChild.length == rightChild.length == n
-1 <= leftChild[i], rightChild[i] <= n - 1
 */
public class Q1361_Validate_Binary_Tree_Nodes {
	public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        if (n <= 0 || leftChild == null || leftChild.length == 0 || rightChild == null || rightChild.length == 0 || leftChild.length != rightChild.length)
        {
            return false;
        }
        
        Stack<Integer> stack = new Stack<>();
        int count = 0;
        int root = 0;
        int index1 = 0, index2 = 0;
        int size = leftChild.length;
        
        while (root != -1 || !stack.isEmpty())
        {
            while (root != -1 && index1 < size)
            {
                stack.push(root);
                count++;
                root = leftChild[index1++];
            }
            
            stack.pop();
            
            if (index2 < size)
            {
                root = rightChild[index2++];
            }
        }
        
        System.out.println(count + ", " + index1 + ", " + index2);
        
        return count == n && stack.isEmpty() && index1 == size && index2 == size;
    }
	
	public boolean validateBinaryTreeNodes2(int n, int[] leftChild, int[] rightChild) {
        if (n <= 0 || leftChild == null || leftChild.length == 0 || rightChild == null || rightChild.length == 0 || leftChild.length != rightChild.length)
        {
            return false;
        }
        
        Stack<Integer> stack = new Stack<>();
        int count = 1;
        int root = 0;
        int index1 = 0, index2 = 0;
        int size = leftChild.length;
        
        while (root != -1 || !stack.isEmpty())
        {
            while (root != -1 && index1 < size)
            {
                stack.push(root);
                count++;
                root = leftChild[index1++];
            }
            
            stack.pop();
            
            if (index2 < size)
            {
                root = rightChild[index2++];
            }
        }
        
        return count == n && stack.isEmpty() && index1 == size && index2 == size;
    }

	
	
	public static void main(String[] args)
	{
		Q1361_Validate_Binary_Tree_Nodes test = new Q1361_Validate_Binary_Tree_Nodes();
		int[] leftChild = {1, -1, 3, -1};
		int[] rightChild = {2, 3, -1, -1};
		int n = 4;
		
		System.out.println(test.validateBinaryTreeNodes(n, leftChild, rightChild) == true);
	}
}