import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Given two binary search trees root1 and root2.

Return a list containing all the integers from both trees sorted in ascending order.

 

Example 1:


Input: root1 = [2,1,4], root2 = [1,0,3]
Output: [0,1,1,2,3,4]
Example 2:

Input: root1 = [0,-10,10], root2 = [5,1,7,0,2]
Output: [-10,0,0,1,2,5,7,10]
Example 3:

Input: root1 = [], root2 = [5,1,7,0,2]
Output: [0,1,2,5,7]
Example 4:

Input: root1 = [0,-10,10], root2 = []
Output: [-10,0,10]
Example 5:


Input: root1 = [1,null,8], root2 = [8,1]
Output: [1,1,8,8]
 

Constraints:

Each tree has at most 5000 nodes.
Each node's minValue is between [-10^5, 10^5].
 */
public class Q1305_All_Elements_in_Two_Binary_Search_Trees 
{
	private TreeNode r1 = null;
    private TreeNode r2 = null;
    private Integer num1 = null;
    private Integer num2 = null;
    private Stack<TreeNode> stack1 = new Stack<>();
    private Stack<TreeNode> stack2 = new Stack<>();
        
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) 
    {
        List<Integer> result = new LinkedList<>();
        r1 = root1;
        r2 = root2;
        refreshNext(root1, stack1, true);
        refreshNext(root2, stack2, false);
        
        while (num1 != null || num2 != null)
        {
            if (num1 != null && num2 != null)
            {
                if (num1 < num2)
                {
                    result.add(num1);
                    refreshNext(r1, stack1, true);
                }
                else
                {
                    result.add(num2);
                    refreshNext(r2, stack2, false);
                }
            }
            else if (num1 != null)
            {
                result.add(num1);
                refreshNext(r1, stack1, true);
            }
            else
            {
                result.add(num2);
                refreshNext(r2, stack2, false);
            }
        }
        
        return result;
    }
    
    private boolean hasNext(TreeNode root, Stack<TreeNode> stack)
    {
        return root != null || !stack.isEmpty();
    }

    private void refreshNext(TreeNode root, Stack<TreeNode> stack, boolean isFirst)
    {      
        Integer num = null;
        
        if (hasNext(root, stack))
        {   
            while (root != null)
            {
                stack.push(root);
                root = root.left;
            }
            
            root = stack.pop();
            num = root.val;
            root = root.right;
        }
        
        if (isFirst)
        {
            num1 = num;
            r1 = root;
        }
        else
        {
            num2 = num; 
            r2 = root;
        }
    }
}
