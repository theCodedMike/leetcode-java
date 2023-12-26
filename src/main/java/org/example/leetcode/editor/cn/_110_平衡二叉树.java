package org.example.leetcode.editor.cn;
//给定一个二叉树，判断它是否是高度平衡的二叉树。
//
// 本题中，一棵高度平衡二叉树定义为： 
//
// 
// 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。 
// 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：true
// 
//
// 示例 2： 
// 
// 
//输入：root = [1,2,2,3,3,null,null,4,4]
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：root = []
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// 树中的节点数在范围 [0, 5000] 内 
// -10⁴ <= Node.val <= 10⁴ 
// 
//
// Related Topics 树 深度优先搜索 二叉树 👍 1463 👎 0


import java.util.function.Function;
import java.util.function.Predicate;

//leetcode submit region begin(Prohibit modification and deletion)
public class _110_平衡二叉树 {
    public boolean isBalanced(TreeNode root) {
        //return this.fromBottomToTop(root);
        return this.fromTopToBottom(root);
    }

    Function<TreeNode, Object[]> recur_helper = root -> {
        if (root == null) {
            return new Object[]{0, true};
        }
        Object[] l_res = this.recur_helper.apply(root.left);
        int l_height = (int) l_res[0];
        boolean l_bal = (boolean) l_res[1];
        Object[] r_res = this.recur_helper.apply(root.right);
        int r_height = (int) r_res[0];
        boolean r_bal = (boolean) r_res[1];

        return new Object[]{Math.max(l_height, r_height) + 1, l_bal && r_bal && Math.abs(l_height - r_height) <= 1};
    };

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    boolean fromBottomToTop(TreeNode root) {
        return (boolean) this.recur_helper.apply(root)[1];
    }

    Function<TreeNode, Integer> calc_height = root -> {
        if (root == null) {
            return 0;
        }

        return Math.max(this.calc_height.apply(root.left), this.calc_height.apply(root.right)) + 1;
    };

    Predicate<TreeNode> check_balance = root -> {
        if (root == null) {
            return true;
        }

        int l_height = this.calc_height.apply(root.left);
        int r_height = this.calc_height.apply(root.right);
        if (Math.abs(l_height - r_height) > 1) {
            return false;
        }

        return this.check_balance.test(root.left) && this.check_balance.test(root.right);
    };

    /**
     * Time Complexity: O(n^2)
     * Space Complexity: O(n)
     */
    boolean fromTopToBottom(TreeNode root) {
        return this.check_balance.test(root);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
