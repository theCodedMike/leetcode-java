package org.example.leetcode.editor.cn;
//给定 s 和 t 两个字符串，当它们分别被输入到空白的文本编辑器后，如果两者相等，返回 true 。# 代表退格字符。
//
// 注意：如果对空文本输入退格字符，文本继续为空。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "ab#c", t = "ad#c"
//输出：true
//解释：s 和 t 都会变成 "ac"。
// 
//
// 示例 2： 
//
// 
//输入：s = "ab##", t = "c#d#"
//输出：true
//解释：s 和 t 都会变成 ""。
// 
//
// 示例 3： 
//
// 
//输入：s = "a#c", t = "b"
//输出：false
//解释：s 会变成 "c"，但 t 仍然是 "b"。 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length, t.length <= 200 
// s 和 t 只含有小写字母以及字符 '#' 
// 
//
// 
//
// 进阶： 
//
// 
// 你可以用 O(n) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？ 
// 
//
// Related Topics 栈 双指针 字符串 模拟 👍 678 👎 0

import java.util.Arrays;
import java.util.Stack;
import java.util.function.Function;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean backspaceCompare(String s, String t) {
        //return this.buildString(s, t);
        return this.two_pointers(s, t);
    }

    public boolean buildString(String s, String t) {
        Function<String, String> build = (String str) -> {
            Stack<Character> chars = new Stack<>();

            for (char ch : str.toCharArray()) {
                if (ch == '#') {
                    if (!chars.isEmpty()) {
                        chars.pop();
                    }
                } else {
                    chars.push(ch);
                }
            }

            return Arrays.toString(chars.toArray());
        };

        return build.apply(s).equals(build.apply(t));
    }

    public boolean two_pointers(String s, String t) {
        int s_idx = s.length() - 1;
        int t_idx = t.length() - 1;
        int s_sharp_count = 0;
        int t_sharp_count = 0;

        while (s_idx >= 0 || t_idx >= 0) {
            while (s_idx >= 0) {
                if (s.charAt(s_idx) == '#') {
                    s_sharp_count++;
                    s_idx--;
                } else if (s_sharp_count > 0) {
                    s_sharp_count--;
                    s_idx--;
                } else {
                    break;
                }
            }

            while (t_idx >= 0) {
                if (t.charAt(t_idx) == '#') {
                    t_sharp_count++;
                    t_idx--;
                } else if (t_sharp_count > 0) {
                    t_sharp_count--;
                    t_idx--;
                } else {
                    break;
                }
            }
            // If two characters are different
            if (s_idx >= 0 && t_idx >= 0 && s.charAt(s_idx) != t.charAt(t_idx)) {
                return false;
            }
            // If char vs nothing
            if ((s_idx >= 0) != (t_idx >= 0)) {
                return false;
            }

            s_idx--;
            t_idx--;
        }

        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
