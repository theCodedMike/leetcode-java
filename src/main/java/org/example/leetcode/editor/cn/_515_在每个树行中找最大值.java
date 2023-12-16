package org.example.leetcode.editor.cn;
//给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
//
// 
//
// 示例1： 
//
// 
//
// 
//输入: root = [1,3,2,5,3,null,9]
//输出: [1,3,9]
// 
//
// 示例2： 
//
// 
//输入: root = [1,2,3]
//输出: [1,3]
// 
//
// 
//
// 提示： 
//
// 
// 二叉树的节点个数的范围是 [0,10⁴] 
// 
// -2³¹ <= Node.val <= 2³¹ - 1 
// 
//
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 348 👎 0


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
public class _515_在每个树行中找最大值 {
    public List<Integer> largestValues(TreeNode root) {
        //return this.dfsRecurPreOrder(root);
        //return this.dfsIterPreOrder3(root);
        return this.bfsIter2(root);
    }

    @FunctionalInterface
    interface TriConsumer<P1, P2, P3> {
        void accept(P1 p1, P2 p2, P3 p3);
    }

    TriConsumer<TreeNode, Integer, List<Integer>> preOrder = (root, level, res) -> {
        if (root == null) {
            return;
        }
        if (level == res.size()) {
            res.add(Integer.MIN_VALUE);
        }
        int currVal = root.val;
        if (res.get(level) < currVal) {
            res.set(level, currVal);
        }

        if (root.left != null) {
            this.preOrder.accept(root.left, level + 1, res);
        }
        if (root.right != null) {
            this.preOrder.accept(root.right, level + 1, res);
        }
    };

    /**
     * DFS - Recursion(Pre-Order)
     */
    List<Integer> dfsRecurPreOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        this.preOrder.accept(root, 0, res);
        return res;
    }

    /**
     * DFS - Iteration(Pre-Order)
     */
    List<Integer> dfsIterPreOrder3(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (root != null) {
            Deque<Object[]> stack = new ArrayDeque<>() {{
                this.push(new Object[]{root, 0});
            }};
            
            while (!stack.isEmpty()) {
                Object[] pop = stack.pop();
                Object curr = pop[0];
                int level = (int) pop[1];
                
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
                    case Integer currVal -> {
                        if (level == res.size()) {
                            res.add(Integer.MIN_VALUE);
                        }
                        if (res.get(level) < currVal) {
                            res.set(level, currVal);
                        }
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + curr);
                }
            }
        }

        return res;
    }

    /**
     * BFS - Iteration(Level Order)
     */
    List<Integer> bfsIter2(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (root != null) {
            Deque<TreeNode> queue = new ArrayDeque<>() {{
                this.addLast(root);
            }};

            while (!queue.isEmpty()) {
                int levelSize = queue.size();
                int levelLargest = Integer.MIN_VALUE;

                for (int i = 0; i < levelSize; i++) {
                    TreeNode curr = queue.removeFirst();
                    if (curr.val > levelLargest) {
                        levelLargest = curr.val;
                    }

                    if (curr.left != null) {
                        queue.addLast(curr.left);
                    }
                    if (curr.right != null) {
                        queue.addLast(curr.right);
                    }
                }

                res.add(levelLargest);
            }
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
