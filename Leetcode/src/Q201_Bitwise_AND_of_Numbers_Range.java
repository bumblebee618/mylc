public class Q201_Bitwise_AND_of_Numbers_Range {
	public int rangeBitwiseAnd(int m, int n) {
        if (m > n)
        {
            return 0;
        }
        
        int shift = 0;
    
        while (m < n) 
        {
            m >>= 1;
            n >>= 1;
            ++shift;
        }
    
        return m << shift;
    }

	public static void main(String[] args) {
		Q201_Bitwise_AND_of_Numbers_Range t = new Q201_Bitwise_AND_of_Numbers_Range();
		System.out.println(t.rangeBitwiseAnd(5, 9));
	}
}
