import java.util.*;

/*******
 * 
Given a singly linked list, determine if it is a palindrome.

Follow up:
	Could you do it in O(n) time and O(1) space?
 * 
 * **/


public class Q234_Palindrome_Linked_List 
{
	// solution 1: time O(n), space O(1)
	public boolean isPalindrome(ListNode head) 
    {
        if (head == null || head.next == null)
        {
            return true;   
        }
        
        ListNode faster = head, slower = head, prev = head;
        
        while (faster != null && faster.next != null)
        {
            faster = faster.next.next;
            prev = slower;
            slower = slower.next;
        }
        
        prev.next = null;
        slower = reverseList(slower);
        
        while (head != null && slower != null)
        {
            if (head.val != slower.val)
            {
                return false;
            }
            
            head = head.next;
            slower = slower.next;
        }
        
        return true;
    }
    
    private ListNode reverseList(ListNode head)
    {
        if (head == null || head.next == null)
        {
            return head;
        }
        
        ListNode current = head, curNext = head.next, curNextNext = head.next.next;
        
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
	
    
    
	// solution 2: using hashmap, time O(n), space O(n)
	public boolean isPalindrome2(ListNode head) 
	{
        if(head == null || head.next == null) {
            return true;
        }
        
        Map<Integer, ListNode> map = new HashMap();
        int index = 0;
        ListNode node = head;
        
        while(node != null) {
            map.put(index, node);
            node = node.next;
            index++;
        }
        
        int len = index;
        
        for(int i = 0; i <= len/2; i++) {
            ListNode n1 = map.get(i);
            ListNode n2 = map.get(len - 1 - i);
            
            if(n1.val != n2.val) {
                return false;
            }
        }
        
        return true;
    }
	
	
	
	
    
    
    /********************************* main function *******************************/
	public static void main(String[] args){
		Q234_Palindrome_Linked_List p = new Q234_Palindrome_Linked_List();
		ListNode head = new ListNode(1);
		head.Insert(head, 1);
		head.Insert(head, 0);
		head.Insert(head, 0);
		head.Insert(head, 1);
		if(p.isPalindrome2(head)) System.out.println("yes");
		else System.out.println("no");
	}
}
