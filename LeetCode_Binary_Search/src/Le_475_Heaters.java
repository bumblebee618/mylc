import java.util.*;
/*****
 * 
Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.

Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that all houses could be covered by those heaters.

So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum radius standard of heaters.

Note:
	Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
	Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
	As long as a house is in the heaters' warm radius range, it can be warmed.
	All the heaters follow your radius standard and the warm radius will the same.

Example 1:
	Input: [1,2,3],[2]
	Output: 1
	Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.

Example 2:
	Input: [1,2,3,4],[1,4]
	Output: 1
	Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.
 
 * 
 * */


public class Le_475_Heaters {
	// test case: houses is empty or heaters is empty	    
    public int findRadius(int[] houses, int[] heaters) {
        if(houses == null || houses.length == 0 || heaters == null || heaters.length == 0) {
            return 0;
        }
        
        Arrays.sort(heaters);
        int ans = 0;
        
        for(int house : houses) {
            int index = Arrays.binarySearch(heaters, house);
            index = (index < 0) ? -(index + 1) : index;
            int distance1 = (index - 1 >= 0) ? house - heaters[index - 1] : Integer.MAX_VALUE;
            int distance2 = (index < heaters.length) ? heaters[index] - house : Integer.MAX_VALUE;
            ans = Math.max(ans, Math.min(distance1, distance2));
        }
        
        return ans;
    }
}
