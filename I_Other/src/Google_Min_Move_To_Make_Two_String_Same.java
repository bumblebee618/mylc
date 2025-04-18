
public class Google_Min_Move_To_Make_Two_String_Same {
	public int minMove(String target, String str) {
		int size = target.length();
		int[][] dp = new int[size+1][size+1];
		
		for (int i = 1; i <= size; i++) {
			for (int j = 1; j <= size; j++) {
				if (target.charAt(i-1) == str.charAt(j-1)) {
					dp[i][j] = Math.max(dp[i-1][j-1]+1, dp[i][j-1]);
				} else {
					dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
				}
			}
		}
		
		return size - dp[size][size];
	}
	
	
	
	
	
	public static void main(String[] args) {
		Google_Min_Move_To_Make_Two_String_Same test = new Google_Min_Move_To_Make_Two_String_Same();
		String target = "abcdef";
		String str = "fadbec";
		System.out.println(test.minMove(target, str));
	}
}
