
public class Liveperseon {
	public int findNumberOfSubArrayLessThanK(int[] nums, long k) {
		int n = nums.length, count = 0;
		int totalCount = (1 + n) * n / 2;
		long sum = 1;
		
		for (int front = 0, back = 0; front < n; front++) {
			sum *= nums[front];
			
			while (sum > k) {
				count += n-front;
				sum /= nums[back++];
			}
		}
		
		return totalCount - count;
	}
	
	
	
	public static void main(String[] args) {
		Liveperseon test = new Liveperseon();
		int[] nums = {1,2,3,4,5};
		int k = 6;
		System.out.println(test.findNumberOfSubArrayLessThanK(nums, k));
	}
}
