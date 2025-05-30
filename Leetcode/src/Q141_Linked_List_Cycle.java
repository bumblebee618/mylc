
/****
 * 
Given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space?
 * 
 * */

public class Q141_Linked_List_Cycle 
{
	public boolean hasCycle(ListNode head) 
	{
        if (head == null || head.next == null) 
        {
            return false;
        }

        ListNode fast, slow;
        fast = head.next;    // 注意错开，fast = head.next
        slow = head;
        
        while (fast != null && fast.next != null && fast != slow)
        {
            fast = fast.next.next;
            slow = slow.next;
        } 
        
        return fast == slow;
    }
}
