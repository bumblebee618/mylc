import java.util.LinkedList;
import java.util.Queue;

/***
 * 
 *Design the CombinationIterator class:

CombinationIterator(string characters, int combinationLength) Initializes the object with a string characters of sorted distinct lowercase English letters and a number combinationLength as arguments.
next() Returns the next combination of length combinationLength in lexicographical order.
hasNext() Returns true if and only if there exists a next combination.
 

Example 1:

Input
["CombinationIterator", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
[["abc", 2], [], [], [], [], [], []]
Output
[null, "ab", true, "ac", true, "bc", false]

Explanation
CombinationIterator itr = new CombinationIterator("abc", 2);
itr.next();    // return "ab"
itr.hasNext(); // return True
itr.next();    // return "ac"
itr.hasNext(); // return True
itr.next();    // return "bc"
itr.hasNext(); // return False
 

Constraints:

1 <= combinationLength <= characters.length <= 15
All the characters of characters are unique.
At most 104 calls will be made to next and hasNext.
It is guaranteed that all calls of the function next are valid.
 *
 */

public class Q1286_Iterator_for_Combination {
	private Queue<String> queue = new LinkedList<>();
    private String characters;
    private int len = 0;

    public Q1286_Iterator_for_Combination(String characters, int combinationLength) {
        if (characters == null || characters.length() == 0 || characters.length() < combinationLength) {
            return;
        }
        
        this.characters = characters;
        len = combinationLength;
        backtrack(0, "");
    }
    
    private void backtrack(int start, String solution) {
        if (solution.length() == len) {
            queue.offer(solution);
            return;
        }
        
        for (int i = start; i < characters.length(); i++) {
            backtrack(i+1, solution + characters.charAt(i));
        }
    }
    
    public String next() {
        return queue.poll();
    }
    
    public boolean hasNext() {
        return !queue.isEmpty();
    }
}
