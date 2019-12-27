import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/***
 * 
 * @author jackie
 * 
 * Design a HashSet without using any built-in hash table libraries.

To be specific, your design should include these functions:

add(value): Insert a value into the HashSet. 
contains(value) : Return whether the value exists in the HashSet or not.
remove(value): Remove a value in the HashSet. If the value does not exist in the HashSet, do nothing.

Example:

MyHashSet hashSet = new MyHashSet();
hashSet.add(1);         
hashSet.add(2);         
hashSet.contains(1);    // returns true
hashSet.contains(3);    // returns false (not found)
hashSet.add(2);          
hashSet.contains(2);    // returns true
hashSet.remove(2);          
hashSet.contains(2);    // returns false (already removed)

Note:

All values will be in the range of [0, 1000000].
The number of operations will be in the range of [1, 10000].
Please do not use the built-in HashSet library.
 */
public class Q705_Design_HashSet {
	private final int capacity = 1024;
    private List<Integer>[] list;
    
    
    /** Initialize your data structure here. */
    public Q705_Design_HashSet() {
        list = new List[capacity];
    }
    
    public void add(int key) {
        int hash = Objects.hashCode(key);
        int pos = hash & (capacity-1);
        
        if (list[pos] == null)
        {
            list[pos] = new ArrayList<Integer>();
            list[pos].add(key);
        }
        else
        {
            int index = 0;
            
            while (index < list[pos].size() && list[pos].get(index) != key)
            {
                index++;
            }
            
            if (index == list[pos].size())
            {
                list[pos].add(key);
            }
            else
            {
                list[pos].set(index, key);
            }
        }
    }
    
    public void remove(int key) {
        int hash = Objects.hashCode(key);
        int pos = hash & (capacity-1);
        
        if (list[pos] != null)
        {
            int index = 0;
            
            while (index < list[pos].size() && list[pos].get(index) != key)
            {
                index++;
            }
            
            if (index < list[pos].size())
            {
                list[pos].remove(index);
            }
        }
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int hash = Objects.hashCode(key);
        int pos = hash & (capacity-1);
        
        if (list[pos] == null)
        {
            return false;
        }
        else
        {
            int index = 0;
            
            while (index < list[pos].size() && list[pos].get(index) != key)
            {
                index++;
            }
            
            return index == list[pos].size() ? false : true;
        }
    }
}
