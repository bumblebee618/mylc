import java.util.LinkedList;
import java.util.List;
/****
 * 
Given a binary tree and a sum, find all root-to-leaf paths where each path's 
sum equals the given sum.

For example:
	Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]

 * 
 * */

public class Q113_Path_Sum_II {
	/*************************************
	 * corner case: [-2, null, -3, null] -5
	 * 
	 *************************************/
	// using dfs
	public List<List<Integer>> pathSum(TreeNode root, int sum) 
	{
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        
        if (root == null) 
        {
            return result;
        }
        
        dfs(root, result, new LinkedList<Integer>(), 0, sum);
        return result;
    }
    
    private void dfs(TreeNode node, List<List<Integer>> result, List<Integer> solution, int curSum, int target) 
    {
        if (node == null) 
        {
            return;
        }  
        
        solution.add(node.val);
        
        if (node.left == null && node.right == null && curSum + node.val == target) 
        {
            result.add(new LinkedList<Integer>(solution));
        } 
        
        dfs(node.left, result, solution, curSum + node.val, target);
        dfs(node.right, result, solution, curSum + node.val, target);
        
        solution.remove(solution.size() - 1);
    }
	
	
	
   
    
    
    
    
    /************************* main function ******************************/
    public static void main(String[] args){
    	TreeNode root = new TreeNode(5);   	
    	
    	root.left = new TreeNode(4);
    	root.left.left = new TreeNode(11);
    	root.left.left.left = new TreeNode(7);
    	root.left.left.right = new TreeNode(2);
    	
    	root.right = new TreeNode(8);
    	root.right.left = new TreeNode(13);
    	root.right.right = new TreeNode(4);
    	root.right.right.left = new TreeNode(5);
    	root.right.right.right = new TreeNode(1);
    	
    	Q113_Path_Sum_II t = new Q113_Path_Sum_II();
    	List<List<Integer>> res = t.pathSum(root, 22);
    	
    	for(int i = 0; i < res.size(); ++i){
    		for(int j = 0; j < res.get(i).size(); ++j){
    			System.out.print(res.get(i).get(j) + ", ");
    		}
    		System.out.println();
    	}
    }
}
