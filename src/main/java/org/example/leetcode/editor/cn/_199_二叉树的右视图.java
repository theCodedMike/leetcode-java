package org.example.leetcode.editor.cn;
//给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入: [1,2,3,null,5,null,4]
//输出: [1,3,4]
// 
//
// 示例 2: 
//
// 
//输入: [1,null,3]
//输出: [1,3]
// 
//
// 示例 3: 
//
// 
//输入: []
//输出: []
// 
//
// 
//
// 提示: 
//
// 
// 二叉树的节点个数的范围是 [0,100] 
// 
// -100 <= Node.val <= 100 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 1005 👎 0


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
public class _199_二叉树的右视图 {
    public List<Integer> rightSideView(TreeNode root) {
        //return this.dfsRecursion(root);
        //return this.dfsIteration1(root);
        //return this.dfsIteration2(root);
        //return this.dfsIteration3(root);
        //return this.bfsIteration1(root);
        return this.bfsIteration2(root);
    }

    @FunctionalInterface
    interface TriConsumer<X, Y, Z> {
        void accept(X x, Y y, Z z);
    }

    TriConsumer<TreeNode, Integer, List<Integer>> recurImpl = (root, level, res) -> {
        if (root == null) {
            return;
        }

        if (level == res.size()) {
            res.add(root.val);
        }
        if (root.right != null) {
            this.recurImpl.accept(root.right, level + 1, res);
        }
        if (root.left != null) {
            this.recurImpl.accept(root.left, level + 1, res);
        }
    };

    List<Integer> dfsRecursion(TreeNode _root) {
        List<Integer> res = new ArrayList<>();
        this.recurImpl.accept(_root, 0, res);
        return res;
    }

    List<Integer> dfsIteration1(TreeNode _root) {
        List<Integer> res = new ArrayList<>();

        Deque<Object[]> stack = new ArrayDeque<>();
        Object[] root = new Object[]{_root, 0};
        while (root[0] != null || !stack.isEmpty()) {
            TreeNode curr = (TreeNode) root[0];
            int curr_level = (int) root[1];
            if (curr != null) {
                if (curr_level == res.size()) {
                    res.add(curr.val);
                }
                stack.push(new Object[]{curr, curr_level});
                // go Right
                root = new Object[]{curr.right, curr_level + 1};
            } else {
                Object[] top = stack.pop();
                TreeNode top_node = (TreeNode) top[0];
                int top_level = (int) top[1];
                root = new Object[]{top_node.left, top_level + 1};
            }
        }

        return res;
    }

    List<Integer> dfsIteration2(TreeNode _root) {
        List<Integer> res = new ArrayList<>();

        Deque<Object[]> stack = new ArrayDeque<>();
        Object[] root = new Object[]{_root, 0};
        while (root[0] != null || !stack.isEmpty()) {
            while (root[0] != null) {
                TreeNode curr = (TreeNode) root[0];
                int curr_level = (int) root[1];

                if (curr_level == res.size()) {
                    res.add(curr.val);
                }
                stack.push(new Object[]{curr, curr_level});
                // go Right
                root = new Object[]{curr.right, curr_level + 1};
            }

            Object[] top = stack.pop();
            TreeNode top_node = (TreeNode) top[0];
            int top_level = (int) top[1];
            root = new Object[]{top_node.left, top_level + 1};
        }

        return res;
    }

    List<Integer> dfsIteration3(TreeNode _root) {
        List<Integer> res = new ArrayList<>();

        if (_root != null) {
            Deque<Object[]> stack = new ArrayDeque<>() {{
                this.push(new Object[]{_root, 0});
            }};
            while (!stack.isEmpty()) {
                Object[] pop = stack.pop();
                if (pop instanceof Integer[] vals) {
                    if (vals[1] == res.size()) {
                        res.add(vals[0]);
                    }
                } else {
                    TreeNode curr = (TreeNode) pop[0];
                    int curr_level = (int) pop[1];
                    // Left
                    if (curr.left != null) {
                        stack.push(new Object[]{curr.left, curr_level + 1});
                    }
                    // Right
                    if (curr.right != null) {
                        stack.push(new Object[]{curr.right, curr_level + 1});
                    }
                    // Root
                    stack.push(new Integer[]{curr.val, curr_level});
                }
            }
        }

        return res;
    }

    List<Integer> bfsIteration1(TreeNode _root) {
        List<Integer> res = new ArrayList<>();

        if (_root != null) {
            Deque<TreeNode> queue = new ArrayDeque<>() {{
                this.addLast(_root);
            }};
            while (!queue.isEmpty()) {
                int levelSize = queue.size();

                for (int i = 1; i <= levelSize; i++) {
                    TreeNode curr = queue.removeFirst();
                    // if you enqueue left node first, here should be i == levelSize
                    // if you enqueue right node first, here should be i == 0
                    if (i == levelSize) {
                        res.add(curr.val);
                    }

                    if (curr.left != null) {
                        queue.addLast(curr.left);
                    }
                    if (curr.right != null) {
                        queue.addLast(curr.right);
                    }
                }
            }
        }

        return res;
    }

    List<Integer> bfsIteration2(TreeNode _root) {
        List<Integer> res = new ArrayList<>();

        if (_root != null) {
            Deque<Object[]> queue = new ArrayDeque<>() {{
                this.addLast(new Object[]{_root, 0});
            }};
            while (!queue.isEmpty()) {
                Object[] pop = queue.removeFirst();
                TreeNode curr = (TreeNode) pop[0];
                int curr_level = (int) pop[1];

                if (curr_level == res.size()) {
                    res.add(curr.val);
                }
                if (curr.right != null) {
                    queue.addLast(new Object[]{curr.right, curr_level + 1});
                }
                if (curr.left != null) {
                    queue.addLast(new Object[]{curr.left, curr_level + 1});
                }
            }
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
