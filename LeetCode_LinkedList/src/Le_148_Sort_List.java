
public class Le_148_Sort_List {	
	/***************************
	 *       mergeSort         *
	 ***************************/
	public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);  // 这里使用dummy的原因是防止只有2个节点head->node->null的情况
        dummy.next = head;
        ListNode faster = dummy;
        ListNode slower = dummy;
        
        while (faster != null && faster.next != null) {
            faster = faster.next.next;
            slower = slower.next;
        }
        
        ListNode nextHead = slower.next;
        slower.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(nextHead);
        return mergeList(left, right);
    }
    
    private ListNode mergeList(ListNode left, ListNode right) {
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        }
        
        ListNode dummy = new ListNode(0);
        ListNode traver = dummy;
        
        while (left != null || right != null) {
            if (left != null && right != null) {
                if (left.val < right.val) {
                    traver.next = left;
                    left = left.next;
                } else {
                    traver.next = right;
                    right = right.next;
                }
            } else if (left != null) {
                traver.next = left;
                left = left.next;
            } else {
                traver.next = right;
                right = right.next;
            }
            
            traver = traver.next;
        }
        
        traver.next = null;
        return dummy.next;
    }
	
	
	
	// solution 2: by other using mergeSort
	public ListNode sortList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode fakeHead = new ListNode(0);
        fakeHead.next = head;
        ListNode pre = fakeHead;
        ListNode post = fakeHead;
        
        while (post != null && post.next != null) {  // 找到list的中点
            post = post.next.next;
            pre = pre.next;
        }
        ListNode next = pre.next;
        pre.next = null;
        ListNode a = sortList(next);
        ListNode b = sortList(head);
        return merge(a,b);
    }

    public ListNode merge(ListNode a, ListNode b) {
        if (a == null) return b;
        if (b == null) return a;
        if (a.val > b.val) {
            b.next = merge(a,b.next);   // merge混合很巧妙。。。
            return b;
        }
        else {
            a.next = merge(a.next,b);
            return a;
        }
    }
    
    
    /***************************
	 *       quickSort         *
	 ***************************/
    
}
