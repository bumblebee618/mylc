import java.util.Arrays;
import java.util.Stack;

import javax.swing.plaf.metal.MetalBorders.OptionDialogBorder;

public class Q_Verifying_an_Alien_String {
	public boolean isAlienSorted(String testStr, String orderStr) {
        if (testStr == null || testStr.length() == 0 || orderStr == null || orderStr.length() == 0) {
        	return true;
        }
        
        int[] order = new int[256];
        Arrays.fill(order, -1);
        
        for (int i = 0; i < orderStr.length(); i++) {
        	char c = orderStr.charAt(i);
        	
        	if (order[c] != -1) {
        		return false;
        	}
        	
        	order[c] = i;
        }
        
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < testStr.length(); i++) {
        	char c = testStr.charAt(i);
        	
        	if (order[c] == -1) {
        		continue;
        	}
        	
        	if (!stack.isEmpty() && order[c] < order[stack.peek()]) {
        		return false;
        	}
        	
        	stack.push(c);
        }
        
        return true;
    }
	
	
	
	public static void main(String[] args) {
		Q_Verifying_an_Alien_String test = new Q_Verifying_an_Alien_String();
		
		String orderStr = "654321";
		String testStr1 = "631";
		String testStr2 = "613";
		String testStr3 = "123";
		String testStr4 = "3t3";
		String testStr5 = "abc";
		String testStr6 = "333333";
		
		System.out.println(test.isAlienSorted(testStr1, orderStr));
		System.out.println(test.isAlienSorted(testStr2, orderStr));
		System.out.println(test.isAlienSorted(testStr3, orderStr));
		System.out.println(test.isAlienSorted(testStr4, orderStr));
		System.out.println(test.isAlienSorted(testStr5, orderStr));
		System.out.println(test.isAlienSorted(testStr6, orderStr));
		
		String orderStr2 = "ape"; 
		String testStr7 = "aaaabccccccp";
		String testStr8 = "zzpppppffaaeeeee";
		
		System.out.println(test.isAlienSorted(testStr7, orderStr2));
		System.out.println(test.isAlienSorted(testStr8, orderStr2));
	}
}
