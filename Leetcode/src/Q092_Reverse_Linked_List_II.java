
public class Q092_Reverse_Linked_List_II {
	// solution 1
	public ListNode reverseBetween(ListNode head, int m, int n) 
    {
        if(head == null || head.next == null || n - m <= 0) 
        {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode start = dummy;
        ListNode end = dummy;
        
        ListNode m_node = dummy;
        ListNode n_node = dummy;
        
        for (int i = 0; i < m - 1; i++) 
        {
            start = start.next;
        }
        
        m_node = start.next;
        
        for (int i = 0; i < n; i++) 
        {
            end = end.next;
        }
        
        n_node = end;
        end = end.next;
        start.next = reverseList(m_node, end);
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
