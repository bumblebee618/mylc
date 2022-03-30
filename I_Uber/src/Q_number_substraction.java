

public class Q_number_substraction {
	public String substraction(String num1, String num2) {
		if (num1 == null || num1.length() == 0) {
			return num2;
		} else if (num2 == null || num2.length() == 0) {
			return num1;
		} 
		
		num1 = adjustNum(num1);
		num2 = adjustNum(num2);
		boolean isNegative = false;
		
		if (num1.length() <= num2.length() && num1.compareTo(num2) < 0) {
			String tmp = num1;
			num1 = num2;
			num2 = tmp;
			isNegative = true;
		}
		
		StringBuilder builder = new StringBuilder();
		char[] digits1 = num1.toCharArray();
		char[] digits2 = num2.toCharArray();
		int index1 = digits1.length-1, index2 = digits2.length-1;
		int flag = 0, sum = 0;
		
		while (index1 >= 0 || index2 >= 0) {
			if (index1 >= 0 && index2 >= 0) {
				sum = (digits1[index1--] - '0') - (digits2[index2--] - '0') + flag;
			} else if (index1 >= 0) {
				sum = (digits1[index1--] - '0') + flag;
			} else {
				sum = (digits2[index2--] - '0') + flag;
			}
			
			flag = sum >= 0 ? 0 : -1;
			sum = sum >= 0 ? sum : sum + 10;
			builder.insert(0, sum);
		}
		
		String result = adjustNum(builder.toString());
		return isNegative ? "-".concat(result) : result;
	}
	
	private String adjustNum(String num) {
		num = num.replaceAll(" ", "");
		int start = 0;
		
		while (start < num.length()-1 && num.charAt(start) == '0') {
			start++;
		}
		
		return num.substring(start);
	}
	
	
	
	
	
	/*********************** main ***********************/
	
	public static void main(String[] args) {
		Q_number_substraction test = new Q_number_substraction();
		String num1_1 = "123", num1_2 = "99";
		String num2_1 = "10000", num2_2 = "12";
		String num3_1 = "123", num3_2 = "132";
		String num4_1 = "0", num4_2 = "132";
		String num5_1 = "000 ", num5_2 = "132";
		String num6_1 = "   0  00", num6_2 = "1  3 2";

		System.out.println(test.substraction(num1_1, num1_2));
		System.out.println(test.substraction(num2_1, num2_2));
		System.out.println(test.substraction(num3_1, num3_2));
		System.out.println(test.substraction(num4_1, num4_2));
		System.out.println(test.substraction(num5_1, num5_2));
		System.out.println(test.substraction(num6_1, num6_2));
	}
}
