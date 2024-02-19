//给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素 。你可以按 任意顺序 返回答案。 
//
// 数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [4,6,7,7]
//输出：[[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [4,4,3,2,1]
//输出：[[4,4]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 15 
// -100 <= nums[i] <= 100 
// 
//
// Related Topics 位运算 数组 哈希表 回溯 👍 771 👎 0


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
public class _491_非递减子序列 {
    public List<List<Integer>> findSubsequences(int[] nums) {
        return this.backtracking(nums);
    }

    @FunctionalInterface
    interface QuadrConsumer<A, B, C, D> {
        void accept(A a, B b, C c, D d);
    }

    QuadrConsumer<Integer, int[], List<Integer>, List<List<Integer>>> dfs =
            (idx, nums, subSeq, res) -> {
                if (subSeq.size() >= 2) {
                    res.add(new ArrayList<>(subSeq));
                }
                if (idx == nums.length) {
                    return;
                }

                Set<Integer> used = new HashSet<>();
                for (int i = idx; i < nums.length; i++) {
                    if (idx > 0 && nums[idx - 1] > nums[i]) {
                        continue;
                    }
                    if (used.contains(nums[i])) {
                        continue;
                    }

                    used.add(nums[i]);
                    subSeq.addLast(nums[i]);

                    this.dfs.accept(i + 1, nums, subSeq, res);

                    subSeq.removeLast();
                }
            };

    public List<List<Integer>> backtracking(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        this.dfs.accept(0, nums, new ArrayList<>(), res);
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
