import java.util.Random;

public class Algorithm_ShuffleCard 
{
	public static void shuffle(int[] array)
	{
		if (array == null || array.length <= 1)
		{
			return;
		}
		
		Random random = new Random();
		
		for (int i = array.length-1; i > 0; i--)
		{
			int index = random.nextInt(i+1);
			int temp = array[i];
			array[i] = array[index];
			array[index] = temp;
		}
	}
	
	
	public static void main(String[] args)
	{
		int[] array = {1,2,3,4,5,6};
		shuffle(array);
		
		for (int num : array)
		{
			System.out.print(num + ",");
		}
	}
}
