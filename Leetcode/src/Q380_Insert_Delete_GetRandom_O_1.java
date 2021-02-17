import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/******
 * 
Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
Example:

// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 1 is the only number in the set, getRandom always return 1.
randomSet.getRandom();

 * 
 * */

// follow up: Le_381

public class Q380_Insert_Delete_GetRandom_O_1 
{
	private ArrayList<Integer> list;
    private HashMap<Integer, Integer> map;
    private Random rand;

    /** Initialize your data structure here. */
    public Q380_Insert_Delete_GetRandom_O_1() 
    {
        list = new ArrayList<Integer>();
        map = new HashMap<Integer, Integer>();
        rand = new Random();
    }
    
    /** Inserts a minValue to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) 
    {
        if (map.containsKey(val))
        {
            return false;   
        } 
        
        list.add(val);
        map.put(val, list.size() - 1);    
        return true;
    }
    
    /** Removes a minValue from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) 
    {
        if (!map.containsKey(val))
        { 
            return false;
        }
        
        int pos = map.get(val);
        map.remove(val);
        
        if (pos < list.size() - 1)  // not the last one then swap the last one with this val
        {
            int lastElement = list.get(list.size() - 1);
            list.set(pos, lastElement);
            map.put(lastElement, pos);
        }        
        
        list.remove(list.size() - 1);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() 
    {
        return list.get(rand.nextInt(list.size()));
    }
}
