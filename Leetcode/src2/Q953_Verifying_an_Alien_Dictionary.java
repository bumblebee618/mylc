import java.util.Arrays;

/***
 * 
 * @author jackie
 * In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.

 

Example 1:

Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
Example 2:

Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
Example 3:

Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
 

Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 20
order.length == 26
All characters in words[i] and order are English lowercase letters.

 */
public class Q953_Verifying_an_Alien_Dictionary {
	// solution 1:
	public boolean isAlienSorted(String[] words, String order) {
        if (words == null || words.length <= 1) {
            return true;
        } else if (order == null || order.length() == 0) {
            return false;
        }
        
        int[] index = new int[256];
        Arrays.fill(index, -1);
        
        for (int i = 0; i < order.length(); i++) {
        	char c = order.charAt(i);
        	
            if (index[c] != -1) {
                return false;
            }
            
            index[c] = i;
        }
        
        for (int i = 0; i < words.length-1; i++) {
            if (words[i].equals(words[i+1])) {
                continue;
            }
            
            int size = Math.min(words[i].length(), words[i+1].length());
            
            for (int j = 0; j < size; j++) {
                char c1 = words[i].charAt(j);
                char c2 = words[i+1].charAt(j);
                
                if (index[c1] - index[c2] > 0) {
                    return false;
                } else if (index[c1] - index[c2] < 0) {
                    break;
                }
                
                if (j == size-1 && words[i].length() > words[i+1].length()) {
                    return false;
                }
            }
        }
        
        return true;
    }

	
	
	
	
	// solution 2:
	public boolean isAlienSorted2(String[] words, String order) {
        if (words == null || words.length == 0 || order == null || order.length() == 0)
        {
            return true;
        }
        
        int[] map = new int[26];
        
        for (int i = 0; i < order.length(); i++)
        {
            map[order.charAt(i)-'a'] = i;
        }
        
        for (int i = 0; i < words.length-1; i++)
        {
            int diffIndex = findFirstDiffPos(words[i], words[i+1]);
            
            if (diffIndex != -1)
            {
                int p1 = words[i].charAt(diffIndex)-'a';
                int p2 = words[i+1].charAt(diffIndex)-'a';
                
                if (map[p1] > map[p2])
                {
                    return false;
                }
            }
            else
            {
                if (words[i].length() > words[i+1].length())
                {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private int findFirstDiffPos(String word1, String word2)
    {
        int index = 0;
        
        while (index < word1.length() && index < word2.length())
        {
            if (word1.charAt(index) == word2.charAt(index))
            {
                index++;
            }
            else
            {
                return index;
            }
        }
        
        return -1;
    }
}
