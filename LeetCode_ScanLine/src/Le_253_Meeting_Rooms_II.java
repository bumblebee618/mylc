import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
/*******
 * 
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
find the minimum number of conference rooms required.

For example,
	Given [[0, 30],[5, 10],[15, 20]],
	return 2.
	
 * 
 * */

public class Le_253_Meeting_Rooms_II {
	// solution 1
	public int minMeetingRooms(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) {
            return 0;
        }
        
        int len = intervals.size();
        List<Tuple> tuples = new ArrayList<Tuple>(len*2);
        
        for (Interval interval : intervals) {
            tuples.add(new Tuple(interval.start, 1));
            tuples.add(new Tuple(interval.end, 0));
        }
        
        Collections.sort(tuples, new Comparator<Tuple>() {
            public int compare(Tuple t1, Tuple t2) {
                if (t1.time != t2.time) {
                    return t1.time - t2.time;
                } else {
                    return t1.startFlag - t2.startFlag;
                }
            }
        });  
        
        int count = 0;
        int maxCount = 0;
        
        for (Tuple tuple : tuples) {
            if (tuple.startFlag == 1) {
                count++;
            } else {
                count--;
            }
            
            maxCount = Math.max(maxCount, count);
        }
        
        return maxCount;
    }
    
    class Tuple {
        int time;
        int startFlag;
        
        public Tuple(int time, int startFlag) {
            this.time = time;
            this.startFlag = startFlag;
        }
    }
	
	
	
	
	
	// solution 2
	public int minMeetingRooms(Interval[] intervals) {
        if(intervals == null || intervals.length == 0){
            return 0;
        }
        
        int ans = 0;
        int count = 0;
        
        Queue<Pair> heap = new PriorityQueue<Pair>(1, new Comparator<Pair>(){
            public int compare(Pair left, Pair right){
                if(left.pos != right.pos){
                    return left.pos - right.pos;
                } else {
                    if(left.isStart == true && right.isStart == false){
                        return 1;
                    } else if(left.isStart == false && right.isStart == true){
                        return -1;
                    } else {
                        return 0;
                    }
                }
            }
        });
        
        for(Interval interval : intervals){
            heap.offer(new Pair(interval.start, true));
            heap.offer(new Pair(interval.end, false));
        }
        
        while(!heap.isEmpty()){
            Pair node = heap.poll();
            
            if(node.isStart == true){
                count++;
            } else {
                count--;
            }
            
            ans = Math.max(ans, count);
        }
        
        return ans;
    }
    
    class Pair {
        int pos;
        boolean isStart;
        
        public Pair(int pos, boolean isStart){
            this.pos = pos;
            this.isStart = isStart;
        }
    }
}
