import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Given an m x n matrix, return a new matrix answer where answer[row][col] is the rank of matrix[row][col].

The rank is an integer that represents how large an element is compared to other elements. It is calculated using the following rules:

The rank is an integer starting from 1.
If two elements p and q are in the same row or column, then:
If p < q then rank(p) < rank(q)
If p == q then rank(p) == rank(q)
If p > q then rank(p) > rank(q)
The rank should be as small as possible.
It is guaranteed that answer is unique under the given rules.

 

Example 1:


Input: matrix = [[1,2],[3,4]]
Output: [[1,2],[2,3]]
Explanation:
The rank of matrix[0][0] is 1 because it is the smallest integer in its row and column.
The rank of matrix[0][1] is 2 because matrix[0][1] > matrix[0][0] and matrix[0][0] is rank 1.
The rank of matrix[1][0] is 2 because matrix[1][0] > matrix[0][0] and matrix[0][0] is rank 1.
The rank of matrix[1][1] is 3 because matrix[1][1] > matrix[0][1], matrix[1][1] > matrix[1][0], and both matrix[0][1] and matrix[1][0] are rank 2.
Example 2:


Input: matrix = [[7,7],[7,7]]
Output: [[1,1],[1,1]]
Example 3:


Input: matrix = [[20,-21,14],[-19,4,19],[22,-47,24],[-19,4,19]]
Output: [[4,2,3],[1,3,4],[5,1,6],[1,3,4]]
Example 4:


Input: matrix = [[7,3,6],[1,4,5],[9,8,2]]
Output: [[5,1,4],[1,2,3],[6,3,1]]
 */
public class Q1632_Rank_Transform_of_a_Matrix {
	public int[][] matrixRankTransform(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[] rank = new int[row + col];
        Map<Integer, List<Node>> map = new TreeMap<>();
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                map.computeIfAbsent(matrix[i][j], x -> new ArrayList<>()).add(new Node(i, j));
            }
        }
        
        for (int key : map.keySet()) {
            UnionFind uf = new UnionFind(row + col);
            int[] rank2 = rank.clone();
            
            for (Node coord : map.get(key)) {
                Node res = uf.union(coord.x, coord.y + row);
                rank2[res.y] = Math.max(rank2[res.y], rank2[res.x]);
            }
            
            for (Node coord : map.get(key)) {
                matrix[coord.x][coord.y] = rank2[uf.find(coord.x)] + 1;
                rank[coord.y + row] = matrix[coord.x][coord.y];
                rank[coord.x] = rank[coord.y + row];
            }
        }
        
        return matrix;
    }

    class UnionFind {
        int[] parent;
    
        public UnionFind(int n) {
            parent = new int[n];
        
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
    
        public int find(int x) {
            return parent[x] = (parent[x] == x) ? x : find(parent[x]);
        }
    
        public Node union(int x, int y) {
            int px = find(x);
            int py = find(y);
            parent[px] = py;
            return new Node(px, py);
        }
    }
    
    class Node {
		public int x;
        public int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
         
                
            
      
	
	
	
	public static void main(String[] args) {
		int[][] matrix1 = {
				{-2,-35,-32,-5,-30,33,-12},
				{7,2,-43,4,-49,14,17},
				{4,23,-6,-15,-24,-17,6},
				{-47,20,39,-26,9,-44,39},
				{-50,-47,44,43,-22,33,-36},
				{-13,34,49,24,23,-2,-35},
				{-40,43,-22,-19,-4,23,-18}
			};
		
		int[][] matrix2 = {
				{-23,20,-49,-30,-39,-28,-5,-14},
				{-19,4,-33,2,-47,28,43,-6},
				{-47,36,-49,6,17,-8,-21,-30},
				{-27,44,27,10,21,-8,3,14},
				{-19,12,-25,34,-27,-48,-37,14},
				{-47,40,23,46,-39,48,-41,18},
				{-27,-4,7,-10,9,36,43,2},
				{37,44,43,-38,29,-44,19,38}
				};
		
		Q1632_Rank_Transform_of_a_Matrix test = new Q1632_Rank_Transform_of_a_Matrix();
		test.matrixRankTransform(matrix2);
	}
}
