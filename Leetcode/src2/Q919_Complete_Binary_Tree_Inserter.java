import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/***
 * 
 * @author jackie
 * 
 * A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled, and all nodes are as far left as possible.

Write a data structure CBTInserter that is initialized with a complete binary tree and supports the following operations:

CBTInserter(TreeNode root) initializes the data structure on a given tree with head node root;
CBTInserter.insert(int v) will insert a TreeNode into the tree with minValue node.val = v so that the tree remains complete, and returns the minValue of the parent of the inserted TreeNode;
CBTInserter.get_root() will return the head node of the tree.
 

Example 1:

Input: inputs = ["CBTInserter","insert","get_root"], inputs = [[[1]],[2],[]]
Output: [null,1,[1,2]]
Example 2:

Input: inputs = ["CBTInserter","insert","insert","get_root"], inputs = [[[1,2,3,4,5,6]],[7],[8],[]]
Output: [null,3,4,[1,2,3,4,5,6,7,8]]
 

Note:

The initial given tree is complete and contains between 1 and 1000 nodes.
CBTInserter.insert is called at most 10000 times per test case.
Every minValue of a given or inserted node is between 0 and 5000.
 * 
 */
public class Q919_Complete_Binary_Tree_Inserter 
{
	private TreeNode root = null;
    private Queue<TreeNode> parents = null;
    private Queue<TreeNode> leaves = null;
    
    public Q919_Complete_Binary_Tree_Inserter(TreeNode root) 
    {
        if (root == null)
        {
            return;
        }
        
        this.root = root; 
        bfs(root);
    }
    
    private void bfs(TreeNode root)
    {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        Map<Integer, Queue<TreeNode>> levelMap = new HashMap<>();
        int level = 0;
        
        while (!queue.isEmpty())
        {
            int size = queue.size();
            level++;
            Queue<TreeNode> tmpQueue = new LinkedList<>();
            
            for (int i = 0; i < size; i++)
            {
                TreeNode node = queue.poll();
                tmpQueue.offer(node);
                
                if (node.left != null)
                {
                    queue.offer(node.left);
                }
                
                if (node.right != null)
                {
                    queue.offer(node.right);
                }
            }
            
            levelMap.put(level, tmpQueue);
        }
        
        // only have root
        if (level == 1)
        {
            parents = levelMap.get(level);
            leaves = new LinkedList<>();
        }
        else
        {
            parents = levelMap.get(level-1);
            leaves = levelMap.get(level);
        }
    }
    
    public int insert(int v) 
    {
        while (!parents.isEmpty() && parents.peek().left != null && parents.peek().right != null)
        {
            parents.poll();
        }
        
        if (parents.isEmpty())
        {
            parents = new LinkedList<>(leaves);
            leaves = new LinkedList<>();
        }
        
        TreeNode insert = new TreeNode(v);
        leaves.offer(insert);
        TreeNode parent = parents.peek();     
        
        if (parent.left == null)
        {
            parent.left = insert; 
        }
        else
        {
            parent.right = insert; 
            parents.poll();
        }
        
        return parent.val;
    }
    
    public TreeNode get_root() 
    {
        return root;
    }
}
