package org.example.leetcode.editor.cn;
//给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。
//如果 needle 不是 haystack 的一部分，则返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//输入：haystack = "sadbutsad", needle = "sad"
//输出：0
//解释："sad" 在下标 0 和 6 处匹配。
//第一个匹配项的下标是 0 ，所以返回 0 。
// 
//
// 示例 2： 
//
// 
//输入：haystack = "leetcode", needle = "leeto"
//输出：-1
//解释："leeto" 没有在 "leetcode" 中出现，所以返回 -1 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= haystack.length, needle.length <= 10⁴ 
// haystack 和 needle 仅由小写英文字符组成 
// 
//
// Related Topics 双指针 字符串 字符串匹配 👍 2087 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
import java.util.function.Function;

class _28_找出字符串中第一个匹配项的下标 {
    public int strStr(String haystack, String needle) {
        //return this.bruteForce(haystack, needle);
        return this.kmp(haystack, needle);
    }

    // Time Complexity: O(m * n)
    //
    // Space Complexity: O(1)
    int bruteForce(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();


        for (int end = m; end <= n; end++) {
            boolean allEqual = true;
            for (int i = end - m; i < end; i++) {
                if (haystack.charAt(i) != needle.charAt(i - end + m)) {
                    allEqual = false;
                    break;
                }
            }
            if (allEqual) {
                return end - m;
            }
        }

        return -1;
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

    // Time Complexity: O(m + n)
    //
    // Space Complexity: O(m)
    int kmp(String haystack, String needle) {
        int[] next = this.getNext.apply(needle);
        int n = haystack.length();
        int m = needle.length();
        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = next[j - 1];
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
            if (j == m) {
                return i - m + 1;
            }
        }

        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
