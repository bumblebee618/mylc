import java.util.*;

public class Q000_A_Contest {
	public int minOperations(int[] nums1, int[] nums2) 
    {
        if (nums1 == null || nums1.length == 0 
        	|| nums2 == null || nums2.length == 0)
        {
            return -1;
        }
        
        int size1 = nums1.length, size2 = nums2.length;
        int max1 = size1 * 6, min1 = size1;
        int max2 = size2 * 6, min2 = size2;
        
        int sum1 = Arrays.stream(nums1).sum();
        int sum2 = Arrays.stream(nums2).sum(); 
        
        if (max1 < min2 || max2 < min1)
        {
            return -1;
        }
        else if (sum1 == sum2)
        {
            return 0;
        }
        
        int diff = Math.abs(sum1 - sum2);
        
        int[] minArray = sum1 < sum2 ? nums1 : nums2;
        int[] maxArray = sum1 < sum2 ? nums2 : nums1;
        Arrays.sort(minArray);
        Arrays.sort(maxArray);
        
        int index1 = 0, index2 = maxArray.length-1;
        int step = 0;
        
        while (diff > 0)
        {
            step++;
            
            if ((index1 < minArray.length && diff <= 6-minArray[index1]) 
                || (index2 >= 0 && diff < maxArray[index2]-1))
            {
                break;
            }
            
            if (index1 < minArray.length && index2 >= 0)
            {
                if (6-minArray[index1] > maxArray[index2]-1)
                {
                    diff -= 6-minArray[index1++];
                }
                else
                {
                    diff -= maxArray[index2--]-1;
                }
            }
            else if (index2 >= 0)
            {
                diff -= maxArray[index2--]-1;
            }
            else
            {
                diff -= 6-minArray[index1++];
            }
        }
        
        return step;
    }
	
	
	
	
	private int gap = Integer.MAX_VALUE;
    private int resultPrice = 0;
    // private Map<String, Integer> memo = new HashMap<>();
    private int target = 0;
    
    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) 
    {
        if (baseCosts == null || baseCosts.length == 0 
            || toppingCosts == null || toppingCosts.length == 0 || target <= 0)
        {
            return 0;
        }
        
        this.target = target;
        Arrays.sort(toppingCosts);
        
        for (int base : baseCosts)
        {
            if (base == target)
            {
                return target;        
            }
            
            boolean[] visited = new boolean[toppingCosts.length];
            search(toppingCosts, 0, visited, base);
        }
        
        return resultPrice;
    }
    
    private void search(int[] toppingCosts, int start, boolean[] visited, int price)
    {
    	if (gap > Math.abs(price - target) 
           || (gap == Math.abs(price - target) && price < resultPrice))
        {
    		gap = Math.abs(price - target);
            resultPrice = price;
        }
    	
    	if (price > target)
    	{
    		return;
    	}
        
        for (int i = start; i < toppingCosts.length; i++)
        {
            if (visited[i])
            {
                continue;
            }
            
            visited[i] = true;
            
            for (int j = 1; j <= 2; j++)
            {
                int curPrice = price + toppingCosts[i] * j;
                search(toppingCosts, i, visited, curPrice);
            }
            
            visited[i] = false;
        }
    }
    
    
    /***
     *     	
        if (gap > Math.abs(price - target) 
            || (gap == Math.abs(price - target) && price < resultPrice))
        {
            gap = Math.abs(price - target);
            resultPrice = price;
        }
     * @param args
     */
    
    
    
    
    public double[] getCollisionTimes2(int[][] cars) 
    {
        if (cars == null || cars.length == 0 || cars[0].length == 0)
        {
            return new double[0];
        }
        
        int size = cars.length;
        double[] result = new double[size];
        Deque<Integer> stack = new ArrayDeque<>();
        
        for (int i = size - 1; i >= 0; i--) 
        {
            result[i] = -1;
            
			// pop all cars with speed >= current car's speed (they will never collide)
            while (!stack.isEmpty() && cars[i][1] <= cars[stack.peekFirst()][1]) 
            {
                stack.pollFirst();
            }
            
			// check if the next car will collide the next next car before current car collides the next car
			// if so, current car will collide the next next car instead of the next car
            while (!stack.isEmpty()) 
            {
                int next = stack.peekFirst();
                result[i] = (double) (cars[next][0] - cars[i][0]) / (cars[i][1] - cars[next][1]);       
                
                if (result[stack.peekFirst()] != -1 && result[i] >= result[stack.peekFirst()]) 
                {
                    stack.pollFirst();
                } 
                else
                {
                    break;
                }                              
            } 
            
            stack.offerFirst(i);
        }
        
        return result;
    }
    
    class Node
    {
    	public int speed;
    	public int capSpeed;
    	public int position;
    }
    
    
    public static void main(String[] args)
    {
    	Q000_A_Contest test = new Q000_A_Contest();
    	int[] baseCosts = {4};
    	int[] toppingCosts = {9};
    	int target = 9;
    	
    	int[] baseCosts2 = {1,7};
    	int[] toppingCosts2 = {3,4};
    	int target2 = 10;
    	
    	
    	// System.out.println(test.closestCost(baseCosts2, toppingCosts2, target2));
    	
    	
    	int[] nums1_1 = {1,2,3,4,5,6};
    	int[] nums2_1 = {1,1,2,2,2,2};
    	
    	int[] nums1_2 = {1,1,1,1,1,1,1};
    	int[] nums2_2 = {6};
    	
    	int[] nums1_3 = {6,6};
    	int[] nums2_3 = {1};
    	
    	// System.out.println(test.minOperations(nums1_1, nums2_1));
    	// System.out.println(test.minOperations(nums1_2, nums2_2));
    	// System.out.println(test.minOperations(nums1_3, nums2_3));
    	
    	int[][] cars1 = {{1,2},{2,1},{4,3},{7,2}};
    	int[][] cars2 = {{3,4},{5,4},{6,3},{9,1}};
    	
    	double[] result = test.getCollisionTimes2(cars2);
    	
    	for (double time : result)
    	{
    		System.out.print(time + ", ");
    	}
    }
    
}
