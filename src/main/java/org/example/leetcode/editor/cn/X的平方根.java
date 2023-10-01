//给你一个非负整数 x ，计算并返回 x 的 算术平方根 。 
//
// 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。 
//
// 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。 
//
// 
//
// 示例 1： 
//
// 
//输入：x = 4
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：x = 8
//输出：2
//解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= x <= 2³¹ - 1 
// 
//
// Related Topics 数学 二分查找 👍 1445 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int mySqrt(int x) {
        //return this.binarySearch1(x);
        return this.binarySearch2(x);
    }

    public int binarySearch1(int x) {
        long len = (long)x;
        long left = 0;
        long right = len + 1; // 左闭右开，right需要为len+1

        while (left < right) {
            long mid = left + (right - left) / 2;
            long square = mid * mid;
            if (square > len) {
                right = mid;
            } else if (square < len) {
                left = mid + 1;
            } else {
                return (int) mid;
            }
        }

        return (int) (left - 1);
    }

    public int binarySearch2(int x) {
        long len = (long)x;
        long left = 0;
        long right = len;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            long square = mid * mid;
            if (square > len) {
                right = mid - 1;
            } else if (square < len) {
                left = mid + 1;
            } else {
                return (int)mid;
            }
        }

        return (int)left - 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
