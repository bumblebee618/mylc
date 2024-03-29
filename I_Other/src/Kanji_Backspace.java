
public class Kanji_Backspace {
	private int k = 3;
	
	public int decodeString(String input)
	{
		if (input == null || input.length() == 0 || input.length() % k != 0)
		{
			return 0;
		}
		
		int[] dp = new int[input.length()+1];
		dp[0] = 1;
		
		for (int i = k; i <= input.length(); i += k)
		{
			if (input.charAt(i-k) == '0')
			{
				dp[i] = dp[i-k];
			}
			
			if (i >= 2*k && input.charAt(i - 2*k) == '1')
			{
				dp[i] += dp[i - 2*k];
			}
		}
		
		return dp[input.length()];
	}
	
	public static void main(String[] args)
	{
		Kanji_Backspace test = new Kanji_Backspace();
		String input = "011111011011";
		System.out.print(test.decodeString(input));
	}
}
