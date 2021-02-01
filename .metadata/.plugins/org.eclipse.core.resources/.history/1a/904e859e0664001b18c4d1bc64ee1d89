import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;
/*******
 * 
Given a collection of intervals, merge all overlapping intervals.

For example,
	Given [1,3],[2,6],[8,10],[15,18],
	return [1,6],[8,10],[15,18].
	
 * 
 * */

public class Q056_Merge_Intervals {
	// test case:
    // intervals is empty
    // intervals is sorted?
    // invalid input: interval.start >= intervals.end
	
	public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0)
        {
            return new int[0][0];
        }
        
        Arrays.sort(intervals, (a, b) -> (a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]));
        Integer start = null, end = null;
        List<int[]> list = new ArrayList<>();
        
        for (int[] interval : intervals)
        {
            if (start == null && end == null)
            {
                start = interval[0];
                end = interval[1];
                continue;
            }
            
            if (interval[0] <= end)
            {
                start = Math.min(start, interval[0]);
                end = Math.max(end, interval[1]);
            }
            else
            {
                list.add(new int[] {start, end});
                start = interval[0];
                end = interval[1];
            }
        }
        
        list.add(new int[] {start, end});
        return list.toArray(new int[list.size()][]);
    }


	
    
    
	
	
	/******************************* main function *************************************/
	
    public static void main(String[] args){
    	Q056_Merge_Intervals t = new Q056_Merge_Intervals();
    	ArrayList<Interval> list = new ArrayList<Interval>();
    	int[][] array = {
//    			{1,3},
//    			{2,6},
//    			{8,10},
//    			{15,18}
//    			{1,4},
//    			{2,3}
    			{2,3},
    			{4,5},
    			{6,7},
    			{8,9},
    			{1,10}
    	};
    	
    	for(int i = 0; i < array.length; ++i){
    		list.add(new Interval(array[i][0], array[i][1]));
    	}
    	
    	/***
    	List<Interval> ans = t.merge(list);
    	
    	for(int i = 0; i < ans.size(); ++i){
    		System.out.print("[" + ans.get(i).start + ", " + ans.get(i).end + "], ");
    	}
    	***/
    }
}
