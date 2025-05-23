import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 
 * You are given an array of transactions transactions where transactions[i] = [fromi, toi, amounti] indicates that the person with ID = fromi gave amounti $ to the person with ID = toi.
Return the minimum number of transactions required to settle the debt.
 
Example 1:
Input: transactions = [[0,1,10],[2,0,5]]
Output: 2
Explanation:
Person #0 gave person #1 $10.
Person #2 gave person #0 $5.
Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
Example 2:
Input: transactions = [[0,1,10],[1,0,1],[1,2,5],[2,0,5]]
Output: 1
Explanation:
Person #0 gave person #1 $10.
Person #1 gave person #0 $1.
Person #1 gave person #2 $5.
Person #2 gave person #0 $5.
Therefore, person #1 only need to give person #0 $4, and all debt is settled.
 
Constraints:
	• 1 <= transactions.length <= 8
	• transactions[i].length == 3
	• 0 <= fromi, toi < 12
	• fromi != toi
	• 1 <= amounti <= 100
 *
 */


public class Q465_Optimal_Account_Balancing {
	public int minTransfers(int[][] transactions) {
        if (transactions == null || transactions.length == 0 || transactions[0].length != 3) {
            return 0;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int[] transaction : transactions) {
            map.put(transaction[0], map.getOrDefault(transaction[0], 0) - transaction[2]);
            map.put(transaction[1], map.getOrDefault(transaction[1], 0) + transaction[2]);
        }
        
        return backtrack(0, new ArrayList<>(map.values()));
    }
    
    private int backtrack(int startIndex, List<Integer> debts) {
        while (startIndex < debts.size() && debts.get(startIndex) == 0) {
            startIndex++;
        }
        
        // 从最底层返回
        if (startIndex == debts.size()) {
            return 0;
        }
        
        int result = Integer.MAX_VALUE;
        
        // find one debt to exchange with startIndex
        for (int i = startIndex+1; i < debts.size(); i++) {
            if (debts.get(startIndex) * debts.get(i) < 0) {
                debts.set(i, debts.get(i) + debts.get(startIndex));
                result = Math.min(result, backtrack(startIndex+1, debts) + 1); 
                debts.set(i, debts.get(i) - debts.get(startIndex));
            }
        }
        
        return result;
    }

}
