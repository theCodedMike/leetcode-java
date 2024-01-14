package org.example.leetcode.editor.cn;
//给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。” 
//
// 例如，给定如下二叉搜索树: root = [6,2,8,0,4,7,9,null,null,3,5] 
//
// 
//
// 
//
// 示例 1: 
//
// 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
//输出: 6 
//解释: 节点 2 和节点 8 的最近公共祖先是 6。
// 
//
// 示例 2: 
//
// 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
//输出: 2
//解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。 
//
// 
//
// 说明: 
//
// 
// 所有节点的值都是唯一的。 
// p、q 为不同节点且均存在于给定的二叉搜索树中。 
// 
//
    // Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 1191 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class _235_二叉搜索树的最近公共祖先 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //return this.twoTraversalRecur(root, p, q);
        //return this.twoTraversalIter(root, p, q);

        //return this.oneTraversalRecur(root, p, q);
        return this.oneTraversalIter(root, p, q);
    }

    @FunctionalInterface
    interface TriConsumer<A, B, C> {
        void accept(A a, B b, C c);
    }
    TriConsumer<TreeNode, Integer, List<TreeNode>> recur1 = (root, target, path) -> {
        if (root == null) {
            return;
        }

        path.add(root);
        if (root.val == target) {
            return;
        }
        if (target > root.val) {
            this.recur1.accept(root.right, target, path);
        } else {
            this.recur1.accept(root.left, target, path);
        }
    };
    TreeNode twoTraversalRecur(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pPath = new ArrayList<>();
        this.recur1.accept(root, p.val, pPath);
        List<TreeNode> qPath = new ArrayList<>();
        this.recur1.accept(root, q.val, qPath);

        int size = Math.min(pPath.size(), qPath.size());
        for (int i = size - 1; i >= 0; i--) {
            if (pPath.get(i).val == qPath.get(i).val) {
                return pPath.get(i);
            }
        }

        return null;
    }


    BiFunction<TreeNode, Integer, List<TreeNode>> iter1 = (root, target) -> {
        List<TreeNode> path = new ArrayList<>();

        while (root != null) {
            path.add(root);
            if (root.val == target) {
                break;
            }
            if (target > root.val) {
                root = root.right;
            } else {
                root = root.left;
            }
        }

        return path;
    };
    TreeNode twoTraversalIter(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pPath = this.iter1.apply(root, p.val);
        List<TreeNode> qPath = this.iter1.apply(root, q.val);

        int size = Math.min(pPath.size(), qPath.size());
        for (int i = size - 1; i >= 0; i--) {
            if (pPath.get(i).val == qPath.get(i).val) {
                return pPath.get(i);
            }
        }

        return null;
    }


    BiFunction<TreeNode, int[], TreeNode> recur2 = (root, target) -> {
        if (root == null) {
            return null;
        }
        int p = target[0];
        int q = target[1];
        if (p > root.val && q > root.val) {
            return this.recur2.apply(root.right, target);
        } else if (p < root.val && q < root.val) {
            return this.recur2.apply(root.left, target);
        } else {
            return root;
        }
    };

    TreeNode oneTraversalRecur(TreeNode root, TreeNode p, TreeNode q) {
        return this.recur2.apply(root, new int[]{p.val, q.val});
    }


    BiFunction<TreeNode, int[], TreeNode> iter2 = (root, target) -> {
        while (root != null) {
            int p = target[0];
            int q = target[1];

            if (p > root.val && q > root.val) {
                root = root.right;
            } else if (p < root.val && q < root.val) {
                root = root.left;
            } else {
                break;
            }
        }

        return root;
    };
    TreeNode oneTraversalIter(TreeNode root, TreeNode p, TreeNode q) {
        return this.iter2.apply(root, new int[]{p.val, q.val});
    }
}
//leetcode submit region end(Prohibit modification and deletion)
