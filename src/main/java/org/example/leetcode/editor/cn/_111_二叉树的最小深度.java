package org.example.leetcode.editor.cn;
//给定一个二叉树，找出其最小深度。
//
// 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。 
//
// 说明：叶子节点是指没有子节点的节点。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：root = [2,null,3,null,4,null,5,null,6]
//输出：5
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数的范围在 [0, 10⁵] 内 
// -1000 <= Node.val <= 1000 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 1141 👎 0

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Function;

//leetcode submit region begin(Prohibit modification and deletion)
public class _111_二叉树的最小深度 {
    public int minDepth(TreeNode root) {
        //return this.dfsRecur(root);
        //return this.dfsPreOrderIter1(root);
        //return this.dfsPreOrderIter2(root);
        //return this.dfsPreOrderIter3(root);
        return this.bfsIter(root);
    }

    Function<TreeNode, Integer> helper = root -> {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        if (root.left == null) {
            return this.helper.apply(root.right) + 1;
        }
        if (root.right == null) {
            return this.helper.apply(root.left) + 1;
        }

        return Math.min(this.helper.apply(root.left), this.helper.apply(root.right)) + 1;
    };

    int dfsRecur(TreeNode root) {
        return this.helper.apply(root);
    }

    int dfsPreOrderIter1(TreeNode _root) {
        if (_root == null) {
            return 0;
        }

        int min_depth = Integer.MAX_VALUE;
        Object[] root = new Object[]{_root, 1};
        Deque<Object[]> stack = new ArrayDeque<>();
        while (root[0] != null || !stack.isEmpty()) {
            while (root[0] != null) {
                TreeNode curr = (TreeNode) root[0];
                int level = (int) root[1];
                if (curr.left == null && curr.right == null && level < min_depth) {
                    min_depth = level;
                }

                root[0] = curr.left;
                root[1] = level + 1;
                stack.push(new Object[]{curr, level});
            }

            Object[] objs = stack.pop();
            TreeNode curr = (TreeNode) objs[0];
            int level = (int) objs[1];
            root[0] = curr.right;
            root[1] = level + 1;
        }

        return min_depth;
    }

    int dfsPreOrderIter2(TreeNode _root) {
        if (_root == null) {
            return 0;
        }

        int min_depth = Integer.MAX_VALUE;
        Object[] root = new Object[]{_root, 1};
        Deque<Object[]> stack = new ArrayDeque<>();
        while (root[0] != null || !stack.isEmpty()) {
            if (root[0] != null) {
                TreeNode curr = (TreeNode) root[0];
                int level = (int) root[1];
                if (curr.left == null && curr.right == null && level < min_depth) {
                    min_depth = level;
                }

                root[0] = curr.left;
                root[1] = level + 1;
                stack.push(new Object[]{curr, level});
            } else {
                Object[] objs = stack.pop();
                TreeNode curr = (TreeNode) objs[0];
                int level = (int) objs[1];
                root[0] = curr.right;
                root[1] = level + 1;
            }
        }


        return min_depth;
    }

    int dfsPreOrderIter3(TreeNode _root) {
        if (_root == null) {
            return 0;
        }

        int min_depth = Integer.MAX_VALUE;
        Deque<Object[]> stack = new ArrayDeque<>() {{
            this.push(new Object[]{_root, 1});
        }};
        while (!stack.isEmpty()) {
            Object[] objs = stack.pop();
            TreeNode curr = (TreeNode) objs[0];
            int level = (int) objs[1];
            if (curr.left == null && curr.right == null && level < min_depth) {
                min_depth = level;
            }

            if (curr.right != null) {
                stack.push(new Object[] {curr.right, level + 1});
            }
            if (curr.left != null) {
                stack.push(new Object[] {curr.left, level + 1});
            }
        }

        return min_depth;
    }

    int bfsIter(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int min_depth = 0;
        Deque<Object[]> queue = new ArrayDeque<>() {{
            this.addLast(new Object[]{root, 1});
        }};
        while (!queue.isEmpty()) {
            Object[] objs = queue.pollFirst();
            TreeNode curr = (TreeNode) objs[0];
            int level = (int) objs[1];
            if (curr.left == null && curr.right == null) {
                min_depth = level;
                break;
            }

            if (curr.left != null) {
                queue.addLast(new Object[]{curr.left, level + 1});
            }
            if (curr.right != null) {
                queue.addLast(new Object[]{curr.right, level + 1});
            }
        }

        return min_depth;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
