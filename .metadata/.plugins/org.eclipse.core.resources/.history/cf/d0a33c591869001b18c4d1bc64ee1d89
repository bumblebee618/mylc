/**
 * 
 Given an array of characters, compress it in-place.

The length after compression must always be smaller than or equal to the original array.

Every element of the array should be a character (not int) of length 1.

After you are done modifying the input array in-place, return the new length of the array.


Follow up:
Could you solve it using only O(1) extra space?


Example 1:
Input:
["a","a","b","b","c","c","c"]

Output:
Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]

Explanation:
"aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".
Example 2:
Input:
["a"]

Output:
Return 1, and the first 1 characters of the input array should be: ["a"]

Explanation:
Nothing is replaced.
Example 3:
Input:
["a","b","b","b","b","b","b","b","b","b","b","b","b"]

Output:
Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].

Explanation:
Since the character "a" does not repeat, it is not compressed. "bbbbbbbbbbbb" is replaced by "b12".
Notice each digit has it's own entry in the array.
Note:
All characters have an ASCII value in [35, 126].
1 <= len(chars) <= 1000.
 *
 */
public class Q443_String_Compression {
	// solution 1: Time complexity is O(n) and space is O(1)
	public int compress(char[] chars) {
        if (chars == null || chars.length == 0)
        {
            return 0;
        }
        else if (chars.length == 1)
        {
            return 1;
        }
        
        int front = 0, back = 0;
        int size = chars.length;
        
        while (front < size)
        {
            char target = chars[front];
            int count = 0;
            
            while (front < size && chars[front] == target)
            {
                front++;
                count++;
            }
            
            chars[back] = target;
            
            if (count > 1)
            {
                for (char c : Integer.toString(count).toCharArray())
                {
                    chars[++back] = c; 
                }
            }
            
            back++;
        }
        
        return back;
    }

	
	// solution 2: two pointers and time is O(n), space is O(n)
	public int compress2(char[] chars) {
        if (chars == null || chars.length == 0) {
            return 0;
        }
        
        int len = chars.length;
        int front = 0, back = 0;
        StringBuilder sb = new StringBuilder();
        
        while (front < len) {
            int count = 0;
            
            while (front < len && chars[front] == chars[back]) {
                front++;
                count++;
            }
            
            sb.append(chars[back]);
            
            if (count > 1) {
                sb.append(count);
            }
            
            back = front;
        }
        
        String result = sb.toString();
        
        for (int i = 0; i < result.length(); i++) {
            chars[i] = result.charAt(i);
        }
        
        return result.length();
    }
	
	
	// solution 3 for follow up: three pointers and time is O(n), space is O(1)
	public int compress3(char[] chars) {
        if (chars == null || chars.length == 0) {
            return 0;
        }
        
        int len = chars.length;
        int front = 0, back = 0;
        int copy = 0;
        
        while (front < len) {
            int count = 0;
            
            while (front < len && chars[front] == chars[back]) {
                front++;
                count++;
            }
            
            chars[copy++] = chars[back];

            if (count > 1) {
                String str = Integer.toString(count);
                
                for (int i = 0; i < str.length(); i++) {
                    chars[copy++] = str.charAt(i);
                }
            }
            
            back = front;
        }
        
        return copy;
    }
}
