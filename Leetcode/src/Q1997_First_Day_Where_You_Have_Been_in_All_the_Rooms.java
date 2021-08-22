/***
 * 
 * @author jackie
 * 
 * There are n rooms you need to visit, labeled from 0 to n - 1. Each day is labeled, starting from 0. You will go in and visit one room a day.

Initially on day 0, you visit room 0. The order you visit the rooms for the coming days is determined by the following rules and a given 0-indexed array nextVisit of length n:

Assuming that on a day, you visit room i,
if you have been in room i an odd number of times (including the current visit), on the next day you will visit the room specified by nextVisit[i] where 0 <= nextVisit[i] <= i;
if you have been in room i an even number of times (including the current visit), on the next day you will visit room (i + 1) mod n.
Return the label of the first day where you have been in all the rooms. It can be shown that such a day exists. Since the answer may be very large, return it modulo 109 + 7.

 

Example 1:

Input: nextVisit = [0,0]
Output: 2
Explanation:
- On day 0, you visit room 0. The total times you have been in room 0 is 1, which is odd.
  On the next day you will visit room nextVisit[0] = 0
- On day 1, you visit room 0, The total times you have been in room 0 is 2, which is even.
  On the next day you will visit room (0 + 1) mod 2 = 1
- On day 2, you visit room 1. This is the first day where you have been in all the rooms.
Example 2:

Input: nextVisit = [0,0,2]
Output: 6
Explanation:
Your room visiting order for each day is: [0,0,1,0,0,1,2,...].
Day 6 is the first day where you have been in all the rooms.
Example 3:

Input: nextVisit = [0,1,2,0]
Output: 6
Explanation:
Your room visiting order for each day is: [0,0,1,1,2,2,3,...].
Day 6 is the first day where you have been in all the rooms.
 

Constraints:

n == nextVisit.length
2 <= n <= 105
0 <= nextVisit[i] <= i
 */
public class Q1997_First_Day_Where_You_Have_Been_in_All_the_Rooms {
	public int firstDayBeenInAllRooms(int[] nextVisit) {
        int n = nextVisit.length;
        long[] dp = new long[n];
        dp[1] = 2;
        int mod = 1_000_000_007;
        
        for(int i = 2 ; i < n ; i++){
            dp[i] = dp[i-1] + (dp[i-1] - dp[nextVisit[i-1]] + 1) + 1 + mod;
            dp[i] %= mod;
        }
        
        return (int) dp[n-1];
    }
	
	
	
	
	
	/****************************************** main ******************************************/
	
	public static void main(String[] args) {
		int[] nextVisit = {0,1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10,11,11,12,12,13,13,14,14,15,15,16,16,17,17,18,18,19,19,20,20,21,21,22,22,23,23,24,24,25,25,26,26,27,27,28,28,29,29,30,30,31,31,32,32,33,33,34,34,35,35,36,36,37,37,38,38,39,39,40,40,41,41,42,42,43,43,44,44,45,45,46,46,47,47,48};
		Q1997_First_Day_Where_You_Have_Been_in_All_the_Rooms test = new Q1997_First_Day_Where_You_Have_Been_in_All_the_Rooms();
		
		System.out.println(test.firstDayBeenInAllRooms(nextVisit));
	}
}
