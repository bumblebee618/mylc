/***
 * 
 * @author jackie
 * 
 * Given a (0-indexed) integer array nums and two integers low and high, return the number of nice pairs.

A nice pair is a pair (i, j) where 0 <= i < j < nums.length and low <= (nums[i] XOR nums[j]) <= high.

 

Example 1:

Input: nums = [1,4,2,7], low = 2, high = 6
Output: 6
Explanation: All nice pairs (i, j) are as follows:
    - (0, 1): nums[0] XOR nums[1] = 5 
    - (0, 2): nums[0] XOR nums[2] = 3
    - (0, 3): nums[0] XOR nums[3] = 6
    - (1, 2): nums[1] XOR nums[2] = 6
    - (1, 3): nums[1] XOR nums[3] = 3
    - (2, 3): nums[2] XOR nums[3] = 5
Example 2:

Input: nums = [9,8,4,2,1], low = 5, high = 14
Output: 8
Explanation: All nice pairs (i, j) are as follows:
​​​​​    - (0, 2): nums[0] XOR nums[2] = 13
    - (0, 3): nums[0] XOR nums[3] = 11
    - (0, 4): nums[0] XOR nums[4] = 8
    - (1, 2): nums[1] XOR nums[2] = 12
    - (1, 3): nums[1] XOR nums[3] = 10
    - (1, 4): nums[1] XOR nums[4] = 9
    - (2, 3): nums[2] XOR nums[3] = 6
    - (2, 4): nums[2] XOR nums[4] = 5
 

Constraints:

1 <= nums.length <= 2 * 104
1 <= nums[i] <= 2 * 104
1 <= low <= high <= 2 * 104

 */

public class Q1803_Count_Pairs_With_XOR_in_a_Range 
{
	private Trie root;
    
    public int countPairs(int[] nums, int low, int high) 
    {
        if (nums == null || nums.length == 0)
        {
            return 0;
        }
        
        root = new Trie();
        int result = 0;
        
        for (int num : nums)
        {
            result += findSmallerCount(num, high+1) - findSmallerCount(num, low);
            insert(num);
        }
        
        return result;
    }
    
    
    private int findSmallerCount(int num, int limit)
    {
        Trie node = root;
        int count = 0;
        
        // from 31th bit to 0th bit
        for (int i = 31; i >= 0 && node != null; i--)
        {
            int bit_num = ((num >> i) & 1);
            int bit_limit = ((limit >> i) & 1);
            
            // find the number of the candidate who have the same bit with num at ith bit， 
            // so (num ^ candidate) = 0 at ith bit to meet the condition "< limit"
            if (bit_limit == 1)
            {
                if (node.children[bit_num] != null)
                {
                    count += node.children[bit_num].cnt;
                }
                
                // we already add all count from current branch, 
                // then go to find the other branch, just like binary search
                node = node.children[1 - bit_num];
            }
            // find the number of the candidate who have the same bit with num at ith bit， 
            // so (num ^ candidate) = 0 at ith bit to meet the condition "< limit"
            else
            {
                node = node.children[bit_num];
            }
        }
        
        return count;
    }
    
    private void insert(int num)
    {
        Trie node = root;
        
        for (int i = 31; i >= 0; i--)
        {
            int bit = ((num >> i) & 1);
            
            if (node.children[bit] == null)
            {
                node.children[bit] = new Trie();
            }
            
            node.children[bit].cnt++;
            node = node.children[bit];
        }
    }
    
    class Trie
    {
        public Trie[] children;
        public int cnt;
        
        public Trie()
        {
            children = new Trie[2];
            cnt = 0;
        }
    }
}
