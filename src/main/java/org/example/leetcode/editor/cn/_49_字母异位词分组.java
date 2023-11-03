package org.example.leetcode.editor.cn;
//给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
//
// 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
//
//
//
// 示例 1:
//
//
//输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
//
// 示例 2:
//
//
//输入: strs = [""]
//输出: [[""]]
//
//
// 示例 3:
//
//
//输入: strs = ["a"]
//输出: [["a"]]
//
//
//
// 提示：
//
//
// 1 <= strs.length <= 10⁴
// 0 <= strs[i].length <= 100
// strs[i] 仅包含小写字母
//
//
// Related Topics 数组 哈希表 字符串 排序 👍 1698 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
public class _49_字母异位词分组 {
    public List<List<String>> groupAnagrams(String[] strs) {
        //return this.sorting(strs);
        return this.counting(strs);
    }

    List<List<String>> sorting(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>(strs.length);

        for (String str: strs) {
            byte[] bytes = str.getBytes();
            Arrays.sort(bytes);
            String key = Arrays.toString(bytes);
            List<String> val = map.getOrDefault(key, new ArrayList<>());
            val.add(str);
            map.put(key, val);
        }

        return map.values().stream().toList();
    }

    List<List<String>> counting(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>(strs.length);

        int[] counter = new int[26];
        for (String str: strs) {
            for (char c : str.toCharArray()) {
                counter[c - 'a']++;
            }
            StringBuilder sb = new StringBuilder(str.length());
            for (int i = 0; i < 26; i++) {
                if (counter[i] != 0) {
                    sb.append(i);
                    counter[i] = 0;
                }
            }
            String key = sb.toString();
            List<String> val = map.getOrDefault(key, new ArrayList<>());
            val.add(str);
            map.put(key, val);
        }

        return map.values().stream().toList();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
