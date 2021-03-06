/***
 * 
 * @author jackie
 * 
 * Given two numbers, hour and minutes. Return the smaller angle (in degrees) formed between the hour and the minute hand.

 

Example 1:



Input: hour = 12, minutes = 30
Output: 165
Example 2:



Input: hour = 3, minutes = 30
Output: 75
Example 3:



Input: hour = 3, minutes = 15
Output: 7.5
Example 4:

Input: hour = 4, minutes = 50
Output: 155
Example 5:

Input: hour = 12, minutes = 0
Output: 0
 

Constraints:

1 <= hour <= 12
0 <= minutes <= 59
Answers within 10^-5 of the actual minValue will be accepted as correct.
 */
public class Q1344_Angle_Between_Hands_of_a_Clock 
{
	public double angleClock(int hour, int minutes) 
    {
        if (hour < 1 || hour > 12 || minutes < 0 || minutes > 59)
        {
            return 0;
        }
        
        double angel1 = (double) hour / 12 * 360;
        double adjust = (double) minutes / 60 * 30;
        angel1 += adjust;
        double angel2 = (double) minutes / 60 * 360;
        double result = Math.abs(angel1 - angel2);
        return result > 180 ? 360 - result : result;
    }
	
	
	
	
	public static void main(String[] args)
	{
		Q1344_Angle_Between_Hands_of_a_Clock test = new Q1344_Angle_Between_Hands_of_a_Clock();
		System.out.println(test.angleClock(4, 50));
	}
}
