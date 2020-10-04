import java.util.*;
/********
 * 
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

For example,
	Given [[0, 30],[5, 10],[15, 20]],
	return false.

 * 
 * */

public class Q252_Meeting_Rooms {
	// solution 1:
	public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0].length != 2)
        {
            return true;
        }
        
        Arrays.sort(intervals, (a, b) -> (a[0] != b[0]) ? a[0] - b[0] : a[1] - b[1]);
        
        for (int i = 0; i < intervals.length-1; i++)
        {
            if (intervals[i][1] > intervals[i+1][0])
            {
                return false;
            }
        }
        
        return true;
    }
    
    
    // solution 2:
    public boolean canAttendMeetings2(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0].length != 2)
        {
            return true;
        }
        
        int size = intervals.length;
        Queue<Tuple> heap = new PriorityQueue<>(size*2, new Comparator<Tuple>(){
            @Override
            public int compare(Tuple t1, Tuple t2)
            {
                if (t1.index != t2.index)
                {
                    return t1.index - t2.index;
                }
                else if (!t1.isStart && t2.isStart)
                {
                    return -1;
                }
                else if (t1.isStart && !t2.isStart)
                {
                    return 1;
                }
                else
                {
                    return 0;
                }
            }
        });
        
        for (int[] interval : intervals)
        {
            heap.offer(new Tuple(interval[0], true));
            heap.offer(new Tuple(interval[1], false));
        }
        
        int count = 0;
        
        while (!heap.isEmpty())
        {
            Tuple t = heap.poll();
            
            if (t.isStart)
            {
                count++;
            }
            else
            {
                count--;
            }
            
            if (count > 1)
            {
                return false;   
            }
        }
        
        return true;
    }
    
    class Tuple
    {
        public int index;
        public boolean isStart;
        
        public Tuple(int index, boolean isStart)
        {
            this.index = index;
            this.isStart = isStart;
        }
    }

	
	
	
	// solution 3: using sort, time is O(nlogn), space is O(n)
		public boolean canAttendMeetings3(Interval[] intervals) {
	        if (intervals == null || intervals.length == 0) {
	            return true;
	        }
	        
	        int len = intervals.length;
	        int[] begins = new int[len];
	        int[] ends = new int[len];
	        
	        for (int i = 0; i < len; i++) {
	            begins[i] = intervals[i].start;
	            ends[i] = intervals[i].end;
	        }
	        
	        Arrays.sort(begins);
	        Arrays.sort(ends);
	        
	        for (int i = 1; i < len; i++) {
	            if (begins[i] < ends[i - 1]) {
	                return false;
	            }
	        }
	        
	        return true;
	    }
}