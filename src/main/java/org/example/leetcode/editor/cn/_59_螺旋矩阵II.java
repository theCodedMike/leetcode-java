package org.example.leetcode.editor.cn;
//给你一个正整数 n ，生成一个包含 1 到 n² 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
//
// 
//
// 示例 1： 
// 
// 
//输入：n = 3
//输出：[[1,2,3],[8,9,4],[7,6,5]]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：[[1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 20 
// 
//
// Related Topics 数组 矩阵 模拟 👍 1185 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class _59_螺旋矩阵II {
    public int[][] generateMatrix(int n) {
        //return this.simulate(n);
        return this.simulateByLayer(n);
    }

    public int[][] simulate(int n) {
        int totalLen = n * n;
        int[][] res = new int[n][n];
        int row = 0, col = 0;
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int dirIdx = 0;

        for (int val = 1; val <= totalLen; val++) {
            res[row][col] = val;
            int next_row = row + directions[dirIdx][0];
            int next_col = col + directions[dirIdx][1];
            if (next_row < 0 || next_row >= n || next_col < 0 || next_col >= n || res[next_row][next_col] != 0) {
                dirIdx = (dirIdx + 1) % 4;
            }
            row += directions[dirIdx][0];
            col += directions[dirIdx][1];
        }

        return res;
    }

    public int[][] simulateByLayer(int n) {
        int[][] res = new int[n][n];
        int val = 1;
        int left = 0, right = n - 1;
        int top = 0, bottom = n - 1;

        while (left <= right && top <= bottom) {
            // left(top) -> right(top)
            for (int col = left; col <= right; col++) {
                res[top][col] = val++;
            }
            // right(top)
            //     ↓
            // right(bottom)
            for (int row = top + 1; row <= bottom; row++) {
                res[row][right] = val++;
            }
            if (left < right && top < bottom) {
                // left(bottom) <- right(bottom)
                for (int col = right - 1; col >= left + 1; col--) {
                    res[bottom][col] = val++;
                }
                // left(top)
                //     ↑
                // left(bottom)
                for (int row = bottom; row >= top + 1; row--) {
                    res[row][left] = val++;
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
