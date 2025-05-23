import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class Shopping_Options 
{
	public static long getNumberOfOptions(
			List<Integer> priceOfJeans, 
			List<Integer> priceOfShoes, 
			List<Integer> priceOfSkirts, 
			List<Integer> priceOfTops,
			int dollars)
	{
		List<List<Integer>> items = new ArrayList<>();
		items.add(priceOfJeans);
		items.add(priceOfShoes);
		items.add(priceOfSkirts);
		items.add(priceOfTops);
		Collections.sort(items, (a, b) -> a.size() - b.size());
		
		List<Integer>[] dp = new List[4];
		
		for (int i = 0; i < items.size(); i++)
		{
			dp[i] = new LinkedList<>();
			Collections.sort(items.get(i));
			
			for (int price : items.get(i))
			{
				if (i == 0)
				{
					if (price > dollars)
					{
						break;
					}
					
					dp[i].add(price);
				}
				else
				{
					for (int prevCost : dp[i-1])
					{
						if (prevCost + price > dollars)
						{
							break;
						}
						
						dp[i].add(prevCost + price);
					}
				}
			}
			
			Collections.sort(dp[i]);
		}
			
		return dp[3].size();
	}
	
	
	
	// O(4 * m * dollars)
	public static long getNumberOfOptions2(
			List<Integer> priceOfJeans, 
			List<Integer> priceOfShoes, 
			List<Integer> priceOfSkirts, 
			List<Integer> priceOfTops,
			int dollars)
	{
		List<List<Integer>> items = new ArrayList<>();
		items.add(priceOfJeans);
		items.add(priceOfShoes);
		items.add(priceOfSkirts);
		items.add(priceOfTops);
		Collections.sort(items, (a, b) -> a.size() - b.size());
		
		int[][] dp = new int[4][dollars+1];
		
		for (int i = 0; i < items.size(); i++)
		{
			Collections.sort(items.get(i));
			
			for (int price : items.get(i))
			{
				if (i == 0)
				{
					if (price > dollars)
					{
						break;
					}
					
					dp[i][price] = 1;
				}
				else
				{
					for (int prevCost = 0; prevCost < dp[i-1].length; prevCost++)
					{
						int cost = prevCost + price;
						
						if (cost > dollars)
						{
							break;
						}

						dp[i][cost] += dp[i-1][prevCost];
					}
				}
			}
		}
		
		long way = 0;
		
		for (int i = 0; i < dp[3].length; i++)
		{
			way += dp[3][i];
		}
		
		return way;
	}
	
	
	
	
	
	
	
	private static long result = 0;
	
	public static long getNumberOfOptions3(
			List<Integer> priceOfJeans, 
			List<Integer> priceOfShoes, 
			List<Integer> priceOfSkirts, 
			List<Integer> priceOfTops,
			int dollar)
	{
		List<List<Integer>> items = new ArrayList<>();
		items.add(priceOfJeans);
		items.add(priceOfShoes);
		items.add(priceOfSkirts);
		items.add(priceOfTops);
		
		backtrack(items, 0, dollar);
		return result;
	}
	
	private static void backtrack(List<List<Integer>> items, int index, int budget)
	{
		if (index == items.size())
		{
			if (budget >= 0)
			{
				result++;
			}
			
			return;
		}
		
		for (int price : items.get(index))
		{
			if (budget < price)
			{
				continue;
			}
			
			backtrack(items, index+1, budget-price);
		}
	}
	
	
	
	public static void main(String[] args)
	{
		List<Integer> priceOfJeans = new ArrayList<>();
		priceOfJeans.add(2);
		priceOfJeans.add(3);
		
		List<Integer> priceOfShoes = new ArrayList<>();
		priceOfShoes.add(4);
		
		List<Integer> priceOfSkirts = new ArrayList<>();
		priceOfSkirts.add(2);
		priceOfSkirts.add(3);
		
		List<Integer> priceOfTops = new ArrayList<>();
		priceOfTops.add(1);
		priceOfTops.add(2);
		
		long result1 = getNumberOfOptions2(priceOfJeans, priceOfShoes, priceOfSkirts, priceOfTops, 10);
		System.out.println(result1);
	}
}


