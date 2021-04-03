/***
 * 
 * @author jackie
 * 
 * A sentence is a list of words that are separated by a single space with no leading or trailing spaces. For example, "Hello World", "HELLO", "hello world hello world" are all sentences. Words consist of only uppercase and lowercase English letters.

Two sentences sentence1 and sentence2 are similar if it is possible to insert an arbitrary sentence (possibly empty) inside one of these sentences such that the two sentences become equal. For example, sentence1 = "Hello my name is Jane" and sentence2 = "Hello Jane" can be made equal by inserting "my name is" between "Hello" and "Jane" in sentence2.

Given two sentences sentence1 and sentence2, return true if sentence1 and sentence2 are similar. Otherwise, return false.

 

Example 1:

Input: sentence1 = "My name is Haley", sentence2 = "My Haley"
Output: true
Explanation: sentence2 can be turned to sentence1 by inserting "name is" between "My" and "Haley".
Example 2:

Input: sentence1 = "of", sentence2 = "A lot of words"
Output: false
Explanation: No single sentence can be inserted inside one of the sentences to make it equal to the other.
Example 3:

Input: sentence1 = "Eating right now", sentence2 = "Eating"
Output: true
Explanation: sentence2 can be turned to sentence1 by inserting "right now" at the end of the sentence.
Example 4:

Input: sentence1 = "Luky", sentence2 = "Lucccky"
Output: false
 

Constraints:

1 <= sentence1.length, sentence2.length <= 100
sentence1 and sentence2 consist of lowercase and uppercase English letters and spaces.
The words in sentence1 and sentence2 are separated by a single space.
 */
public class Q1813_Sentence_Similarity_III 
{
	public boolean areSentencesSimilar(String sentence1, String sentence2) 
    {
        if (sentence1 == null || sentence2 == null)
        {
            return false;
        }
        else if (sentence1.equals(sentence2))
        {
            return true;
        }
        
        String[] words1 = sentence1.toLowerCase().split(" ");
		String[] words2 = sentence2.toLowerCase().split(" ");
        
        return words1.length >= words2.length 
        		? isSimilar(words1, words2)
        		: isSimilar(words2, words1);
    }
	
	private boolean isSimilar(String[] word1, String[] word2)
	{
		if (word1.length == word2.length)
		{
			return false;
		}
		
		int index1 = 0, index2 = 0;
		int size1 = word1.length, size2 = word2.length;
		int count = 0;
		
		while (index1 != size1 && index2 != size2)
		{
			if (word1[index1].equals(word2[index2]))
			{
				index1++;
				index2++;
				continue;
			}
			
			if (++count > 1)
			{
				return false;
			}
			
			while (index1 < size1 && !word1[index1].equals(word2[index2]))
			{
				index1++;
			}
		}
		
		count += index1 < size1 ? 1 : 0;
		return index2 == size2 && count == 1;
	}
}
