package org.example.leetcode.editor.cn;
//给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
//
// 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。 
//
// 
// 
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,2]
//输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
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
// 
//
// Related Topics 位运算 数组 回溯 👍 1190 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
public class _90_子集II {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        //return this.backtracking(nums);

        return this.iteration(nums);
    }

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
                    if (j > i && nums[j] == nums[j - 1]) {
                        continue;
                    }

                    subset.addLast(nums[j]);
                    this.dfs.accept(j + 1, nums, subset, res);
                    subset.removeLast();
                }
            };

    List<List<Integer>> backtracking(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        this.dfs.accept(0, nums, new ArrayList<>(), res);
        return res;
    }

    List<List<Integer>> iteration(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>() {{
            this.add(new ArrayList<>());
        }};
        int preSize = 0;
        int size = 0;

        for (int i = 0; i < nums.length; i++) {
            preSize = size;
            size = res.size();

            if (i > 0 && nums[i] != nums[i - 1]) {
                preSize = 0;
            }

            for (int j = preSize; j < size; j++) {
                ArrayList<Integer> subset = new ArrayList<>(res.get(j));
                subset.add(nums[i]);
                res.add(subset);
            }
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
