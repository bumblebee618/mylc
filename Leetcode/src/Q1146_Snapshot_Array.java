import java.util.*;

/***
 * 
 * @author jackie
 * Implement a SnapshotArray that supports the following interface:

SnapshotArray(int length) initializes an array-like data structure with the given length.  Initially, each element equals 0.
void set(index, val) sets the element at the given index to be equal to val.
int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
 

Example 1:

Input: ["SnapshotArray","set","snap","set","get"]
[[3],[0,5],[],[0,6],[0,0]]
Output: [null,null,0,null,5]
Explanation: 
SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
snapshotArr.set(0,5);  // Set array[0] = 5
snapshotArr.snap();  // Take a snapshot, return snap_id = 0
snapshotArr.set(0,6);
snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5
 

Constraints:

1 <= length <= 50000
At most 50000 calls will be made to set, snap, and get.
0 <= index < length
0 <= snap_id < (the total number of times we call snap())
0 <= val <= 10^9
 */
public class Q1146_Snapshot_Array {
	private List<Map<Integer, Integer>> snapshotList;
    private HashMap<Integer, Integer> currentMap;
    private int snapId;
    private int length;
    
    public Q1146_Snapshot_Array(int length) {
        this.length = length;
        snapId = -1;
        snapshotList = new ArrayList<Map<Integer, Integer>>();
        currentMap = new HashMap<Integer, Integer>();
    }
    
    public void set(int index, int val) {
         if(index < length && index >= 0)
         {
             currentMap.put(index, val);
         }
    }
    
    public int snap() {
        snapId++;
        // Map<Integer, Integer> copy = (HashMap<Integer, Integer>)this.currentMap.clone();
        Map<Integer, Integer> copy = new HashMap<Integer, Integer>(currentMap);
        snapshotList.add(copy);      
        return snapId;
    }
    
    public int get(int index, int snap_id) {
        if(snap_id >= 0 && snapshotList.size() >= snap_id+1)
        {
            return snapshotList.get(snap_id).getOrDefault(index, 0);
        }
        else
        {
            return 0;
        }
    }
}