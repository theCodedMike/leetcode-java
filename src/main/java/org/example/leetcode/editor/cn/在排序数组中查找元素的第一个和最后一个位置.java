package org.example.leetcode.editor.cn;
//给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。 
//
// 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4] 
//
// 示例 2： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1] 
//
// 示例 3： 
//
// 
//输入：nums = [], target = 0
//输出：[-1,-1] 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 10⁵ 
// -10⁹ <= nums[i] <= 10⁹ 
// nums 是一个非递减数组 
// -10⁹ <= target <= 10⁹ 
// 
//
// Related Topics 数组 二分查找 👍 2484 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[] {-1, -1};
        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target < nums[mid]) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                int prev = mid;
                int next = mid;
                boolean move_prev = false;
                boolean move_next = false;
                while (true) {
                    move_prev = false;
                    if (prev != 0 && nums[prev - 1] == target) {
                        move_prev = true;
                        prev -= 1;
                    }
                    move_next = false;
                    if (next != nums.length - 1 && nums[next + 1] == target) {
                        move_next = true;
                        next += 1;
                    }

                    if (!move_prev && !move_next) {
                        break;
                    }
                }

                res[0] = prev;
                res[1] = next;
                break;
            }
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
