import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/***
 * 
 * @author jackie
 * Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex which randomly picks an index in proportion to its weight.

Note:

1 <= w.length <= 10000
1 <= w[i] <= 10^5
pickIndex will be called at most 10000 times.
Example 1:

Input: 
["Solution","pickIndex"]
[[[1]],[]]
Output: [null,0]
Example 2:

Input: 
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output: [null,0,1,1,1,0]
Explanation of Input Syntax:

The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, the array w. pickIndex has no arguments. Arguments are always wrapped with a list, even if there aren't any.
 *
 */
public class Q528_Random_Pick_with_Weight 
{
	private int[] candidates;
    private Random rand = new Random();
    private int tot = 0;

    public Q528_Random_Pick_with_Weight(int[] w) 
    {
        if (w == null || w.length == 0)
        {
            return;
        }
        
        candidates = new int[w.length];
        
        for (int i = 0; i < w.length; i++)
        {
        	tot += w[i];
        	candidates[i] = tot;
        }
    }
    
    public int pickIndex() 
    {
        int target = rand.nextInt(tot);
        int left = 0;
        int right = candidates.length-1;
        
        while (left+1 < right)
        {
            int mid = left+(right-left)/2;
            
            if (candidates[mid] > target)
            {
                right = mid;
            }
            else
            {
                left = mid;
            }
        }
        
        return candidates[left] > target ? left : right;
    }
}
