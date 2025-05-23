
public class Q203_Remove_Linked_List_Elements {
	public ListNode removeElements(ListNode head, int val) 
    {
        if (head == null)
        {
            return null;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode node = dummy;
        
        while (node.next != null)
        {
            if (node.next.val == val)
            {
                node.next = node.next.next;
            }
            else
            {
                node = node.next;
            }
        }
        
        return dummy.next;
    }
	
	
	
	
	/***************************** main *****************************/ 
	
	public static void main(String[] args){
		ListNode head = new ListNode(1);
		head.Insert(head, 1);
		head.Insert(head, 2);
		Q203_Remove_Linked_List_Elements r = new Q203_Remove_Linked_List_Elements();
		head.Display(r.removeElements(head, 2));
	}
}
