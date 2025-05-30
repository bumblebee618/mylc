import java.util.HashMap;


public class Li_4_1_Tiny_Url 
{
	private static int globalId = 0; 
    private HashMap<Integer, String> idToLongurl; 
    private HashMap<String, Integer> longurlToId;
    
    // base 64: "+" and "/"
    private final String base62Chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final int base = 62;
    private final String urlPrefix = "http://tiny.url/";
    
    public Li_4_1_Tiny_Url()
    {
        idToLongurl = new HashMap<Integer, String>();
        longurlToId = new HashMap<String, Integer>();
    }
    
    /****************** long url to short url ****************/
    
    public String longToShort(String longUrl) 
    {
        if (longurlToId.containsKey(longUrl))
        {
            return urlPrefix + idtoShortKey(longurlToId.get(longUrl));
        } 
        
        globalId++;
        idToLongurl.put(globalId, longUrl);
        longurlToId.put(longUrl, globalId);
        return urlPrefix + idtoShortKey(globalId);
    }
    
    public String idtoShortKey(int id)
    {
        StringBuilder shortKeyBuilder = new StringBuilder();
        
        while (id > 0)
        {
        	shortKeyBuilder.insert(0, base62Chars.charAt(id % base));
            id /= base;
        }
        
        while (shortKeyBuilder.length() < 6)
        {
        	shortKeyBuilder.insert(0, "0");
        }
        
        return shortKeyBuilder.toString();
    }

   
    /****************** short url to long url ****************/

    public String shortToLong(String url) 
    {
        String shortKey = url.substring(urlPrefix.length());
        int id = shortKeytoId(shortKey);
        return idToLongurl.get(id);
    }
    
    public int shortKeytoId(String shortKey)
    {
        int id = 0;
        
        for (int i = 0; i < shortKey.length(); ++i)
        {
            id = id * base + toBase62(shortKey.charAt(i));
        }
        
        return id;
    } 
    
    public int toBase62(char c)
    {
        if (c >= '0' && c <= '9') 
        {
            return c - '0';
        }
        
        if (c >= 'a' && c <= 'z') 
        {
            return c - 'a' + 10;
        }
        
        return c - 'A' + 36;
    }
}
