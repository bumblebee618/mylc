import java.util.*;



public class AutoCompleteSystem {
	private Trie root;
	private String cur_sent = "";
	private int k = 5;
	
	public AutoCompleteSystem(String[] words, int[] times) {
		root = new Trie();
		
		if (words == null || times == null || words.length != times.length) {
			return;
		}
		
		for (int i = 0; i < words.length; i++) {
			insert(words[i], times[i]);
		}
	}
	
	public List<String> getOutput(char c) {
		List<String> result = new LinkedList<>();
		
		if (c == '#') {
			insert(cur_sent, 1);
			cur_sent = "";
		} else {
			cur_sent += c;
			List<Tuple> list = lookup(cur_sent);
			Collections.sort(list, (a, b) -> a.time != b.time ? b.time - a.time : a.sentence.compareTo(b.sentence));
			
			for (int i = 0; i < k && i < list.size(); i++) {
				result.add(list.get(i).sentence);
			}
		}
		
		return result;
	}
	
    private void insert(String word, int time) {
    	Trie node = root;
    	
    	for (char c : word.toCharArray()) {
    		if (node.children[c] == null) {
    			node.children[c] = new Trie();
    		}
    		
    		node = node.children[c];
    	}
    	
    	node.time += time;
    }
    
    private List<Tuple> lookup(String input) {
    	List<Tuple> result = new LinkedList<>();
    	
    	if (input == null || input.length() == 0) {
    		return result;
    	}
    	
    	Trie node = root;
    	
    	for (char c : input.toCharArray()) {
    		if (node.children[c] == null) {
    			return result;
    		} else {
    			node = node.children[c];
    		}
    	}
    	
    	traverse(input, node, result);
    	return result;
    }
    
    private void traverse(String input, Trie node, List<Tuple> result) {
    	if (node.time > 0) {
    		result.add(new Tuple(input, node.time));
    	}
    	
    	for (int i = 0; i < 256; i++) {
    		if (node.children[i] != null) {
    			traverse(input + (char) i, node.children[i], result);
    		}
    	}
    }

    
    
    
    
    public static void main(String[] args) {
    	String[] words = {"i love you", "island", "ironman", "i love leetcode"};
    	int[] times = {5, 3, 2, 2};
    	AutoCompleteSystem autoCompleteSystem = new AutoCompleteSystem(words, times);
    	char c = 'i';
    	List<String> result = autoCompleteSystem.getOutput(c);
    	result = autoCompleteSystem.getOutput('s');
    	result.stream().forEach(element -> System.out.println(element));
    }
}

class Trie {
	int time;
	Trie[] children;
	
	public Trie() {
		this.time = 0;
		children = new Trie[256];
	}
}

class Tuple {
	String sentence;
	int time;
	
	public Tuple(String sentence, int time) {
		this.sentence = sentence;
		this.time = time;
	}
}
