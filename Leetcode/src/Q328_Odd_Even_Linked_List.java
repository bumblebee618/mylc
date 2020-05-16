
public class Q328_Odd_Even_Linked_List {
	// by Jackie
	public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null)
        {
            return head;
        }
        
        ListNode dummy1 = new ListNode(0);
        ListNode odd = dummy1;
        ListNode dummy2 = new ListNode(0);
        ListNode even = dummy2;
        int count = 0;
        
        while (head != null)
        {
            if (count % 2 == 0)
            {
                odd.next = head;
                odd = odd.next;
            }
            else
            {
                even.next = head;
                even = even.next;
            }
            
            head = head.next;
            count++;
        }
        
        odd.next = dummy2.next;
        even.next = null;
        return dummy1.next;
    }
}
