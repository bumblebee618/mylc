
public class Q058_Length_of_Last_Word 
{
	public int lengthOfLastWord(String s) 
    {
        if (s == null)
        {
            return 0;
        }
        
        s = s.trim();
        
        if (s.length() == 0)
        {
            return 0;
        }
        
        int start = s.length()-1;
        
        while (start >= 0 && s.charAt(start) != ' ')
        {
            start--;
        }
        
        return s.length() - (start+1);
    }
	
	
	
	
	
	
	/********************************************************/
	// by Jackie using regular express, a little bit slow
	public int lengthOfLastWord2(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        
        String[] strArray = s.split("\\s{1,}");
        if(strArray.length == 0){
        	return 0;
        }
        
        return strArray[strArray.length - 1].length();    
    }
	
	
	
	
	public static void main(String[] args){
		Q058_Length_of_Last_Word t = new Q058_Length_of_Last_Word();
		String str = "   Hello world   ";
		System.out.println(t.lengthOfLastWord2(str));
	}
}
