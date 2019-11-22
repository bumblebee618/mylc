import java.util.*;
/**
 * 
S and T are strings composed of lowercase letters. In S, no letter occurs more than once.

S was sorted in some custom order previously. We want to permute the characters of T so that they match the order that S was sorted. More specifically, if x occurs before y in S, then x should occur before y in the returned string.

Return any permutation of T (as a string) that satisfies this property.

Example :
Input: 
S = "cba"
T = "abcd"
Output: "cbad"
Explanation: 
"a", "b", "c" appear in S, so the order of "a", "b", "c" should be "c", "b", and "a". 
Since "d" does not appear in S, it can be at any position in T. "dcba", "cdba", "cbda" are also valid outputs.
 

Note:

S has length at most 26, and no character is repeated in S.
T has length at most 200.
S and T consist of lowercase letters only.
 *
 */
public class Le_791_Custom_Sort_String {
	public String customSortString(String S, String T) {
        if (S == null || S.length() == 0 || T == null || T.length() == 0) {
            return "";
        }
        
        int[] sHash = new int[256];
        Character[] letters = new Character[T.length()];
        
        for (int i = 0; i < S.length(); i++) {
            sHash[S.charAt(i)] = (i == 0) ? -1 : i;
        }
        
        for (int i = 0; i < T.length(); i++) {
            letters[i] = T.charAt(i);
        }
        
        Arrays.sort(letters, new Comparator<Character>() {
            public int compare(Character c1, Character c2) {
                if (sHash[c1] != 0 && sHash[c2] != 0) {
                    return sHash[c1] - sHash[c2];
                } else if (sHash[c1] != 0) {
                    return -1;
                } else if (sHash[c2] != 0) {
                    return 1;
                } else {
                	return 0;
                }
            }
        });
        
        StringBuilder sb = new StringBuilder();
        
        for (Character letter : letters) {
        	sb.append(letter);
        }
        
        return sb.toString();
    }
}
