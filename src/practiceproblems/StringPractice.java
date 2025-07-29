package practiceproblems;

import java.util.HashMap;

public class StringPractice {
	
	public static void main(String[] args) {
//		System.out.println(wordPattern("abba", "dog dog dog dog"));
//		System.out.println(patternMatch("abccddaefg", "cdd"));
	}

	public static boolean wordPattern(String pattern, String s) {
        String[] words=s.split(" ");
        int n=words.length;
        if(pattern.length()!=n) {
        	return false;
        }
        HashMap<Character, String> cmap=new HashMap<>();
        HashMap<String, Character> smap=new HashMap<>();
        for(int i=0;i<n;i++) {
        	char c=pattern.charAt(i);
        	String str=words[i];
        	if(cmap.containsKey(c) && !cmap.get(c).equals(str)) {
        		return false;
        	}
        	if(smap.containsKey(str) && smap.get(str)!=c) {
        		return false;
        	}
        	cmap.put(c,str);
        	smap.put(str, c);
        }
        return true;
    }
}
