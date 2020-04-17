package com.jeason.java.review.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();

        for (String word : strs) {
            String key = this.sortStrChar(word.toCharArray());
            List<String> group = groups.get(key);
            if (group == null) {
                group = new ArrayList<String>();

            }

            group.add(word);
            groups.put(key, group);
        }

        List<List<String>> wordGroups = new ArrayList<>();
        for (String k : groups.keySet()) {
            wordGroups.add(groups.get(k));
        }

        return wordGroups;
    }

    public String sortStrChar(char[] chs) {
        char tmp;
        for (int i = 0; i < chs.length-1; i++) {
            for (int j = 0; j < chs.length-i-1; j++) {
                if (chs[j+1] < chs[j]) {
                    tmp = chs[j];
                    chs[j] = chs[j+1];
                    chs[j+1] = tmp;
                }
            }
        }

        return String.valueOf(chs);
    }

    public static void main(String[] args) {
        GroupAnagrams ga = new GroupAnagrams();
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> groups = ga.groupAnagrams(strs);
        for (List<String> g : groups ) {
            System.out.println(g);
        }
    }
}
