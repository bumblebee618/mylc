import java.util.*;


public class Q000_A_Contest 
{	
	public int minimumMoves(String s) {
        int step = 0, index = 0;
        char[] letters = s.toCharArray();
        
        while (index < s.length()) {
            while (index < s.length() && letters[index] == 'O') {
                index++;
            }
            
            if (index < s.length()) {
                step++;
                
                for (int i = 0; i < 2 && index + i < s.length(); i++) {
                    letters[index+i] = 'O';
                }
                
                index += 3;
            } 
        }
        
        return step;
    }
	
	
	
	public int[] missingRolls(int[] rolls, int mean, int n) {
		int m = rolls.length;
        int diff = mean * (m+n) - Arrays.stream(rolls).sum();
        
        if (diff < n || diff > 6 * n) {
        	return new int[0];
        } 

        int[] result = new int[n];
        int avg = diff / n;
        
        for (int i = 0; i < n; i++) {
        	int cur = (diff > avg * (n-i)) ? avg+1 : avg;
        	result[i] = cur;
        	diff -= cur;
        }
        
        return result;
    }
	
	
	
	public boolean stoneGameIX(int[] stones) {
		int zero = 0, one = 0, two = 0;
		
		for (int stone : stones) {
			if (stone % 3 == 0) {
				zero++;
			} else if (stone % 3 == 1) {
				one++;
			} else if (stone % 3 == 2) {
				two++;
			}
		}
		
		if (one == 0 && two == 0){
			return false;
		}
		
		int max = Math.max(one, two), min = Math.min(one, two);
		
		if (zero % 2 == 0) {
			if(min == 0) {
				return false;
			}
			
			return true;
		}
		
		if (zero % 2 == 1) {
			if (min == 0 && max > 2) {
				return true;
			}
			
			if (max -2 > min && min > 0) {
				return true;
			}
			
			return false;
		}
		
		return false;
	}
	
	
	
	private Map<String, Character> memo = new HashMap<>();
	private int[] stones;
	
	public boolean stoneGameIX2(int[] stones) {
        char[] status = new char[stones.length];
        Arrays.fill(status, ' ');
        this.stones = stones;
        return search(status, 0, true, 0) == 'A' ? true : false;
    }
	
	private char search(char[] status, int curRound, boolean isAlice, int sum) {
		if (curRound == status.length) {
			return 'B';
		} 
		
		String statusStr = new String(status);
		
		if (memo.containsKey(statusStr)) {
			return memo.get(statusStr);
		}
		
		char target = isAlice ? 'A' : 'B';
		
		for (int i = 0; i < status.length; i++) {
			if (status[i] == ' ') {
				if ((sum + stones[i]) % 3 != 0) {
					status[i] = target;
					
					if (search(status, curRound+1, !isAlice, (sum + stones[i]) % 3) == target) {
						memo.put(statusStr, target);
						status[i] = ' ';
						return target;
					}
					
					status[i] = ' ';
				}
			}
		}
		
		char result = isAlice ? 'B' : 'A';
		memo.put(statusStr, result);
		return result;
	}
	
	
	
    public static void main(String[] args)
    {
    	Q000_A_Contest test = new Q000_A_Contest();
    	
    	/****************************************************/
    	
    	int[] stones1 = {2,1};
    	int[] stones2 = {2};
    	int[] stones3 = {5,1,2,4,3};
    	
    	System.out.println(test.stoneGameIX(stones1));
    	System.out.println(test.stoneGameIX(stones2));
    	System.out.println(test.stoneGameIX(stones3));
    }
}
