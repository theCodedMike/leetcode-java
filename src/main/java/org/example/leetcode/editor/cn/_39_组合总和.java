package org.example.leetcode.editor.cn;
//给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的
// 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。 
//
// candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。 
//
// 对于给定的输入，保证和为 target 的不同组合数少于 150 个。 
//
// 
//
// 示例 1： 
//
// 
//输入：candidates = [2,3,6,7], target = 7
//输出：[[2,2,3],[7]]
//解释：
//2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
//7 也是一个候选， 7 = 7 。
//仅有这两种组合。 
//
// 示例 2： 
//
// 
//输入: candidates = [2,3,5], target = 8
//输出: [[2,2,2,2],[2,3,3],[3,5]] 
//
// 示例 3： 
//
// 
//输入: candidates = [2], target = 1
//输出: []
// 
//
// 
//
// 提示： 
//
// 
// 1 <= candidates.length <= 30 
// 2 <= candidates[i] <= 40 
// candidates 的所有元素 互不相同 
// 1 <= target <= 40 
// 
//
// Related Topics 数组 回溯 👍 2711 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
public class _39_组合总和 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        //return this.backtracking1(candidates, target);

        return this.backtracking2(candidates, target);
    }


    @FunctionalInterface
    interface QuintConsumer<A, B, C, D, E> {
        void accept(A a, B b, C c, D d, E e);
    }

    QuintConsumer<Integer, int[], Integer, List<Integer>, List<List<Integer>>> backtrack1 =
            (idx, candidates, target, combine, res) -> {
                if (target < 0) {
                    return;
                }

                if (target == 0) {
                    res.add(new ArrayList<>(combine));
                    return;
                }

                for (int i = idx; i < candidates.length; i++) {
                    combine.addLast(candidates[i]);
                    this.backtrack1.accept(i, candidates, target - candidates[i], combine, res);
                    combine.removeLast();
                }
            };

    List<List<Integer>> backtracking1(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();

        this.backtrack1.accept(0, candidates, target, new ArrayList<>(), res);

        return res;
    }

    QuintConsumer<Integer, int[], Integer, List<Integer>, List<List<Integer>>> backtrack2 =
            (idx, candidates, target, combine, res) -> {
                if (idx == candidates.length) {
                    return;
                }

                if (target == 0) {
                    res.add(new ArrayList<>(combine));
                    return;
                }

                this.backtrack2.accept(idx + 1, candidates, target, combine, res);

                if (target - candidates[idx] >= 0) {
                    combine.addLast(candidates[idx]);
                    this.backtrack2.accept(idx, candidates, target - candidates[idx], combine, res);
                    combine.removeLast();
                }
            };

    List<List<Integer>> backtracking2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();

        this.backtrack2.accept(0, candidates, target, new ArrayList<>(), res);

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
