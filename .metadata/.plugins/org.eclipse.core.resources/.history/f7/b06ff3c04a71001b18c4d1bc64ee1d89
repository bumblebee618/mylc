/***
 * 
 * @author jackie
 * 
 * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.

You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

Example 1:

Input: 1->2->3->4->5->NULL
Output: 1->3->5->2->4->NULL
Example 2:

Input: 2->1->3->5->6->4->7->NULL
Output: 2->3->6->7->1->5->4->NULL
 

Constraints:

The relative order inside both the even and odd groups should remain as it was in the input.
The first node is considered odd, the second node even and so on ...
The length of the linked list is between [0, 10^4].

 */
public class Q328_Odd_Even_Linked_List {
	public ListNode oddEvenList(ListNode head) 
	{
        if (head == null || head.next == null || head.next.next == null)
        {
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
