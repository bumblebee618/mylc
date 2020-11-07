import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Implement a data structure supporting the following operations:

Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1. If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
Challenge: Perform all these in O(1) time complexity.
 */
public class Q432_All_O_one_Data_Structure 
{
	private Map<String, Node> map;
    private Queue<Node> minHeap;
    private Queue<Node> maxHeap;
    
    /** Initialize your data structure here. */
    public Q432_All_O_one_Data_Structure() 
    {
        map = new HashMap<String, Node>();
        minHeap = new PriorityQueue<Node>((a, b) -> a.val - b.val);        
        maxHeap = new PriorityQueue<Node>((a, b) -> b.val - a.val);
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) 
    {
        if (!map.containsKey(key)) 
        {
            Node node = new Node(key, 1);  
            map.put(key, node);
            minHeap.add(node);
            maxHeap.add(node);
        } 
        else 
        {
            Node node = map.get(key);
            minHeap.remove(node);
            maxHeap.remove(node);
            
            node.val++;
            minHeap.add(node);
            maxHeap.add(node);
        }
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) 
    {
        if (map.containsKey(key)) 
        {
            Node node = map.get(key);
            
            if (node.val == 1) 
            {
                map.remove(key);
                minHeap.remove(node);
                maxHeap.remove(node);
            } 
            else 
            {
                minHeap.remove(node);
                maxHeap.remove(node);
                
                node.val--;
                minHeap.add(node);
                maxHeap.add(node);
            }
        }
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() 
    {
        return maxHeap.isEmpty() ? "" : maxHeap.peek().key;
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() 
    {
        return minHeap.isEmpty() ? "" : minHeap.peek().key;
    }
    
    class Node
    {
        String key;
        int val;
        
        public Node(String key, int val) 
        {
            this.key = key;
            this.val = val;
        }
    }
}
