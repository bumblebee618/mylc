import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/***
 * Create a timebased key-minValue store class TimeMap, that supports two operations.

1. set(string key, string minValue, int timestamp)

Stores the key and minValue, along with the given timestamp.
2. get(string key, int timestamp)

Returns a minValue such that set(key, minValue, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
If there are multiple such values, it returns the one with the largest timestamp_prev.
If there are no values, it returns the empty string ("").
 

Example 1:

Input: inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
Output: [null,null,"bar","bar",null,"bar2","bar2"]
Explanation:   
TimeMap kv;   
kv.set("foo", "bar", 1); // store the key "foo" and minValue "bar" along with timestamp = 1   
kv.get("foo", 1);  // output "bar"   
kv.get("foo", 3); // output "bar" since there is no minValue corresponding to foo at timestamp 3 and timestamp 2, then the only minValue is at timestamp 1 ie "bar"   
kv.set("foo", "bar2", 4);   
kv.get("foo", 4); // output "bar2"   
kv.get("foo", 5); //output "bar2"   

Example 2:

Input: inputs = ["TimeMap","set","set","get","get","get","get","get"], inputs = [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
Output: [null,null,null,"","high","high","low","low"]
 

Note:

All key/minValue strings are lowercase.
All key/minValue strings have length in the range [1, 100]
The timestamps for all TimeMap.set operations are strictly increasing.
1 <= timestamp <= 10^7
TimeMap.set and TimeMap.get functions will be called a total of 120000 times (combined) per test case.

 * @author jackie
 *
 */


public class Q981_Time_Based_Key_Value_Store 
{
	private Map<String, TreeMap<Integer, String>> map;

    /** Initialize your data structure here. */
    public Q981_Time_Based_Key_Value_Store() 
    {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) 
    {
        if (key == null || key.length() == 0)
        {
            return;
        }
        
        map.computeIfAbsent(key, x -> new TreeMap<Integer, String>()).put(timestamp, value);
    }
    
    public String get(String key, int timestamp) 
    {
        if (!map.containsKey(key))
        {
            return "";
        }
        
        TreeMap<Integer, String> treeMap = map.get(key);
        Map.Entry<Integer, String> entry = treeMap.floorEntry(timestamp);
        return entry == null ? "" : entry.getValue(); 
    }

	
	
	/***
	
    private Map<String, List<Tuple>> map;
    
    public Q981_Time_Based_Key_Value_Store() {
        map = new HashMap<String, List<Tuple>>();
    }
    
    public void set(String key, String minValue, int timestamp) {
        if (!map.containsKey(key))
        {
            map.put(key, new LinkedList<Tuple>());
        }
        
        map.get(key).add(new Tuple(minValue, timestamp));
    }
    
    public String get(String key, int timestamp) {
        if (!map.containsKey(key))
        {
            return "";
        }
        
        int index = binarySearch(map.get(key), timestamp);
        return index == -1 ? "" : map.get(key).get(index).value;
    }
    
    private int binarySearch(List<Tuple>list, int curTimestamp)
    {
        if (list.size() == 0 || curTimestamp < list.get(0).timestamp)
        {
            return -1;
        }
        else if (curTimestamp > list.get(list.size()-1).timestamp)
        {
            return list.size()-1;
        }
        
        int left = 0, right = list.size()-1;
        
        while (left+1 < right)
        {
            int midIndex = left+(right-left)/2;
            int mid = list.get(midIndex).timestamp;
            
            if (mid < curTimestamp)
            {
                left = midIndex;
            }
            else if (mid > curTimestamp)
            {
                right = midIndex;
            }
            else
            {
                return midIndex;
            }
        }
        
        return list.get(right).timestamp <= curTimestamp ? right : left;
    }
    
    
    class Tuple
    {
        public String minValue;
        public int timestamp;
        
        public Tuple(String minValue, int timestamp)
        {
            this.value = minValue;
            this.timestamp = timestamp;
        }
    }
    
    ***/
}
