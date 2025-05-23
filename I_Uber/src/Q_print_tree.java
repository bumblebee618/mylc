import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Q_print_tree {
	public List<Integer> printQueue(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		
		if (root == null) {
			return result;
		}
		
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			
			for (int i = 0; i < size; i++) {
				TreeNode curNode = queue.poll();
				
				if (i == 0) {
					result.add(0, curNode.value);
				} else if (i == size-1) {
					result.add(curNode.value);
				}
				
				if (curNode.left != null) {
					queue.offer(curNode.left);
				}
				
				if (curNode.right != null) {
					queue.offer(curNode.right);
				}
			}
		}
		
		return result;
	}
	
	static class TreeNode {
		public int value;
		public TreeNode left, right;
		
		public TreeNode(int v, TreeNode l, TreeNode r) {
			value = v;
			left = l;
			right = r;
		}
	}
	
	
	/***
	 *          5
	 *        /   \
	 *       3     7
	 *     /  \   /  \
	 *    2    4 6    8
	 */
	
	public static void main(String[] args) {
		TreeNode node2 = new TreeNode(2, null, null);
		TreeNode node4 = new TreeNode(4, null, null);
		TreeNode node6 = new TreeNode(6, null, null);
		TreeNode node8 = new TreeNode(8, null, null);
		
		TreeNode node3 = new TreeNode(3, node2, node4);
		TreeNode node7 = new TreeNode(7, node6, node8);
		
		TreeNode root = new TreeNode(5, node3, node7);

	    Q_print_tree test = new Q_print_tree();
		List<Integer> result = test.printQueue(root);
		
		for (int node : result) {
			System.out.println(node);
		}
	}
}
