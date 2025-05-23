import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;
import java.util.Set;

public class Q000_DataStructure_HashCode 
{
	public static void main(String[] args)
	{
		List<Integer> list1 = new LinkedList<>();
		list1.add(1);
		list1.add(2);
		list1.add(3);
		
		Set<List<Integer>> testSet1 = new HashSet<>();
		testSet1.add(list1);
		
		List<Integer> list2 = new LinkedList<>();
		list2.add(1);
		list2.add(2);
		list2.add(3);
		System.out.println("testSet1 contains list2 ? " + testSet1.contains(list2));
		System.out.println(String.format("list1 hashCode = %s", list1.hashCode()));
		System.out.println(String.format("list2 hashCode = %s", list2.hashCode()));
		
		
		
		
		List<Integer> list3 = new ArrayList<>();
		list3.add(1);
		list3.add(2);
		list3.add(3);
		
		Set<List<Integer>> testSet2 = new HashSet<>();
		testSet2.add(list3);
		
		List<Integer> list4 = new ArrayList<>();
		list4.add(1);
		list4.add(2);
		list4.add(3);
		System.out.println("testSet2 contains list4 ? " + testSet2.contains(list4));
		
		
		
		
		
		Set<Integer> set1 = new HashSet<>();
		set1.add(1);
		set1.add(2);
		set1.add(3);
		
		Set<Set<Integer>> testSet3 = new HashSet<>();
		testSet3.add(set1);
		
		Set<Integer> set2 = new HashSet<>();
		set2.add(3);
		set2.add(2);
		set2.add(1);
		System.out.println("testSet3 contains set2 ? " + testSet3.contains(set2));
		
		
		
		
		
		
		Set<Integer> set3 = new LinkedHashSet();
		set3.add(1);
		set3.add(2);
		set3.add(3);
		
		Set<Set<Integer>> testSet4 = new HashSet<>();
		testSet4.add(set3);
		
		Set<Integer> set4 = new LinkedHashSet();
		set4.add(3);
		set4.add(2);
		set4.add(1);
		System.out.println("testSet4 contains set4 ? " + testSet4.contains(set4));
		
		
		
		
		int[] array1 = {1, 2, 3};
		int[] array2 = {1, 2, 3};
		Set<int[]> testSet5 = new HashSet<>();
		testSet5.add(array1);
		System.out.println("testSet5 contains array2 ? " + testSet5.contains(array2));
		System.out.println(String.format("array1 hashCode = %s", array1.hashCode()));
		System.out.println(String.format("array2 hashCode = %s", array2.hashCode()));
		
		
		
		
		
		
		
		Iterator<Integer> iter1 = set1.iterator();
		while (iter1.hasNext())
		{
			System.out.print(iter1.next() + ", ");
		}
		
		System.out.println();
		
		Iterator<Integer> iter2 = set2.iterator();
		while (iter2.hasNext())
		{
			System.out.print(iter2.next() + ", ");
		}
		
		System.out.println();
		
		Iterator<Integer> iter3 = set3.iterator();
		while (iter3.hasNext())
		{
			System.out.print(iter3.next() + ", ");
		}
		
		System.out.println();
		
		Iterator<Integer> iter4 = set4.iterator();
		while (iter4.hasNext())
		{
			System.out.print(iter4.next() + ", ");
		}
		
		System.out.println();
	}
}
