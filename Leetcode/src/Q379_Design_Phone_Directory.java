import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
/*******
 * 
Design a Phone Directory which supports the following operations:

get: Provide a number which is not assigned to anyone.
check: Check if a number is available or not.
release: Recycle or release a number.
Example:

// Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
PhoneDirectory directory = new PhoneDirectory(3);

// It can return any available phone number. Here we assume it returns 0.
directory.get();

// Assume it returns 1.
directory.get();

// The number 2 is available, so return true.
directory.check(2);

// It returns 2, the only number that is left.
directory.get();

// The number 2 is no longer available, so return false.
directory.check(2);

// Release number 2 back to the pool.
directory.release(2);

// Number 2 is available again, return true.
directory.check(2);

 * 
 * */

public class Q379_Design_Phone_Directory 
{
	private Queue<Integer> queue;
    private Set<Integer> occupidedNums;
    private int guid = 0;
    private int capacity = 0;

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public Q379_Design_Phone_Directory(int maxNumbers) 
    {
        queue = new LinkedList<>();
        occupidedNums = new HashSet<>();
        
        if (maxNumbers <= 0)
        {
            return;
        }
        
        capacity = maxNumbers;
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() 
    {
        if (occupidedNums.size() == capacity)
        {
            return -1;
        }
        
        if (queue.isEmpty())
        {
            for (int i = 0; i < 10 && guid < capacity; i++)
            {
                queue.offer(guid++);
            }
        }
        
        int number = queue.poll();
        occupidedNums.add(number);
        return number;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) 
    {
        return !occupidedNums.contains(number);    
    }
    
    /** Recycle or release a number. */
    public void release(int number) 
    {
        if (occupidedNums.contains(number))
        {
            occupidedNums.remove(number);
            queue.offer(number);
        }
    }

}
