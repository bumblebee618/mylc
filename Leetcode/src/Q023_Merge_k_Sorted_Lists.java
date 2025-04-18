import java.util.*;
/******
 * 
 * Merge k sorted linked lists and return it as one sorted list. 
 * Analyze and describe its complexity.
 * 
 * */

public class Q023_Merge_k_Sorted_Lists 
{
	// Solution 1: using priorityQueue
	public ListNode mergeKLists(ListNode[] lists) 
	{
		if (lists == null || lists.length == 0) 
		{
			return null;
		}

		/***
		 * Queue<ListNode> heap = new PriorityQueue<>(size, new
		 * Comparator<ListNode>(){
		 * 
		 * @Override public int compare(ListNode node1, ListNode node2) { return
		 *           node1.val - node2.val; } });
		 ***/

		Queue<ListNode> heap = new PriorityQueue<>((a, b) -> a.val - b.val);

		for (ListNode list : lists) 
		{
			if (list != null) 
			{
				heap.offer(list);
			}
		}

		ListNode dummy = new ListNode(0);
		ListNode pointer = dummy;

		while (!heap.isEmpty()) 
		{
			ListNode node = heap.poll();
			pointer.next = node;
			pointer = pointer.next;

			if (node.next != null) 
			{
				heap.offer(node.next);
			}
		}

		return dummy.next;
	}
		
		
		
	// Solution 2: using divide & conquer
	public ListNode mergeKLists2(ListNode[] lists) 
	{
		if (lists == null || lists.length == 0) 
		{
			return null;
		}

		return mergeList(lists, 0, lists.length-1);
	}

	public ListNode mergeList(ListNode[] lists, int start, int end) 
	{
		if (start > end) 
		{
			return null;
		} 
		else if (start == end) 
		{
			return lists[start];
		}

		int mid = start + (end - start) / 2;
		ListNode leftList = mergeList(lists, start, mid);
		ListNode rightList = mergeList(lists, mid + 1, end);

		ListNode dummy = new ListNode(0);
		ListNode pointer = dummy;

		while (leftList != null || rightList != null) 
		{
			if (leftList != null && rightList != null) 
			{
				if (leftList.val < rightList.val) 
				{
					pointer.next = leftList;
					leftList = leftList.next;
				} 
				else 
				{
					pointer.next = rightList;
					rightList = rightList.next;
				}
			} 
			else if (leftList != null && rightList == null)
			{
				pointer.next = leftList;
				leftList = leftList.next;
			} 
			else 
			{
				pointer.next = rightList;
				rightList = rightList.next;
			}

			pointer = pointer.next;
		}

		pointer.next = null;
		return dummy.next;
	}

	
	
	


	
	
	

	
	
	
	
	
	
	
	/****************************************************/
	
	public ListNode mergeKLists3(List<ListNode> lists) {
		if (lists == null || lists.size() == 0) {
			return null;
		} else if (lists.size() == 1) {
			return lists.get(0);
		}
		
		int length = lists.size();
		int mid = (length - 1) / 2;
		ListNode l1 = mergeKLists3(lists.subList(0, mid + 1));
		ListNode l2 = mergeKLists3(lists.subList(mid + 1, length));

		return merge2Lists(l1, l2);
	}

	ListNode merge2Lists(ListNode list1, ListNode list2) {
		ListNode head = new ListNode(-1);
		ListNode current = head;
		while (list1 != null && list2 != null) {
			if (list1.val < list2.val) {
				current.next = list1;
				list1 = list1.next;
			} else {
				current.next = list2;
				list2 = list2.next;
			}
			current = current.next;
		}
		if (list1 != null) {
			current.next = list1;
		} else {
			current.next = list2;
		}
		return head.next;
	}

	
	
	
	
	
	
	
	/****************************************************/
	// by other using PriorityQueue
	public ListNode mergeKLists4(ListNode[] lists) {
		Queue<ListNode> q = new PriorityQueue<ListNode>(new ListComparator());
		for (ListNode n : lists) {
			if (n != null) {
				q.add(n);
			}
		}
		ListNode head = new ListNode(0), p = head, cur = null;
		while (!q.isEmpty()) {
			cur = q.poll();
			if (cur.next != null)
				q.offer(cur.next);
			p.next = cur;
			p = p.next;
		}
		return head.next;
	}

	class ListComparator implements Comparator<ListNode> {
		@Override
		public int compare(ListNode n1, ListNode n2) {
			return n1.val - n2.val;
		}
	}
}
