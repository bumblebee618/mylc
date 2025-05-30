/***
 * 
 * @author jackie
 * 
 * In an array A containing only 0s and 1s, a K-bit flip consists of choosing a (contiguous) subarray of length K and simultaneously changing every 0 in the subarray to 1, and every 1 in the subarray to 0.

Return the minimum number of K-bit flips required so that there is no 0 in the array.  If it is not possible, return -1.

 

Example 1:

Input: A = [0,1,0], K = 1
Output: 2
Explanation: Flip A[0], then flip A[2].
Example 2:

Input: A = [1,1,0], K = 2
Output: -1
Explanation: No matter how we flip subarrays of size 2, we can't make the array become [1,1,1].
Example 3:

Input: A = [0,0,0,1,0,1,1,0], K = 3
Output: 3
Explanation:
Flip A[0],A[1],A[2]: A becomes [1,1,1,1,0,1,1,0]
Flip A[4],A[5],A[6]: A becomes [1,1,1,1,1,0,0,0]
Flip A[5],A[6],A[7]: A becomes [1,1,1,1,1,1,1,1]
 

Note:

1 <= A.length <= 30000
1 <= K <= A.length
 */
public class Q995_Minimum_Number_of_K_Consecutive_Bit_Flips 
{
	// solution 1: Time is O(n)
	public int minKBitFlips(int[] A, int K) 
    {
        if (A == null || A.length == 0 || K <= 0 || K > A.length)
        {
            return 0;
        }
        
        int size = A.length;
        int[] hint = new int[size];
        int result = 0, flip = 0;

        // When we flip a subarray like A[i], A[i+1], ..., A[i+K-1]
        // we can instead flip our current writing state, and put a hint at
        // position i+K to flip back our writing state.
        for (int i = 0; i < size; ++i) 
        {
        	flip = flip ^ hint[i];
            
            // If we must flip the subarray starting here...
            if (A[i] == flip)   
            {  
                result++;  // We're flipping the subarray from A[i] to A[i+K-1]
                
                //If we can't flip the entire subarray, its impossible
                if (i + K > size)   
                {
                    return -1;
                }
                
                flip ^= 1;  // turn over
                
                if (i + K < size) 
                {
                    hint[i + K] ^= 1;
                }
            }
        }

        return result;
    }
    
	
	
	// solution 2: Time is O(n*k)
    public int minKBitFlips2(int[] A, int K) 
    {
        if (A == null || A.length == 0 || K <= 0 || K > A.length)
        {
            return 0;
        }
            
        int count = 0;
        int index = 0;
        
        while (index+K <= A.length)
        {
            while (index+K <= A.length && A[index] == 1)
            {
                index++;
            }
            
            if (index+K <= A.length)
            {
                count++;
                
                for (int i = 0; i < K; i++)
                {
                    A[index+i] = A[index+i] ^ 1;
                }
            }
        }
        
        while (index < A.length)
        {
            if (A[index++] == 0)
            {
                return -1;
            }
        }
        
        return count;
    }
    
    
    
    
    
    public static void main(String[] args)
    {
    	Q995_Minimum_Number_of_K_Consecutive_Bit_Flips test = new Q995_Minimum_Number_of_K_Consecutive_Bit_Flips();
    	int[] A = {0,1,0};
    	int K = 2;
    	System.out.println(test.minKBitFlips(A, K));
    	System.out.println(0^0);
    	System.out.println(1^0);
    }
}
