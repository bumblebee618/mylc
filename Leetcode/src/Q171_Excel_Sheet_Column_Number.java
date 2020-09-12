
public class Q171_Excel_Sheet_Column_Number {
	public int titleToNumber(String s) {
        if (s == null || s.length() == 0)
        {
            return 0;
        }
        
        long sum = 0;
        int base = 26;
        
        for (int i = 0; i < s.length(); i++)
        {
            int digit = s.charAt(i)-'A'+1;
            sum = sum * base + digit;
        }
        
        return sum > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) sum;
    }
	
	
	public static void main(String[] args){
		Q171_Excel_Sheet_Column_Number t = new Q171_Excel_Sheet_Column_Number();
		System.out.println(t.titleToNumber("AAZ"));
	}
}
