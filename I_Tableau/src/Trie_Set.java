import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Trie_Set 
{
	public Trie root = new Trie("", -1);
	
	public void addInformation(String information)
	{
		String[] infoStrs = information.split(",");
		addInfoToTrie(infoStrs);
	}
	
	private void addInfoToTrie(String[] infoStrs)
	{
		Trie node = root;
		
		for (String info : infoStrs)
		{
			info = info.trim();
			
			if (info.length() == 0)
			{
				continue;
			}
			
			if (!node.children.containsKey(info))
			{
				node.children.put(info, new Trie(info, node.level+1));
			}
			
			node = node.children.get(info);
		}
	}
	
	
	
	
	
	/****************************** main ******************************/ 
	public static void main(String[] args)
	{
		Trie_Set test = new Trie_Set();

		List<String> infoList = new LinkedList<>();
		infoList.add("MFST, Azure, Networking, ms_person1");
		infoList.add("MFST, Azure, Networking, ms_person2");
		infoList.add("MFST, Azure, Computing, ms_person3");
		
		infoList.add("Google, Cloud, Networking, gg_person1");
		infoList.add("Google, Cloud, Computing, gg_person2");
		infoList.add("Google, Youtube, Infra, gg_person3");
		
		infoList.add("Apple, Iphone, Hardware, ap_person1");
		infoList.add("Apple, Iphone, App, ap_person2");
		infoList.add("Apple, Iphone, App,  , ");
		
		
		for (String infos : infoList)
		{
			test.addInformation(infos);
		}
		
		System.out.println(test.root.getString());
	}
}

class Trie
{
	public String node;
	public int level;
	public Map<String, Trie> children;
	
	public Trie(String node, int level)
	{
		this.node = node;
		this.level = level;
		children = new HashMap<>();
	}
	
	public String getString()
	{
		StringBuilder builder = new StringBuilder();
		
		// print parent content
		if (level >= 0 || node.length() > 0)
		{
			for (int i = 0; i < level; i++)
			{
				builder.append("  ");
			}
			
			builder.append(node).append("\n");
		}
		
		for (Trie child : children.values())
		{
			builder.append(child.getString());
		}

		return builder.toString();
	}
}
