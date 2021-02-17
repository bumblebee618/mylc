import java.util.*;
/*******
 * 
There are N children standing in a line. Each child is assigned a rating value.
You are giving candies to these children subjected to the following requirements:
	1. Each child must have at least one candy.
	2. Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?

 * 
 * */

public class Q135_Candy {
	// solution 1: using priority queue, time is(nlogn), space is O(n)
	public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0)
        {
            return 0;
        }
        
        int size = ratings.length;
        int[] candies = new int[size];
        int candyNum = 0;
        Queue<Integer> heap = new PriorityQueue<>(size, (index1, index2) -> (ratings[index1]-ratings[index2]));
        
        for (int index = 0; index < size; index++)
        {
            heap.offer(index);
        }
        
        while (!heap.isEmpty())
        {
            int curIndex = heap.poll();
            
            if (curIndex-1 >= 0 && ratings[curIndex-1] > ratings[curIndex] && candies[curIndex-1] <= candies[curIndex])
            {
                candies[curIndex-1] = candies[curIndex]+1;
            }
            
            if (curIndex+1 < size && ratings[curIndex+1] > ratings[curIndex] && candies[curIndex+1] <= candies[curIndex])
            {
                candies[curIndex+1] = candies[curIndex]+1;
            }
        }
        
        for (int candy : candies)
        {
            candyNum += candy;
        }
        
        return candyNum+size;
    }

	
	

	// solution 2: time is O(n), space is O(n)
    public int candy2(int[] ratings) {
		if(ratings == null || ratings.length == 0) {
            return 0;
        }
        
        int len = ratings.length;
        int[] candies = new int[len];
        int count = len;   // give each child one candy
        
        // Scan from left to right, to make sure right higher rated child gets 1 more candy than left lower rated child
        for(int i = 1; i < len; i++) {
            if(ratings[i] > ratings[i-1]) {
                count += candies[i-1]+1 - candies[i];
                candies[i] = candies[i-1]+1;
            }
        }
        
     // Scan from right to left, to make sure left higher rated child gets 1 more candy than right lower rated child
        for(int i = len-2; i >= 0; i--) {
            if(ratings[i] > ratings[i+1]) {
                count += Math.max(candies[i], candies[i+1]+1) - candies[i];
                candies[i] = Math.max(candies[i], candies[i+1]+1);
            }
        }
        
        return count;
    } 
}
