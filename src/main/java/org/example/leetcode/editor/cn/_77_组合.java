package org.example.leetcode.editor.cn;
//给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
//
// 你可以按 任何顺序 返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 4, k = 2
//输出：
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
//
// 示例 2： 
//
// 
//输入：n = 1, k = 1
//输出：[[1]] 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 20 
// 1 <= k <= n 
// 
//
// Related Topics 回溯 👍 1577 👎 0


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
public class _77_组合 {
    public List<List<Integer>> combine(int n, int k) {
        return this.backtracking(n, k);

        //return this.combinationEnumRecur(n, k);

        //return this.combinationEnumIter(n, k);
    }

    @FunctionalInterface
    interface QuintConsumer<A, B, C, D, E> {
        void accept(A a, B b, C c, D d, E e);
    }

    QuintConsumer<Integer, Integer, Integer, List<Integer>, List<List<Integer>>> recur = (start, n, k, path, res) -> {
        // 剪枝
        if (n - start + 1 + path.size() < k) {
            return;
        }

        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i <= n; i++) {
            path.add(i);
            this.recur.accept(i + 1, n, k, path, res);
            path.removeLast();
        }
    };

    List<List<Integer>> backtracking(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();

        this.recur.accept(1, n, k, new ArrayList<>(), res);

        return res;
    }


    QuintConsumer<Integer, Integer, Integer, List<Integer>, List<List<Integer>>> dfs = (start, n, k, path, res) -> {
        if (n - start + 1 + path.size() < k) {
            return;
        }

        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }

        path.add(start);
        this.dfs.accept(start + 1, n, k, path, res);
        path.removeLast();
        this.dfs.accept(start + 1, n, k, path, res);
    };

    List<List<Integer>> combinationEnumRecur(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        this.dfs.accept(1, n, k, new ArrayList<>(), res);
        return res;
    }

    List<List<Integer>> combinationEnumIter(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>(k + 1);
        for (int i = 1; i <= k; i++) {
            path.add(i);
        }
        path.add(n + 1);

        int j = 0;
        while (j < k) {
            res.add(new ArrayList<>(path.subList(0, k)));
            j = 0;

            while (j < k && path.get(j) + 1 == path.get(j + 1)) {
                path.set(j, j + 1);
                j++;
            }

            path.set(j, path.get(j) + 1);
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
