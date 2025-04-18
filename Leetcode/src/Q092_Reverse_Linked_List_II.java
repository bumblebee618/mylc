/***
 * 
 * @author jackie
 * Reverse a linked list from position m to n. Do it in one-pass.

Note: 1 ≤ m ≤ n ≤ length of list.

Example:

Input: 1->2->3->4->5->NULL, m = 2, n = 4
Output: 1->4->3->2->5->NULL

 */

public class Q092_Reverse_Linked_List_II {
	// solution 1
	public ListNode reverseBetween(ListNode head, int m, int n) 
    {
        if (head == null || head.next == null || n - m <= 0) 
        {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode start = dummy;
        ListNode m_node = null;
        ListNode n_node = dummy;
        
        for (int i = 0; i < m-1 && start != null; i++)
        {
            start = start.next;
        }
        
        if (start == null)
        {
        	return head;
        }
        
        m_node = start.next;
        
        for (int i = 0; i < n && n_node != null; i++)
        {
        	n_node = n_node.next;
        }
        
        if (n_node == null)
        {
        	return head;
        }
        
        ListNode tail = n_node.next;
        start.next = reverseList(m_node, tail);
        return dummy.next;
    }
    
    private ListNode reverseList(ListNode head, ListNode tail) 
    {
        if (head == tail || head.next == tail) 
        {
            return head;
        }
        
        ListNode current = head;
        ListNode curNext = head.next;
        ListNode curNextNext = head.next.next;
        
        while (curNextNext != tail) 
        {
            curNext.next = current;
            current = curNext;
            curNext = curNextNext;
            curNextNext = curNextNext.next;
        }
        
        curNext.next = current;
        head.next = tail;
        return curNext;
    }
    
    
    
	
    
    
    
	// solution 2
	public ListNode reverseBetween2(ListNode head, int m, int n) {
        if(head == null || head.next == null || n - m <= 0) return head;
        ListNode start_pos = new ListNode(0), current, cur_next, r;
        ListNode new_head = start_pos;
        start_pos.next = head;
        
        for(int i = 1; i < m; i++)
            start_pos = start_pos.next;
        current = start_pos.next;
        cur_next = current.next;
        for(int i = 0; i < (n-m); i++){
            r = cur_next.next;
            cur_next.next = current;
            current = cur_next;
            cur_next = r;
        }
        start_pos.next.next = cur_next;
        start_pos.next = current;
        return new_head.next;
    }
}
