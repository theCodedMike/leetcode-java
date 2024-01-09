package org.example.leetcode.editor.cn;
//给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
//
// 差值是一个正数，其数值等于两值之差的绝对值。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [4,2,6,1,3]
//输出：1
// 
//
// 示例 2： 
// 
// 
//输入：root = [1,0,48,null,null,12,49]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数目范围是 [2, 10⁴] 
// 0 <= Node.val <= 10⁵ 
// 
//
// 
//
// 注意：本题与 783 https://leetcode-cn.com/problems/minimum-distance-between-bst-
//nodes/ 相同 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉搜索树 二叉树 👍 535 👎 0


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.BiConsumer;

//leetcode submit region begin(Prohibit modification and deletion)
public class _530_二叉搜索树的最小绝对差 {
    public int getMinimumDifference(TreeNode root) {
        //return this.inorderRecur(root);
        return this.inorderIter(root);
    }

    BiConsumer<TreeNode, int[]> inorder = (root, vals) -> {
        if (root == null) {
            return;
        }

        this.inorder.accept(root.left, vals);

        int currVal = root.val;
        int diff = currVal - vals[1]; // vals[1] is prevVal
        if (diff < vals[0]) { // vals[0] is min diff at present
            vals[0] = diff;
        }
        vals[1] = currVal;

        this.inorder.accept(root.right, vals);
    };

    int inorderRecur(TreeNode root) {
        int[] vals = {Integer.MAX_VALUE, Integer.MIN_VALUE / 2};

        this.inorder.accept(root, vals);

        return vals[0];
    }

    int inorderIter(TreeNode root) {
        int minAbsDiff = Integer.MAX_VALUE;
        int prevVal = Integer.MIN_VALUE / 2;

        Deque<Object> stack = new ArrayDeque<>() {{
            this.push(root);
        }};
        while (!stack.isEmpty()) {
            Object obj = stack.pop();
            switch (obj) {
                case TreeNode node -> {
                    if (node.right != null) {
                        stack.push(node.right);
                    }
                    stack.push(node.val);
                    if (node.left != null) {
                        stack.push(node.left);
                    }
                }
                case Integer currVal -> {
                    int diff = currVal - prevVal;
                    if (diff < minAbsDiff) {
                        minAbsDiff = diff;
                    }
                    prevVal = currVal;
                }
                default -> throw new IllegalStateException("Unexpected value: " + obj);
            }
        }

        return minAbsDiff;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
