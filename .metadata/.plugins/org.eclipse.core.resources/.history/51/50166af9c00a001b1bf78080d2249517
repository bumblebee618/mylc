
public class Q328_Odd_Even_Linked_List {
	// by Jackie
	public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null){
            return head;
        }

        ListNode dummy1 = new ListNode(0), dummy2 = new ListNode(0);
        ListNode oddStart = dummy1, evenStart = dummy2;
        
        while (head != null)
        {
            oddStart.next = head;
            oddStart = oddStart.next;
            head = head.next;
            
            evenStart.next = head;
            
            if (head != null)
            {
                evenStart = evenStart.next;
                head = head.next;
            }
        }
        
        oddStart.next = dummy2.next;
        return dummy1.next;
    }
}
