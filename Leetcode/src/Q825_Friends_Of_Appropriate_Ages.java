/***
 * 
 * 
 * @author jackie
 *
 * Some people will make friend requests. The list of their ages is given and ages[i] is the age of the ith person. 

Person A will NOT friend request person B (B != A) if any of the following conditions are true:

age[B] <= 0.5 * age[A] + 7
age[B] > age[A]
age[B] > 100 && age[A] < 100
Otherwise, A will friend request B.

Note that if A requests B, B does not necessarily request A.  Also, people will not friend request themselves.

How many total friend requests are made?

Example 1:

Input: [16,16]
Output: 2
Explanation: 2 people friend request each other.
Example 2:

Input: [16,17,18]
Output: 2
Explanation: Friend requests are made 17 -> 16, 18 -> 17.
Example 3:

Input: [20,30,100,110,120]
Output: 
Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 -> 100.
 

Notes:

1 <= ages.length <= 20000.
1 <= ages[i] <= 120.
 */
public class Q825_Friends_Of_Appropriate_Ages {
	public int numFriendRequests(int[] ages) {
        if (ages == null || ages.length == 0)
        {
            return 0;
        }
        
        int[] counts = new int[121];
        int result = 0;
        
        for (int age : ages)
        {
            counts[age]++;
        }
        
        for (int age1 = 0; age1 <= 120; age1++)
        {
            for (int age2 = 0; age2 <= 120; age2++)
            {
                if (isValid(age1, age2))
                {
                    result += counts[age1] * counts[age2];
                    
                    if (age1 == age2)
                    {
                        result -= counts[age1];
                    }
                }
            }
        }
        
        return result;
    }
    
    private boolean isValid(int age1, int age2)
    {
        return (age1 * 0.5 + 7 >= age2 || age1 < age2 || (age1 < 100 && 100 < age1)) ? false : true;
    }
}
