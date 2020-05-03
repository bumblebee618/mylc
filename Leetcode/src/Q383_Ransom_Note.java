
public class Q383_Ransom_Note {
	public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote == null || magazine == null)
        {
            return true;
        }
        else if (ransomNote.length() > magazine.length())
        {
            return false;
        }
        
        int[] hash = new int[256];
        
        for (char c : magazine.toCharArray())
        {
            hash[c]++;
        }
        
        for (char c : ransomNote.toCharArray())
        {
            if (hash[c]-- == 0)
            {
                return false;
            }
        }
        
        return true;
    }
}
