import java.util.*;

public class Q722_Remove_Comments {
	public List<String> removeComments(String[] source) {
        List<String> result = new LinkedList<>();
        
        if (source == null || source.length == 0) {
            return result;
        }
        
        int len = source.length;
        boolean inblock = false;
        StringBuilder sb = new StringBuilder();
        
        for (String line : source) {
            int lineLen = line.length();
            int index = 0;
            char[] letters = line.toCharArray();
            
            if (!inblock) {
                sb = new StringBuilder();    
            }
            
            while (index < lineLen) {
                if (!inblock && index+1 < lineLen && letters[index] == '/' && letters[index+1] == '*') {
                    inblock = true;
                    index++;
                } else if (inblock && index+1 < lineLen && letters[index] == '*' && letters[index+1] == '/') {
                    inblock = false;
                    index++;
                } else if (!inblock && index+1 < lineLen && letters[index] == '/' && letters[index+1] == '/') {
                    break;
                } else if (!inblock) {
                    sb.append(letters[index]);
                }
                
                index++;
            }
            
            if (!inblock && sb.length() > 0) {
                result.add(sb.toString());
            }
        }
        
        return result;
    }
}
