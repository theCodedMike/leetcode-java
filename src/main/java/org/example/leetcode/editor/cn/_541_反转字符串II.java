package org.example.leetcode.editor.cn;
//给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
//
// 
// 如果剩余字符少于 k 个，则将剩余字符全部反转。 
// 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "abcdefg", k = 2
//输出："bacdfeg"
// 
//
// 示例 2： 
//
// 
//输入：s = "abcd", k = 2
//输出："bacd"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁴ 
// s 仅由小写英文组成 
// 1 <= k <= 10⁴ 
// 
//
// Related Topics 双指针 字符串 👍 553 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class _541_反转字符串II {
    public String reverseStr(String s, int k) {
        return this.twoPointers(s, k);
    }

    String twoPointers(String s, int k) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        int begin = 0;
        int end = begin + k;

        do {
            if (end > len) {
                end = len;
            }

            int l = begin;
            int r = end - 1;
            while (l < r) {
                char temp = chars[l];
                chars[l] = chars[r];
                chars[r] = temp;
                l++;
                r--;
            }

            begin += 2 * k;
            end = begin + k;
        } while (begin < len);

        return String.valueOf(chars);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
