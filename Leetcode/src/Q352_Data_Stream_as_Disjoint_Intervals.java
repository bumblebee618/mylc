/***
 * Given a data stream input of non-negative integers a1, a2, ..., an, summarize the numbers seen so far as a list of disjoint intervals.

Implement the SummaryRanges class:

SummaryRanges() Initializes the object with an empty stream.
void addNum(int val) Adds the integer val to the stream.
int[][] getIntervals() Returns a summary of the integers in the stream currently as a list of disjoint intervals [starti, endi].
 

Example 1:

Input
["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals"]
[[], [1], [], [3], [], [7], [], [2], [], [6], []]
Output
[null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]], null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]

Explanation
SummaryRanges summaryRanges = new SummaryRanges();
summaryRanges.addNum(1);      // arr = [1]
summaryRanges.getIntervals(); // return [[1, 1]]
summaryRanges.addNum(3);      // arr = [1, 3]
summaryRanges.getIntervals(); // return [[1, 1], [3, 3]]
summaryRanges.addNum(7);      // arr = [1, 3, 7]
summaryRanges.getIntervals(); // return [[1, 1], [3, 3], [7, 7]]
summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
summaryRanges.getIntervals(); // return [[1, 3], [7, 7]]
summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
summaryRanges.getIntervals(); // return [[1, 3], [6, 7]]
 

Constraints:

0 <= val <= 104
At most 3 * 104 calls will be made to addNum and getIntervals.
 */


import java.util.*;



public class Q352_Data_Stream_as_Disjoint_Intervals {
	private TreeMap<Integer, int[]> treeMap;

    /** Initialize your data structure here. */
    public Q352_Data_Stream_as_Disjoint_Intervals() {
        treeMap = new TreeMap<>();
    }
    
    public void addNum(int val) {
        if (treeMap.containsKey(val)) {
            return;
        }
        
        Map.Entry<Integer, int[]> lower = treeMap.floorEntry(val);
        Map.Entry<Integer, int[]> upper = treeMap.ceilingEntry(val);
        
        // merge
        if (lower != null && upper != null && lower.getValue()[1]+1 == val && upper.getKey()-1 == val) {
            lower.getValue()[1] = upper.getValue()[1];
            treeMap.remove(upper.getKey());
        } else if (lower != null && lower.getValue()[1]+1 >= val) {
            lower.getValue()[1] = Math.max(val, lower.getValue()[1]);
        } else if (upper != null && upper.getKey()-1 == val) {
            treeMap.put(val, new int[] {val, upper.getValue()[1]});
            treeMap.remove(upper.getKey());
        } else {
            treeMap.put(val, new int[] {val, val});
        }
    }
    
