package org.example.leetcode.editor.cn;
//给你一个字符串 s ，请你反转字符串中 单词 的顺序。
//
// 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。 
//
// 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。 
//
// 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "the sky is blue"
//输出："blue is sky the"
// 
//
// 示例 2： 
//
// 
//输入：s = "  hello world  "
//输出："world hello"
//解释：反转后的字符串中不能存在前导空格和尾随空格。
// 
//
// 示例 3： 
//
// 
//输入：s = "a good   example"
//输出："example good a"
//解释：如果两个单词间有多余的空格，反转后的字符串需要将单词间的空格减少到仅有一个。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁴ 
// s 包含英文大小写字母、数字和空格 ' ' 
// s 中 至少存在一个 单词 
// 
//
// 
// 
//
// 
//
// 进阶：如果字符串在你使用的编程语言中是一种可变数据类型，请尝试使用 O(1) 额外空间复杂度的 原地 解法。 
//
// Related Topics 双指针 字符串 👍 1068 👎 0

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    @FunctionalInterface
    interface TriConsumer<T1, T2, T3> {
        void accept(T1 t1, T2 t2, T3 t3);
    }

    public String reverseWords(String s) {
        //return this.use_std_split(s);
        //return this.use_custom_split(s);
        return this.use_stack(s);
    }

    // Time Complexity: O(n)
    //
    // Space Complexity: O(n)
    public String use_std_split(String s) {
        List<String> words = Arrays.stream(s.split(" ")).filter(word -> !word.isBlank()).collect(Collectors.toList());
        Collections.reverse(words);
        return String.join(" ", words);
    }

    TriConsumer<List<Character>, Integer, Integer> reverse = (List<Character> list, Integer in, Integer out) -> {
        int begin = in;
        int end = out - 1;

        while (begin < end) {
            Character temp = list.get(begin);
            list.set(begin, list.get(end));
            list.set(end, temp);
            begin++;
            end--;
        }
    };

    // Time Complexity: O(n)
    //
    // Space Complexity: O(n)
    public String use_custom_split(String s) {
        List<Character> chars = s.chars().mapToObj(i -> (char) i).collect(Collectors.toList());
        this.reverse.accept(chars, 0, chars.size());

        // remove leading spaces
        while (Character.isSpaceChar(chars.get(0))) {
            chars.remove(0);
        }

        // remove trailing spaces
        while (Character.isSpaceChar(chars.get(chars.size() - 1))) {
            chars.remove(chars.size() - 1);
        }

        // Reverse the mid by word
        int space = 0;
        int slow = 0;
        int fast = 0;
        while (fast < chars.size()) {
            if (Character.isSpaceChar(chars.get(fast))) {
                space++;
                if (space == 1) {
                    this.reverse.accept(chars, slow, fast);
                    slow = fast + 1;
                    fast++;
                } else {
                    chars.remove(fast);
                }
            } else {
                space = 0;
                fast++;
                if (fast == chars.size()) {
                    this.reverse.accept(chars, slow, fast);
                }
            }
        }

        return chars.stream().collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
    }

    // Time Complexity: O(n)
    //
    // Space Complexity: O(n)
    public String use_stack(String s) {
        char[] chars = s.toCharArray();
        Stack<Integer[]> stack = new Stack<>();
        int begin = 0, end = 0;

        for (int i = 0; i < chars.length; i++) {
            if (!Character.isSpaceChar(chars[i])) {
                if (i == 0 || Character.isSpaceChar(chars[i - 1])) {
                    begin = i;
                }
                if (i + 1 == chars.length || Character.isSpaceChar(chars[i + 1])) {
                    end = i + 1;
                    stack.push(new Integer[]{begin, end});
                }
            }
        }

        StringBuilder sb = new StringBuilder(chars.length);
        while (!stack.isEmpty()) {
            Integer[] idx = stack.pop();
            sb.append(s, idx[0], idx[1]);
            if (!stack.isEmpty()) {
                sb.append(' ');
            }
        }

        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
