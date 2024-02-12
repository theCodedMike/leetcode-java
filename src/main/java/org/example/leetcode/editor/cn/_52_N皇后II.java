package org.example.leetcode.editor.cn;
//n 皇后问题 研究的是如何将 n 个皇后放置在 n × n 的棋盘上，并且使皇后彼此之间不能相互攻击。
//
// 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。 
//
// 
//
// 
// 
// 示例 1： 
// 
// 
//输入：n = 4
//输出：2
//解释：如上图所示，4 皇后问题存在两个不同的解法。
// 
// 
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：1
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
// Related Topics 回溯 👍 508 👎 0


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//leetcode submit region begin(Prohibit modification and deletion)
public class _52_N皇后II {
    public int totalNQueens(int n) {
        return this.backtracking(n);
    }

    interface QuadrConsumer<A, B, C, D> {
        void accept(A a, B b, C c, D d);
    }

    QuadrConsumer<Integer, Integer, List<int[]>, int[]> dfs =
            (row, len, pos, total) -> {
                if (Objects.equals(row, len)) {
                    total[0] += 1;
                    return;
                }

                for (int col = 0; col < len; col++) {
                    int finalCol = col;
                    if (pos.stream().anyMatch(p -> {
                        if (p[1] == finalCol) {
                            return true;
                        }
                        double slope = (double) (row - p[0]) / (finalCol - p[1]);
                        return slope == 1 || slope == -1;
                    })) {
                        continue;
                    }

                    pos.addLast(new int[]{row, col});
                    this.dfs.accept(row + 1, len, pos, total);
                    pos.removeLast();
                }
            };

    int backtracking(int n) {
        int[] res = new int[1];
        this.dfs.accept(0, n, new ArrayList<>(), res);
        return res[0];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
