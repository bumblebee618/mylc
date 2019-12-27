import java.util.PriorityQueue;

/***
 * 
 * @author jackie
 * 
 * Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Your KthLargest class will have a constructor which accepts an integer k and an integer array nums, which contains initial elements from the stream. For each call to the method KthLargest.add, return the element representing the kth largest element in the stream.

Example:

int k = 3;
int[] arr = [4,5,8,2];
KthLargest kthLargest = new KthLargest(3, arr);
kthLargest.add(3);   // returns 4
kthLargest.add(5);   // returns 5
kthLargest.add(10);  // returns 5
kthLargest.add(9);   // returns 8
kthLargest.add(4);   // returns 8
Note:
You may assume that nums' length ≥ k-1 and k ≥ 1.
 */
public class Q703_Kth_Largest_Element_in_a_Stream {
	private PriorityQueue<Integer> minHeap;
    private int capacity = 0;
    
    public Q703_Kth_Largest_Element_in_a_Stream(int k, int[] nums) {
        if (k <= 0 || nums == null)
        {
            return;
        }
        
        capacity = k;
        minHeap = new PriorityQueue<Integer>(k+1, (n1, n2) -> n1-n2);
        
        for (int num : nums)
        {
            addValToHeap(num);
        }
    }
    
    public int add(int val) {
        addValToHeap(val);
        return minHeap.size() == capacity ? minHeap.peek() : -1;
    }
    
    private void addValToHeap(int val)
    {
        minHeap.offer(val);
        
        if (minHeap.size() > capacity)
        {
            minHeap.poll();
        }
    }
}
