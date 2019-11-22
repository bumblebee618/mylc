import java.util.*;

public class OTS_test {
	public int findMinStop(int gas, int distance, int[][] gasStation) {
		if (gas >= distance) {
			return 0;
		} else if (gasStation == null || gasStation.length == 0 || gasStation[0].length == 0) {
			return -1;
		}
		
		Arrays.sort(gasStation, (a, b) -> b[0] - a[0]);
		int curFastest = gas, nextFastest = gas;
		int step = 0, pos = 0, len = gasStation.length;
		
		while (curFastest < distance) {
			step++;
			
			while (pos < len && curFastest >= distance - gasStation[pos][0]) {
				int curDistance = distance - gasStation[pos][0];
				nextFastest = Math.max(nextFastest, curDistance + gasStation[pos][1]);
				pos++;
			}
			
			if (curFastest == nextFastest && curFastest < distance) {
				return -1;
			}
			
			curFastest = nextFastest;
		}
		
		return step;
	}
	
	
	
	
	
	
	
	public static int findMinStop2(int g, int d, int[][] gasStations) {
        if (g >= d) return 0;
        if (gasStations == null || gasStations.length == 0) return -1;
        for (int i = 0; i < gasStations.length; i++) {
            gasStations[i][0] = d - gasStations[i][0];
        }
        int[] maxReach = new int[gasStations.length + 1];
        maxReach[0] = g;
        Arrays.sort(gasStations, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int res = 0;
        HashSet<Integer> visited = new HashSet<>();
        while (res < maxReach.length - 1 && maxReach[res] < d) {
            maxReach[res + 1] = maxReach[res] + findLargestWithOutRepeat(gasStations, visited, maxReach[res]);
            res++;
        }
        System.out.println(visited);
        return maxReach[res] < d ? -1 : res;
    }

    //O(N)
    private static int findLargestWithOutRepeat(int[][] gasStations, Set<Integer> visited, int maxDis) {
        int max = 0;
        int maxIndex = -1;
        for (int i = 0; i < gasStations.length; i++) {
            if (gasStations[i][0] > maxDis) {
                break;
            }
            if (visited.contains(i)) continue;
            if (gasStations[i][1] > max) {
                max = gasStations[i][1];
                maxIndex = i;
            }
        }
        visited.add(maxIndex);
        return max;
    }
    
    public boolean parseInt(String str) {
//    	if (str == null || str.length() == 0) {
//    		return false;
//    	}
    	
    	String maxStr = Integer.toString(Integer.MAX_VALUE);
    	String minStr = Integer.toString(Integer.MIN_VALUE);
    	
    	System.out.println(maxStr + ", " + minStr);
    	
    	try {
    		Integer.parseInt(str);
    	} catch (NumberFormatException nfe) {
    		System.out.println("NumberFormatException");
    		return false;
    	}
    	
    	return true;
    }
	
	
	public static void main(String[] args) {
		OTS_test test = new OTS_test();
		int[][] gasStation =  {{24,10},{25, 1},{26,12}};
//		int[][] gasStation =  {{15,1},{14, 10},{12,12}};
		int gas = 10;
		int distance = 34;
		System.out.println(test.findMinStop(gas, distance, gasStation));
        System.out.println(test.findMinStop2(gas, distance, gasStation));
        
        String str = "-11111111111111111111111111111111111111111";
        String str1 = "";
        System.out.println(test.parseInt(null));
	}
}
