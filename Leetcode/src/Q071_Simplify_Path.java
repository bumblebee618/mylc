import java.util.Stack;
/***********
 * 
Given an absolute path for a file (Unix-style), simplify it.

For example,
	path = "/home/", => "/home"
	path = "/a/./b/../../c/", => "/c"

 * 
 * */



public class Q071_Simplify_Path {
	/*******************************************
	  (1). String.split & String.join                
	/*******************************************/
	// test case: [/], [/..], [///]
	
	public String simplifyPath(String path) {
        if (path == null || path.length() == 0) {
            return "";
        }
        
        String[] strs = path.split("/");
        Stack<String> stack = new Stack<>();
        
        for (String str : strs) {
            switch (str) {
                case "": 
                case ".": break;
                case "..": {
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                    break;
                }
                default: stack.push(str); 
            }
        }
        
        StringBuilder builder = new StringBuilder();
        
        while (!stack.isEmpty()) {
            builder.insert(0, "/".concat(stack.pop()));
        }
        
        return builder.length() == 0 ? "/" : builder.toString();
    }
	
	
	
	// solution 2:
	public String simplifyPath2(String path) 
	{ 
		if (path.length() <= 1) 
		{
			return path;
		}
		
        Stack<String> stack = new Stack<>();
        
		for (String s : path.split("/")) 
		{
			if (s.equals("..") && !stack.isEmpty()) 
			{
				stack.pop();     // 注意"."表示当前路径，因此简化过程中可以忽略
			} 
			else if (!s.equals(".") && !s.equals("..") && !s.equals("")) 
			{
				stack.push(s);
			}
		}
		
		if (stack.isEmpty())   // 防止 test case: [///]
		{    
			return "/";
		}
		
		StringBuilder builder = new StringBuilder();
        
        while (!stack.isEmpty())
        {
            builder.insert(0, String.format("/%s", stack.pop()));
        }
        
        return builder.toString();
	}
	
	
	
	// solution 3:
	public String simplifyPath_3(String path) { //by other using String.join
	    Stack<String> stack = new Stack<String>();
	    for (String s : path.split("/")) {
	        if (s.equals("..") && !stack.isEmpty())
	            stack.pop();
	        else if (!s.equals(".") && !s.equals("..") && !s.equals(""))
	            stack.push(s);
	    }
	    return "/" + String.join("/", stack);
	}

	// solution 4:
	public String simplifyPath_4(String path) {  //by other
		StringBuffer rst = new StringBuffer();
		int index = 0;
		String[] strings = path.split("/");
		for (String item : strings) {
			if (item.equals("..")) {
				if (index > 0)
					index--;
			} 
			else if ((item.equals(".")) || (item.equals(""))); //do nothing
			else {
				strings[index] = item;
				index++;
			}
		}
		int length = index;
		while (0 != index) {
			rst.append("/");
			rst.append(strings[length - index]);
			index--;
		}	
		return (rst.toString().equals("")) ? "/" : rst.toString();
	}
	
	
	
	
	
	
	
	
	
	
	/*************************** main function **********************************/
	
	public static void main(String[] args){
		Q071_Simplify_Path s = new Q071_Simplify_Path();
		String str = "/a/./b/../../c/";
		System.out.println(s.simplifyPath(str));
		System.out.println(s.simplifyPath2(str));
	}
}
