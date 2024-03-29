import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/********
 * 
Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
A solution is:

[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]

 * 
 * */

public class Q249_Group_Shifted_Strings {
	public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> result = new LinkedList<>();
        
        if (strings == null || strings.length == 0) {
            return result;
        }
        
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strings) {
            String key = findOriginStr(str);
            map.computeIfAbsent(key, x -> new LinkedList<>()).add(str);
        }
        
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            result.add(entry.getValue());
        }
        
        return result;
    }
    
    private String findOriginStr(String str) {
        char[] letters = str.toCharArray();
        int diff = letters[0] - 'a';
        
        for (int i = 0; i < letters.length; i++) {
            letters[i] = (char) ((letters[i] - diff + 26) % 26);
        }
        
        return new String(letters);
    }

	

	
    
    
    
    /************************************** main function ****************************************/
    
    public static void main(String[] args){
    	Q249_Group_Shifted_Strings t = new Q249_Group_Shifted_Strings();
    	String[] strings = {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
//    	String[] strings = {"az", "ba"};
    	List<List<String>> ans = t.groupStrings(strings);
    	
    	for(int i = 0; i < ans.size(); i++){
    		for(int j = 0; j < ans.get(i).size(); j++){
    			System.out.print(ans.get(i).get(j) + ", ");
    		}
    		System.out.println();
    	}
    }
}
