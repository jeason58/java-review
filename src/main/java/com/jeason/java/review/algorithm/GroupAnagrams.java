package com.jeason.java.review.algorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List> group = new HashMap<>();

        for (String word : strs) {

        }

        return null;
    }

    public static String sortStrChar(char[] chs) {
        for (int i = 0; i < chs.length; i--) {
            for (int j = 0; j < chs.length-i-1; j++) {
                if (chs[j+1] < chs[j]) {
                    char tmp = chs[j];
                    chs[j] = chs[j+1];
                    chs[j+1] = tmp;
                }
            }
        }

        return String.valueOf(chs);
    }

    public static boolean wordExists(String word, String chars) {
        if ("".equals(word)) {
            return true;
        }

        String curC = word.substring(0, 1);
        String nextWord = "";
        if (word.length() == 1) {
            nextWord = "";
        } else {
            nextWord = word.substring(1);
        }

        for (int i = 0; i < chars.length(); i++) {
            String ch = chars.substring(i, i + 1);
            if (ch.equals(curC)) {
                return wordExists(nextWord, chars);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String s = "trsab";
        char[] chs = sortStrChar(s.toCharArray());
        System.out.println(chs);
    }
}
