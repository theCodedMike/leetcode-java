package org.example.leetcode.editor.cn;
//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 8 
// -10 <= nums[i] <= 10 
// 
//
// Related Topics 数组 回溯 👍 1530 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
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
                    if (i > 0 && nums[i] == nums[i - 1]) {
                        continue;
                    }

                    permute.addLast(nums[i]);
                    nums[i] = Integer.MIN_VALUE;
                    this.dfs.accept(nums, permute, res);
                    nums[i] = permute.removeLast();
                }
            };

    List<List<Integer>> backtracking(int[] nums) {
        Arrays.sort(nums);
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
            if (i > 0 && nums.get(i) == nums.get(i - 1)) {
                continue;
            }
            Integer num = nums.get(i);
            int finalI = i;
            List<Integer> newNums = IntStream.range(0, nums.size())
                    .filter(idx -> idx != finalI)
                    .mapToObj(nums::get)
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
        Arrays.sort(_nums);
        List<Integer> nums = Arrays.stream(_nums).boxed().collect(Collectors.toList());
        return this.recur.apply(nums);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
