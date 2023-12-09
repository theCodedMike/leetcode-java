package org.example.leetcode.editor.cn;
//给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [1,null,2,3]
//输出：[1,3,2]
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
// 树中节点数目在范围 [0, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
//
// Related Topics 栈 树 深度优先搜索 二叉树 👍 1984 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class _94_二叉树的中序遍历 {
    public List<Integer> inorderTraversal(TreeNode root) {
        //List<Integer> res = new ArrayList<>();
        //this.recursionImpl(root, res);
        //return res;

        //return this.iterationImpl1(root);
        //return this.iterationImpl2(root);
        return this.iterationImpl3(root);
    }

    void recursionImpl(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }

        this.recursionImpl(root.left, res);
        res.add(root.val);
        this.recursionImpl(root.right, res);
    }

    List<Integer> iterationImpl1(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        Deque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            TreeNode curr = stack.pop();
            root = curr.right;
            res.add(curr.val);
        }

        return res;
    }

    List<Integer> iterationImpl2(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        Deque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode curr = stack.pop();
                root = curr.right;
                res.add(curr.val);
            }
        }

        return res;
    }

    List<Integer> iterationImpl3(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (root != null) {
            Deque<Object> stack = new ArrayDeque<>() {{
                this.push(root);
            }};
            while (!stack.isEmpty()) {
                switch (stack.pop()) {
                    case TreeNode node -> {
                        if (node.right != null) {
                            stack.push(node.right);
                        }
                        stack.push(node.val);
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

}
//leetcode submit region end(Prohibit modification and deletion)
