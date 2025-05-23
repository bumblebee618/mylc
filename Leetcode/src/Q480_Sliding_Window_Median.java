import java.util.*;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

/***
 * 
 * @author jackie
 *
 *         Median is the middle minValue in an ordered integer list. If the size of
 *         the list is even, there is no middle minValue. So the median is the mean
 *         of the two middle minValue.
 * 
 *         Examples: [2,3,4] , the median is 3
 * 
 *         [2,3], the median is (2 + 3) / 2 = 2.5
 * 
 *         Given an array nums, there is a sliding window of size k which is
 *         moving from the very left of the array to the very right. You can
 *         only see the k numbers in the window. Each time the sliding window
 *         moves right by one position. Your job is to output the median array
 *         for each window in the original array.
 * 
 *         For example, Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 * 
 *         Window position Median --------------- ----- [1 3 -1] -3 5 3 6 7 1 1
 *         [3 -1 -3] 5 3 6 7 -1 1 3 [-1 -3 5] 3 6 7 -1 1 3 -1 [-3 5 3] 6 7 3 1 3
 *         -1 -3 [5 3 6] 7 5 1 3 -1 -3 5 [3 6 7] 6 Therefore, return the median
 *         sliding window as [1,-1,-1,3,5,6].
 * 
 *         Note: You may assume k is always valid, ie: k is always smaller than
 *         input array's size for non-empty array.
 */

public class Q480_Sliding_Window_Median {
	// Solution 1:
	// 类似题295
	// test case: [1] [k = 1], [2147483647,2147483647] [k = 2],
	// [-2147483648,-2147483648,2147483647,-2147483648,-2147483648,-2147483648,2147483647,2147483647,2147483647,2147483647,-2147483648,2147483647,-2147483648] [k = 3]
	private TreeSet<Integer> smallNumers;
    private TreeSet<Integer> largeNumers;
    private int[] nums;

    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new double[0];
        }

        // 使用Integer.compare防止溢出，如Integer.MAX_VALUE, Integer.MIN_VALUE
        largeNumers = new TreeSet<>((a, b) -> nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : Integer.compare(a, b));
        smallNumers = new TreeSet<>((a, b) -> nums[a] != nums[b] ? Integer.compare(nums[b], nums[a]) : Integer.compare(b, a));
        this.nums = nums;
        double[] result = new double[nums.length - k + 1];
        int index = 0;

        for (int i = 0; i < nums.length; i++) {
            addNum(i);

            if (i >= k) {
                removeNum(i-k);
            }

            if (i >= k-1) {
                result[index++] = getMedian();
            }
        }

        return result;
    }

    private void addNum(int idx) {
        smallNumers.add(idx);
        largeNumers.add(smallNumers.pollFirst());
        balance();
    }

    private void removeNum(int idx) {
        if (largeNumers.contains(idx)) {
            largeNumers.remove(idx);
        } else {
            smallNumers.remove(idx);
        }

        balance();
    }

    private double getMedian() {
        if (smallNumers.size() == largeNumers.size()) {
            return ((double) nums[smallNumers.first()] + nums[largeNumers.first()]) / 2;
        }

        return nums[smallNumers.first()];
    }

    private void balance() {
        if (smallNumers.size() < largeNumers.size()) {
            smallNumers.add(largeNumers.pollFirst());
        } else if (largeNumers.size() + 1 < smallNumers.size()) {
            largeNumers.add(smallNumers.pollFirst());
        }
    }
	
    /***
    
    // Solution 2
	private Queue<Double> maxHeap = new PriorityQueue<>((a, b) -> b > a ? 1 : -1);
    private Queue<Double> minHeap = new PriorityQueue<>();
    private Double median;
    private int k;
    
    public double[] medianSlidingWindow2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new double[0];
        } else if (k > nums.length) {
            k = nums.length;
        }
        
        int size = nums.length;
        double[] result = new double[size-k+1];
        this.k = k;
        int index = 0;
        
        for (int i = 0; i < size; i++) {
            addNum((double) nums[i]);
            
            if (i >= k) {
                removeNum((double) nums[i-k]);
            }
            
            if (i >= k-1) {
                result[index++] = getMedian();
            }
        }
        
        return result;
    }
    
    private void addNum(double num) {
        if (median == null) {
            median = num;
            return;
        }
        
        if (num < median) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        
        balance();  // need balance
    }
    
    private void removeNum(double num) {
        if (num < median) {
            maxHeap.remove(num);
        } else if (num > median) {
            minHeap.remove(num);
        } else {
            median = maxHeap.size() > minHeap.size() ? maxHeap.poll() : minHeap.poll();
        }
        
        balance();  // need balance
    }
    
    private void balance() {
        if (maxHeap.size() + 1 < minHeap.size()) {
            maxHeap.offer(median);
            median = minHeap.poll();
        } else if (maxHeap.size() > minHeap.size()) {
            minHeap.offer(median);
            median = maxHeap.poll();
        }
    }
    
    private double getMedian() {
        if (k % 2 == 1) {
            return median;
        } else {
            double num = maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek();
            return (num + median) / 2.0;
        }  
    }
    
    ***/
    
    
	


	/********************************
	 * main function
	 ******************************************/

	public static void main(String[] args) {
		Q480_Sliding_Window_Median t = new Q480_Sliding_Window_Median();
		// int[] nums = {1,3,-1,-3,5,3,6,7};
		int[] nums = {Integer.MAX_VALUE, Integer.MAX_VALUE};
		// int[] nums = {-1,-1,1,-1,-1,-1,1,1,1,1,-1,1,-1}; Integer.MAX_VALUE
		/***
		 int[] nums = { Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE,
				Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE,
				Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE };
		***/		
				
		// System.out.println(nums.length);
		int k = 2;
		double[] ans = t.medianSlidingWindow(nums, k);

		for (double elem : ans) {
			System.out.print(elem + " ");
		}
	}
}
