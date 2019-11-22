import java.util.ArrayList;
import java.util.List;

/**
 * 
Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.

Example:

Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
Explanation:
The above output corresponds to the 5 unique BST's shown below:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 *
 */
public class Le_095_Unique_Binary_Search_Trees_II {
	/**********************************************************
	 * BST
	 * 树的组合取决于： 左子树的组合 * 右子树的组合
	 * 	(1). 由子树递推出当前树的组合个数，考虑用DP。
	 * 	(2). 从 1 trave 到 n, 列举出分别以其作为root结点的情况。
	 * 	(3). 用Divide & Conquer (递归) 先计算左右子树的组合，
	 * 		 从而得到当前需要求的组合。 
	 *  (4). 引入记忆化搜索，去除重复计算。  
	 *      
	 **********************************************************/
	// by Jackie
	private List[][] memo;
    
    public List<TreeNode> generateTrees(int n) {
        if(n <= 0){
            return new ArrayList<TreeNode>();
        }
        memo = new List[n + 1][n + 1];
        return helper(1, n);
    }
    
    public List<TreeNode> helper(int start, int end){
        List<TreeNode> ans = new ArrayList<TreeNode>();
        if(start > end){
            ans.add(null);        // 这步很重要！！！  test case: [1]
            return ans;
        } else if(memo[start][end] != null){
            return memo[start][end];
        }
       
        for(int i = start; i <= end; ++i){
            List<TreeNode> leftTree = helper(start, i - 1);
            List<TreeNode> rightTree = helper(i + 1, end);
            
            for(TreeNode l : leftTree){
                for(TreeNode r : rightTree){
                    TreeNode node = new TreeNode(i);
                    node.left = l;
                    node.right = r;
                    ans.add(node);
                }
            }
        }
        
        memo[start][end] = ans;
        return ans;
    }
    
    
    
    
    public static void main(String[] args){
    	Le_095_Unique_Binary_Search_Trees_II t = new Le_095_Unique_Binary_Search_Trees_II();
    	t.generateTrees(3);
    }
}
