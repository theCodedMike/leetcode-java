package org.example.leetcode.editor.cn;
//给定一个非空二叉树的根节点
// root , 以数组的形式返回每一层节点的平均值。与实际答案相差 10⁻⁵ 以内的答案可以被接受。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：[3.00000,14.50000,11.00000]
//解释：第 0 层的平均值为 3,第 1 层的平均值为 14.5,第 2 层的平均值为 11 。
//因此返回 [3, 14.5, 11] 。
// 
//
// 示例 2: 
//
// 
//
// 
//输入：root = [3,9,20,15,7]
//输出：[3.00000,14.50000,11.00000]
// 
//
// 
//
// 提示： 
//
// 
// 
//
// 
// 树中节点数量在 [1, 10⁴] 范围内 
// -2³¹ <= Node.val <= 2³¹ - 1 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 466 👎 0


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

//leetcode submit region begin(Prohibit modification and deletion)
public class _637_二叉树的层平均值 {
    public List<Double> averageOfLevels(TreeNode root) {
        //return this.dfsRecursion(root);
        //return this.dfsIteration(root);

        //return this.bfsIteration1(root);
        return this.bfsIteration2(root);
    }

    @FunctionalInterface
    interface TriConsumer<A, B, C> {
        void accept(A a, B b, C c);
    }

    TriConsumer<TreeNode, Integer, List<Long[]>> preOrder = (root, level, levelSum) -> {
        if (root == null) {
            return;
        }

        if (level == levelSum.size()) {
            levelSum.add(new Long[]{0L, 0L});
        }
        levelSum.get(level)[0] += 1;
        levelSum.get(level)[1] += root.val;

        if (root.left != null) {
            this.preOrder.accept(root.left, level + 1, levelSum);
        }
        if (root.right != null) {
            this.preOrder.accept(root.right, level + 1, levelSum);
        }
    };

    List<Double> dfsRecursion(TreeNode root) {
        List<Long[]> levelSum = new ArrayList<>();
        this.preOrder.accept(root, 0, levelSum);
        return levelSum.stream().map(vals -> (double) vals[1] / vals[0]).collect(Collectors.toList());
    }

    List<Double> dfsIteration(TreeNode root) {
        List<Long[]> levelSum = new ArrayList<>();

        Deque<Object[]> stack = new ArrayDeque<>() {{
            this.push(new Object[]{root, 0});
        }};
        while (!stack.isEmpty()) {
            Object[] pop = stack.pop();
            Integer level = (Integer) pop[1];
            Object curr = pop[0];
            switch (curr) {
                case TreeNode node -> {
                    if (node.right != null) {
                        stack.push(new Object[]{node.right, level + 1});
                    }
                    if (node.left != null) {
                        stack.push(new Object[]{node.left, level + 1});
                    }
                    stack.push(new Object[]{node.val, level});
                }
                case Integer val -> {
                    if (level == levelSum.size()) {
                        levelSum.add(new Long[]{0L, 0L});
                    }
                    levelSum.get(level)[0] += 1;
                    levelSum.get(level)[1] += val;
                }
                default -> throw new IllegalStateException("Unexpected value: " + curr);
            }
        }

        return levelSum.stream().map(vals -> (double) vals[1] / vals[0]).collect(Collectors.toList());
    }

    List<Double> bfsIteration1(TreeNode root) {
        List<Double> res = new ArrayList<>();

        ArrayDeque<Object[]> queue = new ArrayDeque<>() {{
            this.addLast(new Object[]{root, 0});
        }};
        int levelCount = 0;
        long levelSum = 0L;
        int currLevel = 0;
        while (!queue.isEmpty()) {
            Object[] objs = queue.removeFirst();
            TreeNode curr = (TreeNode) objs[0];
            int level = (Integer) objs[1];

            if (level != currLevel) {
                res.add((double) levelSum / levelCount);
                levelSum = 0L;
                levelCount = 0;
            }
            currLevel = level;
            levelCount += 1;
            levelSum += curr.val;

            if (curr.left != null) {
                queue.addLast(new Object[]{curr.left, level + 1});
            }
            if (curr.right != null) {
                queue.addLast(new Object[]{curr.right, level + 1});
            }
        }
        res.add((double) levelSum / levelCount);

        return res;
    }

    List<Double> bfsIteration2(TreeNode root) {
        List<Double> res = new ArrayList<>();

        ArrayDeque<TreeNode> queue = new ArrayDeque<>() {{
            this.addLast(root);
        }};
        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            long levelSum = 0L;
            for (int i = 1; i <= levelSize; i++) {
                TreeNode curr = queue.removeFirst();
                levelSum += curr.val;

                if (curr.left != null) {
                    queue.addLast(curr.left);
                }
                if (curr.right != null) {
                    queue.addLast(curr.right);
                }
            }
            res.add((double) levelSum / levelSize);
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
