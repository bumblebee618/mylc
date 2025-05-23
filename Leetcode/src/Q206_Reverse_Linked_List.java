/******
 * 
 * Reverse a singly linked list.
 * 
 * */


public class Q206_Reverse_Linked_List 
{
	public ListNode reverseList(ListNode head) 
	{
        if (head == null || head.next == null) 
        {
        	return head;
        }
        
        ListNode current = head;
        ListNode curNext = current.next;
        ListNode curNextNext = curNext.next;
        
        while (curNextNext != null)
        {
        	curNext.next = current;
        	current = curNext;
        	curNext = curNextNext;
        	curNextNext = curNextNext.next;
        }
        
        curNext.next = current;
        head.next = null;
        return curNext;
    }
}
