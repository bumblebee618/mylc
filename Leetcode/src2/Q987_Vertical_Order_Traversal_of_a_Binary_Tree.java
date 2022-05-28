import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

/***
 * 
 * @author jackie
 *
 *	Given a binary tree, return the vertical order traversal of its nodes values.

For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).

Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing Y coordinates).

If two nodes have the same position, then the minValue of the node that is reported first is the minValue that is smaller.

Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.

 

Example 1:



Input: [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
Explanation: 
Without loss of generality, we can assume the root node is at position (0, 0):
Then, the node with minValue 9 occurs at position (-1, -1);
The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
The node with minValue 20 occurs at position (1, -1);
The node with minValue 7 occurs at position (2, -2).
Example 2:



Input: [1,2,3,4,5,6,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation: 
The node with minValue 5 and the node with minValue 6 have the same position according to the given scheme.
However, in the report "[1,5,6]", the node minValue of 5 comes first since 5 is smaller than 6.
 

Note:

The tree will have between 1 and 1000 nodes.
Each node's minValue will be between 0 and 1000.
 */
public class Q987_Vertical_Order_Traversal_of_a_Binary_Tree {
	public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        
        if (root == null) {
            return result;
        }
        
        Queue<Tuple> queue = new LinkedList<>();
        queue.offer(new Tuple(root, 0, 0));
        Map<Integer, List<Tuple>> map = new HashMap<>();
        int leftBound = Integer.MAX_VALUE, rightBound = Integer.MIN_VALUE;
        
        while (!queue.isEmpty()) {
            Tuple t = queue.poll();
            map.computeIfAbsent(t.col, x -> new LinkedList<>()).add(t);
            leftBound = Math.min(leftBound, t.col);
            rightBound = Math.max(rightBound, t.col);
            
            if (t.node.left != null) {
                queue.offer(new Tuple(t.node.left, t.row+1, t.col-1));
            }
            
            if (t.node.right != null) {
                queue.offer(new Tuple(t.node.right, t.row+1, t.col+1));
            }
        }
        
        for (int i = leftBound; i <= rightBound; i++) {
            List<Tuple> tuples = map.get(i);
            Collections.sort(tuples, (a, b) -> a.row != b.row ? a.row - b.row : a.node.val - b.node.val);
            List<Integer> list = tuples.stream().map(t -> t.node.val).collect(Collectors.toList());
            result.add(list);
        }
        
        return result;
    }
    
    class Tuple {
        public TreeNode node;
        public int row, col;
        
        public Tuple(TreeNode n, int r, int c) {
            node = n;
            row = r;
            col = c;
        }
    }
}
