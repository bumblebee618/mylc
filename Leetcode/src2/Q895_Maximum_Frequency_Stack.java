import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Implement FreqStack, a class which simulates the operation of a stack-like data structure.

FreqStack has two functions:

push(int x), which pushes an integer x onto the stack.
pop(), which removes and returns the most frequent element in the stack.
If there is a tie for most frequent element, the element closest to the top of the stack is removed and returned.
 

Example 1:

Input: 
["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
[[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
Output: [null,null,null,null,null,null,null,5,7,5,4]
Explanation:
After making six .push operations, the stack is [5,7,5,7,4,5] from bottom to top.  Then:

pop() -> returns 5, as 5 is the most frequent.
The stack becomes [5,7,5,7,4].

pop() -> returns 7, as 5 and 7 is the most frequent, but 7 is closest to the top.
The stack becomes [5,7,5,4].

pop() -> returns 5.
The stack becomes [5,7,4].

pop() -> returns 4.
The stack becomes [5,7].
 

Note:

Calls to FreqStack.push(int x) will be such that 0 <= x <= 10^9.
It is guaranteed that FreqStack.pop() won't be called if the stack has zero elements.
The total number of FreqStack.push calls will not exceed 10000 in a single test case.
The total number of FreqStack.pop calls will not exceed 10000 in a single test case.
The total number of FreqStack.push and FreqStack.pop calls will not exceed 150000 across all test cases.
 */
public class Q895_Maximum_Frequency_Stack 
{
	private Map<Integer, Integer> freqMap;
    private Map<Integer, Stack<Integer>> group;
    private int maxfreq = 0;

    public Q895_Maximum_Frequency_Stack() 
    {
        freqMap = new HashMap<>();
        group = new HashMap<>();
        maxfreq = 0;
    }

    public void push(int num) 
    {
        int freq = freqMap.getOrDefault(num, 0) + 1;
        freqMap.put(num, freq);
        
        if (freq > maxfreq)
        {
            maxfreq = freq;
        }

        group.computeIfAbsent(freq, x -> new Stack<>()).push(num);
    }

    public int pop() 
    {
        int num = group.get(maxfreq).pop();
        freqMap.put(num, freqMap.get(num) - 1);
        
        if (group.get(maxfreq).size() == 0)
        {
            maxfreq--;
        }
        
        return num;
    }
	
	
	
    
    /***
	private Map<Integer, Node> map;
	private Map<Integer, Queue<Node>> freqMap;
    private int globalTimeStamp = 0;
    private int maxFreq = 0;

    public Q895_Maximum_Frequency_Stack() 
    {
        map = new HashMap<>();
        freqMap = new HashMap<>();
    }
    
    public void push(int value) 
    {
        if (map.containsKey(value))
        {
        	Node node = map.get(value);
        	int prevFreq = node.freq;
        	freqMap.get(prevFreq).remove(node);
        	
        	node.increaseFreq(globalTimeStamp++);
        	freqMap.computeIfAbsent(node.freq, x -> new PriorityQueue<>()).offer(node);
        	
        	maxFreq = Math.max(maxFreq, node.freq);
        }
        else
        {
            Node node = new Node(value, globalTimeStamp++);
            map.put(value, node);
            freqMap.computeIfAbsent(node.freq, x -> new PriorityQueue<>()).offer(node);
            
            maxFreq = Math.max(maxFreq, node.freq);
        }
    }
    
    public int pop() 
    {
    	if (maxFreq == 0)
    	{
    		return -1;
    	}
    	
    	Node popNode = freqMap.get(maxFreq).poll();
    	
    	if (freqMap.get(maxFreq).size() == 0)
    	{
    		maxFreq--;
    	}
    	
    	popNode.decreaseFreq();
    	
    	if (popNode.freq == 0)
    	{
    		map.remove(popNode.value);
    	}
    	else
    	{
    		freqMap.get(popNode.freq).offer(popNode);
    	}
    	
        return popNode.value;
    }
	
	
    
    class Node implements Comparable<Node>
    {
        public int value;
        public int freq;
        public Stack<Integer> timeStamps;
        
        public Node(int value, int timeStamp)
        {
            this.value = value;
            freq = 1;
            timeStamps = new Stack<>();
            timeStamps.push(timeStamp);
        }
        
        public void increaseFreq(int timeStamp)
        {
            freq++;
            timeStamps.push(timeStamp);
        }
        
        public void decreaseFreq()
        {
            freq--;
            timeStamps.pop();
        }

		@Override
		public int compareTo(Node other) 
		{
			return other.timeStamps.peek() - this.timeStamps.peek();
		}
    }
    
    ***/
    
    
    /*********************************** main ***********************************/
    
    public static void main(String[] args)
    {
    	Q895_Maximum_Frequency_Stack test = new Q895_Maximum_Frequency_Stack();
    	int[] nums = {5,7,5,7,4,5};
    	
    	for (int num : nums)
    	{
    		test.push(num);
    	}
    	
    	System.out.println(test.pop());
    	System.out.println(test.pop());
    	System.out.println(test.pop());
    	System.out.println(test.pop());
    }
}
