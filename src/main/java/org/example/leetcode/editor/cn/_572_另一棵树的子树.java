package org.example.leetcode.editor.cn;
// 给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。如果存在，返回 true ；否则
//，返回 false 。 
// 
// 
//
// 二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [3,4,5,1,2], subRoot = [4,1,2]
//输出：true
// 
//
// 示例 2： 
// 
// 
//输入：root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// root 树上的节点数量范围是 [1, 2000] 
// subRoot 树上的节点数量范围是 [1, 1000] 
// -10⁴ <= root.val <= 10⁴ 
// -10⁴ <= subRoot.val <= 10⁴ 
// 
//
// Related Topics 树 深度优先搜索 二叉树 字符串匹配 哈希函数 👍 1000 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;

//leetcode submit region begin(Prohibit modification and deletion)
public class _572_另一棵树的子树 {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        //return this.dfsRecurMatch(root, subRoot);

        //return this.dfsSequenceMatch(root, subRoot);

        return this.treeHash(root, subRoot);
    }


    BiFunction<TreeNode, TreeNode, Boolean> check = (root, subRoot) -> {
        if (root == null && subRoot == null) {
            return true;
        } else if (root != null && subRoot != null) {
            if (root.val != subRoot.val) {
                return false;
            }

            return this.check.apply(root.left, subRoot.left)
                    && this.check.apply(root.right, subRoot.right);
        } else {
            return false;
        }
    };
    BiFunction<TreeNode, TreeNode, Boolean> dfs = (root, subRoot) -> {
        if (root == null) {
            return false;
        }

        return this.check.apply(root, subRoot)
                || this.dfs.apply(root.left, subRoot)
                || this.dfs.apply(root.right, subRoot);
    };

    /**
     * 时间复杂度：O(|r| * |s|)
     * 空间复杂度：O(max(dr, ds))
     */
    boolean dfsRecurMatch(TreeNode root, TreeNode subRoot) {
        return this.dfs.apply(root, subRoot);
    }


    Function<TreeNode, Integer> getMaxElem = (root) -> {
        if (root == null) {
            return Integer.MIN_VALUE;
        }

        int maxChild = Math.max(this.getMaxElem.apply(root.left), this.getMaxElem.apply(root.right));

        return Math.max(maxChild, root.val);
    };


    @FunctionalInterface
    interface QuadrConsumer<A, B, C, D> {
        void accept(A a, B b, C c, D d);
    }
    QuadrConsumer<TreeNode, Integer, Integer, List<Integer>> getDfsOrder = (root, lNull, rNull, vals) -> {
        if (root == null) {
            return;
        }

        vals.add(root.val);

        if (root.left != null) {
            this.getDfsOrder.accept(root.left, lNull, rNull, vals);
        } else {
            vals.add(lNull);
        }
        if (root.right != null) {
            this.getDfsOrder.accept(root.right, lNull, rNull, vals);
        } else {
            vals.add(rNull);
        }
    };

    BiPredicate<List<Integer>, List<Integer>> kmp = (rootVals, subVals) -> {
        int rootSize = rootVals.size();
        int subSize = subVals.size();
        int[] fail = new int[subSize];
        Arrays.fill(fail, -1);

        for (int i = 1, j = -1; i < subSize; i++) {
            while (j != -1 && !Objects.equals(subVals.get(i), subVals.get(j + 1))) {
                j = fail[j];
            }
            if (Objects.equals(subVals.get(i), subVals.get(j + 1))) {
                j++;
            }
            fail[i] = j;
        }

        for (int i = 0, j = -1; i < rootSize; i++) {
            while (j != -1 && !Objects.equals(rootVals.get(i), subVals.get(j + 1))) {
                j = fail[j];
            }
            if (Objects.equals(rootVals.get(i), subVals.get(j + 1))) {
                j++;
            }
            if (j + 1 == subSize) {
                return true;
            }
        }

        return false;
    };

    /**
     * 时间复杂度：O(|r| + |s|)
     * 空间复杂度：O(|r| + |s|)
     */
    boolean dfsSequenceMatch(TreeNode root, TreeNode subRoot) {
        int max = Math.max(this.getMaxElem.apply(root), this.getMaxElem.apply(subRoot));
        int lNull = max + 1;
        int rNull = max + 2;

        List<Integer> rootVals = new ArrayList<>();
        this.getDfsOrder.accept(root, lNull, rNull, rootVals);
        List<Integer> subVals = new ArrayList<>();
        this.getDfsOrder.accept(subRoot, lNull, rNull, subVals);

        return this.kmp.test(rootVals, subVals);
    }

    boolean treeHash(TreeNode root, TreeNode subRoot) {
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
