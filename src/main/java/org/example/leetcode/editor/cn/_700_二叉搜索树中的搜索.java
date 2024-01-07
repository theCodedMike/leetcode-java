package org.example.leetcode.editor.cn;
//给定二叉搜索树（BST）的根节点
// root 和一个整数值
// val。 
//
// 你需要在 BST 中找到节点值等于 val 的节点。 返回以该节点为根的子树。 如果节点不存在，则返回
// null 。 
//
// 
//
// 示例 1: 
//
// 
// 
//
// 
//输入：root = [4,2,7,1,3], val = 2
//输出：[2,1,3]
// 
//
// 示例 2: 
// 
// 
//输入：root = [4,2,7,1,3], val = 5
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数在 [1, 5000] 范围内 
// 1 <= Node.val <= 10⁷ 
// root 是二叉搜索树 
// 1 <= val <= 10⁷ 
// 
//
// Related Topics 树 二叉搜索树 二叉树 👍 457 👎 0


import java.util.function.BiFunction;

//leetcode submit region begin(Prohibit modification and deletion)
public class _700_二叉搜索树中的搜索 {
    public TreeNode searchBST(TreeNode root, int val) {
        //return this.recursion(root, val);
        return this.iteration(root, val);
    }

    BiFunction<TreeNode, Integer, TreeNode> search = (curr, val) -> {
        if (curr == null) {
            return null;
        }

        if (val > curr.val) {
            return this.search.apply(curr.right, val);
        } else if (val < curr.val) {
            return this.search.apply(curr.left, val);
        } else {
            return curr;
        }
    };
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    TreeNode recursion(TreeNode root, int val) {
        return this.search.apply(root, val);
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    TreeNode iteration(TreeNode root, int val) {
        while (root != null) {
            if (val > root.val) {
                root = root.right;
            } else if (val < root.val) {
                root = root.left;
            } else {
                return root;
            }
        }

        return null;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
