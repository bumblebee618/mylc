import java.util.*;

/*******
 * 
 * Design and implement a data structure for Least Recently Used (LRU) cache. 
 * It should support the following operations: get and set.
 * 
 * get(key) - Get the minValue (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * set(key, minValue) - Set or insert the minValue if the key is not already present. When the cache reached its capacity, 
 * it should invalidate the least recently used item before inserting a new item.
 * 
 * 
 **/

public class Q146_LRU_Cache {
	private CacheNode head, tail;
    private int capacity;
    private Map<Integer, CacheNode> map;
    
    public Q146_LRU_Cache(int capacity) {
        if (capacity <= 0) {
            return;
        }
        
        this.capacity = capacity;
        head = new CacheNode(-1, -1);
        tail = new CacheNode(-1, -1);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        
        // remove current
        CacheNode node = map.get(key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        
        // move current to tail
        moveToHead(node);
        return node.value;
    }
    
    public void put(int key, int value) {
    	// 这里必须用get(key)而不是map.containsKey(key)， 因为相当于访问过，需要做move_to_tail操作
        if (get(key) != -1) {
            map.get(key).value = value;
            return;
        }
        
        if (map.size() == capacity) {
            CacheNode delete = tail.prev;
            map.remove(delete.key);  // 必须先remove，否则head.next改变了 ！！！
            tail.prev = delete.prev;
            delete.prev.next = tail;
        }
        
        CacheNode insert = new CacheNode(key, value);
        map.put(key, insert);
        moveToHead(insert);
    }
    
    private void moveToHead(CacheNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }
    
    class CacheNode {
        public CacheNode prev, next;
        public int key, value;
        
        public CacheNode(int k, int v) {
            key = k;
            value = v;
            prev = next = null;
        }
    }
    
    
    
    
    
    
    /********************************** main **********************************/
    
    // class Amazon_OA2_LRU_Cache_Count_Miss
    public int Solution(int[] array, int size) {
		if (array == null){
			return 0;
		}
		
		List<Integer> cache = new LinkedList<Integer>();   // need to use linkedlist
		int count = 0;
		
		for (int i = 0; i < array.length; i++) {
			if (cache.contains(array[i])) {
				cache.remove(new Integer(array[i]));    // remove the number "i"，not the number whose index is i ！！！
			} else {
				count++;
				
				if (size == cache.size()){
					cache.remove(0);
				}
			}
			
			cache.add(array[i]);      // each element should be added to the cache ！！！
		}
		
		return count;
	}
}
