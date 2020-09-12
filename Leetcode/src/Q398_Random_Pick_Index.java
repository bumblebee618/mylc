import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Q398_Random_Pick_Index {
	private Random rand = new Random();
    private HashMap<Integer, List<Integer>> map = new HashMap<>();

    public Q398_Random_Pick_Index(int[] nums) {
        if (nums == null || nums.length == 0)
        {
            return;
        }
        
        for (int i = 0; i < nums.length; i++)
        {
            List<Integer> indexes = map.getOrDefault(nums[i], new ArrayList<Integer>());
            indexes.add(i);
            map.put(nums[i], indexes);
        }
    }
    
    public int pick(int target) {
        if (!map.containsKey(target))
        {
            return -1;
        }
        
        List<Integer> indexes = map.get(target);
        int pos = rand.nextInt(indexes.size());
        return indexes.get(pos);
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
