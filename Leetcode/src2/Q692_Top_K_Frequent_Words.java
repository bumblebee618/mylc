import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 
Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

Example 1:
Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.
Example 2:
Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
    with the number of occurrence being 4, 3, 2 and 1 respectively.
Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Input words contain only lowercase letters.
Follow up:
Try to solve it in O(n log k) time and O(n) extra space.
 *
 */
public class Q692_Top_K_Frequent_Words {
	public List<String> topKFrequent(String[] words, int k) 
	{
        List<String> result = new LinkedList<>();
        
        if (words == null || words.length == 0)
        {
            return result;
        }
        
        Map<String, Integer> map = new HashMap<>();
        
        for (String word : words) 
        {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        
        Queue<Tuple> minHeap = new PriorityQueue<>(k+1, new Comparator<Tuple>() 
        {
        	@Override
            public int compare(Tuple t1, Tuple t2) 
            {
                if (t1.freq != t2.freq) 
                {
                    return t1.freq - t2.freq;
                } 
                else 
                {
                    return t2.word.compareTo(t1.word);                    
                }
            }
        });
        
        for (String word : map.keySet()) 
        {
            int freq = map.get(word);
            minHeap.offer(new Tuple(word, freq));
            
            if (minHeap.size() > k) 
            {
                minHeap.poll();
            }
        }
        
        while (!minHeap.isEmpty()) 
        {
            result.add(0, minHeap.poll().word);
        }
        
        return result;
    }
    
    class Tuple {
        String word;
        int freq;
        
        public Tuple(String word, int freq) {
            this.word = word;
            this.freq = freq;
        }
    }
}
