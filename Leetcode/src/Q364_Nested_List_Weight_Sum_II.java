import java.util.*;
/************
 * 
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Different from the previous question where weight is increasing from root to leaf, now the weight 
is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

Example 1:
	Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)

Example 2:
	Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)

 * 
 * */

public class Q364_Nested_List_Weight_Sum_II {
	// solution 1:
	public int depthSumInverse(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) {
            return 0;
        }
        
        Queue<NestedInteger> queue = new LinkedList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (NestedInteger elem : nestedList) {
            queue.offer(elem);
        }
        
        int level = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            
            for (int i = 0; i < size; i++) {
                NestedInteger curNode = queue.poll();
                
                if (curNode.isInteger()) {
                    map.computeIfAbsent(level, x -> new LinkedList<>()).add(curNode.getInteger());
                } else {
                    for (NestedInteger nextNode : curNode.getList()) {
                        queue.offer(nextNode);
                    }
                }
            }
        }
        
        int result = 0;
        
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            int curLevel = entry.getKey();
            
            for (int elem : entry.getValue()) {
                result += elem * (level - curLevel + 1);
            }
        }
        
        return result;
    }

	
    	
	// solution 2: using iterator
	public int depthSumInverse2(List<NestedInteger> nestedList) {
        if(nestedList == null || nestedList.size() == 0) {
            return 0;
        }
        
        int sum = 0;
        int totalLevel = 1;
        Queue<Pair> queue = new LinkedList();
        List<Pair> list = new ArrayList();
        
        for(NestedInteger node : nestedList) {
            queue.offer(new Pair(node, 1));
        }
        
        while(!queue.isEmpty()) {
            Pair n = queue.poll();
            NestedInteger current = n.node;
            totalLevel = Math.max(totalLevel, n.level);
            
            if(current.isInteger()) {
                list.add(n);
            } else {
                for(NestedInteger next : current.getList()) {
                    queue.offer(new Pair(next, n.level + 1));
                }
            }
        }
        
        for(Pair n : list) {
            sum += n.node.getInteger() * (totalLevel - n.level + 1);
        }
        
        return sum;
    }
    
    class Pair {
        NestedInteger node;
        int level;
        
        public Pair(NestedInteger node, int level) {
            this.node = node;
            this.level = level;
        }
    }
	
    
    
	// solution 3: recursion
	private Stack<Node2> stack = new Stack<Node2>();
    private int totalLevel = 0;
    
    public int depthSumInverse3(List<NestedInteger> nestedList) {
        if(nestedList == null || nestedList.size() == 0){
            return 0;
        }
        
        int ans = 0;
        
        for(NestedInteger item : nestedList){
            dfs2(item, 1);    // 必须从1开始 ！！！
        }
        
        while(!stack.isEmpty()){
            Node2 tempNode = stack.pop();
            ans += tempNode.value * (totalLevel - tempNode.level + 1);
        }
        
        return ans;
    }
    
    public void dfs2(NestedInteger node, int level){
        totalLevel = Math.max(totalLevel, level);
        
        if(node.isInteger() == true){
            stack.push(new Node2(node.getInteger(), level));
        } else {
            for(NestedInteger nextNode : node.getList()){
                dfs2(nextNode, level + 1);
            }
        }
    }
    
    class Node2{
        int value;
        int level;
        
        public Node2(int v, int l){
            value = v;
            level = l;
        }
    }
    
    
    
    
    
    /********************* example ***********************/
    class NestedInteger{
    	public boolean isInteger(){
    		return true;
    	}
    	
    	public int getInteger(){
    		return 1;
    	}
    	
    	public List<NestedInteger> getList(){
    		return new ArrayList<NestedInteger>();
    	}
    }
}
