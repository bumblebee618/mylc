import java.util.Stack;
/***
 * 
 * @author jackie
 * 
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You may not modify the values in the list's nodes, only nodes itself may be changed.

Example 1:

Given 1->2->3->4, reorder it to 1->4->2->3.
Example 2:

Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 */


public class Q143_Reorder_List {
	// Solution 1: using reverse list, time O(n), space (1)
	public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode faster = dummy, slower = dummy;
        
        while (faster != null && faster.next != null) {
            faster = faster.next.next;
            slower = slower.next;
        }
        
        ListNode nextHead = reverse(slower.next);
        slower.next = null;
        ListNode pointer = dummy;
        
        while (head != null || nextHead != null) {
            if (head != null) {
                pointer.next = head;
                pointer = pointer.next;
                head = head.next;
            }
            
            if (nextHead != null) {
                pointer.next = nextHead;
                pointer = pointer.next;
                nextHead = nextHead.next;
            }
        }
    }
    
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode curNode = head;
        ListNode nextNode = head.next;
        ListNode nextNextNode = head.next.next;
        
        while (nextNextNode != null) {
            nextNode.next = curNode;
            curNode = nextNode;
            nextNode = nextNextNode;
            nextNextNode = nextNextNode.next;
        }
        
        nextNode.next = curNode;
        head.next = null;
        return nextNode;
    }
    
    
    
    
    
    // Solution 2: using stack, , time O(n), space (n)
 	public void reorderList2(ListNode head) 
 	{
         if (head == null || head.next == null)
         {
             return ;
         }
         
         ListNode dummy = new ListNode(0);
         dummy.next = head;
         ListNode fast = dummy, slow = dummy;
         
         while (fast != null && fast.next != null)
         {
             fast = fast.next.next;
             slow = slow.next;
         }
         
         Stack<ListNode> s = new Stack<ListNode>();
         ListNode start = head;
         slow = slow.next;
         
         while (slow != null)
         {
             s.push(slow);
             slow = slow.next;
         }
         
         while (!s.isEmpty())
         {
             ListNode temp = s.pop();
             temp.next = start.next;
             start.next = temp;
             start = start.next.next;
         }

         start.next = null;
     }
    
    
	
    
	
	/***********************************************/
	// by Jackie using stack
	public ListNode reorderList3(ListNode head) {  
        if(head == null || head.next == null){
        	return head;
        }
        Stack<ListNode> s1 = new Stack<>();       
        ListNode front = head, back = head , temp;
        int len = 1;
        while(front.next != null){
        	len++;
        	front = front.next;
        	if(len > 2 && len % 2 == 0)
        		back = back.next;
        }
        back = back.next;
        if(len % 2 != 0) 
        	back = back.next;
        while(back != null){
        	s1.push(back);
        	back = back.next;
        }
        front = head;
        while(!s1.isEmpty()){
        	temp = s1.pop();
        	System.out.println(temp.val);
        	temp.next = front.next;
        	front.next = temp;
        	if(s1.isEmpty()){
        		if(len % 2 != 0) 
        			front = front.next;
        		front = front.next;
        		front.next = null;       		
        	}
        	else{
        		front = front.next.next;
//        		front = front.next;
        	}
        }
        return head;
    }
	
	
	
	public static void main(String[] args){
		Q143_Reorder_List r = new Q143_Reorder_List();
//		ListNode head = new ListNode(1);
//		ListNode node = head;
//		node.next = new ListNode(2);
//		node = node.next;
//		node.next = new ListNode(3);
//		node = node.next;
//		node.next = new ListNode(4);
//		node = node.next;
		
		
		ListNode head = new ListNode(1);
		head.Insert(head, 2);
		head.Insert(head, 3);
		head.Insert(head, 4);
		head.Insert(head, 5);
		head.Insert(head, 6);
		head.Insert(head, 7);
		head.Insert(head, 8);
		head.Insert(head, 9);
		r.reorderList(head);
		head.Display(head);		
	}
}