    public int[][] getIntervals() {
        int[][] result = new int[treeMap.size()][2];
        int index = 0;
        
        for (int[] value : treeMap.values()) {
            result[index++] = value;
        }
        
        return result;
    }
	
	
	/***
	// by other using TreeMap
	private TreeMap<Integer, Interval> tree;

    public Q352_Data_Stream_as_Disjoint_Intervals() {
        tree = new TreeMap<>();
    }
    
    public void addNum(int val) {
        if(tree.containsKey(val)){
            return ;
        }
        
        Integer low = tree.lowerKey(val);
        Integer high = tree.higherKey(val);
        
        if(low != null && high != null && tree.get(low).end + 1 == val && high == val + 1){
            tree.get(low).end = tree.get(high).end;
            tree.remove(high);
        } else if(low != null && tree.get(low).end + 1 >= val){
            tree.get(low).end = Math.max(tree.get(low).end, val);
        } else if(high != null && high == val + 1){
            tree.put(val, new Interval(val, tree.get(high).end));
            tree.remove(high);
        } else{
            tree.put(val, new Interval(val, val));
        }
    }
    
    public List<Interval> getIntervals() {
        return new ArrayList<>(tree.values());
    }
    
    ***/
    
    
    /*** Solution 2: Use two treeMap
    
    private TreeMap<Integer, Integer> lowerMap;
    private TreeMap<Integer, Integer> upperMap;

    public SummaryRanges() {
        lowerMap = new TreeMap<>();
        upperMap = new TreeMap<>();
    }
    
    public void addNum(int val) {
        int left = val, right = val;
        
        Map.Entry<Integer, Integer> lower = lowerMap.ceilingEntry(val);
        
        if (lower != null && lower.getValue() <= val && lower.getKey() >= val) {
            return;
        }
    
        if (lowerMap.containsKey(val - 1)) {
            left = lowerMap.get(val - 1);
            lowerMap.remove(val - 1);
        }
        
        Map.Entry<Integer, Integer> upper = upperMap.ceilingEntry(val);
        
        if (upper != null && upper.getKey() <= val && upper.getValue() >= val) {
            return;
        }
        
        if (upperMap.containsKey(val + 1)) {
            right = upperMap.get(val + 1);
            upperMap.remove(val + 1);
        }
        
        lowerMap.put(right, left);
        upperMap.put(left, right);
    }
    
    public int[][] getIntervals() {
        int[][] result = new int[upperMap.size()][2];
        int index = 0;
        
        for (Map.Entry<Integer, Integer> entry : upperMap.entrySet()) {
            result[index++] = new int[] {entry.getKey(), entry.getValue()};
        }
        
        return result;
    }
    
    ***/
	
	
//	// by Jackie using binary search
//	private List<Interval> list;
//	
//    /** Initialize your data structure here. */
//    public Q352_Data_Stream_as_Disjoint_Intervals() {
//        list = new ArrayList<Interval>();
//    }
//    
//    public void addNum(int val) {
//        if(list.size() == 0){
//            list.add(new Interval(val, val));
//        } else {
//            int left = 0, right = list.size() - 1;
//            while(left + 1 < right){
//                int mid = left + (right - left) / 2;
//                Interval midInterval = list.get(mid);
//                if(val < midInterval.start){
//                    right = mid;
//                } else if(val > midInterval.end){
//                    left = mid;
//                } else {
//                    return ;
//                }
//            }
//            
//            Interval leftInterval = list.get(left);
//            Interval rightInterval = list.get(right);
//            
//            if(val < leftInterval.start){
//                if(left == 0){
//                    if(val + 1 == leftInterval.start){
//                        list.get(left).start = val;
//                    } else {
//                        list.add(0, new Interval(val, val));
//                    }
//                } else {
//                    Interval leftLeftInterval = list.get(left - 1);
//                    if(val + 1 == leftInterval.start && leftLeftInterval.end == val - 1){
//                        list.get(left).start = leftLeftInterval.start;
//                        list.remove(left - 1);
//                    } else if(val + 1 == leftInterval.start){
//                        list.get(left).start = val;
//                    } else if(leftLeftInterval.end == val - 1){
//                        list.get(left - 1).end = val;
//                    } else {
//                        list.add(left, new Interval(val, val));
//                    }
//                }
//            } else if(val > leftInterval.end && val < rightInterval.start){
//                if(val + 1 == rightInterval.start && val - 1 == leftInterval.end){
//                    list.get(left).end = rightInterval.end;
//                    list.remove(right);
//                } else if(val + 1 == rightInterval.start){
//                    list.get(right).start = val;
//                } else if(val - 1 == leftInterval.end){
//                    list.get(left).end = val;
//                } else {
//                    list.add(right, new Interval(val, val));
//                }
//            } else if(val > rightInterval.end){
//                if(right == list.size() - 1){
//                    if(val - 1 == rightInterval.end){
//                        list.get(right).end = val;
//                    } else {
//                        list.add(new Interval(val, val));
//                    }
//                } else {
//                    Interval rightRightInterval = list.get(right + 1);
//                    if(val + 1 == rightRightInterval.start && rightInterval.end == val - 1){
//                        list.get(right).end = rightRightInterval.end;
//                        list.remove(right + 1);
//                    } else if(val + 1 == rightRightInterval.start){
//                        list.get(right + 1).start = val;
//                    } else if(rightInterval.end == val - 1){
//                        list.get(right).end = val;
//                    } else {
//                        list.add(right + 1, new Interval(val, val));
//                    }
//                }
//            }
//        }
//    }
//    
//    public List<Interval> getIntervals() {
//        return list;
//    }
//    
//    public static void main(String[] args){
//    	Q352_Data_Stream_as_Disjoint_Intervals t = new Q352_Data_Stream_as_Disjoint_Intervals();
//    	
//    	int[] nums = {1, 3, 7, 2, 6};
//    	
//    	for(int i = 0; i < nums.length; i++){
//    		t.addNum(nums[i]);
//    		List<Interval> list = t.getIntervals();
//    		for(int j = 0; j < list.size(); j++){
//    			System.out.print("[" + list.get(j).start + ", " + list.get(j).end + "]  ");
//    		}
//    		System.out.println();
//    	}
//    }
//    
//    class Interval {
//    	int start;
//    	int end;
//    	Interval() { start = 0; end = 0; }
//    	Interval(int s, int e) { start = s; end = e; }
//    }
}
