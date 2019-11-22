import java.util.*;
/******
 * 
 * 
 * *4, 8, 12, *13, *25, 
 * 33, *48, 53, *54, 71, 
 * 73, 75, 79, 91, 106, *114,
 * 117, *124, 141, 151, *168, 
 * 204, 212, 213, 215, *218, 
 * 236, *238, *285, *300, *333, 
 * 337, *348, 365, 402, *419, 
 * 445, 452, 513, *642, *647, 
 * *650, *651, *722, 794
 * 
 * 212, 218, 300, 337, 348, 365, 419, 
 * 
 * Design: 
 * 			
 * 
 * 
 * */

public class A_Data_Structure {

}

class RandomListNode {
	int label;
	RandomListNode next, random;
	RandomListNode(int x) { this.label = x; }
};

class TreeNode {
	int val;
	TreeNode left, right;
	
	public TreeNode (int value) {
		this.val = value;
		left = right = null;
	}
}

class Point {
	int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class ListNode {
	int val;
	ListNode next;
	
	ListNode(int x) { val = x; }
}

class Connection{
	String city1;
	String city2;
	int cost;
	
	public Connection(String c1, String c2, int c){
		city1 = c1;
		city2 = c2;
		cost = c;
	}
	
	@Override
	public String toString() {
		return city1 + " " + city2 + " " + cost;
	}
}

class Interval {
	int start;
	int end;
	Interval() { start = 0; end = 0; }
	Interval(int s, int e) { start = s; end = e; }
}

class UndirectedGraphNode {
	int label;
	List<UndirectedGraphNode> neighbors;
	UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
};


