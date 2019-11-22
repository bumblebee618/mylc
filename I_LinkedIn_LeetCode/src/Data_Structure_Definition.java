import java.util.*;

/******
 * 
 * 
 * 4, 50, 57*, 68*, 127, 
 * 149*, 150, 156*, 170*, 187, 
 * 244*(O(n) 的遍历方法), 277*, 339, 367* (注意long type的使用), 464*, 
 * 647*, 698*, 716
 * 
 * 
 ******/

public class Data_Structure_Definition {
	
}

class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
}

class Interval {
	int start;
	int end;
	Interval() { start = 0; end = 0; }
	Interval(int s, int e) { start = s; end = e; }
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

class UndirectedGraphNode {
	int label;
	List<UndirectedGraphNode> neighbors;
	UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
};