/***
 * 
 * @author jackie
 * 
 * In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a move consists of either replacing one occurrence of "XL" with "LX", or replacing one occurrence of "RX" with "XR". Given the starting string start and the ending string end, return True if and only if there exists a sequence of moves to transform one string to the other.

 

Example 1:

Input: start = "RXXLRXRXL", end = "XRLXXRRLX"
Output: true
Explanation: We can transform start to end following these steps:
RXXLRXRXL ->
XRXLRXRXL ->
XRLXRXRXL ->
XRLXXRRXL ->
XRLXXRRLX
Example 2:

Input: start = "X", end = "L"
Output: false
Example 3:

Input: start = "LLR", end = "RRL"
Output: false
Example 4:

Input: start = "XL", end = "LX"
Output: true
Example 5:

Input: start = "XLLR", end = "LXLX"
Output: false
 

Constraints:

1 <= start.length <= 104
start.length == end.length
Both start and end will only consist of characters in 'L', 'R', and 'X'.
 */

public class Q777_Swap_Adjacent_in_LR_String 
{
	// two pointers, time is O(n)
	public boolean canTransform(String start, String end) 
    {
        if (start == null || end == null)
        {
            return false;
        }
        else if (start.length() != end.length())
        {
            return false;
        }
        
        int count1 = 0, count2 = 0;
        int size = start.length();
        
        for (int i = 0; i < size; i++)
        {
            count1 += start.charAt(i) == 'X' ? 1 : 0;
            count2 += end.charAt(i) == 'X' ? 1 : 0;
        }
        
        if (count1 != count2)
        {
            return false;
        }
        
        int index1 = 0, index2 = 0;
        
        while (index1 < size && index2 < size)
        {
            while (index1 < size && start.charAt(index1) == 'X')
            {
                index1++;
            }
            
            while (index2 < size && end.charAt(index2) == 'X')
            {
                index2++;
            }
            
            if (index1 == size || index2 == size)
            {
                return index1 == size && index2 == size;
            }
            
            if (start.charAt(index1) != end.charAt(index2))
            {
                return false;
            }
            else if (start.charAt(index1) == 'L' && index1 < index2)
            {
                return false;    
            }
            else if (start.charAt(index1) == 'R' && index1 > index2)
            {
                return false;    
            }
            
            index1++;
            index2++;
        }
        
        return true;
    }
}
