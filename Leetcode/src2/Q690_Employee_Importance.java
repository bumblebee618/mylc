import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/***
 * 
 * @author jackie
 * 
 * You are given a data structure of employee information, which includes the employee's unique id, their importance minValue and their direct subordinates' id.

For example, employee 1 is the leader of employee 2, and employee 2 is the leader of employee 3. They have importance minValue 15, 10 and 5, respectively. Then employee 1 has a data structure like [1, 15, [2]], and employee 2 has [2, 10, [3]], and employee 3 has [3, 5, []]. Note that although employee 3 is also a subordinate of employee 1, the relationship is not direct.

Now given the employee information of a company, and an employee id, you need to return the total importance minValue of this employee and all their subordinates.

Example 1:

Input: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
Output: 11
Explanation:
Employee 1 has importance minValue 5, and he has two direct subordinates: employee 2 and employee 3. They both have importance minValue 3. So the total importance minValue of employee 1 is 5 + 3 + 3 = 11.
 

Note:

One employee has at most one direct leader and may have several subordinates.
The maximum number of employees won't exceed 2000.
 */
public class Q690_Employee_Importance 
{
	public int getImportance(List<Employee> employees, int id) 
    {
        if (employees == null || employees.size() == 0 || id < 0)
        {
            return 0;
        }
        
        Map<Integer, Employee> map = new HashMap<>();
        
        for (Employee employee : employees)
        {
            map.put(employee.id, employee);
        }
        
        if (!map.containsKey(id))
        {
            return 0;
        }
        
        Queue<Employee> queue = new LinkedList<>();
        queue.offer(map.get(id));
        int result = 0;
        
        while (!queue.isEmpty())
        {
            Employee node = queue.poll();
            result += node.importance;
            
            for (int subordinate : node.subordinates)
            {
                if (map.containsKey(subordinate))
                {
                    queue.offer(map.get(subordinate));
                }
            }
        }
        
        return result;
    }
	
	class Employee 
	{
	    public int id;
	    public int importance;
	    public List<Integer> subordinates;
	};
}
