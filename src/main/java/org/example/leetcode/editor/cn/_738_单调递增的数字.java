package org.example.leetcode.editor.cn;
//当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。
//
// 给定一个整数 n ，返回 小于或等于 n 的最大数字，且数字呈 单调递增 。 
//
// 
//
// 示例 1: 
//
// 
//输入: n = 10
//输出: 9
// 
//
// 示例 2: 
//
// 
//输入: n = 1234
//输出: 1234
// 
//
// 示例 3: 
//
// 
//输入: n = 332
//输出: 299
// 
//
// 
//
// 提示: 
//
// 
// 0 <= n <= 10⁹ 
// 
//
// Related Topics 贪心 数学 👍 451 👎 0


import java.util.function.Predicate;

//leetcode submit region begin(Prohibit modification and deletion)
public class _738_单调递增的数字 {
    public int monotoneIncreasingDigits(int n) {
        //return this.bruteForce(n);
        return this.greedy(n);
    }

    Predicate<Integer> isMonotoneIncreasing = m -> {
        int remCount = 0;
        int curr = 0, prev = 0;
        while (m != 0) {
            prev = curr;
            curr = m % 10;
            remCount++;
            if (remCount >= 2 && curr > prev) {
                return false;
            }
            m /= 10;
        }
        return true;
    };
    int bruteForce(int n) {
        int res = n;

        while (true) {
            if (this.isMonotoneIncreasing.test(n)) {
                res = n;
                break;
            }
            n--;
        }

        return res;
    }

    int greedy(int n) {
        byte[] bytes_n = String.valueOf(n).getBytes();
        int len = bytes_n.length;

        int i = 1;
        while (i < len && bytes_n[i - 1] <= bytes_n[i]) {
            i++;
        }

        if (i < len) {
            while (i > 0 && bytes_n[i - 1] > bytes_n[i]) {
                bytes_n[i - 1]--;
                i--;
            }
            for (int j = i + 1; j < len; j++) {
                bytes_n[j] = (byte)'9';
            }
        }

        return Integer.parseInt(new String(bytes_n));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
