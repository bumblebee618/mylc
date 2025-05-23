import java.util.Arrays;

/***
 * 
 * @author jackie
 * 
 * You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the array sweetness.
You want to share the chocolate with your K friends so you start cutting the chocolate bar into K+1 pieces using K cuts, each piece consists of some consecutive chunks.
Being generous, you will eat the piece with the minimum total sweetness and give the other pieces to your friends.
Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.
 
Example 1:
Input: sweetness = [1,2,3,4,5,6,7,8,9], K = 5
Output: 6
Explanation: You can divide the chocolate to [1,2,3], [4,5], [6], [7], [8], [9]
Example 2:
Input: sweetness = [5,6,7,8,9,1,2,3,4], K = 8
Output: 1
Explanation: There is only one way to cut the bar into 9 pieces.
Example 3:
Input: sweetness = [1,2,2,1,2,2,1,2,2], K = 2
Output: 5
Explanation: You can divide the chocolate to [1,2,2], [1,2,2], [1,2,2]
 
Constraints:
	• 0 <= K < sweetness.length <= 10^4
	• 1 <= sweetness[i] <= 10^5

 */

public class Q1231_Divide_Chocolate 
{
	public int maximizeSweetness(int[] sweetness, int K) 
    {
        if (sweetness == null || sweetness.length == 0 || K < 0)
        {
            return 0;
        }
        
        int left = 1, right = Arrays.stream(sweetness).sum() / (K+1);
        
        while (left < right) 
        {
            int mid = (left + right + 1) / 2;
            int cuts = findCount(sweetness, mid, K);
            
            if (cuts > K)
            {
                left = mid;
            }
            else
            {
                right = mid - 1;
            }
        }
        
        return left;
    }
    
    private int findCount(int[] sweetness, int cutSize, int maxCount)
    {
        int sum = 0, cuts = 0;
            
        for (int sweet : sweetness) 
        {
            if ((sum += sweet) >= cutSize) 
            {
                sum = 0;
                
                if (++cuts > maxCount)
                {
                    break;
                }
            }
        }
        
        return cuts;
    }

    
    
    
    
    

    
    /******************************* main *******************************/
    
    public static void main(String[] args)
    {
    	Q1231_Divide_Chocolate test = new Q1231_Divide_Chocolate();
    	int[] sweetness = {22256, 47646, 19294, 31272, 75367};
    	int k = 4;
    	System.out.println(test.maximizeSweetness(sweetness, k));
    }
}
