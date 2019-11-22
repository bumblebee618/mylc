import java.util.*;
/**
 * 
 TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.

Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to 
a tiny URL and the tiny URL can be decoded to the original URL.
 *
 */
public class Le_535_Encode_and_Decode_TinyURL {
	private String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private Map<Integer, String> idToUrl = new HashMap<>();
    private Map<String, Integer> urlToId = new HashMap<>();
    private int globalId = 0;
    
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if (longUrl == null || longUrl.length() == 0) {
            return "";
        } else if (urlToId.containsKey(longUrl)) {
            return "http://tinyurl.com/" + idToShort(urlToId.get(longUrl));
        } else {
            globalId++;
            idToUrl.put(globalId, longUrl);
            urlToId.put(longUrl, globalId);
            return "http://tinyurl.com/" + idToShort(globalId);
        }
    }
    
    private String idToShort(int id) {
        StringBuilder sb = new StringBuilder();
        
        while (id > 0) {
            sb.append(chars.charAt(id % 62));
            id /= 62;
        }
        
        while (sb.length() < 6) {
            sb.insert(0, "0");
        }
        
        return sb.toString();
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        if (shortUrl == null || shortUrl.length() == 0) {
            return "";
        }
        
        String shortKey = shortUrl.substring("http://tinyurl.com/".length());
        int id = shortToId(shortKey);
        return idToUrl.get(id);
    }
    
    private int shortToId(String shortUrl) {
        int id = 0;
        
        for (char c : shortUrl.toCharArray()) {
            id = id * 62 + getBase62(c);
        }
        
        return id;
    }
    
    private int getBase62(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        } else if (c >= 'a' && c <= 'z') {
            return c - 'a' + 10;
        } else if (c >= 'A' && c <= 'Z') {
            return c - 'A' + 36;
        } else {
            return 0;
        }
    }
}
