import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Uber_Stretch_Tree {
	public TreeNode StretchTree(TreeNode root, int n) {		
		TreeNode dummy = new TreeNode(0, root, null);
		dfs(root, dummy, n);
		return dummy.left;
	}
	
	private void dfs(TreeNode node, TreeNode parent, int n) {
		if (node == null || n == 1) {
			return;
		}
		
		dfs(node.left, node, n);
		dfs(node.right, node, n);
		
		TreeNode head = new TreeNode(node.val, null, null);
		TreeNode pointer = head;
		
		for (int i = 1; i < n-1; i++) {
			TreeNode newNode = new TreeNode(node.val, null, null);
			
			if (parent.left == node) {
				pointer.left = newNode;
				pointer = pointer.left;
			} else {
				pointer.right = newNode;
				pointer = pointer.right;
			}
		}
		
		if (parent.left == node) {
			parent.left = head;
			pointer.left = node;
		} else {
			parent.right = head;
			pointer.right = node;
		}
	}
	
	
	
	
	public void printTree_BSF(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				System.out.print(node.val + ", ");
				
				if (node.left != null) {
					queue.offer(node.left);
				}
				
				if (node.right != null) {
					queue.offer(node.right);
				}
			}
			
			System.out.println();
		}
	}
	
	public void printTree_DSF(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		
		while (!stack.isEmpty() || root != null) {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			
			root = stack.pop();
			System.out.println(root.val);
			root = root.right;
		}
	}
	
	
	public static void main(String[] args) {
		Uber_Stretch_Tree test = new Uber_Stretch_Tree();
		TreeNode root = new TreeNode(1, null, null);
		
		TreeNode node2 = new TreeNode(2, null, null);
		root.left = node2;
		
		TreeNode node3 = new TreeNode(3, null, null);
		root.right = node3;
		
		TreeNode node4 = new TreeNode(4, null, null);
		node3.right = node4;
		
		TreeNode result = test.StretchTree(root, 2);
		
		test.printTree_DSF(result);
	}
}

class TreeNode {
	public int val;
	public TreeNode left, right;
	
	public TreeNode (int v, TreeNode l, TreeNode r) {
		val = v;
		left = l;
		right = r;
	}
}
