import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Implement a data structure supporting the following operations:

Inc(Key) - Inserts a new key with minValue 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
Dec(Key) - If Key's minValue is 1, remove it from the data structure. Otherwise decrements an existing key by 1. If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
GetMaxKey() - Returns one of the keys with maximal minValue. If no element exists, return an empty string "".
GetMinKey() - Returns one of the keys with minimal minValue. If no element exists, return an empty string "".
Challenge: Perform all these in O(1) time complexity.
 */
public class Q432_All_O_one_Data_Structure {
	private TreeSet<Node> treeSet;
    private Map<String, Integer> map;

    public Q432_All_O_one_Data_Structure() {
    	treeSet = new TreeSet<>((a, b) -> a.val == b.val ? a.key.compareTo(b.key) : a.val - b.val);
        map = new HashMap<>();
    }
    
    public void inc(String key) {
        if (!map.containsKey(key)) {
            map.put(key, 1);
            treeSet.add(new Node(key, 1));
        } else {
            int count = map.get(key);
            map.put(key, count+1);
            treeSet.remove(new Node(key, count));
            treeSet.add(new Node(key, count+1));
        }
    }
    
    public void dec(String key) {
        if (!map.containsKey(key)) {
            return;
        } 

        int count = map.get(key);
        
        if (count == 1) {
            map.remove(key);
            treeSet.remove(new Node(key, count));
        } else {
            map.put(key, count-1);
            treeSet.remove(new Node(key, count));
            treeSet.add(new Node(key, count-1));
        }
    }
    
    public String getMaxKey() {
        return treeSet.isEmpty() ? "" : treeSet.last().key;
    }
    
    public String getMinKey() {
        return treeSet.isEmpty() ? "" : treeSet.first().key;
    }
    
    class Node {
        String key;
        int val;
        
        public Node(String key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
