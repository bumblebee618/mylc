import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class Q_Word_Combination {
	// solution 1:
	public List<String> findWordCombination(List<String> words) {
		List<String> result = new LinkedList<String>();
		
		if (words == null || words.size() <= 2) {
			return result;
		}
		
		Set<String> set = new HashSet<>();
		
		for (String word : words) {
			set.add(word);
			addWord(word);
		}
		
		for (String word : words) {
			for (int end = 1; end < word.length()-1; end++) {
				String part1 = word.substring(0, end);
				
				if (set.contains(part1)) {
					String part2 = word.substring(end);
					
					if (set.contains(part2)) {
						result.add(part1);
						result.add(part2);
					}
				}
			}
		}
		
		return result;
	}
	
	
	
	// solution 2:
	private Trie root = new Trie();
	
	public List<String> findWordCombination2(List<String> words) {
		List<String> result = new LinkedList<String>();
		
		if (words == null || words.size() <= 2) {
			return result;
		}
		
		Set<String> set = new HashSet<>();
		
		for (String word : words) {
			set.add(word);
			addWord(word);
		}
		
		for (String word : words) {
			Trie node = root;
			
			for (int i = 0; i < word.length(); i++) {
				node = node.children[word.charAt(i)];
				
				if (node.isWord) {
					String part2 = word.substring(i+1);
					
					if (set.contains(part2)) {
						String part1 = word.substring(0, i+1);
						result.add(part1);
						result.add(part2);
					}
				}
			}
		}
		
		return result;
	}
	
	private void addWord(String word) {
		Trie node = root;
		
		for (char c : word.toCharArray()) {
			if (node.children[c] == null) {
				node.children[c] = new Trie();
			}
			
			node = node.children[c];
		}
		
		node.isWord = true;
	} 
	
	class Trie {
		public Trie[] children;
		public boolean isWord;
		
		public Trie() {
			children = new Trie[256];
			isWord = false;
		}
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		Q_Word_Combination test = new Q_Word_Combination();
		List<String> words = new LinkedList<>();
		words.add("ubereats");
		words.add("uber");
		words.add("eats");
		words.add("breadcrumb");
		words.add("bread");
		words.add("crumb");
		
		List<String> result = test.findWordCombination(words);
		
		for (String word : result) {
			System.out.println(word);
		}
	}
}
