package org.example.leetcode.editor.cn;
//给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [1,null,2,3]
//输出：[1,2,3]
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
// 示例 4： 
// 
// 
//输入：root = [1,2]
//输出：[1,2]
// 
//
// 示例 5： 
// 
// 
//输入：root = [1,null,2]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶：递归算法很简单，你可以通过迭代算法完成吗？ 
//
// Related Topics 栈 树 深度优先搜索 二叉树 👍 1186 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.function.BiConsumer;

public class _144_二叉树的前序遍历 {
    public List<Integer> preorderTraversal(TreeNode root) {
        //return this.recursionImpl(root);

        //return this.iterationImpl1(root);
        //return this.iterationImpl2(root);
        //return this.iterationImpl3(root);
        //return this.iterationImpl4(root);

        return this.morrisImpl(root);
    }

    BiConsumer<TreeNode, List<Integer>> preorder = (root, res) -> {
        if (root == null) {
            return;
        }

        res.add(root.val); // 访问根节点
        this.preorder.accept(root.left, res);  // 遍历左子树
        this.preorder.accept(root.right, res); // 遍历右子树
    };
    List<Integer> recursionImpl(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        this.preorder.accept(root, res);
        return res;
    }

    List<Integer> iterationImpl1(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (root != null) {
            Deque<TreeNode> stack = new ArrayDeque<>() {{
                this.push(root);
            }};

            while (!stack.isEmpty()) {
                TreeNode curr = stack.pop();
                res.add(curr.val);

                if (curr.right != null) {
                    stack.push(curr.right);
                }
                if (curr.left != null) {
                    stack.push(curr.left);
                }
            }
        }

        return res;
    }

    List<Integer> iterationImpl2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                res.add(root.val);
                stack.push(root);
                root = root.left;
            }

            TreeNode curr = stack.pop();
            root = curr.right;
        }

        return res;
    }

    List<Integer> iterationImpl3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                res.add(root.val);
                stack.push(root);
                root = root.left;
            } else {
                TreeNode curr = stack.pop();
                root = curr.right;
            }
        }

        return res;
    }

    List<Integer> iterationImpl4(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (root != null) {
            Deque<Object> stack = new ArrayDeque<>() {{
                this.push(root);
            }};
            while (!stack.isEmpty()) {
                Object curr = stack.pop();
                switch (curr) {
                    case TreeNode node -> {
                        if (node.right != null) {
                            stack.push(node.right);
                        }
                        if (node.left != null) {
                            stack.push(node.left);
                        }
                        stack.push(node.val);
                    }
                    case Integer val -> res.add(val);
                    default -> throw new IllegalStateException("Unexpected value: " + curr);
                }
            }
        }

        return res;
    }

    List<Integer> morrisImpl(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        while (root != null) {
            TreeNode left = root.left;
            if (left != null) {
                TreeNode prev = left;
                while (prev.right != null && prev.right != root) {
                    prev = prev.right;
                }

                if (prev.right == null) {
                    res.add(root.val);
                    prev.right = root;
                    root = left;
                } else {
                    prev.right = null;
                    root = root.right;
                }
            } else {
                res.add(root.val);
                root = root.right;
            }
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
