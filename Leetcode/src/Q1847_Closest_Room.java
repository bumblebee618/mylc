import java.util.*;

/***
 * 
 * @author jackie
 * 
 * There is a hotel with n rooms. The rooms are represented by a 2D integer array rooms where rooms[i] = [roomIdi, sizei] denotes that there is a room with room number roomIdi and size equal to sizei. Each roomIdi is guaranteed to be unique.

You are also given k queries in a 2D array queries where queries[j] = [preferredj, minSizej]. The answer to the jth query is the room number id of a room such that:

The room has a size of at least minSizej, and
abs(id - preferredj) is minimized, where abs(x) is the absolute value of x.
If there is a tie in the absolute difference, then use the room with the smallest such id. If there is no such room, the answer is -1.

Return an array answer of length k where answer[j] contains the answer to the jth query.

 

Example 1:

Input: rooms = [[2,2],[1,2],[3,2]], queries = [[3,1],[3,3],[5,2]]
Output: [3,-1,3]
Explanation: The answers to the queries are as follows:
Query = [3,1]: Room number 3 is the closest as abs(3 - 3) = 0, and its size of 2 is at least 1. The answer is 3.
Query = [3,3]: There are no rooms with a size of at least 3, so the answer is -1.
Query = [5,2]: Room number 3 is the closest as abs(3 - 5) = 2, and its size of 2 is at least 2. The answer is 3.
Example 2:

Input: rooms = [[1,4],[2,3],[3,5],[4,1],[5,2]], queries = [[2,3],[2,4],[2,5]]
Output: [2,1,3]
Explanation: The answers to the queries are as follows:
Query = [2,3]: Room number 2 is the closest as abs(2 - 2) = 0, and its size of 3 is at least 3. The answer is 2.
Query = [2,4]: Room numbers 1 and 3 both have sizes of at least 4. The answer is 1 since it is smaller.
Query = [2,5]: Room number 3 is the only room with a size of at least 5. The answer is 3.
 

Constraints:

n == rooms.length
1 <= n <= 105
k == queries.length
1 <= k <= 104
1 <= roomIdi, preferredj <= 107
1 <= sizei, minSizej <= 107
 */
public class Q1847_Closest_Room 
{
	public int[] closestRoom(int[][] rooms, int[][] queries) 
    {
        if (rooms == null || rooms.length == 0 || rooms[0].length != 2 || queries == null || queries.length == 0 || queries[0].length != 2)
        {
            return new int[0];
        }
    	
        Arrays.sort(rooms, (a,b) -> b[1] - a[1]);
        int m = queries.length;
        int n = rooms.length;
        int[][] query = new int[m][3];
        
        for (int i = 0; i < m; i++)
        {
            query[i][0] = queries[i][0];
            query[i][1] = queries[i][1];
            query[i][2] = i;
        }
        
        int[] result = new int[m];
        Arrays.sort(query, (a,b) -> b[1] - a[1]);
        TreeSet<Integer> treeSet = new TreeSet<>();
        int j = 0;
        
        for (int i = 0; i < m; i++)
        {
            while (j < n && rooms[j][1] >= query[i][1])
            {
                treeSet.add(rooms[j++][0]);
            }
            
            Integer floor = treeSet.floor(query[i][0]);
            Integer ceil = treeSet.ceiling(query[i][0]);
            
            if (floor == null && ceil == null)
            {
                 result[query[i][2]] = -1;
            }
            else if (floor == null)
            {
                 result[query[i][2]] = ceil;
            }
            else if (ceil == null)
            {
                 result[query[i][2]] = floor;
            }
            else
            {
                 result[query[i][2]] = ((ceil - query[i][0]) < (query[i][0] - floor) ? ceil : floor);
            }
        }
        
        return  result;
    }
}
