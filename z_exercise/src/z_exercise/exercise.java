package z_exercise;

import java.util.Stack;


public class exercise {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void preorder(TreeNode root) {
		if (root == null) {
			return ;
		}
		
		Stack<TreeNode> stack = new Stack<>();
		
		while (root != null || !stack.isEmpty()) {
			while(root != null) {
				System.out.println(root.value);
				stack.push(root);
				root = root.left;
			}
			
			root = stack.pop();
			root = root.right;
		}
	}

	
}

class TreeNode {
	int value;
	TreeNode left;
	TreeNode right;
	
}
