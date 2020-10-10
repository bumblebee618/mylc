import java.util.Arrays;
import java.util.Comparator;

/***
 * 
 * @author jackie
 * 
 * You are driving a vehicle that has capacity empty seats initially available for passengers.  The vehicle only drives east (ie. it cannot turn around and drive west.)

Given a list of trips, trip[i] = [num_passengers, start_location, end_location] contains information about the i-th trip: the number of passengers that must be picked up, and the locations to pick them up and drop them off.  The locations are given as the number of kilometers due east from your vehicle's initial location.

Return true if and only if it is possible to pick up and drop off all passengers for all the given trips. 

 

Example 1:

Input: trips = [[2,1,5],[3,3,7]], capacity = 4
Output: false
Example 2:

Input: trips = [[2,1,5],[3,3,7]], capacity = 5
Output: true
Example 3:

Input: trips = [[2,1,5],[3,5,7]], capacity = 3
Output: true
Example 4:

Input: trips = [[3,2,7],[3,7,9],[8,3,9]], capacity = 11
Output: true
 
 

Constraints:

trips.length <= 1000
trips[i].length == 3
1 <= trips[i][0] <= 100
0 <= trips[i][1] < trips[i][2] <= 1000
1 <= capacity <= 100000
 */
public class Q1094_Car_Pooling {
	public boolean carPooling(int[][] trips, int capacity) {
        if (trips == null || trips.length == 0 || trips[0].length == 0 || capacity <= 0)
        {
            return false;
        }
        
        Tuple[] tuples = new Tuple[trips.length*2];
        int index = 0;
        
        for (int[] trip : trips)
        {
            tuples[index++] = new Tuple(trip[0], trip[1], true);
            tuples[index++] = new Tuple(trip[0], trip[2], false);
        }

        Arrays.sort(tuples, new Comparator<Tuple>(){
            @Override
            public int compare(Tuple t1, Tuple t2)
            {
                if (t1.loc != t2.loc)
                {
                    return t1.loc - t2.loc;
                }
                else if (!t1.isStart)
                {
                    return -1;
                }
                else if (!t2.isStart)
                {
                    return 1;
                }
                else
                {
                    return 0;
                }
            }
        });
        
        int curCap = 0;
        
        for (Tuple t : tuples)
        {
            if (t.isStart)
            {
                curCap += t.num;
            }
            else
            {
                curCap -= t.num;
            }
            
            if (curCap > capacity)
            {
                return false;
            }
        }
        
        return true;
    }
    
    class Tuple
    {
        int num;
        int loc;
        boolean isStart;
        
        public Tuple(int num, int loc, boolean isStart)
        {
            this.num = num;
            this.loc = loc;
            this.isStart = isStart;
        }
    }
}
