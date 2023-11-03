package org.example.leetcode.editor.cn;
//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
//
// 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "anagram", t = "nagaram"
//输出: true
// 
//
// 示例 2: 
//
// 
//输入: s = "rat", t = "car"
//输出: false 
//
// 
//
// 提示: 
//
// 
// 1 <= s.length, t.length <= 5 * 10⁴ 
// s 和 t 仅包含小写字母 
// 
//
// 
//
// 进阶: 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？ 
//
// Related Topics 哈希表 字符串 排序 👍 857 👎 0


import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

//leetcode submit region begin(Prohibit modification and deletion)
class _242_有效的字母异位词 {
    public boolean isAnagram(String s, String t) {
        //return this.sortThenCompare(s, t);
        return this.useHashMap(s, t);
    }

    boolean sortThenCompare(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] s_chars = s.toCharArray();
        char[] t_chars = t.toCharArray();
        Arrays.sort(s_chars);
        Arrays.sort(t_chars);
        return Arrays.equals(s_chars, t_chars);
    }

    boolean useHashMap(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : t.toCharArray()) {
            if (!map.containsKey(c)) {
                return false;
            }
            Integer count = map.get(c);
            if (count == 0) {
                return false;
            }
            map.put(c, count - 1);
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
