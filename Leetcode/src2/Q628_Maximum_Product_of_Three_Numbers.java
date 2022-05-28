/***
 * 
 * @author jackie
 * 
 * Given an integer array, find three numbers whose product is maximum and output the maximum product.

Example 1:

Input: [1,2,3]
Output: 6
 

Example 2:

Input: [1,2,3,4]
Output: 24
 

Note:

The length of the given array will be in range [3,104] and all elements are in the range [-1000, 1000].
Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.
 */
public class Q628_Maximum_Product_of_Three_Numbers {
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
}
