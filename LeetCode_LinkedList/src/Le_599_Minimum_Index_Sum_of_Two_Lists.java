import java.util.*;
/**
 * 
Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.

You need to help them find out their common interest with the least list index sum. If there is a choice tie between answers, output all of them with no order requirement. You could assume there always exists an answer.

Example 1:
Input:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
Output: ["Shogun"]
Explanation: The only restaurant they both like is "Shogun".
Example 2:
Input:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["KFC", "Shogun", "Burger King"]
Output: ["Shogun"]
Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1).
Note:
The length of both lists will be in the range of [1, 1000].
The length of strings in both lists will be in the range of [1, 30].
The index is starting from 0 to the list length minus 1.
No duplicates in both lists.
 *
 */
public class Le_599_Minimum_Index_Sum_of_Two_Lists {
	public String[] findRestaurant(String[] list1, String[] list2) {
        if (list1 == null || list1.length == 0 || list2 == null || list2.length == 0) {
            return new String[0];
        }
        
        Map<String, Integer> map1 = new HashMap<>();
        int minIndexSum = Integer.MAX_VALUE;
        
        for (int i = 0; i < list1.length; i++) {
            map1.put(list1[i], i);
        }
        
        for (int i = 0; i < list2.length; i++) {
            if (map1.containsKey(list2[i])) {
                int indexSum = map1.get(list2[i]) + i;
                minIndexSum = Math.min(minIndexSum, indexSum);
            }
        }
        
        List<String> list = new LinkedList<>();
        
        for (int i = 0; i < list2.length; i++) {
            if (map1.containsKey(list2[i])) {
                int indexSum = map1.get(list2[i]) + i;
                
                if (minIndexSum == indexSum) {
                    list.add(list2[i]);
                }
            }
        }
        
        String[] result = new String[list.size()];
        int index = 0;
        
        for (String name : list) {
            result[index++] = name;
        }
        
        return result;
    }
}
