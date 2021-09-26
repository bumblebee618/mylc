import java.util.Stack;

/***
 * 
 * @author jackie
 * 
 * We are given an array asteroids of integers representing asteroids in a row.

For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.

Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.

 

Example 1:

Input: asteroids = [5,10,-5]
Output: [5,10]
Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
Example 2:

Input: asteroids = [8,-8]
Output: []
Explanation: The 8 and -8 collide exploding each other.
Example 3:

Input: asteroids = [10,2,-5]
Output: [10]
Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
Example 4:

Input: asteroids = [-2,-1,1,2]
Output: [-2,-1,1,2]
Explanation: The -2 and -1 are moving left, while the 1 and 2 are moving right. Asteroids moving the same direction never meet, so no asteroids will meet each other.
 

Constraints:

2 <= asteroids.length <= 104
-1000 <= asteroids[i] <= 1000
asteroids[i] != 0
 */
public class Q735_Asteroid_Collision {
	public int[] asteroidCollision(int[] asteroids) {
        if (asteroids == null || asteroids.length == 0) {
            return new int[0];
        }
        
        Stack<Integer> stack = new Stack<>();
        
        for (int asteroid : asteroids) {
            if (asteroid < 0 && !stack.isEmpty() && stack.peek() > 0) {
                while (asteroid < 0 && !stack.isEmpty() && stack.peek() > 0) {
                    if (Math.abs(asteroid) > stack.peek()) {
                        stack.pop();
                    } else if (Math.abs(asteroid) == stack.peek()) {
                        stack.pop();
                        asteroid = Integer.MAX_VALUE;  // explode
                    } else {
                        asteroid = Integer.MAX_VALUE;  // explode
                    }
                }
                
                if (asteroid < 0) {
                    stack.push(asteroid);
                }
            } else {
                stack.push(asteroid);
            }
        }
        
        int[] result = new int[stack.size()];
        
        for (int i = stack.size()-1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        
        return result;
    }
}	
