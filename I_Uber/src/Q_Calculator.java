import java.util.Stack;

public class Q_Calculator {
	// solution 1:
	public int calculate(String input) {
		if (input == null || input.length() == 0) {
			return 0;
		}
		
		input = input.replaceAll(" ", "").toLowerCase();
		Stack<Integer> stack = new Stack<>();
		StringBuilder operatorBuilder = new StringBuilder();
		StringBuilder numBuilder = new StringBuilder();
		
		int index = input.length()-1;
		
		while (index >= 0) {
			char c = input.charAt(index);
			
			if (Character.isDigit(c)) {
				numBuilder.insert(0,  c);
			} else if (numBuilder.length() > 0) {
				int num = Integer.parseInt(numBuilder.toString());
				stack.push(num);				
				numBuilder = new StringBuilder();
			}
					
			if (Character.isLetter(c)) {
				operatorBuilder.insert(0, c);
			}
			
			if (c == ',' || c == '(' || index == 0) {			
				if (operatorBuilder.length() > 0) {
					switch (operatorBuilder.toString()) {
						case "add": {
							if (stack.size() < 2) {
								return -1;
							} else {
								int num1 = stack.pop();
								int num2 = stack.pop();
								stack.push(num1 + num2);
							}
							
							break;
						}
						case "sub": {	
							if (stack.size() < 2) {
								return -1;
							} else {
								int num1 = stack.pop();
								int num2 = stack.pop();
								stack.push(num1 - num2);
							}
							
							break;
						}
						default: break;
					}

					operatorBuilder = new StringBuilder();
				} 
			} 
			
			index--;
		}
		
		int result = 0;
		
		while (!stack.isEmpty()) {
			result += stack.pop();
		}
		
		return result;
	}
	
	
	// solution 2:
	public int calculate2(String input) {
		if (input == null || input.length() == 0) {
			return 0;
		}
		
		input = input.replaceAll(" ", "").toLowerCase();
		return helper(input);
	}
	
	private int helper(String input) {
		int index = 0;
		StringBuilder operatorBuilder = new StringBuilder();
		
		while (index < input.length() && Character.isLetter(input.charAt(index))) {
			operatorBuilder.append(input.charAt(index));
			index++;
		}
		
		if (operatorBuilder.length() == 0) {
			return 0;
		}
		
		int start1 = index+1, end1 = findClosePos(input, index);
		
		if (end1 == -1) {
			return 0;
		}
		
		String part1 = input.substring(start1, end1+1);
		String part2 = input.substring(end1+2, input.length()-1);
		
		int num1 = Character.isDigit(part1.charAt(0)) ? Integer.parseInt(part1) : helper(part1);
		int num2 = Character.isDigit(part2.charAt(0)) ? Integer.parseInt(part2) : helper(part2);
		
		return operatorBuilder.toString().equals("add") ? num1 + num2 : num1 - num2;
	}
	
	private int findClosePos(String input, int start) {
		int count = 0;
		
		for (int i = start; i < input.length(); i++) {
			char c = input.charAt(i);
			
			if (c == '(') {
				count++;
			} else if (c == ')') {
				count--;
			}
			
			if (count < 0) {
				break;
			}
			
			if (c == ',' && count == 1) {
				return i-1;
			}
		}
		
		return -1;
	}
	
	
	// solution 3:
	public int calculate3(String input) {
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
	
	
	
	
	/**************************** main ****************************/
	public static void main(String[] args) {
		Q_Calculator test = new Q_Calculator();
		
		String input1 = "Add(1, sub(3, 4))";
		System.out.println(test.calculate(input1));
		System.out.println(test.calculate2(input1));
		
		String input2 = "Add(sub(1234,add(123,334)), add(122,344))";
	    System.out.println(test.calculate(input2));
	    System.out.println(test.calculate2(input2));
	    
	    String input3 = "Add(sub(12,add(1,3)), add(5,6))";
	    System.out.println(test.calculate(input3));
	    System.out.println(test.calculate2(input3));
	}
}