import java.util.Arrays;

public class Find_Min_Remove_Array_Peek {
	public static int findMiniRemove(int[] nums)
	{
		if (nums == null || nums.length == 0)
		{
			return 0;
		}
		
		int size = nums.length;
		int[] left = new int[size];
		int[] right = new int[size];
		Arrays.fill(left, 1);
		Arrays.fill(right, 1);
		int maxLen = 0;
		
		for (int i = 1; i < size; i++)
		{
			for (int j = 0; j < i; j++)
			{
				if (nums[i] > nums[j])
				{
					left[i] = Math.max(left[i], left[j]+1);
				}
			}
		}
		
		for (int i = size-2; i >= 0; i--)
		{
			for (int j = size-1; j > i; j--)
			{
				if (nums[i] > nums[j])
				{
					right[i] = Math.max(right[i], right[j]+1);
				}
			}
		}
		
		print(left);
		print(right);
		
		for (int i = 0; i < size; i++)
		{
			maxLen = Math.max(maxLen, left[i]+right[i]-1);
			System.out.print(maxLen + ", ");
		}
		
		return size-maxLen;
	}
	
	private static void print(int[] nums)
	{
		for (int i = 0; i < nums.length; i++)
		{
			System.out.print(nums[i] + ", ");
		}
		System.out.println();
	}
	
	public static void main(String[] args)
	{
		int[] nums = {2,3,15,5,7,6,4,1};
		System.out.println(findMiniRemove(nums));
	}
}
