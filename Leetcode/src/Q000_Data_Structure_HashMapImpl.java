import java.util.ArrayList;
import java.util.List;

public class Q000_Data_Structure_HashMapImpl 
{
	private int capacity = 0;
	private int usedSlot = 0;
	private int size = 0;
	List<Tuple>[] lists;
	
	public Q000_Data_Structure_HashMapImpl(int initCapacity)
	{
		this.capacity = initCapacity;
		lists = new List[capacity];
	}
	
	public boolean containsKey(int key)
	{
		return findOrDelete(key, false);
	}
	
	public void put(int key, int value)
	{
		if ((double) usedSlot >= capacity * 0.8)
		{
			rehash();
		}
		
		int pos = findPos(key);
		
		if (lists[pos] == null)
		{
			lists[pos] = new ArrayList<>();
			usedSlot++;
		}
		
		insertNode(key, value, lists[pos]);
	}
	
	public boolean remove(int key)
	{
		return findOrDelete(key, true);
	}
	
	
	
	public int size()
	{
		return size;
	}
	
	
	
	
	private void rehash()
	{
		System.out.println("rehash");
		List<Tuple>[] newLists = new List[capacity*2];
		usedSlot = 0;
		
		for (List<Tuple> list : lists)
		{
			if (list != null)
			{
				int index = 0;
				
				while (index < list.size())
				{
					Tuple tuple = list.get(index);
					int pos = findPos(tuple.key);
					
					if (newLists[pos] == null)
					{
						newLists[pos] = new ArrayList<>();
						usedSlot++;
					}
					
					newLists[pos].add(tuple);
					index++;
				}
			}
		}
		
		lists = newLists;
		capacity *= 2;
	}
	
	private void insertNode(int key, int value, List<Tuple> list)
	{
		int index = 0;
		
		while (index < list.size())
		{
			if (list.get(index).key == key)
			{
				list.get(index).value = value;
				return;
			}
			
			index++;
		}
		
		list.add(new Tuple(key, value));
		size++;
	}
	
	private boolean findOrDelete(int key, boolean isDelete)
	{
		int pos = findPos(key);
		
		if (lists[pos] == null)
		{
			return false;
		}
		
		int index = 0;
		
		while (index < lists[pos].size())
		{
			if (lists[pos].get(index).key == key)
			{
				if (isDelete)
				{
					lists[pos].remove(index);
					size--;
				}
				
				return true;
			}
			else
			{
				index++;
			}
		}
		
		return false;
	}
	
	private int findPos(int key)
	{
		return (key-1) % capacity;
	}
	
	
	
	
	
	/******************************************* main *******************************************/
	public static void main (String[] args)
	{
		Q000_Data_Structure_HashMapImpl map = new Q000_Data_Structure_HashMapImpl(1);
		System.out.println(map.containsKey(1));
		map.put(1, 1);
		map.put(2, 2);
		System.out.println(map.size());
		System.out.println(map.containsKey(1));
		System.out.println(map.remove(1));
		System.out.println(map.size());
		System.out.println(map.remove(1));
		System.out.println(map.size());
		map.put(1, 1);
		map.put(2, 2);
		System.out.println(map.size());
		map.put(3, 3);
		System.out.println(map.size());
	}
}

class Tuple
{
	public int key;
	public int value;
	
	public Tuple(int k, int v)
	{
		key = k;
		value = v;
	}
}
