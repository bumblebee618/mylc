
public class Q268_Missing_Number {
	//by other using math
	public int missingNumber(int[] nums) {  //1～n求和，然后按个减去数组里的元素，剩下的即为所要求的数
		if(nums == null || nums.length == 0){
            return 0;
        }
        
        int len = nums.length;
        long sum = len * (len + 1) / 2;
        
        for(int num : nums){
            sum -= num;
        }
        
        return (int) sum;
    }
	
	// by Jackie using bit calculation
	public int missingNumber2(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        
        int ans = 0;
        int n = nums.length;
        
        for(int num : nums){
            ans ^= num;
        }
        
        for(int i = 1; i <= n; i++){
            ans ^= i;
        }
        
        return ans;
    }
	
	// use sort, time is O(n)
	public int missingNumber3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int len = nums.length;
        
        for (int i = 0; i < len; i++) {
            while (nums[i] >= 0 && nums[i] < len && nums[nums[i]] != nums[i]) {
                int temp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = temp;
            }
        }
        
        for (int i = 0; i < len; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        
        return len;
    }
	
	//by other using bit calculation
//	public class Solution {
//	    //bit manipulation
//	    public int missingNumber(int[] nums){
//	        int[] bits = new int [32];
//	        for(int i = 0; i < nums.length; i++){
//	            int number = i + 1;
//	            for(int j = 0; j < 32; j++){
//	                bits[j] ^= number & 1;
//	                bits[j] ^= nums[i] & 1;
//	                number >>= 1;
//	                nums[i] >>= 1;
//	            }
//	        }
//	        int result = 0;
//	        int tmp = 1;
//	        for(int i = 0; i < 32; i++){
//	            result += bits[i] == 1 ? tmp : 0;
//	            tmp <<= 1;
//	        }
//	        return result;
//	    }
//	}
}
