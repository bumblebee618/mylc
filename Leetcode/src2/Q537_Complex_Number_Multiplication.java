/***
 * 
 * @author jackie
 * 
 * A complex number can be represented as a string on the form "real+imaginaryi" where:

real is the real part and is an integer in the range [-100, 100].
imaginary is the imaginary part and is an integer in the range [-100, 100].
i2 == -1.
Given two complex numbers num1 and num2 as strings, return a string of the complex number that represents their multiplications.

 

Example 1:

Input: num1 = "1+1i", num2 = "1+1i"
Output: "0+2i"
Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
Example 2:

Input: num1 = "1+-1i", num2 = "1+-1i"
Output: "0+-2i"
Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
 

Constraints:

num1 and num2 are valid complex numbers.
 */
public class Q537_Complex_Number_Multiplication {
	public String complexNumberMultiply(String num1, String num2) {
        String[] strs1 = num1.split("\\+");
        String[] strs2 = num2.split("\\+");
        
        int a1 = Integer.parseInt(strs1[0]);
        int a2 = Integer.parseInt(strs1[1].replace("i",""));

        int b1 = Integer.parseInt(strs2[0]);
        int b2 = Integer.parseInt(strs2[1].replace("i",""));

        int part1 = a1 * b1;
        int part2 = a2 * b2;
        int part3 = (a1 * b2) + (a2 * b1);
        int p1 = (part1 + (-1 * part2));
        
        return new StringBuilder()
            .append(p1)
            .append("+")
            .append(part3)
            .append("i")
            .toString();
    }
}
