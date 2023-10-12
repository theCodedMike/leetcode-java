package org.example.leetcode.editor.cn;
//给定一个含有 n 个正整数的数组和一个正整数 target 。
//
// 找出该数组中满足其总和大于等于 target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返
//回其长度。如果不存在符合条件的子数组，返回 0 。 
//
// 
//
// 示例 1： 
//
// 
//输入：target = 7, nums = [2,3,1,2,4,3]
//输出：2
//解释：子数组 [4,3] 是该条件下的长度最小的子数组。
// 
//
// 示例 2： 
//
// 
//输入：target = 4, nums = [1,4,4]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：target = 11, nums = [1,1,1,1,1,1,1,1]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= target <= 10⁹ 
// 1 <= nums.length <= 10⁵ 
// 1 <= nums[i] <= 10⁵ 
// 
//
// 
//
// 进阶： 
//
// 
// 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。 
// 
//
// Related Topics 数组 二分查找 前缀和 滑动窗口 👍 1919 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        //return this.bruteForce(target, nums);
        //return this.usingBinarySearch(target, nums);
        return this.twoPointers(target, nums);
    }

    // Time Limit Exceeded
    //
    // Time Complexity: O(n^3), Space complexity: O(1)
    public int bruteForce(int target, int[] nums) {
        for (int width = 1; width <= nums.length; width++) {
            int begin = 0;
            int end = begin + width;

            while (end <= nums.length) {
                int sum = 0;
                for (int i = begin; i < end; i++) {
                    sum += nums[i];
                }
                if (sum >= target) {
                    return width;
                }

                begin++;
                end = begin + width;
            }
        }

        return 0;
    }

    // Time complexity: O(nlog(n)), Space complexity: O(n)
    public int usingBinarySearch(int target, int[] nums) {
        int res = Integer.MAX_VALUE;
        int[] sums = new int[nums.length + 1];

        for (int i = 0; i < nums.length; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            int toFind = target + sums[i];
            int idx = Arrays.binarySearch(sums, toFind);
            idx = idx < 0 ? Math.abs(idx) - 1 : idx;
            if (idx != sums.length) {
                res = Math.min(res, idx - i);
            }
        }

        return res == Integer.MAX_VALUE ? 0 : res;
    }

    // Time complexity: O(n), Space complexity: O(1)
    public int twoPointers(int target, int[] nums) {
        int res = Integer.MAX_VALUE;
        int left = 0;
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= target) {
                res = Math.min(res, i + 1 - left);
                sum -= nums[left++];
            }
        }

        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
