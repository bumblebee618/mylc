import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
/*******
 * 
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

For example,
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".

Note:
You may assume all letters are in lowercase.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
 * 
 * */


public class Q269_Alien_Dictionary 
{
	// using topology sort
	public String alienOrder(String[] words) 
	{
        if (words == null || words.length == 0)
        {
            return "";
        }
        
        Set<Character>[] graph = new Set[256];
        Map<Character, Integer> dependencyMap = new HashMap<>();
        Queue<Character> queue = new LinkedList<>();
        StringBuilder builder = new StringBuilder();
        
        for (String word : words)
        {
            for (char c : word.toCharArray())
            {
                dependencyMap.put(c, 0);
            }
        }
        
        for (int i = 0; i < words.length-1; i++)
        {
            if (words[i].equals(words[i+1]))
            {
                continue;
            }

            int len = Math.min(words[i].length(), words[i+1].length());
            
            for (int j = 0; j < len; j++)
            {
                char c1 = words[i].charAt(j);
                char c2 = words[i+1].charAt(j);
                
                if (c1 != c2)
                {
                    if (graph[c1] == null)
                    {
                        graph[c1] = new HashSet<>();
                    }
                
                    if (!graph[c1].contains(c2))
                    {
                        graph[c1].add(c2);
                        dependencyMap.put(c2, dependencyMap.get(c2) + 1);
                    }
                    
                    break;
                }
                
                if (j == len-1 && words[i].length() > words[i+1].length())
                {
                    return "";
                }
            }
        }
        
        for (Map.Entry<Character, Integer> entry : dependencyMap.entrySet())
        {
            if (entry.getValue() == 0)
            {
                queue.offer(entry.getKey());
            }
        }
        
        while (!queue.isEmpty())
        {
            char node = queue.poll();
            builder.append(node);
            
            if (graph[node] == null)
            {
                continue;
            }
            
            for (char next : graph[node])
            {
                int count = dependencyMap.get(next);
                
                if (count == 1)
                {
                    queue.offer(next);
                }
                else
                {
                    dependencyMap.put(next, count-1);
                }
            }
        }
        
        return builder.length() == dependencyMap.size() ? builder.toString() : "";
    }

	
	
	
	
	
	
	
	
	/******************************* main function ***********************************/
	
	public static void main(String[] args){
		Q269_Alien_Dictionary t = new Q269_Alien_Dictionary();
		String[] words = {
				"wrt",
				  "wrf",
				  "er",
				  "ett",
				  "rftt"
		};
		
		System.out.println(t.alienOrder(words));
	}
}
