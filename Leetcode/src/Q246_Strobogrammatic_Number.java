import java.util.*;

/********
 * 
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

For example, the numbers "69", "88", and "818" are all strobogrammatic.
 
 * 
 * 
 * */

public class Q246_Strobogrammatic_Number {
	// solution 1:
	public boolean isStrobogrammatic(String num) 
    {
        if (num == null || num.length() == 0) 
        {
            return false;
        }
        
        Map<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');
        
        Set<Character> set = new HashSet<>();
        set.add('0');
        set.add('1');
        set.add('8');
        
        int left = 0, right = num.length()-1;
        
        while (left < right)
        {
            char c1 = num.charAt(left);
            char c2 = num.charAt(right);
            
            if (!map.containsKey(c1) || map.get(c1) != c2)
            {
                return false;
            }
            
            left++;
            right--;
        }
        
        return left == right ? set.contains(num.charAt(left)) : true;
    }

	
	
	
	// solution 2:
	public boolean isStrobogrammatic2(String num) {
        if (num == null || num.length() == 0) {
            return false;
        }
        
        String str1 = "01689";
        String str2 = "01986";
        String str3 = "018";
        int left = 0, right = num.length() - 1;
        
        while(left < right) {
            char digit1 = num.charAt(left);
            char digit2 = num.charAt(right);
            int index = str1.indexOf(digit1);
            
            if(index == -1 || str2.charAt(index) != digit2) {
                return false;
            }
            
            left++;
            right--;
        }
        
        if(left == right) {
            return str3.indexOf(num.charAt(left)) != -1;
        } else {
            return true;
        }
    }
}
