/***
 * 
 * @author jackie
 * 
 * Implement the MapSum class:

MapSum() Initializes the MapSum object.
void insert(String key, int val) Inserts the key-val pair into the map. If the key already existed, the original key-value pair will be overridden to the new one.
int sum(string prefix) Returns the sum of all the pairs' value whose key starts with the prefix.
 

Example 1:

Input
["MapSum", "insert", "sum", "insert", "sum"]
[[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
Output
[null, null, 3, null, 5]

Explanation
MapSum mapSum = new MapSum();
mapSum.insert("apple", 3);  
mapSum.sum("ap");           // return 3 (apple = 3)
mapSum.insert("app", 2);    
mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
 

Constraints:

1 <= key.length, prefix.length <= 50
key and prefix consist of only lowercase English letters.
1 <= val <= 1000
At most 50 calls will be made to insert and sum.
 */
public class Q677_Map_Sum_Pairs {
	private Trie root;

    /** Initialize your data structure here. */
    public Q677_Map_Sum_Pairs() {
        root = new Trie();
    }
    
    public void insert(String key, int val) {
        Trie node = root;
        
        for (char c : key.toCharArray()) {
            if (node.children[c-'a'] == null) {
                node.children[c-'a'] = new Trie();
            }
            
            node = node.children[c-'a'];
        }
        
        node.value = val;
    }
    
    public int sum(String prefix) {
        Trie node = root;
        
        for (char c : prefix.toCharArray()) {
            if (node.children[c-'a'] == null) {
                return 0;
            }
            
            node = node.children[c-'a'];
        }
        
        return getSum(node);
    }
    
    private int getSum(Trie node) {
        if (node == null)
            return 0;
        
        int sum = node.value;
        
        for (Trie next : node.children) {
            sum += getSum(next);
        }
        
        return sum;
    }
    
    class Trie {
        public Trie[] children;
        public int value;
        
        public Trie () {
            children = new Trie[26];
            value = 0;
        }
    }
}
