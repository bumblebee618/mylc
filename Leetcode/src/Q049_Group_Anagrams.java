import java.util.*;

/******
Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]

Note: All inputs will be in lower-case.

 * 
 * 
 * */

public class Q049_Group_Anagrams {
	public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new LinkedList<>();
        
        if (strs == null || strs.length == 0) {
            return result;
        }
        
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            String originalStr = getOriginalStr(str);
            map.computeIfAbsent(originalStr, x -> new LinkedList<String>()).add(str);
        }
        
        for (String str : map.keySet()) {
            result.add(map.get(str));
        }
        
        return result;
    }
    
    private String getOriginalStr(String word) {
        char[] letters = word.toCharArray();
        Arrays.sort(letters);
        return new String(letters);
    }
    
    
    
    
	
	/************************ main function ***************************/
	
    public static void main(String[] args){
    	Q049_Group_Anagrams t = new Q049_Group_Anagrams();
    	String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
    	List<List<String>> res = t.groupAnagrams(strs);
    	
    	for(int i = 0; i < res.size(); ++i){
    		for(int j = 0; j < res.get(i).size(); ++j){
    			System.out.print(res.get(i).get(j) + ", ");
    		}
    		System.out.println();
    	}
    }
}
