import java.util.LinkedList;
import java.util.Queue;

/***
 * 
 * @author jackie
 * 
 * Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.

The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.

Example 1:

Input: 

           1
         /   \
        3     2
       / \     \  
      5   3     9 

Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
Example 2:

Input: 

          1
         /  
        3    
       / \       
      5   3     

Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).
Example 3:

Input: 

          1
         / \
        3   2 
       /        
      5      

Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).
Example 4:

Input: 

          1
         / \
        3   2
       /     \  
      5       9 
     /         \
    6           7
Output: 8
Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).


Note: Answer will in the range of 32-bit signed integer.
 */
public class Q662_Maximum_Width_of_Binary_Tree {
	public int widthOfBinaryTree(TreeNode root) {
        if (root == null)
        {
            return 0;
        }
        
        Queue<Tuple> queue = new LinkedList<>();
        queue.offer(new Tuple(root, 0, 0));
        int left = 0;
        int curDepth = 0;
        int maxWidth = 0;
        
        while (!queue.isEmpty())
        {
            Tuple t = queue.poll();
            
            if (t.node != null)
            {
                queue.offer(new Tuple(t.node.left, t.pos*2, t.depth+1));
                queue.offer(new Tuple(t.node.right, t.pos*2+1, t.depth+1));
                
                if (curDepth != t.depth)
                {
                    curDepth = t.depth;
                    left = t.pos;
                }
            
                maxWidth = Math.max(maxWidth, t.pos-left+1);
            }
        }
        
        return maxWidth;
    }
    
    class Tuple
    {
        public TreeNode node;
        public int pos;
        public int depth;
        
        public Tuple(TreeNode node, int pos, int depth)
        {
            this.node = node;
            this.pos = pos;
            this.depth = depth;
        }
    }
}