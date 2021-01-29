import java.util.*;
/******
 * 
Given a non-negative number represented as an array of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.

 * 
 * */

public class Q066_Plus_One {
	public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) {
            return new int[0];
        }
        
        List<Integer> list = new ArrayList<>();
        int len = digits.length;
        int sum = 0, flag = 0;
        
        for (int i = len-1; i >= 0; i--) {
            if (i == len-1) {
                sum = digits[i]+flag+1;
            } else {
                sum = digits[i]+flag;
            }
            
            flag = sum / 10;
            sum %= 10;
            list.add(0, sum);
        }
        
        if (flag > 0) {
            list.add(0, flag);
        }
        
        int[] result = new int[list.size()];
        
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        
        return result;
    }
	
	
	
	
	
	
	/****************************** main function ********************************/
	
	public static void main(String[] args){
		Q066_Plus_One t = new Q066_Plus_One();
		int[] digits = {9};
		int[] ans = t.plusOne(digits);
		
		for(int i = 0; i < ans.length; ++i){
			System.out.print(ans[i]);
		}
	}
}
