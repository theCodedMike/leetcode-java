//编写一个程序，通过填充空格来解决数独问题。 
//
// 数独的解法需 遵循如下规则： 
//
// 
// 数字 1-9 在每一行只能出现一次。 
// 数字 1-9 在每一列只能出现一次。 
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图） 
// 
//
// 数独部分空格内已填入了数字，空白格用 '.' 表示。 
//
// 
//
// 
// 
// 
// 示例 1： 
// 
// 
//输入：board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".
//",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".
//","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6
//"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[
//".",".",".",".","8",".",".","7","9"]]
//输出：[["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8
//"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],[
//"4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9",
//"6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4",
//"5","2","8","6","1","7","9"]]
//解释：输入的数独如上图所示，唯一有效的解决方案如下所示：
// 
// 
// 
// 
//
//
//
// 
//
// 提示： 
//
// 
// board.length == 9 
// board[i].length == 9 
// board[i][j] 是一位数字或者 '.' 
// 题目数据 保证 输入数独仅有一个解 
// 
//
// Related Topics 数组 哈希表 回溯 矩阵 👍 1795 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
public class _37_解数独 {
    List<int[]> spaces;
    boolean valid;

    public void solveSudoku(char[][] board) {
        this.backtracking(board);

        //this.bitOperationOptimization(board);
    }

    @FunctionalInterface
    interface QuintConsumer<A, B, C, D, E> {
        void accept(A a, B b, C c, D d, E e);
    }

    @FunctionalInterface
    interface SexConsumer<A, B, C, D, E, F> {
        void accept(A a, B b, C c, D d, E e, F f);
    }

    @FunctionalInterface
    interface SeptConsumer<A, B, C, D, E, F, G> {
        void accept(A a, B b, C c, D d, E e, F f, G g);
    }

    QuintConsumer<char[][], Integer, boolean[][], boolean[][], boolean[][][]> dfs1 =
            (board, pos, row, col, subBoxes) -> {
                if (pos == this.spaces.size()) {
                    this.valid = true;
                    return;
                }

                int i = spaces.get(pos)[0];
                int j = spaces.get(pos)[1];
                for (int idx = 0; idx < 9 && !this.valid; idx++) {
                    if (!row[i][idx] && !col[j][idx] && !subBoxes[i / 3][j / 3][idx]) {
                        board[i][j] = (char) (idx + '1');
                        this.flip1.accept(row, col, subBoxes, i, j, idx, true);

                        this.dfs1.accept(board, pos + 1, row, col, subBoxes);

                        this.flip1.accept(row, col, subBoxes, i, j, idx, false);
                    }
                }
            };
    SeptConsumer<boolean[][], boolean[][], boolean[][][], Integer, Integer, Integer, Boolean> flip1 =
            (row, col, subBoxes, i, j, idx, val) -> {
                row[i][idx] = val;
                col[j][idx] = val;
                subBoxes[i / 3][j / 3][idx] = val;
            };

    void backtracking(char[][] board) {
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][][] subBoxes = new boolean[3][3][9];
        this.spaces = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    this.spaces.add(new int[]{i, j});
                } else {
                    int idx = board[i][j] - '1';
                    this.flip1.accept(row, col, subBoxes, i, j, idx, true);
                }
            }
        }

        this.dfs1.accept(board, 0, row, col, subBoxes);
    }


    QuintConsumer<char[][], Integer, int[], int[], int[][]> dfs2 =
            (board, pos, row, col, subBoxes) -> {
                if (pos == this.spaces.size()) {
                    this.valid = true;
                    return;
                }

                int i = this.spaces.get(pos)[0];
                int j = this.spaces.get(pos)[1];
                int mask = ~(row[i] | col[j] | subBoxes[i / 3][j / 3]) & 0x1ff;
                for (; mask != 0 && !this.valid; mask &= mask - 1) {
                    int digit = Integer.bitCount((mask & (-mask)) - 1);
                    board[i][j] = (char) (digit + '1');
                    this.flip2.accept(row, col, subBoxes, i, j, digit);

                    this.dfs2.accept(board, pos + 1, row, col, subBoxes);

                    this.flip2.accept(row, col, subBoxes, i, j, digit);
                }
            };
    SexConsumer<int[], int[], int[][], Integer, Integer, Integer> flip2 =
            (row, col, subBoxes, i, j, digit) -> {
                row[i] ^= 1 << digit;
                col[j] ^= 1 << digit;
                subBoxes[i / 3][j / 3] ^= 1 << digit;
            };

    void bitOperationOptimization(char[][] board) {
        int[] row = new int[9];
        int[] col = new int[9];
        int[][] subBoxes = new int[3][3];
        this.spaces = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    this.spaces.add(new int[]{i, j});
                } else {
                    int digit = board[i][j] - '1';
                    this.flip2.accept(row, col, subBoxes, i, j, digit);
                }
            }
        }

        this.dfs2.accept(board, 0, row, col, subBoxes);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
