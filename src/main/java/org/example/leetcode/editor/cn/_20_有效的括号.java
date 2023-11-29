package org.example.leetcode.editor.cn;
//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 每个右括号都有一个对应的相同类型的左括号。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "()"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "()[]{}"
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：s = "(]"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁴ 
// s 仅由括号 '()[]{}' 组成 
// 
//
// Related Topics 栈 字符串 👍 4270 👎 0


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

//leetcode submit region begin(Prohibit modification and deletion)
class _20_有效的括号 {
    public boolean isValid(String s) {
        return this.useStack(s);
        //return this.optimizeUseStack(s);
    }

    BiFunction<Character, Character, Boolean> isMatch = (l_char, r_char) -> switch (l_char) {
        case '(' -> r_char == ')';
        case '{' -> r_char == '}';
        case '[' -> r_char == ']';
        default -> false;
    };

    boolean useStack(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char ch : s.toCharArray()) {
            switch (ch) {
                case '(':
                case '{':
                case '[': stack.push(ch); break;
                case ')':
                case '}':
                case ']': {
                    if (stack.isEmpty() || !this.isMatch.apply(stack.pop(), ch)) {
                        return false;
                    }
                    break;
                }
                default:
                    throw new UnsupportedOperationException("Unsupported char");
            }
        }

        return stack.isEmpty();
    }

    boolean optimizeUseStack(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        Map<Character, Character> map = new HashMap<>() {{
            put(')', '(');
            put('}', '{');
            put(']', '[');
        }};

        for (char ch : s.toCharArray()) {
            if (map.containsKey(ch)) {
                if (stack.isEmpty() || stack.pop() != map.get(ch)) {
                    return false;
                }
            } else {
                stack.push(ch);
            }
        }

        return stack.isEmpty();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
