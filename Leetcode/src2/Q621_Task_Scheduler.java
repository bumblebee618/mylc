import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/***
 * 
 * @author jackie
 * Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks. Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

 

Example:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 

Note:

The number of tasks is in the range [1, 10000].
The integer n is in the range [0, 100].
 *
 */
public class Q621_Task_Scheduler {
	// solution 1:
	public int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0 || n < 0) {
            return 0;
        }

        int[] frequency = new int[256];
        
        for (char task : tasks) {
            frequency[task]++;
        }

        Queue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        for (int i = 0; i < frequency.length; i++) {
            if (frequency[i] > 0) {
            	maxHeap.offer(frequency[i]);
            }
        }

        int totalTime = 0, localTime = 0;;
        List<Integer> list = new LinkedList<>();

        while (!maxHeap.isEmpty()) {
            localTime = 0;
            list.clear();

            while (localTime <= n) {
                if (!maxHeap.isEmpty()) {
                    int freq = maxHeap.poll();

                    if (freq > 1) {
                        list.add(freq - 1);
                    }
                }

                localTime++;
                totalTime++;
                
                if (list.size() == 0 && maxHeap.isEmpty()) {
                    break;
                }
            }

            list.stream().forEach(elem -> maxHeap.offer(elem));
        }

        return totalTime;
    }
	
	
	
	
	// solution 2:
	public int leastInterval2(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0 || n < 0)
        {
            return 0;
        }
        
        int[] map = new int[26];
        
        for (char task : tasks)
        {
            map[task-'A']++;
        }
        
        // return max first
        Queue<Integer> maxHeap = new PriorityQueue<>(26, (a, b) -> b - a);
        
        for (int frequency : map)
        {
            if (frequency > 0)
            {
                maxHeap.offer(frequency);
            }
        }
        
        int totalTime = 0;
        
        while (!maxHeap.isEmpty())
        {
            int time = 0;
            List<Integer> list = new LinkedList<>(); // 这里必须用list
            
            // one period
            while (time <= n)
            {
                if (!maxHeap.isEmpty())
                {
                    int fq = maxHeap.poll();
                    
                    if (fq > 1)
                    {
                        list.add(fq-1);
                    }
                }
                
                totalTime++;
                time++;
                
                if (maxHeap.isEmpty() && list.size() == 0)
                {
                    break;
                }
            }
            
            // 有可能此时的maxHeap里还有元素
            for (int freq : list)
            {
                maxHeap.offer(freq);
            }
        }
        
        return totalTime;
    }
}
