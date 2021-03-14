import java.util.*;

/***
 * 
 * @author jackie
 * 
 * There is a school that has classes of students and each class will be having a final exam. You are given a 2D integer array classes, where classes[i] = [passi, totali]. You know beforehand that in the ith class, there are totali total students, but only passi number of students will pass the exam.

You are also given an integer extraStudents. There are another extraStudents brilliant students that are guaranteed to pass the exam of any class they are assigned to. You want to assign each of the extraStudents students to a class in a way that maximizes the average pass ratio across all the classes.

The pass ratio of a class is equal to the number of students of the class that will pass the exam divided by the total number of students of the class. The average pass ratio is the sum of pass ratios of all the classes divided by the number of the classes.

Return the maximum possible average pass ratio after assigning the extraStudents students. Answers within 10-5 of the actual answer will be accepted.

 

Example 1:

Input: classes = [[1,2],[3,5],[2,2]], extraStudents = 2
Output: 0.78333
Explanation: You can assign the two extra students to the first class. The average pass ratio will be equal to (3/4 + 3/5 + 2/2) / 3 = 0.78333.
Example 2:

Input: classes = [[2,4],[3,9],[4,5],[2,10]], extraStudents = 4
Output: 0.53485
 

Constraints:

1 <= classes.length <= 105
classes[i].length == 2
1 <= passi <= totali <= 105
1 <= extraStudents <= 105
 */
public class Q1792_Maximum_Average_Pass_Ratio 
{
	public double maxAverageRatio(int[][] classes, int extraStudents) 
    {
        if (classes == null || classes.length == 0 || classes[0].length != 2 || extraStudents < 0)
        {
            return 0;
        }
        
        Queue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] status1, int[] status2)
            {
                double newRatio1 = (double) (status1[0] + 1) / (status1[1] + 1);
                double oldRatio1 = (double) (status1[0]) / status1[1];
                double diff1 = newRatio1 - oldRatio1;
                
                double newRatio2 = (double) (status2[0] + 1) / (status2[1] + 1);
                double oldRatio2 = (double) (status2[0]) / status2[1];
                double diff2 = newRatio2 - oldRatio2;
                
                if (diff1 > diff2)
                {
                    return -1;
                }
                else
                {
                    return 1;
                }
            }
        });
        
        for (int[] c : classes)
        {
        	heap.offer(c);
        }
        
        for (int i = 0; i < extraStudents; i++)
        {
        	int[] status = heap.poll();
        	heap.offer(new int[] {status[0]+1, status[1]+1});
        }
        
        double result = 0;
        
        while (!heap.isEmpty())
        {
        	int[] status = heap.poll();
        	result += (double) status[0] / status[1];
        }
        
        return result / classes.length;
    }
}
