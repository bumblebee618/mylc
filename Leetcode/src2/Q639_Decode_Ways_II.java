/***
 * 
 * @author jackie
 * 
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:

'A' -> "1"
'B' -> "2"
...
'Z' -> "26"
To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:

"AAJF" with the grouping (1 1 10 6)
"KJF" with the grouping (11 10 6)
Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".

In addition to the mapping above, an encoded message may contain the '*' character, which can represent any digit from '1' to '9' ('0' is excluded). For example, the encoded message "1*" may represent any of the encoded messages "11", "12", "13", "14", "15", "16", "17", "18", or "19". Decoding "1*" is equivalent to decoding any of the encoded messages it can represent.

Given a string s containing digits and the '*' character, return the number of ways to decode it.

Since the answer may be very large, return it modulo 109 + 7.

 

Example 1:

Input: s = "*"
Output: 9
Explanation: The encoded message can represent any of the encoded messages "1", "2", "3", "4", "5", "6", "7", "8", or "9".
Each of these can be decoded to the strings "A", "B", "C", "D", "E", "F", "G", "H", and "I" respectively.
Hence, there are a total of 9 ways to decode "*".
Example 2:

Input: s = "1*"
Output: 18
Explanation: The encoded message can represent any of the encoded messages "11", "12", "13", "14", "15", "16", "17", "18", or "19".
Each of these encoded messages have 2 ways to be decoded (e.g. "11" can be decoded to "AA" or "K").
Hence, there are a total of 9 * 2 = 18 ways to decode "1*".
Example 3:

Input: s = "2*"
Output: 15
Explanation: The encoded message can represent any of the encoded messages "21", "22", "23", "24", "25", "26", "27", "28", or "29".
"21", "22", "23", "24", "25", and "26" have 2 ways of being decoded, but "27", "28", and "29" only have 1 way.
Hence, there are a total of (6 * 2) + (3 * 1) = 12 + 3 = 15 ways to decode "2*".
 

Constraints:

1 <= s.length <= 105
s[i] is a digit or '*'.
 */
public class Q639_Decode_Ways_II 
{
	// 分类讨论
	public int numDecodings(String s) 
    {
        if (s == null || s.length() == 0)
        {
            return 0;
        }
        else if (s.charAt(0) == '0')
        {
            return 0;
        }
        
        int size = s.length();
        long[] ways = new long[size+1];
        ways[0] = 1;
        ways[1] = s.charAt(0) == '*' ? 9 : 1;
        int mod = 1_000_000_007;
        
        for (int i = 2; i <= size; i++)
        {
            char curChar = s.charAt(i-1);
            char prevChar = s.charAt(i-2);
            
            if (curChar == '*')
            {
                ways[i] = ways[i-1] * 9;
                
                switch (prevChar)
                {
                    case '*': ways[i] += ways[i-2] * 15; break;
                    case '1': ways[i] += ways[i-2] * 9; break;
                    case '2': ways[i] += ways[i-2] * 6; break;
                    default: break;
                }
            }
            else if (curChar == '0')
            {
                switch (prevChar)
                {
                    case '*': ways[i] = ways[i-2] * 2; break;
                    case '1': 
                    case '2': ways[i] = ways[i-2]; break;
                    default: return 0;
                }
            }
            else if (curChar >= '1' && curChar <= '6')
            {
                ways[i] = ways[i-1];
                
                switch (prevChar)
                {
                    case '*': ways[i] += ways[i-2] * 2; break;
                    case '1':
                    case '2': ways[i] += ways[i-2]; break;
                    default: break;
                }
            }
            else
            {
                ways[i] = ways[i-1];
                
                switch (prevChar)
                {
                    case '*': 
                    case '1': ways[i] += ways[i-2]; break;
                    default: break;
                }
            }
            
            ways[i] %= mod;
        }
        
        return (int) ways[size];
    }
}
