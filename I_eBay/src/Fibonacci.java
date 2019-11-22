
public class Fibonacci {
	public int[] generateFibonacci(int number) {
		int[] result = new int[number];
		result[0] = 0;
		result[1] = 1;
		
		for (int i = 2; i <= number; i++) {
			result[i] = result[i-1] + result[i-2];
		}
		
		return result;
	}
}
