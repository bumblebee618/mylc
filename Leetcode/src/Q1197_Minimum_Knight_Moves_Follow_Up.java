/***
 * 
 * @author jackie
 *
 *	此题是1197的 follow up, 无限边界 + 障碍物
 * 
 */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Q1197_Minimum_Knight_Moves_Follow_Up 
{
		// 双向BFS
		private int[] dx = {-1, -1, -1, 0, 1, 1,  1, 0};
		private int[] dy = {-1,  0,  1, 1, 1, 0, -1, -1};
		
		public int MiniKnight_Move(int[][] graph, int[] start, int[] end)
		{
			Queue<int[]> queue1 = new LinkedList<>();
			queue1.offer(start);
			Set<String> visited1 = new HashSet<>();
			visited1.add(start[0] + "," + start[1]);
			int step1 = 0;
			
			Queue<int[]> queue2 = new LinkedList<>();
			queue2.offer(end);
			Set<String> visited2 = new HashSet<>();
			visited2.add(end[0] + "," + end[1]);
			int step2 = 0;
			
			while (!queue1.isEmpty() && !queue2.isEmpty())
			{
				step1++;
				
				if (bfs(graph, queue1, visited1, visited2, true))
				{
					return step1 + step2;
				}
				
				step2++;
				
				if (bfs(graph, queue2, visited2, visited1, false))
				{
					return step1 + step2;
				}
			}
			
			return -1;
		}
		
		private boolean bfs(int[][] graph,
							Queue<int[]> queue, 
							Set<String> srcVisited, 
							Set<String> desVisited,
							boolean isSrc)
		{
			int size = queue.size();
			
			for (int i = 0; i < size; i++)
			{
				int[] node = queue.poll();
				
				for (int j = 0; j < dx.length; j++)
				{
					int newX = node[0] + dx[j];
					int newY = node[1] + dy[j];
					String newNodeStr = newX + "," + newY;
					
					if (newX >= 0 && newX < graph.length 
						&& newY >= 0 && newY < graph[0].length 
						&& graph[newX][newY] == 0 
						&& !srcVisited.contains(newNodeStr))
					{
						if (desVisited.contains(newNodeStr))
						{
							return true;
						}
						
						queue.offer(new int[] {newX, newY});
						srcVisited.add(newNodeStr);
					}
				}
			}

			return false;
		}
		
		
		
		
		/******************************* main *******************************/
		
		public static void main(String[] args)
		{
			int[][] graph1 = {
					{0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0},
			};
			
			int[][] graph2 = {
					{0,0,0,0,0,0,0,0,0,0},
					{0,1,1,1,1,1,1,1,1,0},
					{0,1,1,1,1,1,1,1,1,0},
					{0,1,1,1,1,1,1,1,1,0},
					{0,1,1,1,1,1,1,1,1,0},
					{0,1,1,1,1,1,1,1,1,0},
					{0,1,1,1,1,1,1,1,1,0},
					{0,1,1,1,1,1,1,1,1,0},
					{0,1,1,1,1,1,1,1,1,0},
					{0,0,0,0,0,0,0,0,0,0},
			};
			
			int[][] graph3 = {
					{0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,1,1,1},
					{0,0,0,0,0,0,0,1,0,0},
					{0,0,0,0,0,0,0,1,0,0},
			};
			
			int[] start = {0, 0};
			int[] end = {9, 9};
			
			Q1197_Minimum_Knight_Moves_Follow_Up test = new Q1197_Minimum_Knight_Moves_Follow_Up();
			System.out.println(test.MiniKnight_Move(graph1, start, end));
			System.out.println(test.MiniKnight_Move(graph2, start, end));
			System.out.println(test.MiniKnight_Move(graph3, start, end));
		}
}
