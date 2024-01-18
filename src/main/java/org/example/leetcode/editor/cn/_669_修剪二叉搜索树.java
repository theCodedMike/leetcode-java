package org.example.leetcode.editor.cn;
//给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。通过修剪二叉搜索树，使得所有节点的值在[low, high]中。修剪树 不
//应该 改变保留在树中的元素的相对结构 (即，如果没有被移除，原有的父代子代关系都应当保留)。 可以证明，存在 唯一的答案 。 
//
// 所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [1,0,2], low = 1, high = 2
//输出：[1,null,2]
// 
//
// 示例 2： 
// 
// 
//输入：root = [3,0,4,null,2,null,null,1], low = 1, high = 3
//输出：[3,2,null,1]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数在范围 [1, 10⁴] 内 
// 0 <= Node.val <= 10⁴ 
// 树中每个节点的值都是 唯一 的 
// 题目数据保证输入是一棵有效的二叉搜索树 
// 0 <= low <= high <= 10⁴ 
// 
//
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 903 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
public class _669_修剪二叉搜索树 {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        //return this.recurHelper(root, low, high);

        return this.iterHelper(root, low, high);
    }

    interface TriFunction<A, B, C, D> {
        D apply(A a, B b, C c);
    }
    TriFunction<TreeNode, Integer, Integer, TreeNode> recur = (root, low, high) -> {
        if (root == null) {
            return null;
        }

        if (root.val < low) {
            return this.recur.apply(root.right, low, high);
        } else if (root.val > high) {
            return this.recur.apply(root.left, low, high);
        } else {
            root.left = this.recur.apply(root.left, low, high);
            root.right = this.recur.apply(root.right, low, high);
            return root;
        }
    };
    TreeNode recurHelper(TreeNode root, int low, int high) {
        return this.recur.apply(root, low, high);
    }

    TreeNode iterHelper(TreeNode root, int low, int high) {
        while (root != null && (root.val < low || root.val > high)) {
            if (root.val < low) {
                root = root.right;
            } else {
                root = root.left;
            }
        }

        if (root == null) {
            return null;
        }

        for (TreeNode curr = root; curr.left != null;) {
            TreeNode left = curr.left;
            if (left.val < low) {
                curr.left = left.right;
            } else {
                curr = left;
            }
        }

        for (TreeNode curr = root; curr.right != null;) {
            TreeNode right = curr.right;
            if (right.val > high) {
                curr.right = right.left;
            } else {
                curr = right;
            }
        }

        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
