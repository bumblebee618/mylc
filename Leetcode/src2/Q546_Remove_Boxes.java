/***
 * 
 * @author jackie
 * 
 * You are given several boxes with different colors represented by different positive numbers.

You may experience several rounds to remove boxes until there is no box left. Each time you can choose some continuous boxes with the same color (i.e., composed of k boxes, k >= 1), remove them and get k * k points.

Return the maximum points you can get.

 

Example 1:

Input: boxes = [1,3,2,2,2,3,4,3,1]
Output: 23
Explanation:
[1, 3, 2, 2, 2, 3, 4, 3, 1] 
----> [1, 3, 3, 4, 3, 1] (3*3=9 points) 
----> [1, 3, 3, 3, 1] (1*1=1 points) 
----> [1, 1] (3*3=9 points) 
----> [] (2*2=4 points)
Example 2:

Input: boxes = [1,1,1]
Output: 9
Example 3:

Input: boxes = [1]
Output: 1
 

Constraints:

1 <= boxes.length <= 100
1 <= boxes[i] <= 100

 */
public class Q546_Remove_Boxes {
	// memo[i][j][k] represents the max points from box[i] to box[j] with k boxes whose values equal to box[i];
	private int[][][] memo;
    private int[] boxes;
    
    public int removeBoxes(int[] boxes) {
        int size = boxes.length;
        memo = new int[size][size][size];
        this.boxes = boxes;
        return search(0, size-1, 1);
    }
    
    private int search(int start, int end, int k) {
        if (start > end) {
            return 0;
        } else if (start == end) {
            return k * k;
        } else if (memo[start][end][k] != 0) {
            return memo[start][end][k];
        }
        
        int result = search(start+1, end, 1) + k * k;

        for (int i = start+1; i <= end; i++) {
            if (boxes[start] == boxes[i]) {
                result = Math.max(result, search(start+1, i-1, 1) + search(i, end, k+1));
            }
        }

        memo[start][end][k] = result;
        return memo[start][end][k];
    }
}
