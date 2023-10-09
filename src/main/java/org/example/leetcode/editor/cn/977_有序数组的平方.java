package org.example.leetcode.editor.cn;
//给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
//
// 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-4,-1,0,3,10]
//输出：[0,1,9,16,100]
//解释：平方后，数组变为 [16,1,0,9,100]
//排序后，数组变为 [0,1,9,16,100] 
//
// 示例 2： 
//
// 
//输入：nums = [-7,-3,2,3,11]
//输出：[4,9,9,49,121]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁴ 
// -10⁴ <= nums[i] <= 10⁴ 
// nums 已按 非递减顺序 排序 
// 
//
// 
//
// 进阶： 
//
// 
// 请你设计时间复杂度为 O(n) 的算法解决本问题 
// 
//
// Related Topics 数组 双指针 排序 👍 899 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
import java.util.Arrays;

class Solution {
    public int[] sortedSquares(int[] nums) {
        //return this.bruteForce(nums);
        return this.two_pointers(nums);
    }

    public int[] bruteForce(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] *= nums[i];
        }
        Arrays.sort(nums);
        return nums;
    }

    public int[] two_pointers(int[] nums) {
        int[] res = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;
        int idx = nums.length - 1;

        while (true) {
            int square_of_left = nums[left] * nums[left];
            int square_of_right = nums[right] * nums[right];
            if (square_of_left > square_of_right) {
                res[idx] = square_of_left;
                idx--;
                left++;
            } else if (square_of_left < square_of_right) {
                res[idx] = square_of_right;
                idx--;
                right--;
            } else {
                res[idx] = square_of_right;
                if (left == right) {
                    break;
                }
                idx--;
                res[idx] = square_of_left;
                if (left + 1 == right) {
                    break;
                }
                idx--;
                left++;
                right--;
            }
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
