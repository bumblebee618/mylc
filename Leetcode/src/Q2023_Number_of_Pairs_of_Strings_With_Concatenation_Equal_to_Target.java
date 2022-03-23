/***
 * 
 * @author jackie
 *
 *	Given an array of digit strings nums and a digit string target, return the number of pairs of indices (i, j) (where i != j) such that the concatenation of nums[i] + nums[j] equals target.

 

Example 1:

Input: nums = ["777","7","77","77"], target = "7777"
Output: 4
Explanation: Valid pairs are:
- (0, 1): "777" + "7"
- (1, 0): "7" + "777"
- (2, 3): "77" + "77"
- (3, 2): "77" + "77"
Example 2:

Input: nums = ["123","4","12","34"], target = "1234"
Output: 2
Explanation: Valid pairs are:
- (0, 1): "123" + "4"
- (2, 3): "12" + "34"
Example 3:

Input: nums = ["1","1","1"], target = "11"
Output: 6
Explanation: Valid pairs are:
- (0, 1): "1" + "1"
- (1, 0): "1" + "1"
- (0, 2): "1" + "1"
- (2, 0): "1" + "1"
- (1, 2): "1" + "1"
- (2, 1): "1" + "1"
 

Constraints:

2 <= nums.length <= 100
1 <= nums[i].length <= 100
2 <= target.length <= 100
nums[i] and target consist of digits.
nums[i] and target do not have leading zeros.
 */


public class Q2023_Number_of_Pairs_of_Strings_With_Concatenation_Equal_to_Target {
	// solution 1:
	public int numOfPairs(String[] nums, String target) {
		Trie root = new Trie(), firstNode = root;
		
        for (String num : nums) {
        	Trie node = root;
        	
        	for (char c : num.toCharArray()) {
        		if (node.children[c - '0'] == null) {
        			node.children[c - '0'] = new Trie();
        		}
        		
        		node = node.children[c - '0'];
        	}
        	
        	node.count++;
        }
		
		int result = 0;
		
		for (int i = 0; i < target.length(); i++) {
			int digit = target.charAt(i) - '0';
			
			if (firstNode.children[digit] == null) {
				break;
			}
			
			firstNode = firstNode.children[digit];
			
			if (firstNode.count > 0) {
				int firstCount = firstNode.count--;
				Trie secondNode = root;
				int secondPartCount = search(secondNode, target, i+1);
				result += firstCount * secondPartCount;
				firstNode.count++;
			}
		}
		
		return result;
    }	
	
	private int search(Trie node, String target, int index) {
		if (index == target.length()) {
			return node.count;
		} 
		
		int digit = target.charAt(index) - '0';
		return (node.children[digit] == null) ? 0 : search(node.children[digit], target, index+1);
	}
    
	
	class Trie {
		public Trie[] children;
		public int count;
		
		public Trie() {
			children = new Trie[10];
			count = 0;
		}
	}
	
	
	
	
	// solution 2:
	public int numOfPairs2(String[] nums, String target) {
        int result = 0;
        
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (target.equals(nums[i] + nums[j])) {
                    result++;
                }
                
                if (target.equals(nums[j] + nums[i])) {
                    result++;
                }
            }
        }
        
        return result;
    }
}	
