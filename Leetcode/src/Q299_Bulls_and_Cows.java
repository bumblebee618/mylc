public class Q299_Bulls_and_Cows {
	/**********************************/
	// by Jackie
	public String getHint(String secret, String guess) {
        if (secret == null || secret.length() == 0 || guess == null || guess.length() == 0 || secret.length() != guess.length()) {
            return "";
        }
        
        int len = secret.length();
        int countA = 0;
        int countB = 0;
        int[] hash1 = new int[256];
        int[] hash2 = new int[256];
        
        for (int i = 0; i < len; i++) {
            char c1 = secret.charAt(i);
            char c2 = guess.charAt(i);
            
            if (c1 == c2) {
                countA++;
            } else {
                hash1[c1]++;
                hash2[c2]++;
            }
        }
        
        for (int i = 0; i < 256; i++) {
            countB += Math.min(hash1[i], hash2[i]);
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(countA).append("A").append(countB).append("B");
        return sb.toString();
    }
	
	
	
	/**********************************/
	// by Jackie
	public String getHint2(String secret, String guess) {
		int bulls = 0, cows = 0;
        int[] words = new int[10];

        for(int i = 0, len = secret.length(); i < len; ++i)
            words[secret.charAt(i) - '0']++;
        
        for(int i = 0, len = secret.length(); i < len; ++i){
            char g = guess.charAt(i);
            char s = secret.charAt(i);
            if(g == s){
            	if(words[g - '0'] == 0)
            		cows--;
            	else
            		words[g - '0']--;
            	bulls++;
            }
            else if(words[g - '0'] != 0){
                words[g - '0']--;
                cows++;
            }
        }
        
        String s = new String();
        s = bulls + "A" + cows + "B";        
        return s.toString();
    }
	
	public static void main(String[] args){
		Q299_Bulls_and_Cows t = new Q299_Bulls_and_Cows();
		System.out.println(t.getHint("1121", "0311"));
	}
}
