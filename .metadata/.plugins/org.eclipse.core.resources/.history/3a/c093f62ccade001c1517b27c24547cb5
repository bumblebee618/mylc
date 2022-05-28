/***
 * 
 * @author jackie
 * 
 * Given an array of 4 digits, return the largest 24 hour time that can be made.

The smallest 24 hour time is 00:00, and the largest is 23:59.  Starting from 00:00, a time is larger if more time has elapsed since midnight.

Return the answer as a string of length 5.  If no valid time can be made, return an empty string.

 

Example 1:

Input: [1,2,3,4]
Output: "23:41"
Example 2:

Input: [5,5,5,5]
Output: ""
 

Note:

A.length == 4
0 <= A[i] <= 9
 */
public class Q949_Largest_Time_for_Given_Digits {
	private int maxHour = -1;
    private int maxMin = -1;
        
    public String largestTimeFromDigits(int[] digits) {
        if (digits == null || digits.length != 4)
        {
            return "";
        }
        
        boolean[] visited = new boolean[digits.length];
        backtrack(digits, visited, 0, 0, 0);
        
        if (maxHour == -1 || maxMin == -1)
        {
            return "";
        }
        
        String hour = maxHour < 10 ? "0"+Integer.toString(maxHour) : Integer.toString(maxHour);
        String minute = maxMin < 10 ? "0"+Integer.toString(maxMin) : Integer.toString(maxMin);
        return hour + ":" + minute;
    }
    
    private void backtrack(int[] digits, boolean[] visited, int hour, int minute, int size)
    {
        if (size == 4)
        {
            if (hour > maxHour || (hour == maxHour && minute > maxMin))
            {
                maxHour = hour;
                maxMin = minute;
            }
            
            return;
        }
        
        for (int i = 0; i < digits.length; i++)
        {
            if (size == 0)
            {
                if (!visited[i] && digits[i] <= 2)
                {
                    visited[i] = true;
                    backtrack(digits, visited, digits[i]*10, minute, size+1);
                    visited[i] = false;
                }
            }
            else if (size == 1)
            {
                if (!visited[i] && hour+digits[i] <= 23)
                {
                    visited[i] = true;
                    backtrack(digits, visited, hour+digits[i], minute, size+1);
                    visited[i] = false;
                }
            }
            else if (size == 2)
            {
                if (!visited[i] && digits[i] <= 5)
                {
                    visited[i] = true;
                    backtrack(digits, visited, hour, digits[i]*10, size+1);
                    visited[i] = false;
                }
            }
            else
            {
                if (!visited[i] && minute+digits[i] <= 59)
                {
                    visited[i] = true;
                    backtrack(digits, visited, hour, minute+digits[i], size+1);
                    visited[i] = false;
                }
            }
        }
    }
}
