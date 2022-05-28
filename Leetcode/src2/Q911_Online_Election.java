import java.util.*;

/***
 * 
 * @author jackie
 * 
 * In an election, the i-th vote was cast for persons[i] at time times[i].

Now, we would like to implement the following query function: TopVotedCandidate.q(int t) will return the number of the person that was leading the election at time t.  

Votes cast at time t will count towards our query.  In the case of a tie, the most recent vote (among tied candidates) wins.

 

Example 1:

Input: ["TopVotedCandidate","q","q","q","q","q","q"], [[[0,1,1,0,0,1,0],[0,5,10,15,20,25,30]],[3],[12],[25],[15],[24],[8]]
Output: [null,0,1,1,0,0,1]
Explanation: 
At time 3, the votes are [0], and 0 is leading.
At time 12, the votes are [0,1,1], and 1 is leading.
At time 25, the votes are [0,1,1,0,0,1], and 1 is leading (as ties go to the most recent vote.)
This continues for 3 more queries at time 15, 24, and 8.
 

Note:

1 <= persons.length = times.length <= 5000
0 <= persons[i] <= persons.length
times is a strictly increasing array with all elements in [0, 10^9].
TopVotedCandidate.q is called at most 10000 times per test case.
TopVotedCandidate.q(int t) is always called with t >= times[0].
 */

public class Q911_Online_Election 
{
	private Map<Integer, Integer> map;
    private int[] times;
    
    public Q911_Online_Election(int[] persons, int[] times) 
    {
        if (persons == null || persons.length == 0 
            || times == null || times.length == 0 
            || persons.length != times.length)
        {
            return;
        }
        
        Map<Integer, Integer> count = new HashMap<>();
        map = new HashMap<>();
        this.times = times;
        int leader = -1;
        
        for (int i = 0; i < persons.length; i++)
        {
            count.put(persons[i], count.getOrDefault(persons[i], 0) + 1);
            
            if (leader == -1 || count.get(persons[i]) >= count.get(leader))
            {
                leader = persons[i];
            }
            
            map.put(times[i], leader);
        }
    }
    
    public int q(int t) 
    {
        if (t < 0)
        {
            return -1;
        }
        
        int i = Arrays.binarySearch(times, t);
        return i < 0 ? map.get(times[-i-2]) : map.get(times[i]);
    }
}
