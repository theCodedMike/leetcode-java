package org.example.leetcode.editor.cn;
//给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [1,1,1,2,2,3], k = 2
//输出: [1,2]
// 
//
// 示例 2: 
//
// 
//输入: nums = [1], k = 1
//输出: [1] 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// k 的取值范围是 [1, 数组中不相同的元素的个数] 
// 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的 
// 
//
// 
//
// 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。 
//
// Related Topics 数组 哈希表 分治 桶排序 计数 快速选择 排序 堆（优先队列） 👍 1744 👎 0

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
public class _347_前K个高频元素 {
    public int[] topKFrequent(int[] nums, int k) {
        //return this.useHashmap(nums, k);
        //return this.useHeap(nums, k);
        return this.quickSelect(nums, k);
    }

    // Time Complexity: O(n*log(n))
    //
    // Space Complexity: O(n)
    int[] useHashmap(int[] nums, int k) {
        if (nums.length == k) {
            return nums;
        }

        Map<Integer, int[]> map = new HashMap<>(nums.length / 2);
        for (int num : nums) {
            int[] val = map.getOrDefault(num, new int[]{0, num});
            val[0] += 1;
            map.put(num, val);
        }

        return map.values().stream().sorted((v1, v2) -> v2[0] - v1[0]).limit(k).map(v -> v[1]).mapToInt(Integer::intValue).toArray();
    }

    // Time Complexity: O(n*log(k))
    //
    // Space Complexity: O(n + k)
    int[] useHeap(int[] nums, int k) {
        // O(1) time
        if (nums.length == k) {
            return nums;
        }

        // 1. Build hash map: element and how often it appears
        // O(N) time
        Map<Integer, Integer> map = new HashMap<>(nums.length / 2);
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // 2. Keep k top frequent elements in the heap
        // O(N*log(k)) < O(N*log(N)) time
        Queue<Integer> heap = new PriorityQueue<>(k + 1, Comparator.comparingInt(map::get));
        for (Integer key : map.keySet()) {
            heap.add(key);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        // 3. Build an output array
        // O(k*log(k)) time
        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            res[i] = heap.poll();
        }
        return res;
    }

    // Average Time Complexity: O(n), Worst Case: O(n^2)
    //
    // Space Complexity: O(n)
    int[] quickSelect(int[] nums, int k) {
        // O(1) time
        if (nums.length == k) {
            return nums;
        }

        // build hash map: element and how often it appears
        Map<Integer, Integer> map = new HashMap<>(nums.length / 2);
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // array of unique elements
        int m = map.size();
        List<int[]> values = new ArrayList<>(m);
        map.forEach((key, val) -> {
            values.add(new int[]{key, val});
        });

        // kth top frequent element is (m - k)th less frequent.
        // Do a partial sort: from less frequent to the most frequent, till
        // (m - k)th less frequent element takes its place (m - k) in a sorted array.
        // All elements on the left are less frequent.
        // All the elements on the right are more frequent.
        this.qsort(0, m - 1, m - k, values);

        // Return top k frequent elements
        return values.stream().skip(m - k)
                .map(v -> v[0]).mapToInt(Integer::intValue).toArray();
    }

    void qsort(int start, int end, int k, List<int[]> values) {
        // Sort a list within start..end till kth less frequent element takes its place.
        // Base case: the list contains only one element
        if (start == end) {
            return;
        }

        // Select a random pivot_index
        int pivotIdx = (int) (System.currentTimeMillis() % (end - start + 1) + start);
        pivotIdx = this.partition(start, end, pivotIdx, values);

        if (k < pivotIdx) {
            // go left
            this.qsort(start, pivotIdx - 1, k, values);
        } else if (k > pivotIdx) {
            // go right
            this.qsort(pivotIdx + 1, end, k, values);
        } else {
            return;
        }
    }

    int partition(int start, int end, int pivotIdx, List<int[]> values) {
        int pivotFreq = values.get(pivotIdx)[1];

        // 1. Move pivot to end
        Collections.swap(values, pivotIdx, end);
        // Find the pivot position in a sorted list
        int storeIdx = start;
        // 2. Move all less frequent elements to the left
        for (int i = start; i <= end; i++) {
            if (values.get(i)[1] < pivotFreq) {
                Collections.swap(values, storeIdx, i);
                storeIdx++;
            }
        }
        // 3. Move the pivot to its final place
        Collections.swap(values, storeIdx, end);

        return storeIdx;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
