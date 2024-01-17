package org.example.leetcode.editor.cn;
//给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的
//根节点的引用。 
//
// 一般来说，删除节点可分为两个步骤： 
//
// 
// 首先找到需要删除的节点； 
// 如果找到了，删除它。 
// 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入：root = [5,3,6,2,4,null,7], key = 3
//输出：[5,4,6,2,null,null,7]
//解释：给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
//一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
//另一个正确答案是 [5,2,6,null,4,null,7]。
//
//
// 
//
// 示例 2: 
//
// 
//输入: root = [5,3,6,2,4,null,7], key = 0
//输出: [5,3,6,2,4,null,7]
//解释: 二叉树不包含值为 0 的节点
// 
//
// 示例 3: 
//
// 
//输入: root = [], key = 0
//输出: [] 
//
// 
//
// 提示: 
//
// 
// 节点数的范围 [0, 10⁴]. 
// -10⁵ <= Node.val <= 10⁵ 
// 节点值唯一 
// root 是合法的二叉搜索树 
// -10⁵ <= key <= 10⁵ 
// 
//
// 
//
// 进阶： 要求算法时间复杂度为 O(h)，h 为树的高度。 
//
// Related Topics 树 二叉搜索树 二叉树 👍 1297 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
public class _450_删除二叉搜索树中的节点 {
    public TreeNode deleteNode(TreeNode root, int key) {
        //return this.recurHelper(root, key);

        return this.iterHelper(root, key);
    }

    @FunctionalInterface
    interface QuadrConsumer<A, B, C, D> {
        void accept(A a, B b, C c, D d);
    }

    QuadrConsumer<TreeNode[], Integer, TreeNode, Boolean> delete = (roots, key, parent, isLeft) -> {
        TreeNode root = roots[0];
        if (root == null) {
            return;
        }

        if (root.val == key) {
            TreeNode child;
            if (root.right != null) {
                child = root.right;
                TreeNode leftmost = root.right;
                while (leftmost.left != null) {
                    leftmost = leftmost.left;
                }

                leftmost.left = root.left;
            } else {
                child = root.left;
            }

            if (parent == null) {
                roots[0] = child;
            } else {
                if (isLeft) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            }
        } else {
            if (key < root.val) {
                this.delete.accept(new TreeNode[]{root.left}, key, root, true);
            } else {
                this.delete.accept(new TreeNode[]{root.right}, key, root, false);
            }
        }
    };

    TreeNode recurHelper(TreeNode _root, int key) {
        TreeNode[] root = {_root};
        this.delete.accept(root, key, null, false);

        return root[0];
    }

    TreeNode iterHelper(TreeNode root, int key) {
        TreeNode parent = null;
        boolean isLeft = false;
        TreeNode curr = root;

        while (curr != null) {
            if (curr.val == key) {
                TreeNode child;
                if (curr.right != null) {
                    child = curr.right;
                    TreeNode leftmost = curr.right;
                    while (leftmost.left != null) {
                        leftmost = leftmost.left;
                    }
                    leftmost.left = curr.left;
                } else {
                    child = curr.left;
                }

                if (parent == null) {
                    root = child;
                } else {
                    if (isLeft) {
                        parent.left = child;
                    } else {
                        parent.right = child;
                    }
                }

                break;
            } else {
                parent = curr;
                if (key < curr.val) {
                    isLeft = true;
                    curr = curr.left;
                } else {
                    isLeft = false;
                    curr = curr.right;
                }
            }
        }

        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
