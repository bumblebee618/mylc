
public class Q744_Find_Smallest_Letter_Greater_Than_Target {
	// solution 1: time is O(n)
	public char nextGreatestLetter(char[] letters, char target) {
        if (letters == null || letters.length == 0 || target == ' ') {
            return ' ';
        }
        
        for (char c : letters) {
            if (c > target) {
                return c;
            }
        }   
        
        return letters[0];
    }
	
	
	
	// solution 2: time is O(n)
	public char nextGreatestLetter2(char[] letters, char target) {
        if (letters == null || letters.length == 0 || target == ' ') {
            return ' ';
        } 
        
        int[] hash = new int[256];
        
        for (char c : letters) {
            hash[c]++;
        }
        
        while (true) {
            target++;
            
            if (target > 'z') {
                target = 'a';
            }
            
            if (hash[target] > 0) {
                return target;
            }
        }
    }
}
