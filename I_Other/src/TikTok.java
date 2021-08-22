import javax.security.auth.x500.X500Principal;
import javax.swing.tree.TreeNode;

import java.util.*;

public class TikTok {
	public boolean splitArray(int N, List<int[]> limits) {
		Integer[] status = new Integer[N+1];
		
		for (int[] limit : limits) {
			int num1 = limit[0], num2 = limit[1];
			limit[0] = Math.min(num1, num2);
			limit[1] = Math.max(num1, num2);
		}
		
		Collections.sort(limits, new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return a[0] != b[0] ? a[0] - b[0] : a[1] - b[1];
			}
		});
		
		for (int[] limit : limits) {
			if (status[limit[0]] == null && status[limit[1]] == null) {
				status[limit[0]] = 0;
				status[limit[1]] = 1;
			}
			else if (status[limit[0]] == null) {
				status[limit[0]] = 1-status[limit[1]];
			}
			else if (status[limit[1]] == null) {
				status[limit[1]] = 1-status[limit[0]];
			}
			else {
				if (status[limit[0]] == status[limit[1]]) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	
	
	public boolean splitArray2(int N, List<int[]> unfollows) {
		Set<Integer>[] graph = new Set[N+1];
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new HashSet<>();
		}
		
		for (int[] unfollow : unfollows) {
			graph[unfollow[0]].add(unfollow[1]);
			graph[unfollow[1]].add(unfollow[0]);
		}

         		
		Integer[] status = new Integer[N+1];
		
		for (int i = 1; i <= N; i++) {
            if (status[i] != null) {
                continue;
            }
            
            // bfs
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            status[i] = 0;
            
            while (!queue.isEmpty())
            {
                int node = queue.poll();
                
                for (int neighbor : graph[node])
                {
                    if (status[neighbor] == null)
                    {
                        status[neighbor] = status[node] ^ 1;
                        queue.offer(neighbor);
                    }
                    else if (status[node] == status[neighbor])
                    {
                        return false;
                    }
                }
            } 
        }
        
        return true;
	}
	
	
	
	public void printTree(TreeNode root) {
		if (root == null) {
			return;
		}
		
		System.out.print(root.value + ", ");
		dfs(root, 0, new int[] {0}, true);
		System.out.println();
		dfs(root, 0, new int[] {0}, false);
	}
	
	private void dfs(TreeNode node, int curDepth, int[] maxDepth, boolean isLeft) {
		if (node == null) {
			return;
		}
		
		if (curDepth > maxDepth[0]) {
			maxDepth[0] = curDepth;
			System.out.print(node.value + ", ");
		}
		
		if (isLeft) {
			dfs(node.left, curDepth+1, maxDepth, isLeft);
			dfs(node.right, curDepth+1, maxDepth, isLeft);
		}
		else {
			dfs(node.right, curDepth+1, maxDepth, isLeft);
			dfs(node.left, curDepth+1, maxDepth, isLeft);
		}
	}
	
	
	
	
	class TreeNode {
		public int value;
		public TreeNode left, right;
	}
	
	/**************************** main ***************************/
	
	public static void main(String[] args) {
		TikTok test = new TikTok();
		int N = 8;
		List<int[]> limits = new LinkedList<int[]>();
		limits.add(new int[] {1, 6});
		limits.add(new int[] {2, 8});
		limits.add(new int[] {6, 8});
		System.out.println(test.splitArray2(N, limits));
	}
}
