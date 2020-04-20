import javax.xml.crypto.dsig.SignatureProperties;

public class Q000_Perform_String_shift {
	public String stringShift(String s, int[][] shift) {
        if (s == null || s.length() == 0 || shift == null || shift.length == 0 || shift[0].length == 0)
        {
            return s;
        }
        
        int step = 0;
        
        for (int[] move : shift)
        {
            step += (move[0] == 0) ? -move[1] : move[1];
        }
        
        int size = s.length();
        step %= size;
        
        System.out.println(step);
        
        if (step > 0)
        {
            return s.substring(size-step) + s.substring(0, size-step);
        }
        else if (step < 0)
        {
        	step = -step;
            return s.substring(step) + s.substring(0, step);
        }
        else
        {
            return s;
        }
    }

	
	public static void main(String args[])
	{
		Q000_Perform_String_shift test = new Q000_Perform_String_shift();
		String s = "wpdhhcj";
		int[][] shift = {{0,7},{1,7},{1,0},{1,3},{0,3},{0,6},{1,2}};
		System.out.println(test.stringShift(s, shift));
	}
}
