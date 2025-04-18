package package2;
/***
 * 
 * @author jackie
 * 
 * In a town, there are N people labelled from 1 to N.  There is a rumor that one of these people is secretly the town judge.

If the town judge exists, then:

The town judge trusts nobody.
Everybody (except for the town judge) trusts the town judge.
There is exactly one person that satisfies properties 1 and 2.
You are given trust, an array of pairs trust[i] = [a, b] representing that the person labelled a trusts the person labelled b.

If the town judge exists and can be identified, return the label of the town judge.  Otherwise, return -1.

 

Example 1:

Input: N = 2, trust = [[1,2]]
Output: 2
Example 2:

Input: N = 3, trust = [[1,3],[2,3]]
Output: 3
Example 3:

Input: N = 3, trust = [[1,3],[2,3],[3,1]]
Output: -1
Example 4:

Input: N = 3, trust = [[1,2],[2,3]]
Output: -1
Example 5:

Input: N = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
Output: 3
 

Note:

1 <= N <= 1000
trust.length <= 10000
trust[i] are all different
trust[i][0] != trust[i][1]
1 <= trust[i][0], trust[i][1] <= N
 */
public class Q997_Find_the_Town_Judge {
	public int findJudge(int N, int[][] trust) {
        if (N <= 0)
        {
            return -1;
        }
        else if (trust == null || trust.length == 0 || trust[0].length == 0)
        {
            return N == 1 ? 1 : -1;
        }
        
        int[] trustNum = new int[N+1];  
        boolean[] normalPerson = new boolean[N+1];
        
        for (int[] elem : trust)
        {
            trustNum[elem[1]]++;
            normalPerson[elem[0]] = true;
        }
        
        int candidate = -1;
        
        for (int i = 1; i <= N; i++)
        {
            if (trustNum[i] == N-1 && !normalPerson[i])
            {
                if (candidate == -1)
                {
                    candidate = i;
                }
                else 
                {
                    return -1;
                }
            }
        }
        
        return candidate;
    }
}
