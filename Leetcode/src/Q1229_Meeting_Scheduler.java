import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * 
 * Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration, return the earliest time slot that works for both of them and is of duration duration.
If there is no common time slot that satisfies the requirements, return an empty array.
The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to end.
It is guaranteed that no two availability slots of the same person intersect with each other. That is, for any two time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.
 
Example 1:
Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
Output: [60,68]
Example 2:
Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
Output: []
 
Constraints:
	• 1 <= slots1.length, slots2.length <= 104
	• slots1[i].length, slots2[i].length == 2
	• slots1[i][0] < slots1[i][1]
	• slots2[i][0] < slots2[i][1]
	• 0 <= slots1[i][j], slots2[i][j] <= 109
	• 1 <= duration <= 106
 *
 */
public class Q1229_Meeting_Scheduler {
	public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        List<Integer> result = new ArrayList<>();
        
        if (slots1 == null || slots1.length == 0 || slots1[0].length != 2 || slots2 == null || slots2.length == 0 || slots2[0].length != 2 || duration <= 0) {
            return result;
        }
        
        Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
        Arrays.sort(slots2, (a, b) -> a[0] - b[0]);   
        int index1 = 0, index2 = 0;
        
        while (index1 < slots1.length && index2 < slots2.length) {
            if (slots1[index1][1] <= slots2[index2][0]) {
                index1++;
            } else if (slots2[index2][1] <= slots1[index1][0]) {
                index2++;
            } else {
                int start = Math.max(slots1[index1][0], slots2[index2][0]);
                int end = Math.min(slots1[index1][1], slots2[index2][1]);
                
                if (end - start >= duration) {
                    result.add(start);
                    result.add(start+duration);
                    break;
                } 
                
                // test case: [[10,60]] [[12,17],[21,50]] 8
                if (slots1[index1][1] < slots2[index2][1]) {
                    index1++;
                } else if (slots2[index2][1] < slots1[index1][1]) {
                    index2++;
                } else {
                    index1++;
                    index2++;
                }
            }
        }
        
        return result;
    }
}
