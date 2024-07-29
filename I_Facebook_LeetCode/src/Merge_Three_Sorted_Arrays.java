import java.util.*;

import org.omg.CORBA.SystemException;


public class Merge_Three_Sorted_Arrays {
	public List<Integer> Merge_Three_Sorted_Arrays(int[] array1, int[] array2, int[] array3) {
		List<Integer> result = new LinkedList<>();
		
		if (array1 == null || array2 == null || array3 == null) {
			return result;
		}
		
		int index1 = 0, index2 = 0, index3 = 0;
		Queue<Tuple> heap = new PriorityQueue<Tuple>((a, b) -> a.value - b.value);
		heap.offer(new Tuple(array1[index1], 1));
		heap.offer(new Tuple(array2[index2], 2));
		heap.offer(new Tuple(array3[index3], 3));
		
		while (!heap.isEmpty()) {
			Tuple t = heap.poll();
			
			if (result.size() == 0 || result.get(result.size()-1) != t.value) {
				result.add(t.value);
			}
			
			switch (t.arrayNumber) {
				case 1: {
					if (index1 + 1 < array1.length) {
						heap.offer(new Tuple(array1[++index1], 1));
					}
				
					break;
				}
				
				case 2: {
					if (index2 + 1 < array2.length) {
						heap.offer(new Tuple(array2[++index2], 2));
					}
				
					break;
				}
				
				case 3: {
					if (index3 + 1 < array3.length) {
						heap.offer(new Tuple(array3[++index3], 3));
					}
				
					break;
				}
				
				default: break;
			}
		}
		
		return result;
	}
	
	class Tuple {
		public int value;
		public int arrayNumber;
		
		public Tuple(int v, int a) {
			value = v;
			arrayNumber = a;
		} 
	}
	
	public static void main(String[] args) {
		Merge_Three_Sorted_Arrays test = new Merge_Three_Sorted_Arrays();
		int[] array1 = {1,2,3};
		int[] array2 = {2,4,9};
		int[] array3 = {3,4,5,6};
		
		List<Integer> result = test.Merge_Three_Sorted_Arrays(array1, array2, array3);
		
		for (int num : result) {
			System.out.print(num + ", ");
		}
	}
}
