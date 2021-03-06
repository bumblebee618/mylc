import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/***
 * 
 * @author jackie
 * 
 * Think about Zuma Game. You have a row of balls on the table, colored red(R), yellow(Y), blue(B), green(G), and white(W). You also have several balls in your hand.

Each time, you may choose a ball in your hand, and insert it into the row (including the leftmost place and rightmost place). Then, if there is a group of 3 or more balls in the same color touching, remove these balls. Keep doing this until no more balls can be removed.

Find the minimal balls you have to insert to remove all the balls on the table. If you cannot remove all the balls, output -1.

 

Example 1:

Input: board = "WRRBBW", hand = "RB"
Output: -1
Explanation: WRRBBW -> WRR[R]BBW -> WBBW -> WBB[B]W -> WW
Example 2:

Input: board = "WWRRBBWW", hand = "WRBRW"
Output: 2
Explanation: WWRRBBWW -> WWRR[R]BBWW -> WWBBWW -> WWBB[B]WW -> WWWW -> empty
Example 3:

Input: board = "G", hand = "GGGGG"
Output: 2
Explanation: G -> G[G] -> GG[G] -> empty 
Example 4:

Input: board = "RBYYBBRRB", hand = "YRBGB"
Output: 3
Explanation: RBYYBBRRB -> RBYY[Y]BBRRB -> RBBBRRB -> RRRB -> B -> B[B] -> BB[B] -> empty 
 

Constraints:

You may assume that the initial row of balls on the table won’t have any 3 or more consecutive balls with the same color.
1 <= board.length <= 16
1 <= hand.length <= 5
Both input strings will be non-empty and only contain characters 'R','Y','B','G','W'.
 */

public class Q488_Zuma_Game {
	private Map<String, Node> memo = new HashMap<>();
	
	public int findMinStep(String board, String hand) 
	{
		if (board == null || board.length() == 0 || hand == null || hand.length() == 0)
        {
            return -1;
        }
        
		Map<Character, Integer> curStatus = new HashMap<>();
        
        for(char c : hand.toCharArray())
        {
        	curStatus.put(c, curStatus.getOrDefault(c, 0)+1);
        }
        
        Node result = backtrack(board, curStatus, hand.length());
        return result.needNum == Integer.MAX_VALUE ? -1 : result.needNum;
	}
	
	private Node backtrack(String board, Map<Character, Integer> curStatus, int countLeft)
	{
		if (board == null || board.length() == 0) 
        {
            return new Node(0, new HashMap<>());
        }
		else if (countLeft == 0)
		{
			return new Node(Integer.MAX_VALUE, new HashMap<>());
		}
		else if (memo.containsKey(board))
		{
			return memo.get(board);
		}
		
		Node result = new Node(Integer.MAX_VALUE, new HashMap<>()); 
		
		for (int insert = 0; insert <= board.length(); insert++)
		{
			for (Map.Entry<Character, Integer> entry :  curStatus.entrySet())
			{
				char ball = entry.getKey();
				int count = entry.getValue();
				
				if (count > 0)
				{
					curStatus.put(ball, count-1);
					
					String newBoard = board.substring(0, insert) + ball + board.substring(insert);
					String nextBoard = getNextBoard(newBoard);
					Node nextResult = backtrack(nextBoard, curStatus, countLeft-1);
					
					if (nextResult.needNum != Integer.MAX_VALUE 
						&& result.needNum > nextResult.needNum+1
						&& canMoveTo(curStatus, nextResult.needStatus))
					{
						result.needNum = nextResult.needNum+1;
						result.needStatus = new HashMap<>(nextResult.needStatus);
						result.needStatus.put(ball, result.needStatus.getOrDefault(ball, 0)+1);
					}
					
					curStatus.put(ball, count);
				}
			}
		}
		
		memo.put(board, result);
		return result;
	}
	
