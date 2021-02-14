import java.util.Arrays;

public class Cardinality_Sorting 
{
	public static int[] cardinalitySorting(int[] nums)
	{
		if (nums == null || nums.length == 0)
		{
			return nums;
		}
		
		Node[] array = new Node[nums.length];
		
		for (int i = 0; i < nums.length; i++)
		{
			array[i] = new Node(0, nums[i]);
			
			for (int j = 0; j < 32; j++)
			{
				array[i].binary += (nums[i]) >> j & 1;
			}
		}
		
		Arrays.sort(array, (a, b) -> a.binary != b.binary ? a.binary - b.binary : a.decimal - b.decimal);
		
		for (int i = 0; i < nums.length; i++)
		{
			nums[i] = array[i].decimal;
		}
		
		return nums;
	}
	
	static class Node
	{
		public int binary;
		public int decimal;
		
		public Node(int binary, int decimal)
		{
			this.binary = binary;
			this.decimal = decimal;
		}
	}
	
	
	
	
	public static void main(String[] args)
	{
		int[] nums = {1,2,3,4};
		nums = cardinalitySorting(nums);
		
		for (int num : nums)
		{
			System.out.print(num + ", ");
		}
	}
}
