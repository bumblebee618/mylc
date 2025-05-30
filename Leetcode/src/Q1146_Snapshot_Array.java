import java.util.*;

/***
 * 
 * @author jackie
 * Implement a SnapshotArray that supports the following interface:

SnapshotArray(int capacity) initializes an array-like data structure with the given capacity.  Initially, each element equals 0.
void set(index, val) sets the element at the given index to be equal to val.
int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
int get(index, snap_id) returns the minValue at the given index, at the time we took the snapshot with the given snap_id
 

Example 1:

Input: ["SnapshotArray","set","snap","set","get"]
[[3],[0,5],[],[0,6],[0,0]]
Output: [null,null,0,null,5]
Explanation: 
SnapshotArray snapshotArr = new SnapshotArray(3); // set the capacity to be 3
snapshotArr.set(0,5);  // Set array[0] = 5
snapshotArr.snap();  // Take a snapshot, return snap_id = 0
snapshotArr.set(0,6);
snapshotArr.get(0,0);  // Get the minValue of array[0] with snap_id = 0, return 5
 

Constraints:

1 <= capacity <= 50000
At most 50000 calls will be made to set, snap, and get.
0 <= index < capacity
0 <= snap_id < (the total number of times we call snap())
0 <= val <= 10^9
 */
public class Q1146_Snapshot_Array 
{
	private List<Map<Integer, Integer>> snapshotList;
    private Map<Integer, Integer> currentMap;
    private int capacity;
    
    public Q1146_Snapshot_Array(int capacity) 
    {
        this.capacity = capacity;
        snapshotList = new ArrayList<>();
        currentMap = new HashMap<>();
    }
    
    public void set(int index, int val) 
    {
        if (index >= 0 && index < capacity)
        {
            currentMap.put(index, val);
        }
    }
    
    public int snap() 
    {
        // Map<Integer, Integer> copy = (HashMap<Integer, Integer>)this.currentMap.clone();
        Map<Integer, Integer> copy = new HashMap<>(currentMap);
        snapshotList.add(copy);      
        return snapshotList.size()-1;
    }
    
    public int get(int index, int snap_id) {
        if(snap_id >= 0 && snap_id < snapshotList.size())
        {
            return snapshotList.get(snap_id).getOrDefault(index, 0);
        }
        else
        {
            return 0;
        }
    }
}
