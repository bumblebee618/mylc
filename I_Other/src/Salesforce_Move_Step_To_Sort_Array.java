import java.util.Stack;


public class Salesforce_Move_Step_To_Sort_Array {
	public int minMoveToSortArray(int[] array) {
		int stackTop = -1;
		int movedMin = Integer.MAX_VALUE;
		
		for (int num : array) {
			while (stackTop > -1 && array[stackTop] > num) {
				movedMin = Math.min(movedMin, array[stackTop--]);
			}
			
			if (num < movedMin) {
				array[++stackTop] = num;
			} 
		}
		
		return array.length - (stackTop + 1);
	}
	
	
	public int minMoveToSortArray2(int[] array) {
		Stack<Integer> stack = new Stack<>();
		int moveMin = Integer.MIN_VALUE, count = 0;
		
		for (int num : array) {
			while (!stack.isEmpty() && stack.peek() > num) {
				moveMin = Math.min(moveMin, stack.pop());
			}
			
			stack.push(num);
		}
		
		return array.length - stack.size();
	}
	
	
	
	
	public static void main(String[] args) {
		Salesforce_Move_Step_To_Sort_Array test = new Salesforce_Move_Step_To_Sort_Array();
		int[] array1 = {5,1,3,2};
		int[] array2 = {5,1,3,6};
		int[] array3 = {5,1,3,7,6};
		int[] array4 = {5,1,2,3,4};
		int[] array5 = {1,2,3,4,5};
		int[] array6 = {5,6,1,2,3,9,8};
		int[] array7 = {6,5,1,2,3,9,8};
		int[] array8 = {6,5,1,2,3,8,9};
		int[] array9 = {6,5,1,2,10,3,8,9};
		int[] array10 = {2,3,4,5,1};
		int[] array11 = {6,5,4,3,2,1};
		
		System.out.println(test.minMoveToSortArray(array1));
		System.out.println(test.minMoveToSortArray(array2));
		System.out.println(test.minMoveToSortArray(array3));
		System.out.println(test.minMoveToSortArray(array4));
		System.out.println(test.minMoveToSortArray(array5));
		System.out.println(test.minMoveToSortArray(array6));
		System.out.println(test.minMoveToSortArray(array7));
		System.out.println(test.minMoveToSortArray(array8));
		System.out.println(test.minMoveToSortArray(array9));
		System.out.println(test.minMoveToSortArray(array10));
		System.out.println(test.minMoveToSortArray(array11));
	}
}
