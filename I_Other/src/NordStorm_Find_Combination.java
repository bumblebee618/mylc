import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class NordStorm_Find_Combination {
	public List<Integer> minDiff(int[][] arrays) {
		List<Integer> result = new LinkedList<>();
		int size = arrays.length;
		Tuple[] tuples = new Tuple[size];
		
		for (int i = 0; i < arrays.length; i++) {
			Tuple tuple = new Tuple(i);
			
			for (int num : arrays[i]) {
				tuple.max = Math.max(tuple.max, num);
				tuple.min = Math.min(tuple.min, num);
			}
		}
		
		Arrays.sort(tuples, (a, b) -> a.min - b.min);
		int start = tuples[0].index, end = tuples[tuples.length-1].index;
		int minDiff = Integer.MAX_VALUE;
		int candidate1 = -1, candidate2 = -1;
		
		for (int i = 0; i < arrays[start].length; i++) {
			for (int j = 0; j < arrays[end].length; j++) {
				if (Math.abs(arrays[start][i] - arrays[end][j]) < minDiff) {
					minDiff = Math.abs(arrays[start][i] - arrays[end][j]);
					candidate1 = arrays[start][i];
					candidate2 = arrays[end][j];
				}
			}
		}
		
		for (int i = 1; i < tuples.length-1; i++) {
			
		}
	}
	
	class Tuple {
		public int min, max, index;
		
		public Tuple(int index) {
			this.min = Integer.MAX_VALUE;
			this.max = Integer.MIN_VALUE;
			this.index = index;
		}
	}
}
