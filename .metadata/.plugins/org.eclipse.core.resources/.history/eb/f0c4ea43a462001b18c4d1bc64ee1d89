import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
/**********
 * 
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
	Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
	Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].

 * 
 * */


public class Q057_Insert_Interval {
	// test case:
    // intervals is empty
    // newInterval == null
	
	// solution 1: using sort, time complexity is O(nlogn)
	public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0)
        {
            return new int[][] { newInterval };
        }
        else if (newInterval == null || newInterval.length == 0)
        {
            return intervals;
        }
        
        Arrays.sort(intervals, (a, b) -> (a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]));
        int start = newInterval[0];
        int end = newInterval[1];
        List<int[]> list = new LinkedList<>();
        int index = 0;
        
        while (index < intervals.length)
        {
            if (intervals[index][1] < start)
            {
                list.add(intervals[index]);
            }
            else if (intervals[index][0] > end)
            {
                break;
            }
            else
            {
                start = Math.min(start, intervals[index][0]);
                end = Math.max(end, intervals[index][1]);
            }
            
            index++;
        }
        
        list.add(new int[] {start, end});
        
        while (index < intervals.length)
        {
            list.add(intervals[index++]);
        }
        
        return list.toArray(new int[list.size()][]);
    }


		
		
		
		// follow up solution 2: time complexity is O(n)
		public List<Interval> insert2(List<Interval> intervals, Interval newInterval) {
			List<Interval> ans = new ArrayList<>();
			int len = intervals.size();
			int index = 0;
			int startPos = newInterval.start, endPos = newInterval.end;

			while (index < len) {
				Interval curInterval = intervals.get(index);

				if(curInterval.end < newInterval.start) {
					ans.add(curInterval);
					index++;
				} else {
					break;
				}
			}

			while (index < len) {
				Interval curInterval = intervals.get(index);

				if (curInterval.start <= endPos) {
					startPos = Math.min(startPos, curInterval.start);
					endPos = Math.max(endPos, curInterval.end);
					index++;
				} else {
					break;
				}
			}

			ans.add(new Interval(startPos, endPos));

			while (index < len) {
				ans.add(intervals.get(index++));
			}

			return ans;
		}
	
	
	
    
		
		
		
		
		
	
	
    /****************************** main function ***********************************/
    
    public static void main(String[] args){
    	Q057_Insert_Interval t = new Q057_Insert_Interval();
    	
    	int[][] intervals = {{1, 5}, {6, 9}};
    	int[] newInterval = new int[] {5, 6};
    	
    	int[][] ans = t.insert(intervals, newInterval);
    	System.out.println(ans[0][0] + ", " + ans[0][1]);
    }
}
