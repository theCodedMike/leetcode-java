package org.example.leetcode.editor.cn;
//给定两个数组 nums1 和 nums2 ，返回 它们的交集 。输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,2,2,1], nums2 = [2,2]
//输出：[2]
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//输出：[9,4]
//解释：[4,9] 也是可通过的
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums1.length, nums2.length <= 1000 
// 0 <= nums1[i], nums2[i] <= 1000 
// 
//
// Related Topics 数组 哈希表 双指针 二分查找 排序 👍 850 👎 0


import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

//leetcode submit region begin(Prohibit modification and deletion)
class _349_两个数组的交集 {
    public int[] intersection(int[] nums1, int[] nums2) {
        //return this.useHash(nums1, nums2);
        return this.sorting(nums1, nums2);
    }

    int[] useHash(int[] nums1, int[] nums2) {
        Map<Integer, Boolean> map = Arrays.stream(nums1)
                .boxed()
                .collect(Collectors.toMap(Integer::intValue, (num) -> true, (key1, key2) -> key1));
        return Arrays.stream(nums2).filter(num -> {
            Boolean contains = map.getOrDefault(num, false);
            if (contains) {
                map.put(num, false);
            }
            return contains;
        }).toArray();
    }

    int[] sorting(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        HashSet<Integer> set = new HashSet<>();
        int i1 = 0;
        int i2 = 0;
        while (i1 < nums1.length && i2 < nums2.length) {
            if (nums1[i1] > nums2[i2]) {
                i2++;
            } else if (nums1[i1] < nums2[i2]) {
                i1++;
            } else {
                set.add(nums1[i1]);
                i1++;
                i2++;
            }
        }

        return set.stream().mapToInt(Integer::intValue).toArray();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
