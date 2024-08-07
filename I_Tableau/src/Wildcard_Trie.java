/*******
 * 
 * Give a dictionary with list of words. Find all words meets conditions of the query input.
 * Query input string may contains "?" and "*", "?" can match any character (non-empty) 
 * while "*" can match any character including empty char.
 * 
 */

import java.util.LinkedList;
import java.util.List;

public class Wildcard_Trie 
{
	private Trie root;
	
	public Wildcard_Trie(String[] words)
	{
		root = new Trie();
		
		if (words == null || words.length == 0)
		{
			return;
		}
		
		for (String word : words)
		{
			insertWord(word);
		}
	}
	
	public List<String> searchWords(String input)
	{
		List<String> result = new LinkedList<>();
		
		if (input == null || input.length() == 0)
		{
			return result;
		}
		
		searchWords(root, input, 0, "", result);
		return result;
	}
	
	private void searchWords(Trie node, String input, int index, String solution, List<String> result)
	{
		if (index == input.length())
		{
			if (node.isWord)
			{
				result.add(solution);
			}
			
			return;
		}
		
		char c = input.charAt(index);
		
		if (c == '?')
		{
			for (int i = 0; i < node.children.length; i++)
			{
				if (node.children[i] != null)
				{
					searchWords(node.children[i], input, index+1, solution + (char) i, result);
				}
			}
		}
		else if (c == '*')
		{
			for (int i = 0; i < node.children.length; i++)
			{
				if (node.children[i] != null)
				{
					// "* indicates empty char"
					searchWords(node.children[i], input, index+1, solution, result);
					
					// "* indicates non-empty char"
					searchWords(node.children[i], input, index+1, solution + (char) i, result);
				}
			}
		}
		else 
		{
			if (node.children[c] == null)
			{
				return;
			}
			
			searchWords(node.children[c], input, index+1, solution + c, result);
		}
	}
	
	private void insertWord(String word)
	{
		if (word == null || word.length() == 0)
		{
			return;
		}
		
		Trie node = root;
		
		for (char c : word.toCharArray())
		{
			if (node.children[c] == null)
			{
				node.children[c] = new Trie();
			}
			
			node = node.children[c];
		}
		
		node.isWord = true;
	}
	
	class Trie
	{
		Trie[] children;
		boolean isWord;
		
		public Trie()
		{
			children = new Trie[256];
			isWord = false;
		}
	}
	
	
	
	/***************************************************** main ****************************************************************/
	public static void main(String[] args)
	{
		String[] words = new String[] {"cat", "cap", "cup", "ct"};
		Wildcard_Trie test = new Wildcard_Trie(words);
		String input1 = "cat";
		String input2 = "c?p";
		String input3 = "c*t";
		
		System.out.print("input1:");
		for (String word : test.searchWords(input1))
		{
			System.out.print(word + ", ");
		}
		System.out.println();
		
		System.out.print("input2:");
		for (String word : test.searchWords(input2))
		{
			System.out.print(word + ", ");
		}
		System.out.println();
		
		System.out.print("input3:");
		for (String word : test.searchWords(input3))
		{
			System.out.print(word + ", ");
		}
		System.out.println();
	}
}

