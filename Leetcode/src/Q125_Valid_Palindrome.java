/*******
 * 
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
	"A man, a plan, a canal: Panama" is a palindrome.
	"race a car" is not a palindrome.

Note:
	Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.
 * 
 * */


public class Q125_Valid_Palindrome {
	public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0)
        {
            return true;
        }
        
        int left = 0, right = s.length()-1;
        String str = s.toLowerCase();
        char[] letters = str.toCharArray();
        
        while (left < right)
        {
            while (left < right && !Character.isLetterOrDigit(letters[left]))
            {
                left++;
            }
            
            while (left < right && !Character.isLetterOrDigit(letters[right]))
            {
                right--;
            }
            
            if (left < right && letters[left] != letters[right])
            {
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }
	
	
	
	
	
	public static void main(String[] args){
		Q125_Valid_Palindrome t = new Q125_Valid_Palindrome();
//		String[] test = {"", "1", "22", "313", "333", "A man, a plan, a canal: Panama", "23", "321", "race a car"};
		
		String[] test = {"Live on evasions? No I save no evil."};
		
		for(int i = 0; i < test.length; ++i){
			System.out.println(t.isPalindrome(test[i]));
		}
	}
}
