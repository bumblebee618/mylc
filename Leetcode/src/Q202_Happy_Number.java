/*****
 * 
 * Write an algorithm to determine if a number n is happy.

A happy number is a number defined by the following process:

Starting with any positive integer, replace the number by the sum of the squares of its digits.
Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
Those numbers for which this process ends in 1 are happy.
Return true if n is a happy number, and false if not.

 

Example 1:

Input: n = 19
Output: true
Explanation:
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
Example 2:

Input: n = 2
Output: false
 

Constraints:

1 <= n <= 231 - 1
 * 
 */

import java.util.HashSet;
import java.util.Set;


public class Q202_Happy_Number {
	/******************************************************/
	public boolean isHappy(int n) 
	{
        if (n <= 0)
        {
            return false;
        }
        
        Set<Integer> visited = new HashSet<>();
        
        while (n != 1)
        {
            n = getSum(n);
            
            if (visited.contains(n))
            {
                return false;
            }
            else
            {
                visited.add(n);
            }
        }
        
        return true;
    }
    
    private int getSum(int n)
    {
        int sum = 0;
        
        while (n > 0)
        {
            int digit = n % 10;
            n /= 10;
            sum += digit*digit;
        }
        
        return sum;
    }

    
    
    
    /******************************************************/
	// by Jackie     
    public boolean isHappy2(int n) {
        if(n <= 0)
        {
            return false;
        }
        
        Set<Integer> set = new HashSet();
        set.add(n);
        
        while(n != 1)
        {
            int sum = 0;
            int a = 0;
            
            while(n > 0)
            {
                a = n % 10;
                sum += a * a;
                n /= 10;
            }
            
            if(set.contains(sum))
            {
                return false;
            }
            
            set.add(sum);
            n = sum;
        }
        
        return true;
    }
    
    
    
    /******************************************************/
	// by Jackie 
    public boolean isHappy3(int n) {
        if(n == 0){
            return false;
        } 
        
        n = Math.abs(n);
        Set<Integer> visited = new HashSet<Integer>();
        visited.add(n);
        
        while(n != 1){
            int num = getNextInteger(n);
            
            if(num == -1){
            	System.out.println("1");
                return false;
            } else if(visited.contains(num)){
            	System.out.println("2: " + num);
                return false;
            } else {
                n = num;
                visited.add(num);
            }
        }
        
        return true;
    }
    
    public int getNextInteger(int n){
        long sum = 0;
        
        while(n > 0){
            int digit = n % 10;
            n /= 10;
            sum += digit * digit;
            
            if(sum > Integer.MAX_VALUE){
                return -1;
            }
        }
        
        return (int) sum;
    }
    
    
    public static void main(String[] args){
    	Q202_Happy_Number t = new Q202_Happy_Number();
    	System.out.println(t.isHappy3(7));
    }
}
