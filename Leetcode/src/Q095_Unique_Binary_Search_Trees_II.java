import java.util.LinkedList;
import java.util.List;
/*******
 * 
Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
   
 * 
 * */

public class Q095_Unique_Binary_Search_Trees_II {
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
private List<TreeNode>[][] memo;
    
    public List<TreeNode> generateTrees(int n) {
        if (n <= 0) {
            return new LinkedList<>();
        }
        
    	// because the label is from 1 to n, so the lenght should be n + 1 !!!
        memo = new List[n+1][n+1];
        return search(1, n);
    }
    
    private List<TreeNode> search(int start, int end) {
        if (start > end) {
            List<TreeNode> list = new LinkedList<>();
            list.add(null);     // this step is important !!!
            return list;
        } else if (memo[start][end] != null) {
            return memo[start][end];   
        }
        
        memo[start][end] = new LinkedList<TreeNode>();
        
        for (int i = start; i <= end; i++) {
            List<TreeNode> lefts = search(start, i-1);
            List<TreeNode> rights = search(i+1, end);
            
            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    memo[start][end].add(root);
                }
            }
        }

        return memo[start][end];
    }
	
	
    
    
    
    
    /************************************ main function ************************************/
    
    public static void main(String[] args){
    Q095_Unique_Binary_Search_Trees_II t = new Q095_Unique_Binary_Search_Trees_II();
    	t.generateTrees(3);
    }
}
