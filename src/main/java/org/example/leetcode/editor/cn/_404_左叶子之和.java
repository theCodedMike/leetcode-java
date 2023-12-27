package org.example.leetcode.editor.cn;
//给定二叉树的根节点 root ，返回所有左叶子之和。
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入: root = [3,9,20,null,null,15,7] 
//输出: 24 
//解释: 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
// 
//
// 示例 2: 
//
// 
//输入: root = [1]
//输出: 0
// 
//
// 
//
// 提示: 
//
// 
// 节点数在 [1, 1000] 范围内 
// -1000 <= Node.val <= 1000 
// 
//
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 687 👎 0

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.BiFunction;

//leetcode submit region begin(Prohibit modification and deletion)
public class _404_左叶子之和 {
    public int sumOfLeftLeaves(TreeNode root) {
        //return this.dfsRecur1(root);
        //return this.dfsRecur2(root);
        //return this.dfsIter(root);
        return this.bfsIter(root);
    }

    @FunctionalInterface
    interface TriConsumer<A, B, C> {
        void accept(A a, B b, C c);
    }

    TriConsumer<TreeNode, Boolean, int[]> recur1 = (root, isLeft, sum) -> {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            if (isLeft) {
                sum[0] += root.val;
            }
        } else {
            if (root.left != null) {
                this.recur1.accept(root.left, true, sum);
            }
            if (root.right != null) {
                this.recur1.accept(root.right, false, sum);
            }
        }
    };

    int dfsRecur1(TreeNode root) {
        int[] sum = new int[]{0};
        this.recur1.accept(root, false, sum);
        return sum[0];
    }

    BiFunction<TreeNode, Boolean, Integer> recur2 = (root, isLeft) -> {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            if (isLeft) {
                return root.val;
            } else {
                return 0;
            }
        } else {
            return this.recur2.apply(root.left, true) + this.recur2.apply(root.right, false);
        }
    };

    int dfsRecur2(TreeNode root) {
        return this.recur2.apply(root, false);
    }

    int dfsIter(TreeNode root) {
        int sum = 0;

        if (root != null) {
            Deque<Object[]> stack = new ArrayDeque<>() {{
                this.push(new Object[]{root, false});
            }};

            while (!stack.isEmpty()) {
                Object[] objs = stack.pop();
                TreeNode curr = (TreeNode) objs[0];
                boolean isLeft = (boolean) objs[1];

                if (curr.left == null && curr.right == null && isLeft) {
                    sum += curr.val;
                }

                if (curr.right != null) {
                    stack.push(new Object[]{curr.right, false});
                }
                if (curr.left != null) {
                    stack.push(new Object[]{curr.left, true});
                }
            }
        }

        return sum;
    }

    int bfsIter(TreeNode root) {
        int sum = 0;

        if (root != null) {
            Deque<Object[]> queue = new ArrayDeque<>() {{
                this.addLast(new Object[]{root, false});
            }};

            while (!queue.isEmpty()) {
                Object[] objs = queue.removeFirst();
                TreeNode curr = (TreeNode) objs[0];
                boolean isLeft = (boolean) objs[1];

                if (curr.left == null && curr.right == null && isLeft) {
                    sum += curr.val;
                }
                if (curr.left != null) {
                    queue.addLast(new Object[]{curr.left, true});
                }
                if (curr.right != null) {
                    queue.addLast(new Object[]{curr.right, false});
                }
            }
        }

        return sum;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
