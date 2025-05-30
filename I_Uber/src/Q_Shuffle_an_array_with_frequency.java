import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Q_Shuffle_an_array_with_frequency {
	private int[] nums;
    private Random random;
    private Map<String, Integer> map;

    public Q_Shuffle_an_array_with_frequency(int[] nums) {
        if (nums == null) {
            return;
        }
        
        random = new Random();
        this.nums = nums;
        map = new HashMap<>();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int[] result = nums.clone();
        
        for (int i = 0; i < nums.length; i++) {
            int j = random.nextInt(i+1);
            int temp = result[i];
            result[i] = result[j];
            result[j] = temp;
        }
        
        String hash = getHashCode(result);
        map.put(hash, map.getOrDefault(hash, 0)+1);
        return result;
    }
    
    private String getHashCode(int[] nums) {
    	StringBuilder hash = new StringBuilder();
    	
    	for (int num : nums) {
    		hash.append(Integer.toString(num)).append(","); 
    	}
    	
    	return hash.toString();
    }
    
    
    
    
    
    private void printNums(int[] nums) {
    	for (int num : nums) {
    		System.out.print(num + ", ");
    	}
    	
    	System.out.println();
    }
    
    public static void main(String[] args) {
    	int[] nums = {1, 2, 3};
    	Q_Shuffle_an_array_with_frequency test = new Q_Shuffle_an_array_with_frequency(nums);
    	test.printNums(test.shuffle());
    	test.printNums(test.shuffle());
    	test.printNums(test.shuffle());
    	test.printNums(test.shuffle());
    	test.printNums(test.shuffle());
    }
}
