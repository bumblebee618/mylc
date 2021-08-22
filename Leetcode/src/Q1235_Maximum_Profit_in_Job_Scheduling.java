import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import javax.sound.sampled.Port;

/***
 * 
 * @author jackie
 * 
 * We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].

You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.

If you choose a job that ends at time X you will be able to start another job that starts at time X.

 

Example 1:



Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
Output: 120
Explanation: The subset chosen is the first and fourth job. 
Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
Example 2:



Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
Output: 150
Explanation: The subset chosen is the first, fourth and fifth job. 
Profit obtained 150 = 20 + 70 + 60.
Example 3:



Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
Output: 6
 

Constraints:

1 <= startTime.length == endTime.length == profit.length <= 5 * 104
1 <= startTime[i] < endTime[i] <= 109
1 <= profit[i] <= 104

 */
public class Q1235_Maximum_Profit_in_Job_Scheduling {
	public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int size = startTime.length;
        TreeMap<Integer, Integer> dp = new TreeMap<>();
        int result = 0;
        Pair[] jobs = new Pair[size];
        
        for (int i = 0; i < size; i++) {
        	jobs[i] = new Pair(startTime[i], endTime[i], profit[i]);
        }
        
        Arrays.sort(jobs, (a,b) -> a.startTime != b.startTime ? a.startTime - b.startTime : a.endTime - b.endTime);
        
        for (int i = size-1; i >= 0; i--) {
        	Map.Entry<Integer, Integer> nextEntry = dp.ceilingEntry(jobs[i].endTime);
            int curMaxProfit = nextEntry != null ? jobs[i].profit + nextEntry.getValue() : jobs[i].profit;
            
            Map.Entry<Integer, Integer> curEntry = dp.ceilingEntry(jobs[i].startTime);
            curMaxProfit = curEntry != null ? Math.max(curMaxProfit, curEntry.getValue()) : curMaxProfit;
            
            result = Math.max(result, curMaxProfit);
            dp.put(jobs[i].startTime, curMaxProfit);
        }
        
        return result;
    }
	
	class Pair {
		public int startTime;
		public int endTime;
		public int profit;
		
		public Pair(int s, int e, int p) {
			startTime = s;
			endTime = e;
			profit = p;
		}
	}
	
	
	
	
	
	
	/************************************* main *************************************/
	
	public static void main(String[] args) {
		Q1235_Maximum_Profit_in_Job_Scheduling test = new Q1235_Maximum_Profit_in_Job_Scheduling();
		int[] startTime1 = {1,2,3,3}, endTime1 = {3,4,5,6}, profit1 = {50,10,40,70};
		int[] startTime2 = {1,2,3,4,6}, endTime2 = {3,5,10,6,9}, profit2 = {20,20,100,70,60};
		int[] startTime3 = {1,1,1}, endTime3 = {2,3,4}, profit3 = {5,6,4};
		int[] startTime4 = {6,15,7,11,1,3,16,2}, endTime4 = {19,18,19,16,10,8,19,8}, profit4 = {2,9,1,19,5,7,3,19};
		
		System.out.println(test.jobScheduling(startTime1, endTime1, profit1));
		System.out.println(test.jobScheduling(startTime2, endTime2, profit2));
		System.out.println(test.jobScheduling(startTime3, endTime3, profit3));
		System.out.println(test.jobScheduling(startTime4, endTime4, profit4));
	}
}
