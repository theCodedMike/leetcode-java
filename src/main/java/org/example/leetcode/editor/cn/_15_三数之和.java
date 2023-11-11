package org.example.leetcode.editor.cn;
//给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j !=
//k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请 
//
// 你返回所有和为 0 且不重复的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
//解释：
//nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
//nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
//nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
//不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
//注意，输出的顺序和三元组的顺序并不重要。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1,1]
//输出：[]
//解释：唯一可能的三元组和不为 0 。
// 
//
// 示例 3： 
//
// 
//输入：nums = [0,0,0]
//输出：[[0,0,0]]
//解释：唯一可能的三元组和为 0 。
// 
//
// 
//
// 提示： 
//
// 
// 3 <= nums.length <= 3000 
// -10⁵ <= nums[i] <= 10⁵ 
// 
//
// Related Topics 数组 双指针 排序 👍 6503 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class _15_三数之和 {
    public List<List<Integer>> threeSum(int[] nums) {
        //return this.bruteForce(nums);
        return this.sortingThen2Pointers(nums);
    }

    // Time Complexity: O(n^2*log(n))
    // Space Complexity: O(n)
    List<List<Integer>> bruteForce(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                int k = Arrays.binarySearch(nums, j + 1, nums.length, -nums[i] - nums[j]);
                if (k > 0) {
                    List<Integer> one = new ArrayList<>();
                    one.add(nums[i]);
                    one.add(nums[j]);
                    one.add(nums[k]);
                    set.add(one);
                }
            }
        }

        return set.stream().toList();
    }

    // Time Complexity: O(n^2)
    // Space Complexity: O(n)
    List<List<Integer>> sortingThen2Pointers(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    List<Integer> one = new ArrayList<>();
                    one.add(nums[i]);
                    one.add(nums[left]);
                    one.add(nums[right]);
                    res.add(one);
                    do {
                        left++;
                    } while (nums[left] == nums[left - 1] && left < right);
                    do {
                        right--;
                    } while (nums[right] == nums[right + 1] && left < right);
                }
            }
        }

        return res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
