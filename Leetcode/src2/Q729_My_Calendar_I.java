import java.util.TreeMap;

/***
 * 
 * @author jackie
 * 
 * Implement a MyCalendar class to store your events. A new event can be added if adding the event will not cause a double booking.

Your class will have the method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.

A double booking happens when two events have some non-empty intersection (ie., there is some time that is common to both events.)

For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a double booking. Otherwise, return false and do not add the event to the calendar.

Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
Example 1:

MyCalendar();
MyCalendar.book(10, 20); // returns true
MyCalendar.book(15, 25); // returns false
MyCalendar.book(20, 30); // returns true
Explanation: 
The first event can be booked.  The second can't because time 15 is already booked by another event.
The third event can be booked, as the first event takes every time less than 20, but not including 20.
 

Note:

The number of calls to MyCalendar.book per test case will be at most 1000.
In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].
 */

public class Q729_My_Calendar_I 
{
	// solution 1: insert time is O(logn)
	TreeMap<Integer, Integer> calendar;

	Q729_My_Calendar_I() 
    {
        calendar = new TreeMap<>();
    }

    public boolean book(int start, int end) 
    {
        if (start > end)
        {
            return false;
        }
        
        Integer prev = calendar.floorKey(start);
        Integer next = calendar.ceilingKey(start);
        
        if ((prev == null || calendar.get(prev) <= start) 
            && (next == null || end <= next)) 
        {
            calendar.put(start, end);
            return true;
        }
        
        return false;
    }
    
    
    /***
    // solution 2: TreeMap + scan line
    private TreeMap<Integer, Integer> calendars;

    public MyCalendar() 
    {
        calendars = new TreeMap<Integer, Integer>();
    }
    
    public boolean book(int start, int end) 
    {
        if (end < start)
        {
            return false;
        }
        
        calendars.put(start, calendars.getOrDefault(start, 0)+1);
        calendars.put(end, calendars.getOrDefault(end, 0)-1);
        int eventCount = 0;
        
        for (Map.Entry<Integer, Integer> entry : calendars.entrySet())
        {
            eventCount += entry.getValue();
            
            if (eventCount == 2)
            {
                calendars.put(start, calendars.getOrDefault(start, 0)-1);
                
                if (calendars.get(start) == 0)
                {
                    calendars.remove(start);
                }
                
                calendars.put(end, calendars.getOrDefault(end, 0)+1);
                
                if (calendars.get(end) == 0)
                {
                    calendars.remove(end);
                }
                
                return false;
            }
        }
        
        return true;
    }
	
	
    // solution 3: insert time is O(n)
	private List<int[]> events;
	
    public Q729_My_Calendar_I() 
    {
        events = new ArrayList<>();
    }
    
    public boolean book(int start, int end) 
    {
        if (start > end)
        {
        	return false;
        }
        
        int pos = findPos(start, end);
        
        if (pos != -1)
        {
        	events.add(pos, new int[] {start, end});
        }
        
        return pos == -1 ? false : true;
    }
    
    private int findPos(int start, int end)
    {
    	if (events.size() == 0 || start >= events.get(events.size()-1)[1])
    	{
    		return events.size();
    	}
    	else if (end <= events.get(0)[0])
    	{
    		return 0;
    	}
    	
    	int index = 0;
    	
    	while (index < events.size())
    	{
    		if (end <= events.get(index)[0])
    		{
    			break;
    		}
    		
    		index++;
    	}
    	
    	return (start >= events.get(index-1)[1] && end <= events.get(index)[0]) ? index : -1;
    }
    ***/
    
    
    
    
    
    
    /******************************* main *******************************/
    
    public static void main(String[] args)
    {
    	Q729_My_Calendar_I test = new Q729_My_Calendar_I();
    	System.out.println(test.book(10, 20));
    	System.out.println(test.book(15, 25));
    }
}
