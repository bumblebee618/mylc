import java.util.HashMap;
import java.util.Map;

/***
 * 
 * 
 * Given a pattern and a string s, find if s follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.

 

Example 1:

Input: pattern = "abba", s = "dog cat cat dog"
Output: true
Example 2:

Input: pattern = "abba", s = "dog cat cat fish"
Output: false
Example 3:

Input: pattern = "aaaa", s = "dog cat cat dog"
Output: false
 

Constraints:

1 <= pattern.length <= 300
pattern contains only lower-case English letters.
1 <= s.length <= 3000
s contains only lowercase English letters and spaces ' '.
s does not contain any leading or trailing spaces.
All the words in s are separated by a single space.
 * 
 *
 */

public class Q290_Word_Pattern {
	/***************************************/
	public boolean wordPattern(String pattern, String str) {
        if(pattern == null || str == null){
            if(pattern == null && str == null){
                return true;
            } else {
                return false;
            }
        }
        
        String[] array = str.split("\\s{1,}");
        // String[] array = str.split(" ");
        
        if(pattern.length() != array.length){
            return false;
        }
        
        Map<Character, String> map = new HashMap<Character, String>();
        char[] letters = pattern.toCharArray();
        int len = letters.length;
        
        for(int i = 0; i < len; i++){
            if(map.containsKey(letters[i])){
                if(!map.get(letters[i]).equals(array[i])){
                    return false;
                }
            } else if(map.containsValue(array[i])){  // O(n)
                return false;
            } else {
                map.put(letters[i], array[i]);
            }
        }
        
        return true;
    }
	
	
	
	/***************************************/
	// two hashmap
	public boolean wordPattern2(String pattern, String str) {
		if(pattern == null || pattern.length() == 0){
			return false;
		}
        if(str == null || str.length() == 0){
        	return false;
        }
        
        String[] arr= str.split(" ");
        HashMap<Character, String> map = new HashMap<Character, String>();
        if(arr.length!= pattern.length())
            return false;
        for(int i=0; i<arr.length; i++){
            char c = pattern.charAt(i);
            if(map.containsKey(c)){
                if(!map.get(c).equals(arr[i]))
                    return false;
            }else{
                if(map.containsValue(arr[i]))
                    return false;
                map.put(c, arr[i]);
            }    
        }
        return true;      
    }
	
	
	
	/*************************** main *******************************/
	public boolean wordPattern3(String pattern, String str) {
        if(pattern == null || pattern.length() == 0) return false;
        if(str == null || str.length() == 0) return false;
        
        String[] wordArray = str.split("\\s{1,}");
        if(pattern.length() != wordArray.length) return false;
        HashMap myMap1 = new HashMap<String, Integer>();
        HashMap myMap2 = new HashMap<Character, Integer>();
        for(int i = 0; i < wordArray.length; i++) {
            if(myMap1.containsKey(wordArray[i]) == myMap2.containsKey(pattern.charAt(i))){
                if(!myMap1.containsKey(wordArray[i])){
                    myMap1.put(wordArray[i], i);
                    myMap2.put(pattern.charAt(i), i);
                }
            }
            else
                return false;
        }
        return true;
    }
	
	public static void main(String[] args){
		Q290_Word_Pattern test = new Q290_Word_Pattern();
		String pattern = "abba";
		String str = "dog cat cat fish";
		System.out.println(test.wordPattern(pattern, str));
	}
}
