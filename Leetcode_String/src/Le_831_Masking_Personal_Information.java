/**
 * 
We are given a personal information string S, which may represent either an email address or a phone number.

We would like to mask this personal information according to the following rules:


1. Email address:

We define a name to be a string of length ≥ 2 consisting of only lowercase letters a-z or uppercase letters A-Z.

An email address starts with a name, followed by the symbol '@', followed by a name, followed by the dot '.' and followed by a name. 

All email addresses are guaranteed to be valid and in the format of "name1@name2.name3".

To mask an email, all names must be converted to lowercase and all letters between the first and last letter of the first name must be replaced by 5 asterisks '*'.


2. Phone number:

A phone number is a string consisting of only the digits 0-9 or the characters from the set {'+', '-', '(', ')', ' '}. You may assume a phone number contains 10 to 13 digits.

The last 10 digits make up the local number, while the digits before those make up the country code. Note that the country code is optional. We want to expose only the last 4 digits and mask all other digits.

The local number should be formatted and masked as "***-***-1111", where 1 represents the exposed digits.

To mask a phone number with country code like "+111 111 111 1111", we write it in the form "+***-***-***-1111".  The '+' sign and the first '-' sign before the local number should only exist if there is a country code.  For example, a 12 digit phone number mask should start with "+**-".

Note that extraneous characters like "(", ")", " ", as well as extra dashes or plus signs not part of the above formatting scheme should be removed.

 

Return the correct "mask" of the information provided.

 

Example 1:

Input: "LeetCode@LeetCode.com"
Output: "l*****e@leetcode.com"
Explanation: All names are converted to lowercase, and the letters between the
             first and last letter of the first name is replaced by 5 asterisks.
             Therefore, "leetcode" -> "l*****e".
Example 2:

Input: "AB@qq.com"
Output: "a*****b@qq.com"
Explanation: There must be 5 asterisks between the first and last letter 
             of the first name "ab". Therefore, "ab" -> "a*****b".
Example 3:

Input: "1(234)567-890"
Output: "***-***-7890"
Explanation: 10 digits in the phone number, which means all digits make up the local number.
Example 4:

Input: "86-(10)12345678"
Output: "+**-***-***-5678"
Explanation: 12 digits, 2 digits for country code and 10 digits for local number. 
Notes:

S.length <= 40.
Emails have length at least 8.
Phone numbers have length at least 10.
 *
 */
public class Le_831_Masking_Personal_Information {
	// solution 1
		public String maskPII(String S) {
	        if (S == null || S.length() == 0) {
	            return "";
	        }
	        
	        String[] countCode = {"", "+*-", "+**-", "+***-"};
	        int pos = S.indexOf("@");
	        
	        if (pos > 0) {
	            S = S.toLowerCase();
	            return S.charAt(0) + "*****" + S.charAt(pos-1) + S.substring(pos);
	        } else {
	            S = S.replaceAll("[^0-9]", "");
	            return countCode[S.length()-10] + "***-***-" + S.substring(S.length()-4);
	        }
	    }
		
		
	// solution 2
	public String maskPII2(String S) {
        if (S == null || S.length() == 0) {
            return "";
        }
        
        return !Character.isLetter(S.charAt(0)) ? parsePhoneNumber(S) : parseEmail(S);
    }
    
    private String parsePhoneNumber(String str) {
        int index = 0, len = str.length();
        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        
        while (index < len) {
            if (Character.isDigit(str.charAt(index))) {
                temp.append(str.charAt(index));
            }
                
            index++;
        }
        
        if (temp.length() > 10) {
            sb.append("+");
            
            for (int i = 0; i < temp.length() - 10; i++) {
                sb.append("*");
            }
            
            sb.append("-");
        }
        
        sb.append("***-***-").append(temp.substring(temp.length()-4, temp.length()));
        return sb.toString();
    }
    
    private String parseEmail(String str) {
        String[] parts1 = str.split("@");
        parts1[0] = parts1[0].toLowerCase();
        parts1[1] = parts1[1].toLowerCase();
        StringBuilder sb = new StringBuilder();
        sb.append(parts1[0].charAt(0)).append("*****").append(parts1[0].charAt(parts1[0].length()-1)).append("@").append(parts1[1]);
        return sb.toString();
    }
}
