/********
 * 
Given a linked list, swap every two adjacent nodes and return its head.

For example,
	Given 1->2->3->4, you should return the list as 2->1->4->3.

	Your algorithm should use only constant space. 
	You may not modify the values in the list, only nodes itself can be changed.
	
 * 
 * */

public class Le_024_Swap_Nodes_in_Pairs {
	public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        ListNode curNode = dummy;
        ListNode front = head.next, back = head;
        
        while (front != null) {
            ListNode nextHead = front.next;
            curNode.next = front;
            front.next = back;
            back.next = nextHead;
            back = back.next;
            curNode = curNode.next.next;
            
            if (back != null) {
                front = back.next;
            } else {
                front = null;
            }
        }
        
        return dummy.next;
    }
}
