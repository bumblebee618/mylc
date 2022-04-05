public class Q_quad_tree {
	public QuadTree buildQuadTree(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return null;
		}
		
		return helper(matrix, 0, 0, matrix.length);
	}
	
	private QuadTree helper(int[][] matrix, int rowStart, int colStart, int len) {
		if (len == 1) {
			return new QuadTree(true, matrix[rowStart][colStart]);
		} 
		
		QuadTree leftTop = helper(matrix, rowStart, colStart, len/2);
		QuadTree leftBottom = helper(matrix, rowStart + len/2, colStart, len/2);
		QuadTree rightTop = helper(matrix, rowStart, colStart + len/2, len/2);
		QuadTree rightBottom = helper(matrix, rowStart + len/2, colStart + len/2, len/2);
		
		if (leftTop.isLeaf && leftBottom.isLeaf && rightTop.isLeaf && rightBottom.isLeaf 
				&& leftTop.value == leftBottom.value && leftBottom.value == rightTop.value && rightTop.value == rightBottom.value) {
			return new QuadTree(true, leftTop.value);
		} else {
			QuadTree node = new QuadTree(false, -1);
			node.leftTop = leftTop;
			node.leftBottom = leftBottom;
			node.rightTop = rightTop;
			node.rightBottom = rightBottom;
			return node;
		}
	}
}

class QuadTree {
	public boolean isLeaf;
	public int value;
	public QuadTree leftTop, rightTop, leftBottom, rightBottom;
	
	public QuadTree(boolean isLeaf, int v) {
		this.isLeaf = isLeaf;
		value = v;
		leftTop = rightTop = leftBottom = rightBottom = null;
	}
}
