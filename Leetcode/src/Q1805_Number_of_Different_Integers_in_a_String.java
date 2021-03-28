import java.util.*;

/***
 * 
 * @author jackie
 * 
 * You are given a string word that consists of digits and lowercase English letters.

You will replace every non-digit character with a space. For example, "a123bc34d8ef34" will become " 123  34 8  34". Notice that you are left with some integers that are separated by at least one space: "123", "34", "8", and "34".

Return the number of different integers after performing the replacement operations on word.

Two integers are considered different if their decimal representations without any leading zeros are different.
 */

public class Q1805_Number_of_Different_Integers_in_a_String 
{
	public int numDifferentIntegers(String word) 
    {
        if (word == null || word.length() == 0)
        {
            return 0;   
        }
        
        Set<String> set = new HashSet<>();
        int size = word.length();
        int front = 0, back = 0;
        
        while (front < size)
        {
            while (front < size && !Character.isDigit(word.charAt(front)))
            {
                front++;
            }
            
            back = front;
            
            while (front < size && Character.isDigit(word.charAt(front)))
            {
                front++;
            }
            
            if (back < size)
            {
            	int start = back;
            	
            	while (start < front && word.charAt(start) == '0')
            	{
            		start++;
            	}
            	
            	String str = (start < front) ? word.substring(start, front) : "0";
        		set.add(str);
            }
        }
        
        return set.size();
    }
}
