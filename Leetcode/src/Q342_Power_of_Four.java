
public class Q342_Power_of_Four {
	// Solution 1:
	public boolean isPowerOfFour(int n) {
        if (n <= 1) {
            return n == 1;
        }

        int index = 0;

        while (n > 0) {
            if ((n & 1) == 1) {
                break;
            }        

            n >>= 1;
            index++;
        }

        return n == 1 && index % 2 == 0;
    }
	
	
	
	// solution 2:
	public boolean isPowerOfFour2(int n) {
        while (n > 1) {
            if (n % 4 != 0) {
                return false;
            }
            
            n /= 4;
        }
        
        return n == 1;    
    }
	
	
	
	
	// solution 3:
	public boolean isPowerOfFour3(int num) {
        if (num <= 0) {
            return false;
        }
        
        int diff = 0;
        
        while (num > 1 && diff == 0) {
            diff = num % 4;
            num /= 4;
        }
        
        return diff == 0;
    }
}
