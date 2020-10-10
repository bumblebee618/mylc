import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
/*******
 * 
You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

Define a pair (u,v) which consists of one element from the first array and one element from the second array.

Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

Example 1:
Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3

Return: [1,2],[1,4],[1,6]

The first 3 pairs are returned from the sequence:
[1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]

Example 2:
Given nums1 = [1,1,2], nums2 = [1,2,3],  k = 2

Return: [1,1],[1,1]

The first 2 pairs are returned from the sequence:
[1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]

Example 3:
Given nums1 = [1,2], nums2 = [3],  k = 3 

Return: [1,3],[2,3]

All possible pairs are returned from the sequence:
[1,3],[2,3]

 * 
 * */


public class Q373_Find_K_Pairs_with_Smallest_Sums {
	/*******************************************************************************************************************
	 * 1. Build a minHeap of elements from the first row.
	 * 2. Do the following operations k-1 times :
	 * 		Every time when you poll out the root(Top Element in Heap), 
	 * 		you need to know the row number and column number of that element(so we can create a tuple class here), 
	 * 		replace that root with the next element from the same column.
	 * 
	 * 类似算分公式，分布式的做法，哪个column被取出，就以此column就补上下一个元素
	 * 
	 ********************************************************************************************************************/
	
	// time complexity O( (n+k) * logn)
	public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) 
	{
        List<List<Integer>> result = new LinkedList<>();
        
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0 || k <= 0)
        {
            return result;
        }
        
        int size1 = nums1.length, size2 = nums2.length;

        Queue<int[]> heap = new PriorityQueue<>(size1, new Comparator<int[]>()
        {
            @Override
            public int compare(int[] pair1, int[] pair2)
            {
                return (nums1[pair1[0]] + nums2[pair1[1]]) - (nums1[pair2[0]] + nums2[pair2[1]]);
            }
        });
        
        for (int i = 0; i < size1; i++)
        {
            heap.offer(new int[] {i, 0});
        }
        
        for (int i = 0; i < k && i < size1 * size2; i++)
        {
            int[] node = heap.poll();
            List<Integer> list = new LinkedList<>();
            list.add(nums1[node[0]]);
            list.add(nums2[node[1]]);
            result.add(list);
            
            if (node[1]+1 < size2)
            {
                heap.offer(new int[] {node[0], node[1]+1});
            }
        }
        
        return result;
    }
        
	
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**********************************************************************************/
    // by Jackie, time complexity O(n * n * logn)
	public List<int[]> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        List<int[]> ans = new ArrayList<int[]>();
        if(nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0 || k <= 0){
            return ans;
        }
        
        Queue<Node2> heap = new PriorityQueue<Node2>(1, new Comparator<Node2>(){
            public int compare(Node2 left, Node2 right){
                return (left.x + left.y) - (right.x + right.y);
            }
        });
        
        int curMaxValue = Integer.MIN_VALUE;
        
        for(int i = 0; i < nums1.length; i++){
            if(heap.size() >= k && curMaxValue <= nums1[i] + nums2[0]){
                break;
            }
            
            for(int j = 0; j < nums2.length; j++){
            	curMaxValue = Math.max(curMaxValue, nums1[i] + nums2[j]);
                heap.offer(new Node2(nums1[i], nums2[j]));
            }
        }
        
        for(int i = 0; i < k && !heap.isEmpty(); i++){
            Node2 node = heap.poll();
            ans.add(new int[]{node.x, node.y});
        }
        
        return ans;
    }
    
    class Node2{
        int x;
        int y; 
        
        public Node2(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    
    public static void main(String[] arge){
    	Q373_Find_K_Pairs_with_Smallest_Sums t = new Q373_Find_K_Pairs_with_Smallest_Sums();
    	int[] nums1 = {1,1,2};
    	int[] nums2 = {1,2,3};
    	
    	t.kSmallestPairs(nums1, nums2, 2);
    }
    
}
