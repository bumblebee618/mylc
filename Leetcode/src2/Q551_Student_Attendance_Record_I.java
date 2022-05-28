/***
 * 
 * @author jackie
 * 
 * You are given a string representing an attendance record for a student. The record only contains the following three characters:
'A' : Absent.
'L' : Late.
'P' : Present.
A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

You need to return whether the student could be rewarded according to his attendance record.

Example 1:
Input: "PPALLP"
Output: True
Example 2:
Input: "PPALLL"
Output: False
 */
public class Q551_Student_Attendance_Record_I {
	public boolean checkRecord(String s) {
        if (s == null || s.length() == 0)
        {
            return true;
        }
        
        int absentCount = 0;
        
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            
            if (c == 'A')
            {
                if (absentCount++ == 1)
                {
                    return false;
                }
            }
            else if (c == 'L')
            {
                if (i >= 2 && s.charAt(i-1) == c && s.charAt(i-2) == c)
                {
                    return false;
                }
            }
        }
        
        return true;
    }
}
