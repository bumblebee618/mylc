import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/******
 * 
Design a data structure that supports all following operations in average O(1) time.

Note: Duplicate elements are allowed.
insert(val): Inserts an item val to the collection.
remove(val): Removes an item val from the collection if present.
getRandom: Returns a random element from current collection of elements. 
The probability of each element being returned is linearly related to the number of same minValue the collection contains.

Example:

// Init an empty collection.
RandomizedCollection collection = new RandomizedCollection();

// Inserts 1 to the collection. Returns true as the collection did not contain 1.
collection.insert(1);

// Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
collection.insert(1);

// Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
collection.insert(2);

// getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
collection.getRandom();

// Removes 1 from the collection, returns true. Collection now contains [1,2].
collection.remove(1);

// getRandom should return 1 and 2 both equally likely.
collection.getRandom();
 * 
 * */

public class Q381_Insert_Delete_GetRandom_O_1_Duplicates_allowed 
{
	private List<Integer> list;
    private Map<Integer, Set<Integer>> map;
    private Random rand;
    
    /** Initialize your data structure here. */
    public Q381_Insert_Delete_GetRandom_O_1_Duplicates_allowed() 
    {
        list = new ArrayList<>();
        map = new HashMap<>();
        rand = new Random();
    }
    
    /** Inserts a minValue to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) 
    {
        boolean result = false;
        
        if (!map.containsKey(val) || !map.get(val).iterator().hasNext())
        {
            result = true;
            map.put(val, new HashSet<Integer>());
        }
        
        list.add(val);
        map.get(val).add(list.size()-1);
        return result;
    }
    
    /** Removes a minValue from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) 
    {
        if (!map.containsKey(val) || !map.get(val).iterator().hasNext())
        {
            return false;
        }
        
        int pos = map.get(val).iterator().next();
        map.get(val).remove(pos);
        
        if (pos != list.size()-1)
        {
            int lastElem = list.get(list.size()-1);
            list.set(pos, lastElem);
            map.get(lastElem).remove(list.size()-1);
            map.get(lastElem).add(pos);
        }
        
        list.remove(list.size()-1);
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() 
    {
        return list.get(rand.nextInt(list.size()));
    }
}
