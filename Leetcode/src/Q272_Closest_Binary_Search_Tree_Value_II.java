import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
/*****
 * 
Given a non-empty binary search tree and a target minValue, find k values in the BST that are closest to the target.

Note:
	Given target minValue is a floating point.
	You may assume k is always valid, that is: k ≤ total nodes.
	You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
	
Follow up:
	Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
 * 
 * */

public class Q272_Closest_Binary_Search_Tree_Value_II 
{
	// solution 1: time O(n), space O(n)
	public List<Integer> closestKValues(TreeNode root, double target, int k) 
    {
        List<Integer> result = new LinkedList<Integer>();
        
        if (root == null || k <= 0)
        {
            return result;
        }
        
        // dfs, time O(n), space O(n)
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<Integer>();
        
        while (root != null || !stack.isEmpty())
        {
            while (root != null)
            {
                stack.push(root);
                root = root.left;
            }
            
            root = stack.pop();
            list.add(root.val);
            root = root.right;
        }
        
        // find the start position, time O(n)
        int end = 0;
        
        while (end < list.size())
        {
            if ((double) list.get(end) > target)
            {
                break;
            }
            
            end++;
        }
        
        int start = end-1;
        
        // get k result, time O(k)
        for (int i = 0; i < k; i++)
        {
            if (start >= 0 && end < list.size())
            {
                double diff1 = Math.abs((double) list.get(start) - target);
                double diff2 = Math.abs((double) list.get(end) - target);
                
                if (diff1 < diff2)
                {
                    result.add(list.get(start--));
                }
                else
                {
                    result.add(list.get(end++));
                }
            }
            else if (start >= 0)
            {
                result.add(list.get(start--));
            }
            else
            {
                result.add(list.get(end++));
            }
        }
        
        return result;
    }

	
	
	// solution 2:  using heap, time complexity O(nlogk + klogk)
	public List<Integer> closestKValues2(TreeNode root, double target, int k) 
    {
        List<Integer> result = new LinkedList<>();
        
        if(root == null || k <= 0) 
        {
            return result;
        }
        
        Queue<Integer> heap = new PriorityQueue<Integer>(k, (a, b) -> Double.compare(Math.abs((double) b - target), Math.abs((double) a - target)));
        Stack<TreeNode> stack = new Stack<>();
        
        // inorder traversal, this part total time is O(n*logk)
        while (root != null || !stack.isEmpty()) 
        {
            while (root != null) 
            {
                stack.push(root);
                root = root.left;
            }
            
            root = stack.pop();
            heap.offer(root.val);   // O(logk)
            
            if (heap.size() > k)   // O(logk)
            {     
                heap.poll();
            }
            
            root = root.right;
        }
        
        while (!heap.isEmpty())       // O(klogk)
        {
            result.add(0, heap.poll());
        }
        
        return result;
    }
	
    
    
	/******************************************************************************************************************************
     * The idea is to compare the predecessors and successors of the closest node to the target, 
     * we can use two stacks to track the predecessors and successors, then like what we do in merge sort, 
     * we compare and pick the closest one to the target and put it to the result list.
     * As we know, inorder traversal gives us sorted predecessors, whereas reverse-inorder traversal gives us sorted successors.
     * We can use iterative inorder traversal rather than recursion, but to keep the code clean, here is the recursion version.
     * 
     *******************************************************************************************************************************/
    // solution 2: using two stacks + inorder traversal, time complexity O(n + k)
    public List<Integer> closestKValues3(TreeNode root, double target, int k) {
    	  List<Integer> ans = new ArrayList<>();
    	  
    	  if(root == null || k <= 0) {
              return ans;
          }

    	  Stack<Integer> predStack = new Stack<>(); // predecessors
    	  Stack<Integer> succStack = new Stack<>(); // successors

    	  inorder(root, target, false, predStack);
    	  inorder(root, target, true, succStack);
    	  
    	  while (k-- > 0) {
    	    if (predStack.isEmpty()) {
    	      ans.add(succStack.pop());
    	    } else if (succStack.isEmpty()) {
    	      ans.add(predStack.pop());
    	    } else if (Math.abs(predStack.peek() - target) < Math.abs(succStack.peek() - target)) {
    	      ans.add(predStack.pop());
    	    } else {
    	      ans.add(succStack.pop());
    	    }
    	  }
    	  
    	  return ans;
    	}

