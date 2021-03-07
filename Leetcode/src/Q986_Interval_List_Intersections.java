import java.util.LinkedList;
import java.util.List;

/***
 * 
 * @author jackie
 *
 *Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers row with a <= row <= b.  The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)

 

Example 1:



Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.
 

Note:

0 <= A.length < 1000
0 <= B.length < 1000
0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
public class Q986_Interval_List_Intersections 
{
	public int[][] intervalIntersection(int[][] Intervals1, int[][] Intervals2) 
	{
        if (Intervals1 == null || Intervals1.length == 0 || Intervals1[0].length == 0 
        	|| Intervals2 == null || Intervals2.length == 0 || Intervals2[0].length == 0)
        {
            return new int[0][0];
        }
        
        int len1 = Intervals1.length;
        int len2 = Intervals2.length;
        int index1 = 0, index2 = 0;
        List<int[]> list = new LinkedList<>();
        
        while (index1 < len1 && index2 < len2)
        {
            int start1 = Intervals1[index1][0];
            int end1 = Intervals1[index1][1];
            int start2 = Intervals2[index2][0];
            int end2 = Intervals2[index2][1];
            
            // 注意这里比较的是end，而不是start, test case: {8, 15}  {{8,10}, {12, 20}}
            if (end1 <= end2)
            {
                if(end1 >= start2)
                {
                    int start = Math.max(start1, start2);
                    int end = Math.min(end1, end2);
                    list.add(new int[] {start, end});
                }
                
                index1++;
            }
            else
            {
                if(end2 >= start1)
                {
                    int start = Math.max(start1, start2);
                    int end = Math.min(end1, end2);
                    list.add(new int[] {start, end});
                }
                
                index2++;
            }
        }
        
        return list.toArray(new int[list.size()][]);
    }
}
