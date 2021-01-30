import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/***
 * 
 * @author jackie
 * 
 * Design a HashMap without using any built-in hash table libraries.

To be specific, your design should include these functions:

put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.

Example:

MyHashMap hashMap = new MyHashMap();
hashMap.put(1, 1);          
hashMap.put(2, 2);         
hashMap.get(1);            // returns 1
hashMap.get(3);            // returns -1 (not found)
hashMap.put(2, 1);          // update the existing value
hashMap.get(2);            // returns 1 
hashMap.remove(2);          // remove the mapping for 2
hashMap.get(2);            // returns -1 (not found) 

Note:

All keys and values will be in the range of [0, 1000000].
The number of operations will be in the range of [1, 10000].
Please do not use the built-in HashMap library.
 */
public class Q706_Design_HashMap {
	private int capacity = 1024;
    private List<Node>[] list;
    
    /** Initialize your data structure here. */
    public Q706_Design_HashMap() {
        list = new List[capacity];
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        int hash = Objects.hashCode(key);
        int pos = hash & (capacity-1);
        
        if (list[pos] == null)
        {
            list[pos] = new LinkedList<>();
        }
        
        int index = 0;
        
        while (index < list[pos].size() && key != list[pos].get(index).key)
        {
            index++;
        }
        
        if (index < list[pos].size())
        {
            list[pos].get(index).value = value;
        }
        else
        {
            list[pos].add(new Node(key, value));
        }
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int hash = Objects.hashCode(key);
        int pos = hash & (capacity-1);
        
        if (list[pos] == null)
        {
            return -1;
        }
        
        int index = 0;
        
        while (index < list[pos].size() && key != list[pos].get(index).key)
        {
            index++;
        }
        
        return (index < list[pos].size()) ? list[pos].get(index).value : -1;
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int hash = Objects.hashCode(key);
        int pos = hash & (capacity-1);
        
        if (list[pos] == null)
        {
            return;
        }
        
        int index = 0;
        
        while (index < list[pos].size() && key != list[pos].get(index).key)
        {
            index++;
        }
        
        if (index < list[pos].size())
        {
            list[pos].remove(index);
        }
    }
    
    class Node
    {
        public int key;
        public int value;
        
        public Node(int key, int value)
        {
            this.key = key;
            this.value = value;
        }
    }
}
