/**********************************************************************************
 * BST递归遍历判断子树是否是合格的BST, 需要设置当前最大和最小值的范围，如果超过，则非法，否则合法
 * 
 **********************************************************************************/

public class Q333_Largest_BST_Subtree {
	private int result = 0;
	
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        dfs(root);
        return result;
    }
    
    private Tuple dfs(TreeNode node) {
        if (node == null) {
            return new Tuple(null, null, 0);
        }
        
        Tuple leftResult = dfs(node.left);
        Tuple rightResult = dfs(node.right);
        
        if (leftResult.totalCount == null || rightResult.totalCount == null) {
            return new Tuple(null, null, null);
        } else if (leftResult.maxValue != null && node.val <= leftResult.maxValue) {
            return new Tuple(null, null, null);
        } else if (rightResult.minValue != null && node.val >= rightResult.minValue) {
            return new Tuple(null, null, null);
        } else {
            int min = leftResult.minValue == null ? node.val : leftResult.minValue;
            int max = rightResult.maxValue == null ? node.val : rightResult.maxValue;
            int count = leftResult.totalCount + rightResult.totalCount + 1;
            result = Math.max(result, count);
            return new Tuple(min, max, count);
         }
    }
    
    class Tuple {
        public Integer minValue;
        public Integer maxValue;
        public Integer totalCount;
        
        public Tuple (Integer min, Integer max, Integer count) {
            minValue = min;
            maxValue = max;
            totalCount = count;
        }
    }

	
    /***
	
	// return result contains 3 elements: minVal of child, maxVal of child, count from child
	private int count = 0;
    
    public int largestBSTSubtree(TreeNode root) 
    {
        dfs(root);
        return count;
    }
    
    private Integer[] dfs(TreeNode root)
    {
    	// minvalue, maxvalue, total count of the subBST
        Integer[] result = new Integer[] {null, null, null};
        
        if (root == null)
        {
            result[2] = 0; // count of BST == 0 mean subtree is still a valid BST
            return result;
        }
        
        Integer[] left = dfs(root.left);
        Integer[] right = dfs(root.right);
        
        // not any valid sub tree found
        if (left[2] == null || right[2] == null)
        {
            return result;
        }
        else if (left[1] != null && root.val <= left[1])
        {
            return result;
        }
        else if (right[0] != null && root.val >= right[0])
        {
            return result;
        }
        else
        {
            result[0] = (left[0] == null) ? root.val : left[0];
            result[1] = (right[1] == null) ? root.val : right[1];
            result[2] = left[2] + right[2] + 1;
            count = Math.max(count, result[2]);
            return result;
        }
    }

    
    ***/
    
    
	// esay unstand
		public int largestBSTSubtree2(TreeNode root) {
	        if(root == null){
	            return 0;
	        } 
	        
	        if(isValid(root, Long.MIN_VALUE, Long.MAX_VALUE) == true){
	            return getNum(root);                                                           // 这里用的是getNum !!!
	        } else {
	            return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));  // 注意这里用的是largestBSTSubtree !!!
	        }
	    }
	    
	    public boolean isValid(TreeNode node, long min, long max){
	        if(node == null){
	            return true;
	        }
	        if(node.val >= max || node.val <= min){
	            return false;
	        }
	        return isValid(node.left, min, node.val) && isValid(node.right, node.val, max);
	    }
	    
	    public int getNum(TreeNode node){
	        if(node == null){
	            return 0;
	        } 
	        return getNum(node.left) + getNum(node.right) + 1;
	    }
	    
	    
	      
	    /***********************************************************/
	    // a little bit faster
	    public int largestBSTSubtree3(TreeNode root) {
	        if(root == null){
	            return 0;
	        } 
	        
	        int num = isValid2(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	        
	        if(num >= 0){
	            return num;
	        } else {
	            return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
	        }
	    }
	    
	    public int isValid2(TreeNode node, int min, int max){
	        if(node == null){
	            return 0;
	        }
	        if(node.val > max || node.val < min){
	            return -1;
	        }
	        
	        int leftNum = isValid2(node.left, min, node.val);
	        int rightNum = isValid2(node.right, node.val, max);
	        
	        if(leftNum < 0 || rightNum < 0){
	            return -1;
	        } else {
	            return leftNum + rightNum + 1;
	        }
	    }
}
