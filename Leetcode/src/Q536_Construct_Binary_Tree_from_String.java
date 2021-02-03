/***
 * 
 * @author jackie
 * 
 * You need to construct a binary tree from a string consisting of parenthesis and integers.

The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.

You always start to construct the left child node of the parent first if it exists.

Example:
Input: "4(2(3)(1))(6(5))"
Output: return the tree root node representing the following tree:

       4
     /   \
    2     6
   / \   / 
  3   1 5   
Note:
There will only be '(', ')', '-' and '0' ~ '9' in the input string.
An empty tree is represented by "" instead of "()".
 */
public class Q536_Construct_Binary_Tree_from_String 
{
	public TreeNode str2tree(String s) 
	{
        if (s == null || s.length() == 0)
        {
            return null;
        }
        
        int index = 0;
        StringBuilder numBuilder = new StringBuilder();
        TreeNode root = null;
        int flag = 1;
        
        while (index < s.length())
        {
            char c = s.charAt(index);
            
            if (Character.isDigit(c))
            {
                numBuilder.append(c);
                
                if (index == s.length()-1)
                {
                    int num = Integer.parseInt(numBuilder.toString()) * flag;
                    root = new TreeNode(num);
                }
                
                index++;
            }
            else if (index == 0)
            {
                switch (c)
                {
                    case '+': index++; break;
                    case '-': index++; flag = -1; break;
                    default: return null;
                }
            }
            else
            {
            	if (c != '(')
                {
                    break;
                }
            	
                int num = Integer.parseInt(numBuilder.toString()) * flag;
                root = new TreeNode(num);
                int leftEnd = -1;
                int rightEnd = -1;

                // left subtree
                leftEnd = findPos(s, index);
            
                if (leftEnd != -1)
                {
                    root.left = str2tree(s.substring(index+1, leftEnd));
                }

                // right subtree
                if (leftEnd != -1 && leftEnd+1 < s.length() && s.charAt(leftEnd+1) == '(')
                {
                    rightEnd = findPos(s, leftEnd+1);
                }
            
                if (rightEnd == s.length()-1)
                {
                    root.right = str2tree(s.substring(leftEnd+2, rightEnd));
                }
            
                break;
            }
        }
        
        return root;
    }
    
	private int findPos(String s, int start)
    {
        int count = 0;
        
        for (int i = start; i < s.length(); i++)
        {
            char c = s.charAt(i);
            
            if (c == '(')
            {
                count++;
            }
            else if (c == ')')
            {
                count--;
            }
            
            if (count == 0)
            {
                return i;
            }
            else if (count < 0)
            {
                return -1;
            }
        }
        
        return -1;
    }
}
