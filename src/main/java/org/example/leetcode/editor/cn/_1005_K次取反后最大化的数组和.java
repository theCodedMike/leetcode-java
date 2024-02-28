package org.example.leetcode.editor.cn;
//给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
//
// 
// 选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。 
// 
//
// 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。 
//
// 以这种方式修改数组后，返回数组 可能的最大和 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [4,2,3], k = 1
//输出：5
//解释：选择下标 1 ，nums 变为 [4,-2,3] 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,-1,0,2], k = 3
//输出：6
//解释：选择下标 (1, 2, 2) ，nums 变为 [3,1,0,2] 。
// 
//
// 示例 3： 
//
// 
//输入：nums = [2,-3,-1,5,-4], k = 2
//输出：13
//解释：选择下标 (1, 4) ，nums 变为 [2,3,-1,5,4] 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁴ 
// -100 <= nums[i] <= 100 
// 1 <= k <= 10⁴ 
// 
//
// Related Topics 贪心 数组 排序 👍 436 👎 0


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
public class _1005_K次取反后最大化的数组和 {
    public int largestSumAfterKNegations(int[] nums, int k) {
        //return this.modifyNegNum(nums, k);

        //return this.sortThenModify(nums, k);

        return this.useMinHeap(nums, k);
    }

    /**
     * 时间复杂度：O(n + C)，其中n是数组nums的长度，C是数组nums中元素的范围
     * 空间复杂度：O(C)
     */
    int modifyNegNum(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>(nums.length);
        int sum = 0;
        for (int num : nums) {
            sum += num;
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        for (int i = -100; i < 0; i++) {
            if (freq.containsKey(i)) {
                int ops = Math.min(k, freq.get(i));
                sum += (-i) * ops * 2;
                freq.put(i, freq.get(i) - ops);
                freq.put(-i, freq.getOrDefault(-i, 0) + ops);
                k -= ops;
                if (k == 0) {
                    break;
                }
            }
        }

        if (k % 2 == 1 && !freq.containsKey(0)) {
            for (int i = 1; i <= 100; i++) {
                if (freq.containsKey(i) && freq.get(i) != 0) {
                    sum -= i * 2;
                    break;
                }
            }
        }

        return sum;
    }

    /**
     * 时间复杂度：O(n * log(n))
     * 空间复杂度：O(n)
     */
    int sortThenModify(int[] nums, int k) {
        Arrays.sort(nums);
        int sum = 0;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0 && k > 0) {
                nums[i] = -nums[i];
                k--;
            }
            sum += nums[i];
            if (nums[i] < min) {
                min = nums[i];
            }
        }

        return sum - (k % 2 == 0 ? 0 : 2 * min);
    }

    /**
     * 时间复杂度：O(n * log(n))
     * 空间复杂度：O(n)
     */
    int useMinHeap(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(nums.length);
        int sum = 0;

        for (int num : nums) {
            sum += num;
            heap.add(num);
        }

        while (k-- > 0) {
            int min = heap.poll();
            sum -= 2 * min;
            heap.add(-min);
        }

        return sum;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
