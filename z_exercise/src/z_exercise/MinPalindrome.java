package z_exercise;

public class MinPalindrome {
	public int minPalindrome(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		
		int len = str.length();
		int[][] dp = new int[len][len];
		
		for (int i = 0; i < len-1; i++) {
			dp[i][i+1] = str.charAt(i) == str.charAt(i+1) ? 0 : 1;
		}
		
		for (int length = 2; length < len; length++) {
			for (int start = 0; start + length < len; start++) {
				int end = start + length;
				
				if (str.charAt(start) == str.charAt(end)) {
					dp[start][end] = dp[start+1][end-1];
				} else {
					dp[start][end] = Math.min(dp[start+1][end], dp[start][end-1]) + 1;
				}
			}
		}
		
		return dp[0][len-1];
	}
	
	
	public void print(int[][] dp) {
		for (int[] array : dp) {
			for (int elem : array) {
				System.out.print(elem + ", ");
			}
			System.out.println();
		}
	}
	
	
	public static void main(String[] args) {
		MinPalindrome test = new MinPalindrome();
		String str = "abac";
		System.out.println(test.minPalindrome(str));
	}
}
