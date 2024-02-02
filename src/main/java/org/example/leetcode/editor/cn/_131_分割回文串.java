package org.example.leetcode.editor.cn;
//给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
//
// 回文串 是正着读和反着读都一样的字符串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "aab"
//输出：[["a","a","b"],["aa","b"]]
// 
//
// 示例 2： 
//
// 
//输入：s = "a"
//输出：[["a"]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 16 
// s 仅由小写英文字母组成 
// 
//
// Related Topics 字符串 动态规划 回溯 👍 1721 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

//leetcode submit region begin(Prohibit modification and deletion)
public class _131_分割回文串 {
    public List<List<String>> partition(String s) {
        return this.backtracking(s);

        //return this.backtrackingAndDp(s);
    }

    Predicate<String> isPalindrome = (s) -> {
        int i = 0;
        int j = s.length() - 1;
        boolean isPalindrome = true;

        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                isPalindrome = false;
            }
        }

        return isPalindrome;
    };

    @FunctionalInterface
    interface QuadrConsumer<A, B, C, D> {
        void accept(A a, B b, C c, D d);
    }

    QuadrConsumer<Integer, String, List<String>, List<List<String>>> dfs1 =
            (i, s, combine, res) -> {
                if (i == s.length()) {
                    res.add(new ArrayList<>(combine));
                    return;
                }

                for (int j = i + 1; j <= s.length(); j++) {
                    String substring = s.substring(i, j);
                    if (this.isPalindrome.test(substring)) {
                        combine.addLast(substring);
                        this.dfs1.accept(j, s, combine, res);
                        combine.removeLast();
                    }
                }
            };

    List<List<String>> backtracking(String s) {
        List<List<String>> res = new ArrayList<>();
        this.dfs1.accept(0, s, new ArrayList<>(), res);
        return res;
    }


    interface QuintConsumer<A, B, C, D, E> {
        void accept(A a, B b, C c, D d, E e);
    }

    QuintConsumer<Integer, String, List<String>, List<List<String>>, boolean[][]> dfs2 =
            (i, s, combine, res, f) -> {
                if (i == s.length()) {
                    res.add(new ArrayList<>(combine));
                    return;
                }

                for (int j = i; j < s.length(); j++) {
                    if (f[i][j]) {
                        combine.addLast(s.substring(i, j + 1));
                        this.dfs2.accept(j + 1, s, combine, res, f);
                        combine.removeLast();
                    }
                }
            };

    List<List<String>> backtrackingAndDp(String s) {
        int len = s.length();
        boolean[][] f = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            Arrays.fill(f[i], true);
        }
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
            }
        }

        List<List<String>> res = new ArrayList<>();
        this.dfs2.accept(0, s, new ArrayList<>(), res, f);
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
