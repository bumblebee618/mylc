/*******
 * 
Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:
    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
    
 * 
 * */

public class Q168_Excel_Sheet_Column_Title {
	public String convertToTitle(int n) {
        if (n <= 0) 
        {
        	return null;
        }

        StringBuilder builder = new StringBuilder();

        while (n > 0)
        {
            char c = (char) ((n-1) % 26 + 'A');
            builder.insert(0, c);
            n = (n-1) / 26;
        }

        return builder.toString();
    }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*********************************** main function ***************************************/
	
	public static void main(String[] args){
		Q168_Excel_Sheet_Column_Title t = new Q168_Excel_Sheet_Column_Title();
		System.out.println(t.convertToTitle(728));
	}
}
