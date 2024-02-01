package org.example.leetcode.editor.cn;
//找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
//
// 
// 只使用数字1到9 
// 每个数字 最多使用一次 
// 
//
// 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。 
//
// 
//
// 示例 1: 
//
// 
//输入: k = 3, n = 7
//输出: [[1,2,4]]
//解释:
//1 + 2 + 4 = 7
//没有其他符合的组合了。 
//
// 示例 2: 
//
// 
//输入: k = 3, n = 9
//输出: [[1,2,6], [1,3,5], [2,3,4]]
//解释:
//1 + 2 + 6 = 9
//1 + 3 + 5 = 9
//2 + 3 + 4 = 9
//没有其他符合的组合了。 
//
// 示例 3: 
//
// 
//输入: k = 4, n = 1
//输出: []
//解释: 不存在有效的组合。
//在[1,9]范围内使用4个不同的数字，我们可以得到的最小和是1+2+3+4 = 10，因为10 > 1，没有有效的组合。
// 
//
// 
//
// 提示: 
//
// 
// 2 <= k <= 9 
// 1 <= n <= 60 
// 
//
// Related Topics 数组 回溯 👍 800 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
public class _216_组合总和III {
    public List<List<Integer>> combinationSum3(int k, int n) {
        //return this.backtracking(k, n);

        return this.subsetEnum(k, n);
    }

    @FunctionalInterface
    interface QuintConsumer<A, B, C, D, E> {
        void accept(A a, B b, C c, D d, E e);
    }

    QuintConsumer<Integer, Integer, Integer, List<Integer>, List<List<Integer>>> dfs =
            (idx, k, n, combine, res) -> {
                if (k == 0) {
                    if (n == 0) {
                        res.add(new ArrayList<>(combine));
                    }
                    return;
                }

                for (int i = idx; i <= 9; i++) {
                    if (n < i || (n == i && k != 1)) {
                        break;
                    }

                    combine.addLast(i);
                    this.dfs.accept(i + 1, k - 1, n - i, combine, res);
                    combine.removeLast();
                }
            };

    List<List<Integer>> backtracking(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        this.dfs.accept(1, k, n, new ArrayList<>(), res);
        return res;
    }

    @FunctionalInterface
    interface QuadrPredicate<A, B, C, D> {
        boolean test(A a, B b, C c, D d);
    }

    QuadrPredicate<Integer, Integer, Integer, List<Integer>> check =
            (mask, k, n, combine) -> {
                combine.clear();

                int sum = 0;
                for (int i = 0; i < 9; i++) {
                    if (((1 << i) & mask) != 0) {
                        sum += i + 1;
                        combine.add(i + 1);
                    }
                }

                return combine.size() == k && sum == n;
            };

    List<List<Integer>> subsetEnum(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();

        for (int mask = 0, max = 1 << 9; mask < max; mask++) {
            if (this.check.test(mask, k, n, combine)) {
                res.add(new ArrayList<>(combine));
            }
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
