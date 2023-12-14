import java.util.Stack;
/********
 * 
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
find the area of largest rectangle in the histogram.

For example,
Given heights = [2,1,5,6,2,3],
return 10.
 * 
 * */

public class Q084_Largest_Rectangle_in_Histogram {
	/*****************************************
	 * 单调栈
	 * 
	 *****************************************/
	
	public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        
        for (int i = 0; i <= heights.length; i++) {
        	// 利于最后一个元素出栈, 且使用 "<="
            while (!stack.isEmpty() && (i == heights.length || heights[i] <= heights[stack.peek()])) {
                int high = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i-1-stack.peek();  // 取值范围
                maxArea = Math.max(maxArea, high*width);
            }
            
            stack.push(i);
        }
        
        return maxArea;
    }
}
