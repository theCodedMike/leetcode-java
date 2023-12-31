package org.example.leetcode.editor.cn;
//给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [4,2,7,1,3,6,9]
//输出：[4,7,2,9,6,3,1]
// 
//
// 示例 2： 
//
// 
//
// 
//输入：root = [2,1,3]
//输出：[2,3,1]
// 
//
// 示例 3： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目范围在 [0, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 1749 👎 0


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Consumer;

//leetcode submit region begin(Prohibit modification and deletion)
public class _226_翻转二叉树 {
    public TreeNode invertTree(TreeNode root) {
        //return dfsRecur(root);
        //return dfsIter(root);
        return bfsIter(root);
    }

    Consumer<TreeNode> recur = root -> {
        if (root == null) {
            return;
        }

        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;

        this.recur.accept(root.left);
        this.recur.accept(root.right);
    };
    TreeNode dfsRecur(TreeNode root) {
        this.recur.accept(root);

        return root;
    }

    TreeNode dfsIter(TreeNode root) {
        if (root != null) {
            Deque<TreeNode> stack = new ArrayDeque<>() {{
                this.push(root);
            }};

            while (!stack.isEmpty()) {
                TreeNode curr = stack.pop();
                TreeNode left = curr.left;
                curr.left = curr.right;
                curr.right = left;

                if (curr.right != null) {
                    stack.push(curr.right);
                }
                if (curr.left != null) {
                    stack.push(curr.left);
                }
            }
        }

        return root;
    }

    TreeNode bfsIter(TreeNode root) {
        if (root != null) {
            Deque<TreeNode> queue = new ArrayDeque<>() {{
                this.addLast(root);
            }};

            while (!queue.isEmpty()) {
                TreeNode curr = queue.removeFirst();
                TreeNode left = curr.left;
                curr.left = curr.right;
                curr.right = left;

                if (curr.left != null) {
                    queue.addLast(curr.left);
                }
                if (curr.right != null) {
                    queue.addLast(curr.right);
                }
            }
        }

        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
