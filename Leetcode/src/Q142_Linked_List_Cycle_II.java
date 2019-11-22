import java.util.HashSet;
import java.util.Set;
/*****
 * 
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Note: Do not modify the linked list.

Follow up:
	Can you solve it without using extra space?
 * 
 * */

public class Q142_Linked_List_Cycle_II {
	// solution 1: using two pointers, time O(n), space O(1)
	public ListNode detectCycle(ListNode head) {
		if(head == null || head.next == null){
            return null;
        }
        
        ListNode faster = head.next;
        ListNode slower = head;
        
        while(faster != null && faster.next != null && faster != slower){
            faster = faster.next.next;
            slower = slower.next;
        }
        
        if(faster != slower){
            return null;
        }
        
        faster = head;
        slower = slower.next;
        
        while(faster != slower){
            faster = faster.next;
            slower = slower.next;
        }
        
        return faster;
    }
	
	
	
	// solution 2: using hashmap, time O(n), space O(n)
	public ListNode detectCycle2(ListNode head) {
        if (head == null) {
            return null;
        }
        
        Set<ListNode> visited = new HashSet<>();
        ListNode node = head;
        
        while (node != null) {
            if (visited.contains(node)) {
                return node;
            }
            
            visited.add(node);
            node = node.next;
        }
        
        return null;
    }
}
