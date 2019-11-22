import java.util.Arrays;
import java.util.HashMap;
import java.util.*;
import java.util.Map;

import javax.lang.model.element.Element;

public class Sort_word_by_Frequency {
	// solution 1, time is O(n) + O(logn) = O(n)
	public String sortWords(String[] words, int k) {
		if (words == null || words.length == 0) {
			return "";
		}
		
		Map<String, Integer> map = new HashMap<>();
		
		for (String word : words) {
			map.put(word, map.getOrDefault(word, 0) + 1);
		}
		
		Tuple[] tuples = new Tuple[map.size()];
		int index = 0;
		
		for (String word : map.keySet()) {
			tuples[index++] = new Tuple(word, map.get(word));
		}
		
		return tuples[quickSelect(tuples, k)].word;		
	}
	
	private int quickSelect(Tuple[] tuples, int k) {
		int left = 0, right = tuples.length - 1;
		
		while (left < right) {
			int index = partition(tuples, left, right);
			
			if (index + 1 < k) {
				left = index + 1;
			} else if (index + 1 > k) {
				right = index - 1;
			} else {
				return index;
			}
		}
		
		return left;
	}
	
	private int partition(Tuple[] tuples, int left, int right) {
		int pivot = tuples[right].frequency;
		int index = left;
		
		for (int i = left ; i < right; i++) {
			if (tuples[i].frequency >= pivot) {
				Tuple temp = tuples[left];
				tuples[left] = tuples[index];
				tuples[index] = temp;
				index++;
			}
		}
		
		Tuple temp = tuples[right];
		tuples[right] = tuples[index];
		tuples[index] = temp;
		return index;
	}
	
	
	// solution 2: time is O(nlogn)
	public List<String> sortWords2(String[] words, int k) {
		List<String> result = new LinkedList<>();
		
		if (words == null || words.length == 0) {
			return result;
		}
		
		Map<String, Integer> map = new HashMap<>();
		
		for (String word : words) {
			map.put(word, map.getOrDefault(word, 0) + 1);
		}
		
		Tuple[] tuples = new Tuple[map.size()];
		int index = 0;
		
		for (String word : map.keySet()) {
			tuples[index++] = new Tuple(word, map.get(word));
		}
		
		Arrays.sort(tuples, (a, b) -> b.frequency - a.frequency);
		int prevFreq = 0, count = 0;
		
		for (int i = 0; i < tuples.length; i++) {
			if (tuples[i].frequency != prevFreq) {
				prevFreq = tuples[i].frequency;
				count++;
				
				if (count > k) {
					break;
				}
			}
			
			result.add(tuples[i].word);
		}
		
		return result;
	}
	
	
	public static void main(String[] args) {
		Sort_word_by_Frequency test = new Sort_word_by_Frequency();
		String[] words = {"a", "a", "b", "b", "c", "c", "beta", "beta", "beta", "alpha"};
		int k = 2;
		System.out.println(test.sortWords(words, k));
		
		List<String> result = test.sortWords2(words, k);
		result.stream().forEach(str -> System.out.println(str));
	}
}





class Tuple {
	String word;
	int frequency;
	
	public Tuple (String word, int frequency) {
		this.word = word;
		this.frequency = frequency;
	}
}
