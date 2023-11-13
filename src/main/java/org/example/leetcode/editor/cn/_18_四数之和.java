package org.example.leetcode.editor.cn;
//给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[
//b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）： 
//
// 
// 0 <= a, b, c, d < n 
// a、b、c 和 d 互不相同 
// nums[a] + nums[b] + nums[c] + nums[d] == target 
// 
//
// 你可以按 任意顺序 返回答案 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,0,-1,0,-2,2], target = 0
//输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [2,2,2,2,2], target = 8
//输出：[[2,2,2,2]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// -10⁹ <= nums[i] <= 10⁹ 
// -10⁹ <= target <= 10⁹ 
// 
//
// Related Topics 数组 双指针 排序 👍 1796 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        //return this.sortingThen2Pointers(nums, target);
        return this.sortingThen4Pointers(nums, target);
    }

    List<List<Integer>> sortingThen2Pointers(int[] nums, long target) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len < 4) {
            return res;
        }
        Arrays.sort(nums);

        for (int i = 0; i < len - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < len - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                int m = j + 1;
                int n = len - 1;
                while (m < n) {
                    long sum = (long)nums[i] + (long)nums[j] + (long)nums[m] + (long)nums[n];
                    if (sum > target) {
                        n--;
                    } else if (sum < target) {
                        m++;
                    } else {
                        res.add(List.of(nums[i], nums[j], nums[m], nums[n]));
                        do {
                            m++;
                        } while (nums[m] == nums[m - 1] && m < n);
                        do {
                            n--;
                        } while (nums[n] == nums[n + 1] && m < n);
                    }
                }
            }
        }

        return res;
    }

    List<List<Integer>> sortingThen4Pointers(int[] nums, long target) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len < 4) {
            return res;
        }
        Arrays.sort(nums);

        int i = 0;
        int j = len - 1;
        while (i < j) {
            int m = i + 1;
            int n = j - 1;
            while (m < n) {
                long sum = (long)nums[i] + (long)nums[j] + (long)nums[m] + (long)nums[n];
                if (sum > target) {
                    n--;
                } else if (sum < target) {
                    m++;
                } else {
                    res.add(List.of(nums[i], nums[m], nums[n], nums[j]));
                    do {
                        m++;
                    } while (nums[m] == nums[m - 1] && m < n);
                    do {
                        n--;
                    } while (nums[n] == nums[n + 1] && m < n);
                }
            }

            if (i + 3 <= j) {
                do {
                    j--;
                } while (nums[j] == nums[j + 1] && i + 3 <= j);
            } else {
                j = len - 1;
                do {
                    i++;
                } while (nums[i] == nums[i - 1] && i + 3 <= j);
            }
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
