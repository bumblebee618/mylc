import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
/***
 * 
 * @author jackie
 *
 * Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.

Example 1:

Input: nums = [1,2,3,1], k = 3
Output: true
Example 2:

Input: nums = [1,0,1,1], k = 1
Output: true
Example 3:

Input: nums = [1,2,3,1,2,3], k = 2
Output: false
 */

public class Q219_Contains_Duplicate_II {
	public boolean containsNearbyDuplicate(int[] nums, int k) 
	{
        if (nums == null || k <= 0)
        {
        	return false;
        }
        
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < len; ++i)
        {
            if (map.containsKey(nums[i]))
            {
                if (i - map.get(nums[i]) <= k)
                {
                    return true;
                }
            }
            
            map.put(nums[i], i);
        }
        
        return false;
    }
	
	
	
	
	
	
	
	
	
	public boolean containsNearbyDuplicate2(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return false;
        }
        
        Node[] array = new Node[nums.length];
        
        for(int i = 0; i < nums.length; i++){
            array[i] = new Node(nums[i], i);
        }
        
        Arrays.sort(array, new Comparator<Node>(){
            public int compare(Node left, Node right){
                if(left.value != right.value){
                    return left.value - right.value;
                } else {
                    return left.index - right.index;
                }
            }
        });
        
        for(int i = 0; i < nums.length - 1; i++){
            if(array[i].value == array[i + 1].value && Math.abs(array[i].index - array[i + 1].index) <= k){
                return true;
            }
        }
        
        return false;
    }
    
    class Node {
        int value;
        int index;
        
        public Node(int v, int i){
            value = v;
            index = i;
        }
    }
}