	public String getNextBoard(String board)
    {
		Stack<Character> characters = new Stack<>();
		Stack<Integer> frequency = new Stack<>();
		int index = 0;
		
		while (index < board.length())
		{
			char c = board.charAt(index);
			int count = 0;
			
			while (index < board.length() && board.charAt(index) == c)
			{
				count++;
				index++;
			}
			
			if (characters.isEmpty() || characters.peek() != c)
			{
				if (count < 3)
				{
					characters.push(c);
					frequency.push(count);
				}
			}
			else
			{
				int totalCount = frequency.pop() + count;
				
				if (totalCount >= 3)
				{
					characters.pop();
				}
				else
				{
					frequency.push(totalCount);
				}
			}
		}
		
    	StringBuilder builder = new StringBuilder();
        
        while (!characters.isEmpty())
        {
        	char c = characters.pop();
        	int count = frequency.pop();
        	
        	for (int i = 0; i < count; i++)
        	{
        		builder.append(c);
        	}
        }
        
        return builder.toString();
    }
	
	private boolean canMoveTo(Map<Character, Integer> status, Map<Character, Integer> needStatus)
	{
		for (Map.Entry<Character, Integer> entry :  needStatus.entrySet())
		{
			char ball = entry.getKey();
			int count = entry.getValue();
			
			if (status.getOrDefault(ball, 0) < count)
			{
				return false;
			}
		}
		
		return true;
	}
	
	class Node
	{
		public int needNum;
		public Map<Character, Integer> needStatus;
		
		public Node(int needNum, Map<Character, Integer> needStatus)
		{
			this.needNum = needNum;
			this.needStatus = needStatus;
		}
	}
	
	
	
	/***
	public int findMinStep2(String board, String hand) 
    {
        if (board == null || board.length() == 0 || hand == null || hand.length() == 0)
        {
            return -1;
        }
        
        int[] count = new int[256];
        
        for(char x : hand.toCharArray())
        {
            count[x]++;
        }
        
        return backtrack(board, count);
    }
    
    private int backtrack(String board, int[] count)
    {
        if (board == null || board.length() == 0) 
        {
            return 0;
        }
        
        int result = Integer.MAX_VALUE; 
        int end = 0;
        
        while (end < board.length())
        {
            int start = end;
            char target = board.charAt(start);
            
            while (end < board.length() && board.charAt(end) == target)
            {
                end++;
            }
         
            int needNum = 3 - (end - start);
            
            if (count[target] >= needNum)
            {
            	int curUsed = (needNum <= 0) ? 0 : needNum; 
                count[board.charAt(start)] -= curUsed;
                String nextBoard = refreshBoard(board.substring(0, start) + board.substring(end));
                int nextUsed = backtrack(nextBoard, count);
                
                if (nextUsed >= 0)
                {
                    result = Math.min(result, curUsed + nextUsed);
                }
                
                count[board.charAt(start)] += curUsed;
            }
        }
        
        return result == Integer.MAX_VALUE ? -1 : result;
    }
    
    private String refreshBoard(String board)
    {
    	int end = 0;
    	StringBuilder builder = new StringBuilder();
        
        while (end < board.length())
        {
        	int start = end;
            char target = board.charAt(start);
            
            while (end < board.length() && board.charAt(end) == target)
            {
                end++;
            }
            
            if (end-start < 3)
            {
            	builder.append(board.substring(start, end));
            }
        }
        
        return builder.toString();
    }
    ***/
    
    
    
    
    
    /************************************* main *************************************/
    
    public static void main(String[] args)
    {
    	String board1 = "WWBBWBBWW";
    	String hand1 = "BB";
    	
    	String board2 = "RRWWRRBBRR";
    	String hand2 = "WB";
    	
    	String board3 = "RBYYBBRRB";
    	String hand3 ="YRBGB";
    	
    	
    	// hit here and use ball [B] and current board is [RWRBBBRRWWRR], next board is [RRWWRWR]
    	
    	Q488_Zuma_Game test = new Q488_Zuma_Game();
    	System.out.println(test.findMinStep(board1, hand1));
    	System.out.println(test.findMinStep(board2, hand2));
    	System.out.println(test.findMinStep(board3, hand3));
    }
}
