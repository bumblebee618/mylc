import java.util.*;

/***
 * 
 * @author jackie
 * 
 * You are playing a game that contains multiple characters, and each of the characters has two main properties: attack and defense. You are given a 2D integer array properties where properties[i] = [attacki, defensei] represents the properties of the ith character in the game.

A character is said to be weak if any other character has both attack and defense levels strictly greater than this character's attack and defense levels. More formally, a character i is said to be weak if there exists another character j where attackj > attacki and defensej > defensei.

Return the number of weak characters.

 

Example 1:

Input: properties = [[5,5],[6,3],[3,6]]
Output: 0
Explanation: No character has strictly greater attack and defense than the other.
Example 2:

Input: properties = [[2,2],[3,3]]
Output: 1
Explanation: The first character is weak because the second character has a strictly greater attack and defense.
Example 3:

Input: properties = [[1,5],[10,4],[4,3]]
Output: 1
Explanation: The third character is weak because the second character has a strictly greater attack and defense.
 

Constraints:

2 <= properties.length <= 105
properties[i].length == 2
1 <= attacki, defensei <= 105
 */
public class Q1996_The_Number_of_Weak_Characters_in_the_Game {
	public int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort(properties, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        int[] queue = new int[properties.length];
        int result = 0, tail = -1;
        
        for (int i = properties.length-1; i >= 0; i--) {
        	if (tail == -1 || properties[i][1] > queue[tail]) {
        		queue[++tail] = properties[i][1];
        		continue;
        	}
        	
        	result += properties[i][1] < queue[tail] ? 1 : 0;
        	int pos = binarySearch(queue, tail, properties[i][1]);
        	queue[pos] = Math.max(properties[i][1], queue[pos]);
        }
        
        return result;
    }
	
	private int binarySearch(int[] queue, int tail, int target) {
		if (target <= queue[0]) {
			return 0;
		} 
		
		int start = 0, end = tail;
		
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			
			if (queue[mid] < target) {
				start = mid;
			} else {
				end = mid;
			}
		}
		
		return target <= queue[start] ? start : end;
	}
	
	
	
	
	
	
	
	
	/********************************** main **********************************/
	
	public static void main(String[] args) {
		int[][] properties = {{10,1},{5,1},{7,10},{4,1},{5,9},{6,9},{7,2},{1,10}};
		Q1996_The_Number_of_Weak_Characters_in_the_Game test = new Q1996_The_Number_of_Weak_Characters_in_the_Game();
		System.out.println(test.numberOfWeakCharacters(properties));
	}
	
	
}
