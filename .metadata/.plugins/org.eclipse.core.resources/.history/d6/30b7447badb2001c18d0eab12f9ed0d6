public class Q_Calculator {
	public int calculat(String input) {
		if (input == null || input.length() == 0) {
			return 0;
		}
		
		input = input.replaceAll(" ", "").toLowerCase();
		return helper(input, 1);
	}
	
	private int helper(String input, int previousFlag) {
		if (Character.isDigit(input.charAt(0))) {
			int end = 0;
			
			while (end < input.length() && Character.isDigit(input.charAt(end))) {
				end++;
			}

			return end == input.length() 
					? Integer.parseInt(input.substring(0, end))
					: Integer.parseInt(input.substring(0, end)) + previousFlag * helper(input.substring(end+1), previousFlag);
		} else if (input.startsWith("add") || input.startsWith("sub")) {
			int closePos = findEndPos(input, 3);
			int flag = input.startsWith("add") ? 1 : -1;
			
			if (closePos == -1) {
				return -1;
			}
			
			return closePos == input.length()-1
					? helper(input.substring(4, closePos), flag)
					: helper(input.substring(4, closePos), flag) + previousFlag * helper(input.substring(closePos+2), flag);
		} else {
			return -1;
		}
	}
	
	private int findEndPos(String input, int start) {
		int count = 0;
		
		for (int i = start; i < input.length(); i++) {
			char c = input.charAt(i);
					
			if (c == '(') {
				count++;
			} else if (c == ')') {
				count--;
			}
			
			if (count == 0) {
				return i;
			} else if (count < 0) {
				return -1;
			}
		}
		
		return -1;
	}
	
	
	
	
	public static void main(String[] args) {
		Q_Calculator test = new Q_Calculator();
		String input1 = "Add(1, sub(3, 4))";
		System.out.println(test.calculat(input1));
		
		String input2 = "Add(sub(1234,add(123,334)), add(122,344))";
	    System.out.println(test.calculat(input2));
	    
	    String input3 = "Add(sub(12,add(1,3)), add(5,6))";
	    System.out.println(test.calculat(input3));
	}
}
