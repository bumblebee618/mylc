/********
 * 
Given a linked list, swap every two adjacent nodes and return its head.

For example,
	Given 1->2->3->4, you should return the list as 2->1->4->3.

	Your algorithm should use only constant space. 
	You may not modify the values in the list, only nodes itself can be changed.
	
 * 
 * */

public class Q024_Swap_Nodes_in_Pairs {
	public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
        {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curNode = dummy;
        ListNode front = dummy, back = dummy;
        
        while (front != null && front.next != null)
        {
            front = front.next.next;
            back = back.next;
            
            if (front == null)
            {
                break;
            }
            
            ListNode nextFront = front.next;
            curNode.next = front;
            front.next = back;
            back.next = nextFront;
            curNode = curNode.next.next;
            front = back = curNode;
        }
        
        return dummy.next;
    }
}
