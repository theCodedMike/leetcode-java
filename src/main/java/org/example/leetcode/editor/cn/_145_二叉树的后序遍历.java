package org.example.leetcode.editor.cn;
//给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [1,null,2,3]
//输出：[3,2,1]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数目在范围 [0, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶：递归算法很简单，你可以通过迭代算法完成吗？ 
//
// Related Topics 栈 树 深度优先搜索 二叉树 👍 1127 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.function.BiConsumer;


public class _145_二叉树的后序遍历 {
    public List<Integer> postorderTraversal(TreeNode root) {
        //return this.recursionImpl(root);

        //return this.iterationImpl1(root);
        //return this.iterationImpl2(root);
        //return this.iterationImpl3(root);
        //return this.iterationImpl4(root);

        return this.morrisImpl(root);
    }

    BiConsumer<TreeNode, List<Integer>> postorder = (root, res) -> {
        if (root == null) {
            return;
        }

        this.postorder.accept(root.left, res);
        this.postorder.accept(root.right, res);
        res.add(root.val);
    };
    List<Integer> recursionImpl(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        this.postorder.accept(root, res);
        return res;
    }

    List<Integer> iterationImpl1(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (root != null) {
            Deque<TreeNode> stack = new ArrayDeque<>() {{
                this.add(root);
            }};

            while (!stack.isEmpty()) {
                TreeNode curr = stack.pop();
                res.add(curr.val);
                if (curr.left != null) {
                    stack.push(curr.left);
                }
                if (curr.right != null) {
                    stack.push(curr.right);
                }
            }
        }

        Collections.reverse(res);
        return res;
    }

    List<Integer> iterationImpl2(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode lastVisited = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            TreeNode curr = stack.pop();
            if (curr.right != null && !curr.right.equals(lastVisited)) {
                root = curr.right;
                stack.push(curr);
            } else {
                res.add(curr.val);
                lastVisited = curr;
            }
        }

        return res;
    }

    List<Integer> iterationImpl3(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode lastVisited = null;
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode curr = stack.pop();
                if (curr.right != null && !curr.right.equals(lastVisited)) {
                    root = curr.right;
                    stack.push(curr);
                } else {
                    res.add(curr.val);
                    lastVisited = curr;
                }
            }
        }

        return res;
    }

    List<Integer> iterationImpl4(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (root != null) {
            Deque<Object> stack = new ArrayDeque<>() {{
                this.add(root);
            }};

            while (!stack.isEmpty()) {
                Object curr = stack.pop();
                switch (curr) {
                    case TreeNode node -> {
                        stack.push(node.val);
                        if (node.right != null) {
                            stack.push(node.right);
                        }
                        if (node.left != null) {
                            stack.push(node.left);
                        }
                    }
                    case Integer val -> res.add(val);
                    default -> throw new IllegalStateException("Unexpected value: " + curr);
                }
            }
        }

        return res;
    }

    BiConsumer<TreeNode, List<Integer>> addPath = (root, res) -> {
        List<Integer> path = new ArrayList<>();

        while (root != null) {
            path.add(root.val);
            root = root.right;
        }

        res.addAll(path.reversed());
    };
    List<Integer> morrisImpl(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode curr = root;

        while (curr != null) {
            TreeNode left = curr.left;
            if (left != null) {
                TreeNode prev = left;
                while (prev.right != null && prev.right != curr) {
                    prev = prev.right;
                }

                if (prev.right == null) {
                    prev.right = curr;
                    curr = left;
                } else {
                    prev.right = null;
                    this.addPath.accept(left, res);
                    curr = curr.right;
                }
            } else {
                curr = curr.right;
            }
        }
        this.addPath.accept(root, res);

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
