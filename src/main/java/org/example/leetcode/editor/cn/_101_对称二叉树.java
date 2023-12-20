package org.example.leetcode.editor.cn;
//给你一个二叉树的根节点 root ， 检查它是否轴对称。
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [1,2,2,3,4,4,3]
//输出：true
// 
//
// 示例 2： 
// 
// 
//输入：root = [1,2,2,null,3,null,3]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [1, 1000] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶：你可以运用递归和迭代两种方法解决这个问题吗？ 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 2610 👎 0

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
import java.util.function.BiPredicate;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isSymmetric(TreeNode root) {
        //return this.bfsRecur(root);
        //return this.bfsIter(root);
        return this.dfsIter(root);
    }

    BiPredicate<TreeNode, TreeNode> helper = (left, right) -> {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        return left.val == right.val
                && this.helper.test(left.left, right.right)
                && this.helper.test(left.right, right.left);
    };
    boolean bfsRecur(TreeNode root) {
        return this.helper.test(root.left, root.right);
    }

    boolean bfsIter(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>() {{
            this.addLast(root.left);
            this.addLast(root.right);
        }};

        while (!queue.isEmpty()) {
            TreeNode left = queue.pollFirst();
            TreeNode right = queue.pollFirst();
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null) {
                return false;
            }
            if (left.val != right.val) {
                return false;
            }
            queue.addLast(left.left);
            queue.addLast(right.right);
            queue.addLast(left.right);
            queue.addLast(right.left);
        }

        return true;
    }

    boolean dfsIter(TreeNode root) {
        Deque<TreeNode> l_r_stack = new ArrayDeque<>();
        Deque<TreeNode> r_l_stack = new ArrayDeque<>();
        TreeNode l_r_root = root.left;
        TreeNode r_l_root = root.right;

        while (l_r_root != null || !l_r_stack.isEmpty()
                || r_l_root != null || !r_l_stack.isEmpty())
        {
            if (l_r_root == null && r_l_root == null) {
                l_r_root = l_r_stack.pop().right;
                r_l_root = r_l_stack.pop().left;
            } else if (l_r_root == null || r_l_root == null) {
                return false;
            } else {
                if (l_r_root.val != r_l_root.val) {
                    return false;
                }
                l_r_stack.push(l_r_root);
                l_r_root = l_r_root.left;
                r_l_stack.push(r_l_root);
                r_l_root = r_l_root.right;
            }
        }

        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
