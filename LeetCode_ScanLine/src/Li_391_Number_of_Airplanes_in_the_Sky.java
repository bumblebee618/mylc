import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 
 Given an interval list which are flying and landing time of the flight. How many airplanes are on the sky at most?

 Notice
If landing and flying happens at the same time, we consider landing should happen at first.

Example
For interval list

[
  (1,10),
  (2,3),
  (5,8),
  (4,7)
]
Return 3


 *
 */
public class Li_391_Number_of_Airplanes_in_the_Sky {
	public int countOfAirplanes(List<Interval> airplanes) {
        if (airplanes == null || airplanes.size() == 0) {
			return 0;
		}
		
		Queue<node> heap = new PriorityQueue<node>(1, new Comparator<node>(){
		    public int compare(node left, node right){
		        if(left.time != right.time){
		            return left.time - right.time;
		        } else {
		            if(left.isStart == false){
		                return -1;
		            } else if(right.isStart == false){
		                return 1;
		            } else {
		                return 0;
		            }
		        }
		    }
		});
		int maxLen = 0;
		int count = 0;
		
		for(Interval interval : airplanes){
		    heap.offer(new node(interval.start, true));
		    heap.offer(new node(interval.end, false));
		}
		
		while(!heap.isEmpty()){
		    node tempNode = heap.poll();
		    if(tempNode.isStart == true){
		        count++;
		    } else{
		        count--;
		    }
		    maxLen = Math.max(maxLen, count);
		}
		
		return maxLen;
    }
    
    class node{
        int time;
        boolean isStart;
        
        public node(int t, boolean i){
            time = t;
            isStart = i;
        }
    }
}
