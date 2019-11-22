import java.util.*;

/**
 * 
You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7
 *
 */
public class Le_445_Add_Two_Numbers_II {
	// solution 1, reverse the list
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        
        ListNode head1 = reverseList(l1);
        ListNode head2 = reverseList(l2);
        ListNode newHead = addLists(head1, head2);
        return reverseList(newHead);
    }
    
    private ListNode addLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode node = dummyHead;
        int sum = 0, flag = 0;
        
        while (l1 != null || l2 != null) {
            if (l1 != null && l2 != null) {
                sum = l1.val + l2.val + flag;
                l1 = l1.next;
                l2 = l2.next;
            } else if (l1 != null) {
                sum = l1.val + flag;
                l1 = l1.next;
            } else {
                sum = l2.val + flag;
                l2 = l2.next;
            }
            
            flag = sum / 10;
            sum %= 10;
            node.next = new ListNode(sum);
            node = node.next;
        }
        
        if (flag > 0) {
            node.next = new ListNode(flag);
            node = node.next;
        }
        
        node.next = null;
        return dummyHead.next;
    }
    
    private ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode curNode = head;
        ListNode curNext = head.next;
        ListNode curNextNext = head.next.next;
        
        while (curNextNext != null) {
            curNext.next = curNode;
            curNode = curNext;
            curNext = curNextNext;
            curNextNext = curNextNext.next;
        }
        
        curNext.next = curNode;
        head.next = null;
        return curNext;
    }
    
    
    // solution2: use stack
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        
        Stack<Integer> s1 = new Stack();
        Stack<Integer> s2 = new Stack();
        int sum = 0, flag = 0;
        ListNode dummy = new ListNode(0);
        
        ListNode node = l1;
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        
        node = l2;
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        
        while (!s1.isEmpty() || !s2.isEmpty()) {
            if (!s1.isEmpty() && !s2.isEmpty()) {
                sum = s1.pop() + s2.pop() + flag;
            } else if (!s1.isEmpty()) {
                sum = s1.pop() + flag;
            } else {
                sum = s2.pop() + flag;
            }
            
            flag = sum / 10;
            sum %= 10;
            ListNode newNode = new ListNode(sum);
            newNode.next = dummy.next;
            dummy.next = newNode;
        }
        
        if (flag > 0) {
            ListNode newNode = new ListNode(flag);
            newNode.next = dummy.next;
            dummy.next = newNode;
        }
        
        return dummy.next;
     }
    
    
    
    
    /***************************************** main *****************************************/
    
    public static void main(String[] args) {
    	Le_445_Add_Two_Numbers_II test = new Le_445_Add_Two_Numbers_II();
    	ListNode head1 = new ListNode(7);
    	ListNode node1 = head1;
    	node1.next = new ListNode(2);
    	node1 = node1.next;
    	node1.next = new ListNode(4);
    	node1 = node1.next;
    	node1.next = new ListNode(3);
    	
    	ListNode head2 = new ListNode(5);
    	ListNode node2 = head2;
    	node2.next = new ListNode(6);
    	node2 = node2.next;
    	node2.next = new ListNode(4);
    	
    	ListNode head = test.addTwoNumbers2(head1, head2);
    	
    	
    	
    }
}
