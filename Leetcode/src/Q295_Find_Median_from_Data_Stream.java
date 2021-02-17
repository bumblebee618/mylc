import java.util.Queue;
import java.util.PriorityQueue;
/*****
 * 
Median is the middle minValue in an ordered integer list. If the size of the list is even, there is no middle minValue. So the median is the mean of the two middle minValue.

Examples: 
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
For example:

add(1)
add(2)
findMedian() -> 1.5
add(3) 
findMedian() -> 2

 * 
 * */


public class Q295_Find_Median_from_Data_Stream {
	/*******************************************************
	 * 应用heap来处理，解题思路：
	 *	(1). 跟median对比，先加入对应的minHeap或manHeap
	 *	(2). 进行调节
	 *	(3). 调节的条件： minHeap.size() < maxHeap.size() 或
	 *				   maxHeap.size() + 1 < minHeap.size()
	 * 
	 *******************************************************/
	// 类似题480
	private Queue<Integer> maxHeap;
    private Queue<Integer> minHeap;
    private int size = 0;
    private Integer median = null;
    
    /** initialize your data structure here. */
    public Q295_Find_Median_from_Data_Stream() 
    {
        maxHeap = new PriorityQueue<>();
        minHeap = new PriorityQueue<>();
    }
    
    public void addNum(int num) 
    {
        size++;
        
        if (median == null)
        {
            median = num;
            return;
        }
        
        if (num < median)
        {
            maxHeap.offer(-num);
        }
        else
        {
            minHeap.offer(num);
        }
        
        balance();
    }
    
    public double findMedian() 
    {
        if (size % 2 != 0)
        {
            return (double) median;
        }
        else
        {
            int num = (maxHeap.size() > minHeap.size()) ? -maxHeap.peek() : minHeap.peek();
            return (median + num) / 2.0;
        }
    }
    
    private void balance()
    {
        if (maxHeap.size() + 1 < minHeap.size())
        {
            maxHeap.offer(-median);
            median = minHeap.poll();
        }
        else if (maxHeap.size() > minHeap.size())
        {
            minHeap.offer(median);
            median = -maxHeap.poll();
        }
    }
    
    
    
    
    
    
    
    
    
    /*********************** main function *******************************/
    
    public static void main(String[] args){
    	Q295_Find_Median_from_Data_Stream t = new Q295_Find_Median_from_Data_Stream();
    	t.addNum(0);
    	t.addNum(0);
    	System.out.println(t.findMedian());
    	double a = Double.MAX_VALUE;
    }
}
