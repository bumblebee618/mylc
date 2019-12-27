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
	private final int sizeOfTable = 1024;
    private Node[] table;
        
    /** Initialize your data structure here. */
    public Q706_Design_HashMap() {
        table = new Node[sizeOfTable];
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        int hash = Objects.hashCode(key);
        int pos = hash & (sizeOfTable - 1);
        Node p = table[pos];
        
        if (p == null) 
        {
            table[pos] = new Node(key, value);
        } 
        else if (p.key == key) 
        {
            p.value = value;
        } 
        else {
            Node pre = p;
            
            while (p != null && p.key != key)
            {
                pre = p;
                p = p.next;
            }

            if (p == null) 
            {
                pre.next = new Node(key, value);
            } 
            else 
            {
                p.value = value;
            }
        }
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int hash = Objects.hashCode(key);
        int pos = hash & (sizeOfTable - 1);
        Node p = table[pos];
        
        while (p != null && p.key != key) 
        {
            p = p.next;
        }

        return p == null ? -1 : p.value;
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int hash = Objects.hashCode(key);
        int pos = hash & (sizeOfTable - 1);
        Node p = table[pos];
        
        if (p == null) 
        {
            return;
        }
        else if (p.key == key) 
        {
            table[pos] = p.next;
        } 
        else 
        {
            Node pre = p;
            
            while (p != null && p.key != key)
            {
                pre = p;
                p = p.next;
            } 

            if (p != null) 
            {
                pre.next = p.next;
            }
        }
    }
    
    private class Node {
        public int key;
        public int value;
        public Node next;

        public Node(int key, int value) 
        {
            this.key = key;
            this.value = value;
        }
    }
}
