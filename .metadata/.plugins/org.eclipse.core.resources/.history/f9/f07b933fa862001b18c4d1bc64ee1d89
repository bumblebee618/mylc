import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;


public class Q145_Binary_Tree_Postorder_Traversal {
	// solution 1:
	public List<Integer> postorderTraversal(TreeNode root) 
    {
        List<Integer> list = new LinkedList<>();
        
        if (root == null)
        {
            return list;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        Set<TreeNode> visited = new HashSet<>();
        
        while (!stack.isEmpty())
        {
            TreeNode node = stack.peek();
            
            if (node.left != null && !visited.contains(node.left))
            {
                node = node.left;
                
                while (node != null && !visited.contains(node))
                {
                    stack.push(node);
                    node = node.left;
                }
            }
            else if (node.right != null && !visited.contains(node.right))
            {
                stack.push(node.right);
            }
            else
            {
                TreeNode t = stack.pop();
                list.add(t.val);
                visited.add(t);
            }
        }
        
        return list;
    }
	
	
	// solution 2:
	public List<Integer> postorderTraversal2(TreeNode root) {
		List<Integer> result = new ArrayList<>();
        
        if (root == null)
        {
            return result;
        }
        
        Stack<TreeNode> s1 = new Stack<>();
        s1.push(root);
        
        while (!s1.isEmpty())
        {
            TreeNode node = s1.pop();
            result.add(0, node.val);
            
            if (node.left != null)
            {
                s1.push(node.left);
            }
            
            if (node.right != null)
            {
                s1.push(node.right);
            }
        }
        
        return result;
    }
    
    public List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> ans = new ArrayList<Integer>();
        
        if(root == null){
            return ans;
        }
        
        Stack<TreeNode> stack = new Stack<TreeNode>();  
        Set<TreeNode> visited = new HashSet<TreeNode>();
        stack.push(root);
        
        while(!stack.isEmpty()){
            TreeNode node = stack.peek();
            
            if(node.left != null && !visited.contains(node.left)){
                node = node.left;
                
                while(node != null && !visited.contains(node)){
                    stack.push(node);
                    node = node.left;
                }
                
                continue;
            }
            
            if(node.right != null && !visited.contains(node.right)){
                stack.push(node.right);
                continue;
            }
            
            node = stack.pop();
            visited.add(node);
            ans.add(node.val);
        }
        
        return ans;
    }
	
    
	//use two stacks
	public ArrayList<Integer> postorderTraversal4(TreeNode root) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		if (root == null) return res;
		Stack<TreeNode> s1 = new Stack<TreeNode>();
		Stack<TreeNode> s2 = new Stack<TreeNode>();
		s1.push(root);

		while (!s1.isEmpty()) {
			TreeNode node = s1.pop();
			s2.push(node);
			if (node.left != null) s1.push(node.left);
			if (node.right != null) s1.push(node.right);
		}
		
		while (!s2.isEmpty())
			res.add(s2.pop().val);

		return res;
	}
	
	//recursive
	public ArrayList<Integer> postorderTraversal_recursive(TreeNode root) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		ArrayList<Integer> temp = new ArrayList<Integer>();   //必须要使用一个temp来接受传递出来的值
        
        if(root != null){          
            temp = postorderTraversal_recursive(root.left);
            for(int i = 0; i < temp.size(); i++)
                res.add(temp.get(i));
            temp.clear();
            temp = postorderTraversal_recursive(root.right);
            for(int i = 0; i < temp.size(); i++)
                res.add(temp.get(i));
            temp.clear();
            res.add(root.val);
        }
        return res;
	}
}
