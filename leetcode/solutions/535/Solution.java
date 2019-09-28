/**
 * 535	Encode and Decode TinyURL 77.4% Medium
 * Performance: speed=61%, memory=100%
 * todo: is the solution in disscuss (6 random ch) better than mine ?
 */

import java.util.*;

public class Solution {
	Map<Integer, String> map = new HashMap<>();

	// Encodes a URL to a shortened URL.
	public String encode(String longUrl) {
		if (!map.containsKey(longUrl)) map.put(longUrl.hashCode(), longUrl);
		return "" + longUrl.hashCode();
	}

	// Decodes a shortened URL to its original URL.
	public String decode(String shortUrl) {
		return map.get(Integer.parseInt(shortUrl));
	}

	public static void main(String args[]) {
	}
}
