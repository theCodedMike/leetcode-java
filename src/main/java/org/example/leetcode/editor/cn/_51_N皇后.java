package org.example.leetcode.editor.cn;
//按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
//
// n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。 
//
// 
// 
// 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
// 
// 
//
// 
//
// 示例 1： 
// 
// 
//输入：n = 4
//输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
//解释：如上图所示，4 皇后问题存在两个不同的解法。
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：[["Q"]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 9 
// 
//
// Related Topics 数组 回溯 👍 2024 👎 0


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

//leetcode submit region begin(Prohibit modification and deletion)
public class _51_N皇后 {
    public List<List<String>> solveNQueens(int n) {
        return this.backtracking(n);
    }

    @FunctionalInterface
    interface QuadrConsumer<A, B, C, D> {
        void accept(A a, B b, C c, D d);
    }

    QuadrConsumer<Integer, Integer, List<int[]>, List<List<String>>> dfs =
            (row, len, pos, res) -> {
                if (Objects.equals(row, len)) {
                    List<String> ans = pos.stream().map(p -> {
                        StringBuilder s = new StringBuilder(len);
                        for (int c = 0; c < len; c++) {
                            if (c == p[1]) {
                                s.append('Q');
                            } else {
                                s.append('.');
                            }
                        }
                        return s.toString();
                    }).collect(Collectors.toList());
                    res.add(ans);
                    return;
                }

                for (int col = 0; col < len; col++) {
                    int finalCol = col;
                    if (pos.stream().anyMatch(p -> {
                        // 同一列
                        if (p[1] == finalCol) {
                            return true;
                        }

                        double slope = ((double) (row - p[0])) / (finalCol - p[1]);
                        // 同一对角线
                        return slope == 1 || slope == -1;
                    })) {
                        continue;
                    }

                    pos.addLast(new int[]{row, col});
                    this.dfs.accept(row + 1, len, pos, res);
                    pos.removeLast();
                }
            };

    List<List<String>> backtracking(int n) {
        List<List<String>> res = new ArrayList<>();
        this.dfs.accept(0, n, new ArrayList<>(), res);
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
