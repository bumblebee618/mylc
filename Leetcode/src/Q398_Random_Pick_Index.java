/***
 * 
 * Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.

Note:
The array size can be very large. Solution that uses too much extra space will not pass the judge.

Example:

int[] nums = new int[] {1,2,3,3,3};
Solution solution = new Solution(nums);

// pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(3);

// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(1);

 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Q398_Random_Pick_Index {
	private Random rand = new Random();
    private Map<Integer, List<Integer>> map = new HashMap<>();
    
    public Q398_Random_Pick_Index(int[] nums) {
        if (nums == null || nums.length == 0)
        {
            return;
        }

        for (int i = 0; i < nums.length; i++)
        {
            map.computeIfAbsent(nums[i], x -> new ArrayList<Integer>()).add(i);
        }
    }
    
    public int pick(int target) {
        if (!map.containsKey(target))
        {
            return -1;
        }
        
        List<Integer> list = map.get(target);
        return list.get(rand.nextInt(list.size()));
    }

    
    
    
    
    /*** solution 2
    private Random rand = new Random();
    private int[] nums;
    
    public Solution(int[] nums) {
        if (nums == null || nums.length == 0)
        {
            return;
        }
        
        this.nums = nums;
    }
    
    public int pick(int target) {
        if (nums == null || nums.length == 0)
        {
            return -1;
        }
        
        int result = -1;
        int count = 0;
        
        for (int i = 0; i < nums.length; i++)
        {
            if (nums[i] != target)
            {
                continue;
            }
            
            if (rand.nextInt(++count) == 0)
            {
                result = i;
            }
        }
        
        return result;
    }
	***/
}
