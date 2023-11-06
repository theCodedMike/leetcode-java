package org.example.leetcode.editor.cn;
//给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
//
// 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "cbaebabacd", p = "abc"
//输出: [0,6]
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
// 
//
// 示例 2: 
//
// 
//输入: s = "abab", p = "ab"
//输出: [0,1,2]
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
// 
//
// 
//
// 提示: 
//
// 
// 1 <= s.length, p.length <= 3 * 10⁴ 
// s 和 p 仅包含小写字母 
// 
//
// Related Topics 哈希表 字符串 滑动窗口 👍 1321 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        //return this.slidingWindow(s, p);
        return this.optimizeSlidingWindow(s, p);
    }

    List<Integer> slidingWindow(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int s_len = s.length();
        int p_len = p.length();
        if (s_len < p_len) {
            return res;
        }

        int[] s_counter = new int[26];
        int[] p_counter = new int[26];
        for (int i = 0; i < p_len; i++) {
            p_counter[p.charAt(i) - 'a']++;
            s_counter[s.charAt(i) - 'a']++;
        }
        if (Arrays.equals(s_counter, p_counter)) {
            res.add(0);
        }

        for (int i = p_len; i < s_len; i++) {
            s_counter[s.charAt(i - p_len) - 'a']--;
            s_counter[s.charAt(i) - 'a']++;

            if (Arrays.equals(s_counter, p_counter)) {
                res.add(i - p_len + 1);
            }
        }

        return res;
    }

    List<Integer> optimizeSlidingWindow(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int s_len = s.length();
        int p_len = p.length();
        if (s_len < p_len) {
            return res;
        }

        int[] counter = new int[26];
        for (int i = 0; i < p_len; i++) {
            counter[s.charAt(i) - 'a']++;
            counter[p.charAt(i) - 'a']--;
        }
        int diff = 0;
        for (int i = 0; i < 26; i++) {
            if (counter[i] != 0) {
                diff++;
            }
        }
        if (diff == 0) {
            res.add(0);
        }

        for (int i = p_len; i < s_len; i++) {
            int l = s.charAt(i - p_len) - 'a';
            if (counter[l] == 1) {
                diff--;
            } else if (counter[l] == 0) {
                diff++;
            }
            counter[l]--;

            int r = s.charAt(i) - 'a';
            if (counter[r] == -1) {
                diff--;
            } else if (counter[r] == 0) {
                diff++;
            }
            counter[r]++;

            if (diff == 0) {
                res.add(i - p_len + 1);
            }
        }

        return res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
