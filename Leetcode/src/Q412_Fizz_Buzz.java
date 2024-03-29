import java.util.LinkedList;
import java.util.List;

/***
 * 
 * @author jackie
 * 
 * Write a program that outputs the string representation of numbers from 1 to n.

But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”. For numbers which are multiples of both three and five output “FizzBuzz”.

Example:

n = 15,

Return:
[
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "7",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "14",
    "FizzBuzz"
]
 */
public class Q412_Fizz_Buzz {
	public List<String> fizzBuzz(int n) {
        List<String> result = new LinkedList<>();
        
        if (n <= 0)
        {
            return result;
        }
        
        String[] strs = {"FizzBuzz", "Fizz", "Buzz"};
        
        for (int i = 1; i <= n; i++)
        {
            if (i % 3 == 0 && i % 5 == 0)
            {
                result.add(strs[0]);
            }
            else if (i % 3 == 0)
            {
                result.add(strs[1]);
            }
            else if (i % 5 == 0)
            {
                result.add(strs[2]);
            }
            else
            {
                result.add(Integer.toString(i));
            }
        }
        
        return result;
    }
}
