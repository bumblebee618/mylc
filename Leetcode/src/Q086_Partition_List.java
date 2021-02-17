/***
 * 
 * @author jackie
 * 
 * Given the head of a linked list and a minValue x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

 

Example 1:


Input: head = [1,4,3,2,5,2], x = 3
Output: [1,2,2,4,3,5]
Example 2:

Input: head = [2,1], x = 2
Output: [1,2]
 

Constraints:

The number of nodes in the tree is in the range [0, 200].
-100 <= Node.val <= 100
-200 <= x <= 200
 */

public class Q086_Partition_List 
{
	public ListNode partition(ListNode head, int x) 
	{
        if (head == null || head.next == null) 
        {
            return head;
        }
        
        ListNode dummy1 = new ListNode(0);
        ListNode dummy2 = new ListNode(0);
        ListNode node1 = dummy1, node2 = dummy2;
        
        while (head != null) 
        {
            if (head.val < x) 
            {
                node1.next = head;
                node1 = node1.next;
            } 
            else 
            {
                node2.next = head;
                node2 = node2.next;
            }
            
            head = head.next;
        }
        
        node1.next = dummy2.next;
        node2.next = null;
        return dummy1.next;
    }
	
	
	
	
	
	public static void main(String[] args){
		Q086_Partition_List t = new Q086_Partition_List();
		ListNode head = new ListNode(2);
		head.next = new ListNode(1);
		ListNode res = t.partition(head, 2);
		
		while(res != null){
			System.out.print(res.val + ", ");
			res = res.next;
		}
	}
}
