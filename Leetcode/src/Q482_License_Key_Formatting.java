
public class Q482_License_Key_Formatting {
	// test case: [------] [k = 100] -> "",  [2] [k = 2] -> s
	public String licenseKeyFormatting(String s, int k) {
		if(s == null || s.length() == 0 || k <= 0) {
            return s;
        } 
		
        StringBuilder sb = new StringBuilder();
        
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != '-') {
                sb.append(sb.length() % (k + 1) == k ? '-' : "").append(s.charAt(i));  // the number of character in current group is n !!!
            }
        }
        
        return sb.reverse().toString().toUpperCase();
    }
	
	
	// Solution 2
	public String licenseKeyFormatting2(String S, int K) {
        if (S == null || S.length() == 0 || K <= 0)
        {
            return S;
        }
        
        StringBuilder builder = new StringBuilder();
        StringBuilder cache = new StringBuilder();
        int gap = 'a' - 'A';
        
        for (int i = S.length()-1; i >= 0; i--)
        {
            char c = S.charAt(i);
            
            if (c == '-')
            {
                continue;
            }
            
            if (c >= 'a' & c <= 'z')
            {
                c = (char) (c-gap);
            }
            
            cache.insert(0, c);
            
            if (cache.length() == K)
            {
                builder.insert(0, cache.toString()).insert(0, "-");
                cache = new StringBuilder();
            }
        }
        
        if (cache.length() > 0)
        {
            builder.insert(0, cache.toString());
        }
        
        return (builder.length() > 0 && builder.charAt(0) == '-') ? builder.substring(1) : builder.toString();
    }

	
	
	
	
	
	
	
	
	
	
	/********************************* main function *****************************************/
	
	public static void main(String[] args) {
		Q482_License_Key_Formatting t = new Q482_License_Key_Formatting();
		String S = "2-4A0r7-4k";
		int K = 3;
		System.out.println(t.licenseKeyFormatting(S, K));
	}
}
