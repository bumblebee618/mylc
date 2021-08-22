import java.util.*;

public class UserSearchingSystem {
	private Trie root = new Trie();
	private String[] buttons = {" ", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
	
	public UserSearchingSystem(List<String> users) {
		for (String user : users) {
			Trie node = root;
			
			for (char c : user.toCharArray()) {
				if (node.children[c] == null) {
					node.children[c] = new Trie();
				}
				
				node = node.children[c];
				node.names.add(user);
			}
		}
	}
	
	public Set<String> findUsers(String input) {
		String digits = input.trim().replace(" ", "");
		Set<String> result = new HashSet<>();
		search(digits, 0, root, result);
		return result;
	}
	
	private void search(String digits, int index, Trie node, Set<String> result) {
		if (index == digits.length()-1) {
			for (Trie child : node.children) {
				if (child != null) {
					result.addAll(child.names);
				}
			}
			return;
		} 
		
		int pos = digits.charAt(index) - '0';
		
		for (char c : buttons[pos].toCharArray()) {
			if (node.children[c] != null) {
				search(digits, index+1, node.children[c], result);
			}
		}
	}
	
	class Trie {
		public Trie[] children;
		public Set<String> names;
		
		public Trie () {
			this.children = new Trie[256];
			names = new HashSet<>();
		}
	}
	
	
	public static void main(String[] args) {
		List<String> users = new LinkedList<>();
		users.add("abc");
		users.add("beh");
		users.add("cfil");
		
		UserSearchingSystem test = new UserSearchingSystem(users);
		
		String input = "23 4 ";
		Set<String> result = test.findUsers(input);
		
		for (String name : result) {
			System.out.println(name);
		}
	}
}


