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
class _28_找出字符串中第一个匹配项的下标 {
    public int strStr(String haystack, String needle) {
        //return this.bruteForce(haystack, needle);
        return this.kmp(haystack, needle);
    }

    int bruteForce(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();
        int start = 0;
        int end = m;

        while (end <= n) {
            boolean allEqual = true;
            for (int i = start; i < end; i++) {
                if (haystack.charAt(i) != needle.charAt(i - start)) {
                    allEqual = false;
                    break;
                }
            }
            if (allEqual) {
                return start;
            }

            start++;
            end = start + m;
        }

        return -1;
    }

    int kmp(String haystack, String needle) {
        int m = needle.length();
        int[] pi = new int[m];
        for (int i = 1, j = 0; i < m; i++) {
            while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                j = pi[j - 1];
            }
            if (needle.charAt(i) == needle.charAt(j)) {
                j++;
            }
            pi[i] = j;
        }

        int n = haystack.length();
        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = pi[j - 1];
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
