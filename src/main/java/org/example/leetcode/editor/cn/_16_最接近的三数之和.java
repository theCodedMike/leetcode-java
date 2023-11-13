package org.example.leetcode.editor.cn;
//给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
//
// 返回这三个数的和。 
//
// 假定每组输入只存在恰好一个解。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,2,1,-4], target = 1
//输出：2
//解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,0,0], target = 1
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 3 <= nums.length <= 1000 
// -1000 <= nums[i] <= 1000 
// -10⁴ <= target <= 10⁴ 
// 
//
// Related Topics 数组 双指针 排序 👍 1548 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
public class _16_最接近的三数之和 {
    public int threeSumClosest(int[] nums, int target) {
        //return this.bruteForce(nums, target);
        return this.sortingThen2Pointers(nums, target);
    }

    int bruteForce(int[] nums, int target) {
        Arrays.sort(nums);

        int best = Integer.MAX_VALUE / 2;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 1; j++) {
                int third = target - nums[i] - nums[j];
                int k = Arrays.binarySearch(nums, j + 1, nums.length, third);
                if (k >= 0) {
                    return target;
                }
                k = -(k + 1);
                if (k == j + 1) {

                } else if (k == nums.length) {
                    k--;
                } else {
                    if ((third - nums[k - 1]) < (nums[k] - third)) {
                        k--;
                    }
                }
                int sum = nums[i] + nums[j] + nums[k];
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }
            }
        }

        return best;
    }

    int sortingThen2Pointers(int[] nums, int target) {
        Arrays.sort(nums);

        int best = Integer.MAX_VALUE / 2;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }
                if (sum > target) {
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    return best;
                }
            }
        }

        return best;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
