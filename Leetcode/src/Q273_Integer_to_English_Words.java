/***
 * 
 * @author jackie
 *
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

Example 1:

Input: 123
Output: "One Hundred Twenty Three"
Example 2:

Input: 12345
Output: "Twelve Thousand Three Hundred Forty Five"
Example 3:

Input: 1234567
Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
Example 4:

Input: 1234567891
Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"

 */
public class Q273_Integer_to_English_Words {
	private final String[] level1 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

    private final String[] level2 = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    private final String[] level3 = {"", "Thousand", "Million", "Billion"};
    
    public String numberToWords(int num) {
        if (num == 0)
        {
            return "Zero";
        }
        
        StringBuilder builder = new StringBuilder();
        int index = 0;
        
        while (num > 0)
        {
            int part1 = num % 1000;
            num /= 1000;
            String word = insertHelper(part1);
            
            if (word.length() > 0 && index > 0)
            {
                builder.insert(0, String.format("%s ", level3[index]));
            }
            
            builder.insert(0, word);
            index++;
        }
        
        return builder.toString().trim();
    }
    
    private String insertHelper(int num)
    {
        StringBuilder builder = new StringBuilder();
        
        while (num > 0)
        {
            if (num > 99)
            {
                builder.append(level1[num/100]).append(" Hundred ");
                num %= 100;
            }
            else if (num > 19)
            {
                builder.append(level2[num/10]).append(" ");
                num %= 10;
            }
            else
            {
                builder.append(level1[num]).append(" ");
                num = 0;
            }
        }
        
        return builder.toString();
    }
}
