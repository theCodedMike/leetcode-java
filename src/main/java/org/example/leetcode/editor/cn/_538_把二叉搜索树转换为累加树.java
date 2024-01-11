package org.example.leetcode.editor.cn;
//给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等于
// node.val 的值之和。 
//
// 提醒一下，二叉搜索树满足下列约束条件： 
//
// 
// 节点的左子树仅包含键 小于 节点键的节点。 
// 节点的右子树仅包含键 大于 节点键的节点。 
// 左右子树也必须是二叉搜索树。 
// 
//
// 注意：本题和 1038: https://leetcode-cn.com/problems/binary-search-tree-to-greater-
//sum-tree/ 相同 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
//输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
// 
//
// 示例 2： 
//
// 输入：root = [0,null,1]
//输出：[1,null,1]
// 
//
// 示例 3： 
//
// 输入：root = [1,0,2]
//输出：[3,3,2]
// 
//
// 示例 4： 
//
// 输入：root = [3,2,4,1]
//输出：[7,9,4,10]
// 
//
// 
//
// 提示： 
//
// 
// 树中的节点数介于 0 和 10⁴ 之间。 
// 每个节点的值介于 -10⁴ 和 10⁴ 之间。 
// 树中的所有值 互不相同 。 
// 给定的树为二叉搜索树。 
// 
//
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 973 👎 0


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

//leetcode submit region begin(Prohibit modification and deletion)
public class _538_把二叉搜索树转换为累加树 {
    public TreeNode convertBST(TreeNode root) {
        //return mirrorInorderRecur1(root);
        //return mirrorInorderIter1(root);
        //return mirrorInorderRecur2(root);

        return morrisMirrorInorder(root);
    }

    BiConsumer<TreeNode, int[]> traversal1 = (root, sum) -> {
        if (root == null) {
            return;
        }

        this.traversal1.accept(root.right, sum);

        root.val += sum[0];
        sum[0] = root.val;

        this.traversal1.accept(root.left, sum);
    };
    TreeNode mirrorInorderRecur1(TreeNode root) {
        int[] sum = new int[]{0};

        this.traversal1.accept(root, sum);

        return root;
    }

    TreeNode mirrorInorderIter1(TreeNode root) {
        if (root != null) {
            int sum = 0;
            Deque<Object[]> stack = new ArrayDeque<>() {{
                this.push(new Object[]{false, root});
            }};
            while (!stack.isEmpty()) {
                Object[] objs = stack.pop();
                boolean isTarget = (boolean) objs[0];
                TreeNode curr = (TreeNode) objs[1];

                if (isTarget) {
                    curr.val += sum;
                    sum = curr.val;
                } else {
                    if (curr.left != null) {
                        stack.push(new Object[]{false, curr.left});
                    }
                    stack.push(new Object[]{true, curr});
                    if (curr.right != null) {
                        stack.push(new Object[]{false, curr.right});
                    }
                }
            }
        }

        return root;
    }


    BiFunction<TreeNode, Integer, Integer> traversal2 = (root, sum) -> {
        if (root == null) {
            return sum;
        }

        Integer r_sum = this.traversal2.apply(root.right, sum);

        root.val += r_sum;

        return this.traversal2.apply(root.left, root.val);
    };
    TreeNode mirrorInorderRecur2(TreeNode root) {
        this.traversal2.apply(root, 0);

        return root;
    }
    TreeNode morrisMirrorInorder(TreeNode root) {
        TreeNode curr = root;
        int sum = 0;

        while (curr != null) {
            if (curr.right != null) {
                TreeNode prev = curr.right;
                while (prev.left != null && prev.left != curr) {
                    prev = prev.left;
                }
                if (prev.left == null) {
                    prev.left = curr;
                    curr = curr.right;
                } else {
                    prev.left = null;
                    curr.val += sum;
                    sum = curr.val;
                    curr = curr.left;
                }
            } else {
                curr.val += sum;
                sum = curr.val;
                curr = curr.left;
            }
        }

        return root;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
