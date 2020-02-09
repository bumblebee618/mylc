import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Given a list of folders, remove all sub-folders in those folders and return in any order the folders after removing.

If a folder[i] is located within another folder[j], it is called a sub-folder of it.

The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters. For example, /leetcode and /leetcode/problems are valid paths while an empty string and / are not.

 

Example 1:

Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
Output: ["/a","/c/d","/c/f"]
Explanation: Folders "/a/b/" is a subfolder of "/a" and "/c/d/e" is inside of folder "/c/d" in our filesystem.
Example 2:

Input: folder = ["/a","/a/b/c","/a/b/d"]
Output: ["/a"]
Explanation: Folders "/a/b/c" and "/a/b/d/" will be removed because they are subfolders of "/a".
Example 3:

Input: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
Output: ["/a/b/c","/a/b/ca","/a/b/d"]
 

Constraints:

1 <= folder.length <= 4 * 10^4
2 <= folder[i].length <= 100
folder[i] contains only lowercase letters and '/'
folder[i] always starts with character '/'
Each folder name is unique.
 */
public class Q1233_Remove_Sub_Folders_from_the_Filesystem {
	private List<String> result = new LinkedList<>();
    private Trie root = new Trie();
    
    // solution 1: use sort+two pointers, time O(nlogn)
    public List<String> removeSubfolders(String[] folder) {
        if (folder == null || folder.length == 0)
        {
            return result;
        }
        
        Arrays.sort(folder);
        int front = 0, back = 0;
        
        while (front < folder.length)
        {
            if (isSubFolder(folder[front], folder[back]))
            {
                front++;
            }
            else
            {
                result.add(folder[back]);
                back = front;
            }
        }
        
        result.add(folder[back]);
        return result;
    }

    private boolean isSubFolder(String folder, String mainFolder)
    {
        if (folder.equals(mainFolder))
        {
            return true;
        }
        
        return !folder.startsWith(mainFolder) ? false : folder.charAt(mainFolder.length()) == '/';
    }
    
    
    
    // solution 2: use Trie
    public List<String> removeSubfolders2(String[] folder) {
        if (folder == null || folder.length == 0)
        {
            return result;
        }
        
        buildTrie(folder);
        searchFile(root, "");
        return result;
    }
    
    private void buildTrie(String[] folder)
    {
        for (String path : folder)
        {
            String[] strs = path.split("/");
            Trie node = root;
            
            for (int i = 1; i < strs.length; i++)
            {
                if (!node.children.containsKey(strs[i]))
                {
                    node.children.put(strs[i], new Trie());
                }
                
                node = node.children.get(strs[i]);
            }
            
            node.isFileName = true;
        }
    }
    
    private void searchFile(Trie node, String path)
    {
        if (node.isFileName)
        {
            result.add(path);
            return;
        }
        
        for (String key : node.children.keySet())
        {
            searchFile(node.children.get(key), path+"/"+key);
        }
    }
    
    class Trie
    {
        public Map<String, Trie> children;
        public boolean isFileName;
        
        public Trie()
        {
            children = new HashMap<>();
            isFileName = false;
        }
    }

}
