package org.example.leetcode.editor.cn;
//给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
//
// 
//
// 示例 1： 
// 
// 
//输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[1,2,3,6,9,8,7,4,5]
// 
//
// 示例 2： 
// 
// 
//输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//输出：[1,2,3,4,8,12,11,10,9,5,6,7]
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 10 
// -100 <= matrix[i][j] <= 100 
// 
//
// Related Topics 数组 矩阵 模拟 👍 1512 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class _54_螺旋矩阵 {
    public List<Integer> spiralOrder(int[][] matrix) {
        //return this.simulate(matrix);
        return this.simulateByLayer(matrix);
    }

    public List<Integer> simulate(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int totalLen = row * col;

        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int dirIdx = 0;
        List<Integer> res = new ArrayList<>(totalLen);
        int i = 0, j = 0;
        for (;totalLen-- > 0;) {
            res.add(matrix[i][j]);
            matrix[i][j] = Integer.MIN_VALUE;

            int next_i = i + directions[dirIdx][0];
            int next_j = j + directions[dirIdx][1];
            if (next_i < 0 || next_i >= row || next_j < 0 || next_j >= col || matrix[next_i][next_j] == Integer.MIN_VALUE) {
                dirIdx = (dirIdx + 1) % 4;
            }
            i += directions[dirIdx][0];
            j += directions[dirIdx][1];
        }

        return res;
    }

    public List<Integer> simulateByLayer(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int totalLen = row * col;

        List<Integer> res = new ArrayList<>(totalLen);
        int left = 0;
        int right = col - 1;
        int top = 0;
        int bottom = row - 1;
        while (left <= right && top <= bottom) {
            // left(top) -> right(top)
            for (int j = left; j <= right; j++) {
                res.add(matrix[top][j]);
            }
            // right(top)
            //   ↓
            // right(bottom)
            for (int i = top + 1; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }
            if (left < right && top < bottom) {
                // left(bottom) <- right(bottom)
                for (int j = right - 1; j >= left + 1; j--) {
                    res.add(matrix[bottom][j]);
                }
                // left(top)
                //   ↓
                // left(bottom)
                for (int i = bottom; i >= top + 1; i--) {
                    res.add(matrix[i][left]);
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
