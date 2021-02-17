import java.util.Arrays;

/***
 * 
 * @author jackie
 * 
 * You are given a tree with n nodes numbered from 0 to n-1 in the form of a parent array where parent[i] is the parent of node i. The root of the tree is node 0.

Implement the function getKthAncestor(int node, int k) to return the k-th ancestor of the given node. If there is no such ancestor, return -1.

The k-th ancestor of a tree node is the k-th node in the path from that node to the root.

 

Example:



Input:
["TreeAncestor","getKthAncestor","getKthAncestor","getKthAncestor"]
[[7,[-1,0,0,1,1,2,2]],[3,1],[5,2],[6,3]]

Output:
[null,1,0,-1]

Explanation:
TreeAncestor treeAncestor = new TreeAncestor(7, [-1, 0, 0, 1, 1, 2, 2]);

treeAncestor.getKthAncestor(3, 1);  // returns 1 which is the parent of 3
treeAncestor.getKthAncestor(5, 2);  // returns 0 which is the grandparent of 5
treeAncestor.getKthAncestor(6, 3);  // returns -1 because there is no such ancestor
 

Constraints:

1 <= k <= n <= 5*10^4
parent[0] == -1 indicating that 0 is the root node.
0 <= parent[i] < n for all 0 < i < n
0 <= node < n
There will be at most 5*10^4 queries.
 */

public class Q1483_Kth_Ancestor_of_a_Tree_Node 
{
	private int[][] dp;
    private int height;
    
    public Q1483_Kth_Ancestor_of_a_Tree_Node(int n, int[] parent) 
    {
        if (n <= 0 || parent == null || parent.length == 0)
        {
            return;
        }
        
        // height is confusing here, in fact it's the length of n in bits: 
        // we need to find 2^m >= n, so m = log2(n) -> logn/log2
        height = (int) Math.ceil(Math.log(n) / Math.log(2));  
        dp = new int[n][height+1];
        Arrays.fill(dp[0], -1);
        
        for (int i = 1; i < n; i++)
        {
            Arrays.fill(dp[i], -1);
            dp[i][0] = parent[i];
            
            for (int jump = 1; jump <= height; jump++)
            {
                dp[i][jump] = dp[dp[i][jump-1]][jump-1];
                
                if (dp[i][jump] == -1)
                {
                    break;
                }
            }
        }
    }
    
    public int getKthAncestor(int node, int k) 
    {
        for (int mask = height - 1; mask >= 0; mask--)
        {
            if ((k & (1 << mask)) > 0)
            {
                // jump to intermediate node
                node = dp[node][mask]; 
                
                // beyond root, return -1
                if (node == -1)  
                {
                    return -1;
                }
            }
        }
        
        return node;
    }
}
