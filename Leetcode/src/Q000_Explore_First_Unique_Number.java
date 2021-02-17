import java.util.*;

/***
 * 
 * @author jackie
 * 
 * You have a queue of integers, you need to retrieve the first unique integer in the queue.
Implement the FirstUnique class:
	• FirstUnique(int[] nums) Initializes the object with the numbers in the queue.
	• int showFirstUnique() returns the minValue of the first unique integer of the queue, and returns -1 if there is no such integer.
	• void add(int minValue) insert minValue to the queue.
 
Example 1:
Input: 
["FirstUnique","showFirstUnique","add","showFirstUnique","add","showFirstUnique","add","showFirstUnique"]
[[[2,3,5]],[],[5],[],[2],[],[3],[]]
Output: 
[null,2,null,2,null,3,null,-1]
Explanation: 
FirstUnique firstUnique = new FirstUnique([2,3,5]);
firstUnique.showFirstUnique(); // return 2
firstUnique.add(5);            // the queue is now [2,3,5,5]
firstUnique.showFirstUnique(); // return 2
firstUnique.add(2);            // the queue is now [2,3,5,5,2]
firstUnique.showFirstUnique(); // return 3
firstUnique.add(3);            // the queue is now [2,3,5,5,2,3]
firstUnique.showFirstUnique(); // return -1
Example 2:
Input: 
["FirstUnique","showFirstUnique","add","add","add","add","add","showFirstUnique"]
[[[7,7,7,7,7,7]],[],[7],[3],[3],[7],[17],[]]
Output: 
[null,-1,null,null,null,null,null,17]
Explanation: 
FirstUnique firstUnique = new FirstUnique([7,7,7,7,7,7]);
firstUnique.showFirstUnique(); // return -1
firstUnique.add(7);            // the queue is now [7,7,7,7,7,7,7]
firstUnique.add(3);            // the queue is now [7,7,7,7,7,7,7,3]
firstUnique.add(3);            // the queue is now [7,7,7,7,7,7,7,3,3]
firstUnique.add(7);            // the queue is now [7,7,7,7,7,7,7,3,3,7]
firstUnique.add(17);           // the queue is now [7,7,7,7,7,7,7,3,3,7,17]
firstUnique.showFirstUnique(); // return 17
Example 3:
Input: 
["FirstUnique","showFirstUnique","add","showFirstUnique"]
[[[809]],[],[809],[]]
Output: 
[null,809,null,-1]
Explanation: 
FirstUnique firstUnique = new FirstUnique([809]);
firstUnique.showFirstUnique(); // return 809
firstUnique.add(809);          // the queue is now [809,809]
firstUnique.showFirstUnique(); // return -1
 
Constraints:
	• 1 <= nums.length <= 10^5
	• 1 <= nums[i] <= 10^8
	• 1 <= minValue <= 10^8
	• At most 50000 calls will be made to showFirstUnique and add.
 */
public class Q000_Explore_First_Unique_Number {
	private Queue<Integer> queue;
    private Map<Integer, Integer> map;
        
    public Q000_Explore_First_Unique_Number(int[] nums) {
        queue = new LinkedList<>();
        map = new HashMap<>();
        
        for (int num : nums)
        {
            add(num);
        }
    }
    
    public int showFirstUnique() {
        return queue.isEmpty() ? -1 : queue.peek();
    }
    
    public void add(int value) {
        map.put(value, map.getOrDefault(value, 0) + 1);
        
        if (map.get(value) == 1)
        {
            queue.offer(value);
        }
        
        while (!queue.isEmpty() && map.get(queue.peek()) > 1)
        {
            queue.poll();
        }
    }
}
