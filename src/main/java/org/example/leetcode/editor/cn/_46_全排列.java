package org.example.leetcode.editor.cn;
//给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1]
//输出：[[0,1],[1,0]]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1]
//输出：[[1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 6 
// -10 <= nums[i] <= 10 
// nums 中的所有整数 互不相同 
// 
//
// Related Topics 数组 回溯 👍 2809 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//leetcode submit region begin(Prohibit modification and deletion)
public class _46_全排列 {
    public List<List<Integer>> permute(int[] nums) {
        //return this.backtracking(nums);

        return this.recursion(nums);
    }

    @FunctionalInterface
    interface TriConsumer<A, B, C> {
        void accept(A a, B b, C c);
    }

    TriConsumer<int[], List<Integer>, List<List<Integer>>> dfs =
            (nums, permute, res) -> {
                if (permute.size() == nums.length) {
                    res.add(new ArrayList<>(permute));
                    return;
                }

                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] == Integer.MIN_VALUE) {
                        continue;
                    }

                    permute.addLast(nums[i]);
                    nums[i] = Integer.MIN_VALUE;
                    this.dfs.accept(nums, permute, res);
                    nums[i] = permute.removeLast();
                }
            };

    List<List<Integer>> backtracking(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        this.dfs.accept(nums, new ArrayList<>(), res);
        return res;
    }

    Function<List<Integer>, List<List<Integer>>> recur = (nums) -> {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.size() <= 1) {
            res.add(nums);
            return res;
        }

        for (int i = 0; i < nums.size(); i++) {
            int num = nums.get(i);
            int finalI = i;
            List<Integer> newNums =
                    IntStream.range(0, nums.size())
                            .filter(idx -> idx != finalI)
                            .boxed()
                            .map(nums::get)
                            .collect(Collectors.toList());
            List<List<Integer>> perms = this.recur.apply(newNums);
            for (List<Integer> perm : perms) {
                perm.add(num);
                res.add(perm);
            }
        }

        return res;
    };

    List<List<Integer>> recursion(int[] _nums) {
        List<Integer> nums = Arrays.stream(_nums).boxed().collect(Collectors.toList());
        return this.recur.apply(nums);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
