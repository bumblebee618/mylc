
public class Q134_Gas_Station {
	/*********************************************************************
	 * Main Idea
	 * The main idea of the algorithm is to have 2 pointers representing 
	 * where the trip starts and where the trip ends. We move the 
	 * "front pointer" if we have "capacity" to do so (i.e. if we have 
	 * extra gas), and we move the "back pointer" if we lack gas to complete
	 * our trip, therefore trying to find a start position that will give
	 * us the extra gas we need.
	 * When the 2 pointers meet, we just check whether we ended up with 
	 * enough gas or not to complete the trip. 
	 *********************************************************************/
	// Time complexity O(n)
	public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || gas.length == 0 || cost == null || cost.length == 0 || gas.length != cost.length)
        {
            return -1;
        }
        
        int totalGas = 0;
        int curGas = 0;
        int start = 0;
        
        for (int i = 0; i < gas.length; i++)
        {
            totalGas += gas[i]-cost[i];
            curGas += gas[i]-cost[i];
            
            if (curGas < 0)
            {
                curGas = 0;
                start = i+1;
            }
        }
        
        return totalGas >= 0 ? start : -1;
    }
	
    
	// Time complexity O(n^2)
	public int canCompleteCircuit2(int[] gas, int[] cost) 
	{
        if (gas == null || gas.length == 0 || cost == null || cost.length == 0 || gas.length != cost.length)
        {
            return -1;
        }
        
        int size = gas.length;
        
        for (int start = 0; start < size; start++)
        {
            if (gas[start]-cost[start] < 0)
            {
                continue;
            }
            
            int index = (start+1) % size;
            int totalGas = gas[start]-cost[start];
            
            while (index != start)
            {
                totalGas += gas[index]-cost[index];
                    
                if (totalGas < 0)
                {
                    break;
                }
                
                index = (index+1) % size;
            }
            
            if (totalGas >= 0)
            {
                return start;
            }
        }
        
        return -1;
    }

	
	
	/*************************************************/
	// by other using greedy
	public int canCompleteCircuit3(int[] gas, int[] cost) {
        if(gas == null || gas.length == 0 || cost == null ||cost.length == 0) {
            return -1;        
        }

        int n = gas.length;
        int startIndex = 0;
        int endIndex = 0;
        int totalProfit = gas[startIndex] - cost[startIndex];

        do {
            if(totalProfit >= 0) {
                endIndex = (endIndex+1)%n;
                totalProfit += gas[endIndex] - cost[endIndex];
            } 
            else {
                startIndex = startIndex-1 >= 0 ? startIndex-1 : n-1;
                totalProfit += gas[startIndex] - cost[startIndex];
            }
        } while(startIndex != endIndex);

        return totalProfit >= 0 ? startIndex : -1;
    }
}
