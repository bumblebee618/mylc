/********
 * 
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5

Show Company Tags
Show Tags
Show Similar Problems
 
 * 
 * */

public class Q025_Reverse_Nodes_in_kGroup {
	public ListNode reverseKGroup(ListNode head, int k) 
    {
        if (head == null || head.next == null || k == 1)
        {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curNode = dummy;
        ListNode front = dummy;
        ListNode curTail = null;
        
        while (front != null)
        {
            for (int count = 0; count < k && front != null; count++)
            {
                front = front.next;
            }
            
            if (front == null)
            {
                break;
            }
            
            ListNode curHead = curNode.next;
            curTail = front.next;
            curNode.next = reverseList(curHead, curTail);
            curNode = curHead;
            front = curNode;             // 每次front 都是从 node开始 ！！！
        }
        
        return dummy.next;
    }
    
    private ListNode reverseList(ListNode head, ListNode tail)
    {     
        if (head == tail || head.next == tail)
        {
            return head;
        }
        
        ListNode current = head;
        ListNode curNext = current.next;
        ListNode curNextNext = curNext.next;
        
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    /****************************** main function **********************************/
    
    public void print(ListNode node){
    	while(node != null){
    		System.out.print(node.val + ", ");
    		node = node.next;
    	}
    	System.out.println();
    }
    
    public static void main(String[] args){
    	Q025_Reverse_Nodes_in_kGroup t = new Q025_Reverse_Nodes_in_kGroup();
    	ListNode head = new ListNode(1);
    	ListNode node = head;
    	node.next = new ListNode(2);
    	node = node.next;
    	node.next = new ListNode(3);
    	node = node.next;
    	node.next = new ListNode(4);    	
    	
    	t.print(head);
    	t.print(t.reverseKGroup(head, 2));    	
    }
}
