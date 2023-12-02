package org.example.leetcode.editor.cn;
//给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位
//。 
//
// 返回 滑动窗口中的最大值 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
//输出：[3,3,5,5,6,7]
//解释：
//滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
// 
//
// 示例 2： 
//
// 
//输入：nums = [1], k = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// -10⁴ <= nums[i] <= 10⁴ 
// 1 <= k <= nums.length 
// 
//
// Related Topics 队列 数组 滑动窗口 单调队列 堆（优先队列） 👍 2622 👎 0


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
public class _239_滑动窗口最大值 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        //return this.priorityQueue(nums, k);
        //return this.monotonicQueue(nums, k);
        return this.splitBlock(nums, k);
    }

    // Time Complexity: O(n*log(n))
    //
    // Space Complexity: O(n)
    int[] priorityQueue(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        int n = 0;
        Queue<int[]> heap = new PriorityQueue<>(nums.length, (e1, e2) -> e2[0] - e1[0]);
        for (int i = 0; i < k; i++) {
            heap.add(new int[]{nums[i], i});
        }
        res[n++] = heap.element()[0];

        for (int i = k; i < nums.length; i++) {
            heap.add(new int[]{nums[i], i});
            while (!heap.isEmpty() && heap.element()[1] <= i - k) {
                heap.remove();
            }
            res[n++] = heap.element()[0];
        }

        return res;
    }

    @FunctionalInterface
    interface TriConsumer<X, Y, Z> {
        void accept(X x, Y y, Z z);
    }
    TriConsumer<Deque<Integer>, int[], Integer> pushBack = (deque, nums, i) -> {
        while (!deque.isEmpty() && nums[i] >= nums[deque.getLast()]) {
            deque.removeLast();
        }
        deque.addLast(i);
    };

    // Time Complexity: O(n)
    //
    // Space Complexity: O(k)
    int[] monotonicQueue(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        int n = 0;
        Deque<Integer> deque = new ArrayDeque<>(k);

        for (int i = 0; i < k; i++) {
            this.pushBack.accept(deque, nums, i);
        }
        res[n++] = nums[deque.getFirst()];

        for (int i = k; i < nums.length; i++) {
            this.pushBack.accept(deque, nums, i);

            while (!deque.isEmpty() && deque.getFirst() <= i - k) {
                deque.removeFirst();
            }
            res[n++] = nums[deque.getFirst()];
        }

        return res;
    }

    // Time Complexity: O(n)
    //
    // Space Complexity: O(n)
    int[] splitBlock(int[] nums, int k) {
        int[] prefix_max = new int[nums.length];
        int[] suffix_max = new int[nums.length];
        for (int i = 0, j; i < nums.length; i++) {
            if (i % k == 0) {
                prefix_max[i] = nums[i];
            } else {
                prefix_max[i] = Integer.max(prefix_max[i - 1], nums[i]);
            }
            j = nums.length - 1 - i;
            if (j == nums.length - 1 || (j + 1) % k == 0) {
                suffix_max[j] = nums[j];
            } else {
                suffix_max[j] = Integer.max(suffix_max[j + 1], nums[j]);
            }
        }

        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i <= nums.length - k; i++) {
            res[i] = Integer.max(suffix_max[i], prefix_max[i + k - 1]);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