    	// inorder traversal
    	void inorder(TreeNode root, double target, boolean reverse_flag, Stack<Integer> stack) {
    	  if (root == null) {
    		  return;
    	  }

    	  inorder(reverse_flag ? root.right : root.left, target, reverse_flag, stack);
    	  
    	  // early terminate, no need to traverse the whole tree
    	  if ((reverse_flag && root.val <= target) || (!reverse_flag && root.val > target)) { // 需要注意这里有等号 root.val <= target
    		  return;  
    	  }
    	  
    	  // track the minValue of current node
    	  stack.push(root.val);
    	  inorder(reverse_flag ? root.left : root.right, target, reverse_flag, stack);
    	}
	
	
    
	// follow up: solution 3: using two stack + merge sort, time complexity is O(klogn + k)
	public List<Integer> closestKValues4(TreeNode root, double target, int k) {
        List<Integer> ans = new ArrayList<>();
        
        if(root == null || k <= 0) {
            return ans;
        }
        
        Stack<TreeNode> succStack = new Stack<>();
        Stack<TreeNode> predStack = new Stack<>();
        initialStack(root, target, succStack, true);
        initialStack(root, target, predStack, false);
        
        if(!succStack.isEmpty() && !predStack.isEmpty() && succStack.peek().val == predStack.peek().val) {
            getNextNode(succStack, true);
        }
        
        while(k > 0) {
            if(!succStack.isEmpty() && !predStack.isEmpty()) {
                double diff1 = Math.abs((double) succStack.peek().val - target);
                double diff2 = Math.abs((double) predStack.peek().val - target);
                
                if(diff1 < diff2) {
                    ans.add(getNextNode(succStack, true));        
                } else {
                    ans.add(getNextNode(predStack, false));
                }
            } else if(!succStack.isEmpty()) {
                ans.add(getNextNode(succStack, true));       
            } else {
                ans.add(getNextNode(predStack, false));
            }
            
            k--;
        }
        
        return ans;
    }
    
    public void initialStack(TreeNode root, double target, Stack<TreeNode> stack, boolean successorFlag) {
        if(successorFlag) {
            while(root != null) {
                if(root.val > target) {
                    stack.push(root);
                    root = root.left;
                } else if(root.val < target) {
                    root = root.right;
                } else {
                    stack.push(root);
                    break;
                }
            }
        } else {
            while(root != null) {
                if(root.val > target) {
                    root = root.left;
                } else if(root.val < target) {
                    stack.push(root);
                    root = root.right;
                } else {
                    stack.push(root);
                    break;
                }
            }
        }
    }
    
    public int getNextNode(Stack<TreeNode> stack, boolean successorFlag) {
        TreeNode node = stack.pop();
        int ans = node.val;
        
        if(successorFlag) {
            node = node.right;
            
            while(node != null) {
                stack.push(node);
                node = node.left;
            }
        } else {
            node = node.left;
            
            while(node != null) {
                stack.push(node);
                node = node.right;
            }
        }
        
        return ans;
    }
    
    
    
    
    
    
    
    public static void main(String[] args)
    {
    	Q272_Closest_Binary_Search_Tree_Value_II test = new Q272_Closest_Binary_Search_Tree_Value_II();
    	TreeNode root = new TreeNode(1500000000);
    	root.left = new TreeNode(1400000000);
    	double target = -1500000000;
    	int k = 1;
    	
    	System.out.println(test.closestKValues(root, target, k));
    }
}
