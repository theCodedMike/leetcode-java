package org.example.leetcode.editor.cn;
//给定一个非空的字符串
// s ，检查是否可以通过由它的一个子串重复多次构成。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "abab"
//输出: true
//解释: 可由子串 "ab" 重复两次构成。
// 
//
// 示例 2: 
//
// 
//输入: s = "aba"
//输出: false
// 
//
// 示例 3: 
//
// 
//输入: s = "abcabcabcabc"
//输出: true
//解释: 可由子串 "abc" 重复四次构成。 (或子串 "abcabc" 重复两次构成。)
// 
//
// 
//
// 提示： 
//
// 
// 
//
// 
// 1 <= s.length <= 10⁴ 
// s 由小写英文字母组成 
// 
//
// Related Topics 字符串 字符串匹配 👍 1085 👎 0


import java.util.function.Function;

//leetcode submit region begin(Prohibit modification and deletion)
class _459_重复的子字符串 {
    public boolean repeatedSubstringPattern(String s) {
        //return this.bruteForce(s);
        return this.kmp(s);
    }

    // Time Complexity: O(n^2)
    //
    // Space Complexity: O(1)
    boolean bruteForce(String s) {
        int len = s.length();

        for (int sub_len = 1; sub_len * 2 <= len; sub_len++) {
            if (len % sub_len == 0) {
                boolean allEqual = true;
                for (int i = sub_len; i < len; i++) {
                    if (s.charAt(i) != s.charAt(i % sub_len)) {
                        allEqual = false;
                        break;
                    }
                }
                if (allEqual) {
                    return true;
                }
            }
        }

        return false;
    }

    Function<String, int[]> getNext = (needle) -> {
        int m = needle.length();
        int[] next = new int[m];

        for (int i = 1, j = 0; i < m; i++) {
            while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                j = next[j - 1];
            }
            if (needle.charAt(i) == needle.charAt(j)) {
                j++;
            }
            next[i] = j;
        }

        return next;
    };

    // Time Complexity: O(n)
    //
    // Space Complexity: O(n)
    boolean kmp(String s) {
        int[] next = this.getNext.apply(s);
        int len = s.length();

        return next[len - 1] != 0 && len % (len - next[len - 1]) == 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
