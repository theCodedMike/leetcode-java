package org.example.leetcode.editor.cn;
//给定二叉搜索树（BST）的根节点
// root 和要插入树中的值
// value ，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。 
//
// 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [4,2,7,1,3], val = 5
//输出：[4,2,7,1,3,5]
//解释：另一个满足题目要求可以通过的树是：
//
// 
//
// 示例 2： 
//
// 
//输入：root = [40,20,60,10,30,50,70], val = 25
//输出：[40,20,60,10,30,50,70,null,null,25]
// 
//
// 示例 3： 
//
// 
//输入：root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
//输出：[4,2,7,1,3,5]
// 
//
// 
//
// 提示： 
//
// 
// 树中的节点数将在
// [0, 10⁴]的范围内。
// 
// -10⁸ <= Node.val <= 10⁸ 
// 所有值 
// Node.val 是 独一无二 的。 
// -10⁸ <= val <= 10⁸ 
// 保证 val 在原始BST中不存在。 
// 
//
// Related Topics 树 二叉搜索树 二叉树 👍 546 👎 0


import java.util.function.BiConsumer;
import java.util.function.BiFunction;

//leetcode submit region begin(Prohibit modification and deletion)
public class _701_二叉搜索树中的插入操作 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        //return this.iterHelper1(root, val);
        //return this.iterHelper2(root, val);

        //return this.recurHelper1(root, val);
        return this.recurHelper2(root, val);
    }


    TreeNode iterHelper1(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);
        if (root == null) {
            return node;
        }

        TreeNode rootNode = root;
        while (rootNode != null) {
            TreeNode next;
            boolean isRight;

            if (val > rootNode.val) {
                next = rootNode.right;
                isRight = true;
            } else {
                next = rootNode.left;
                isRight = false;
            }

            if (next != null) {
                rootNode = next;
            } else {
                if (isRight) {
                    rootNode.right = node;
                } else {
                    rootNode.left = node;
                }
                break;
            }
        }

        return root;
    }

    TreeNode iterHelper2(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);
        if (root == null) {
            return node;
        }

        TreeNode curr = root;
        while (curr != null) {
            if (val > curr.val) {
                TreeNode right = curr.right;
                if (right != null) {
                    curr = right;
                } else {
                    curr.right = node;
                    break;
                }
            } else {
                TreeNode left = curr.left;
                if (left != null) {
                    curr = left;
                } else {
                    curr.left = node;
                    break;
                }
            }
        }

        return root;
    }


    BiConsumer<TreeNode, TreeNode> traversal1 = (root, newNode) -> {
        if (root == null) {
            return;
        }

        if (newNode.val > root.val) {
            TreeNode right = root.right;
            if (right != null) {
                this.traversal1.accept(right, newNode);
            } else {
                root.right = newNode;
            }
        } else {
            TreeNode left = root.left;
            if (left != null) {
                this.traversal1.accept(left, newNode);
            } else {
                root.left = newNode;
            }
        }
    };
    TreeNode recurHelper1(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);
        if (root == null) {
            return node;
        }

        this.traversal1.accept(root, node);

        return root;
    }


    BiFunction<TreeNode, Integer, TreeNode> traversal2 = (root, val) -> {
        if (root == null) {
            return new TreeNode(val);
        }

        if (val > root.val) {
            root.right = this.traversal2.apply(root.right, val);
        } else {
            root.left = this.traversal2.apply(root.left, val);
        }

        return root;
    };

    TreeNode recurHelper2(TreeNode root, int val) {
        return this.traversal2.apply(root, val);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
