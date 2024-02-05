package org.example.leetcode.editor.cn;
//给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
//
// 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0]
//输出：[[],[0]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10 
// -10 <= nums[i] <= 10 
// nums 中的所有元素 互不相同 
// 
//
// Related Topics 位运算 数组 回溯 👍 2239 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
public class _78_子集 {
    public List<List<Integer>> subsets(int[] nums) {
        //return this.backtracking(nums);

        return this.iteration(nums);
    }

    @FunctionalInterface
    interface QuadrConsumer<A, B, C, D> {
        void accept(A a, B b, C c, D d);
    }

    QuadrConsumer<Integer, int[], List<Integer>, List<List<Integer>>> dfs =
            (i, nums, subset, res) -> {
                res.add(new ArrayList<>(subset));
                if (i == nums.length) {
                    return;
                }

                for (int j = i; j < nums.length; j++) {
                    subset.addLast(nums[j]);
                    this.dfs.accept(j + 1, nums, subset, res);
                    subset.removeLast();
                }
            };

    List<List<Integer>> backtracking(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        this.dfs.accept(0, nums, new ArrayList<>(), res);
        return res;
    }

    List<List<Integer>> iteration(int[] nums) {
        List<List<Integer>> res = new ArrayList<>() {{
            this.add(new ArrayList<>());
        }};

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0, size = res.size(); j < size; j++) {
                ArrayList<Integer> subset = new ArrayList<>(res.get(j));
                subset.add(nums[i]);
                res.add(subset);
            }
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
