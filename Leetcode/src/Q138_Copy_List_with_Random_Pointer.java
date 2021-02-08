import java.util.HashMap;
import java.util.Map;

/*****
 * 
A linked list is given such that each node contains an additional 
random pointer which could point to any node in the list or null.

Return a deep copy of the list.
 * 
 * */

public class Q138_Copy_List_with_Random_Pointer {
	public RandomListNode copyRandomList(RandomListNode head) 
	{
        if (head == null) 
        {
            return null;
        }

        Map<RandomListNode, RandomListNode> map = new HashMap<>();   // 以RandomListNode为key放入map
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode pointer = dummy;
        
        while (head != null) 
        {
        	RandomListNode newNode = map.getOrDefault(head, null);

            if (newNode == null) 
            {
                newNode = new RandomListNode(head.label);
                map.put(head, newNode);
            }
            
            pointer.next = newNode;

            if (head.random != null) 
            {
            	RandomListNode random = map.getOrDefault(head.random, null);

                if (random == null) 
                {
                	random = new RandomListNode(head.random.label);
                    map.put(head.random, random);
                }
                
                newNode.random = random;
            }

            pointer = pointer.next;
            head = head.next;
        }
        
        return dummy.next;
    }
}
