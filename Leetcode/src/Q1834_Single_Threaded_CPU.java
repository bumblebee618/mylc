import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/***
 * 
 * @author jackie
 * 
 * You are given n​​​​​​ tasks labeled from 0 to n - 1 represented by a 2D integer array tasks, where tasks[i] = [enqueueTimei, processingTimei] means that the i​​​​​​th​​​​ task will be available to process at enqueueTimei and will take processingTimei to finish processing.

You have a single-threaded CPU that can process at most one task at a time and will act in the following way:

If the CPU is idle and there are no available tasks to process, the CPU remains idle.
If the CPU is idle and there are available tasks, the CPU will choose the one with the shortest processing time. If multiple tasks have the same shortest processing time, it will choose the task with the smallest index.
Once a task is started, the CPU will process the entire task without stopping.
The CPU can finish a task then start a new one instantly.
Return the order in which the CPU will process the tasks.

 

Example 1:

Input: tasks = [[1,2],[2,4],[3,2],[4,1]]
Output: [0,2,3,1]
Explanation: The events go as follows: 
- At time = 1, task 0 is available to process. Available tasks = {0}.
- Also at time = 1, the idle CPU starts processing task 0. Available tasks = {}.
- At time = 2, task 1 is available to process. Available tasks = {1}.
- At time = 3, task 2 is available to process. Available tasks = {1, 2}.
- Also at time = 3, the CPU finishes task 0 and starts processing task 2 as it is the shortest. Available tasks = {1}.
- At time = 4, task 3 is available to process. Available tasks = {1, 3}.
- At time = 5, the CPU finishes task 2 and starts processing task 3 as it is the shortest. Available tasks = {1}.
- At time = 6, the CPU finishes task 3 and starts processing task 1. Available tasks = {}.
- At time = 10, the CPU finishes task 1 and becomes idle.
Example 2:

Input: tasks = [[7,10],[7,12],[7,5],[7,4],[7,2]]
Output: [4,3,2,0,1]
Explanation: The events go as follows:
- At time = 7, all the tasks become available. Available tasks = {0,1,2,3,4}.
- Also at time = 7, the idle CPU starts processing task 4. Available tasks = {0,1,2,3}.
- At time = 9, the CPU finishes task 4 and starts processing task 3. Available tasks = {0,1,2}.
- At time = 13, the CPU finishes task 3 and starts processing task 2. Available tasks = {0,1}.
- At time = 18, the CPU finishes task 2 and starts processing task 0. Available tasks = {1}.
- At time = 28, the CPU finishes task 0 and starts processing task 1. Available tasks = {}.
- At time = 40, the CPU finishes task 1 and becomes idle.
 

Constraints:

tasks.length == n
1 <= n <= 105
1 <= enqueueTimei, processingTimei <= 109
 */

public class Q1834_Single_Threaded_CPU 
{
	public int[] getOrder(int[][] tasks) 
	{
		if (tasks == null || tasks.length == 0 || tasks[0].length != 2)
		{
			return new int[0];
		}
		
		int index = 0, size = tasks.length;
		int[] result = new int[size];
		Node[] array = new Node[size];
		
		for (int i = 0; i < tasks.length; i++)
		{
			array[i] = new Node(i, tasks[i][0], tasks[i][1]);
		}
		
		Arrays.sort(array, (a, b) 
				-> a.enqueueTime != b.enqueueTime 
				? a.enqueueTime - b.enqueueTime
				: a.processTime - b.processTime);
		
		Queue<Node> heap = new PriorityQueue<Node>((a, b) 
				-> a.processTime != b.processTime 
				? a.processTime - b.processTime 
				: a.index - b.index);
		
		heap.offer(array[0]);
		int curTime = 0;
		int nextIndex = 1;
		
		while (!heap.isEmpty())
		{
			if (!heap.isEmpty() && curTime < heap.peek().enqueueTime)
			{
				curTime = heap.peek().enqueueTime;
			}
			
			Node node = heap.poll();
			result[index++] = node.index;
			curTime += node.processTime;
			
			while (nextIndex < size && array[nextIndex].enqueueTime <= curTime)
			{
				heap.offer(array[nextIndex++]);
			}
			
			// CPU is idle
			if (heap.isEmpty() && nextIndex < size)
			{
				heap.offer(array[nextIndex++]);
			}
		}
		
		return result;
    }
	
	public class Node
	{
		public int index;
		public int enqueueTime;
		public int processTime;
		
		public Node(int i, int e, int p)
		{
			index = i;
			enqueueTime = e;
			processTime = p;
		}
	}
}
