import java.util.*;

/***
 * 
 * @author jackie
 *
 * Given two arrays A and B of equal size, the advantage of A with respect to B is the number of indices i for which A[i] > B[i].

Return any permutation of A that maximizes its advantage with respect to B.

 

Example 1:

Input: A = [2,7,11,15], B = [1,10,4,11]
Output: [2,11,7,15]
Example 2:

Input: A = [12,24,8,32], B = [13,25,32,11]
Output: [24,32,8,12]
 

Note:

1 <= A.length = B.length <= 10000
0 <= A[i] <= 10^9
0 <= B[i] <= 10^9

 */
public class Q870_Advantage_Shuffle 
{
	public int[] advantageCount(int[] A, int[] B) 
    {
        if (A == null || A.length == 0 || B == null || B.length != A.length)
        {
            return new int[0];
        }
        
        Queue<Integer> heapA = new PriorityQueue<>((a, b) -> b - a);
        Queue<Integer> heapB = new PriorityQueue<>((a, b) -> B[b] - B[a]);
        List<Integer> listB = new ArrayList<>();
        
        for (int num : A)
        {
            heapA.offer(num);
        }
        
        for (int i = 0; i < B.length; i++)
        {
            heapB.offer(i);
        }
        
        while (!heapB.isEmpty())
        {
            int num_a = heapA.peek();
            int index_b = heapB.poll();
            
            if (num_a > B[index_b])
            {
                A[index_b] = heapA.poll();
            }
            else
            {
                listB.add(index_b);
            }
        }
        
        for (int index_b : listB)
        {
            A[index_b] = heapA.poll();
        }
        
        return A;
    }
}
