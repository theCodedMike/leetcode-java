package org.example.leetcode.editor.cn;
//给定一个二叉树 root ，返回其最大深度。
//
// 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：3
// 
//
// 示例 2： 
//
// 
//输入：root = [1,null,2]
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数量在 [0, 10⁴] 区间内。 
// -100 <= Node.val <= 100 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 1756 👎 0


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Function;

//leetcode submit region begin(Prohibit modification and deletion)
public class _104_二叉树的最大深度 {
    public int maxDepth(TreeNode root) {
        //return this.dfsRecur(root);
        //return this.bfsIter1(root);
        return this.bfsIter2(root);
    }

    Function<TreeNode, Integer> helper = root -> {
        if (root == null) {
            return 0;
        }
        return Math.max(this.helper.apply(root.left), this.helper.apply(root.right)) + 1;
    };

    int dfsRecur(TreeNode root) {
        return this.helper.apply(root);
    }

    int bfsIter1(TreeNode root) {
        int maxDepth = 0;

        if (root != null) {
            Deque<Object[]> queue = new ArrayDeque<>() {{
                this.addLast(new Object[]{root, 1});
            }};

            while (!queue.isEmpty()) {
                Object[] obj = queue.removeFirst();
                TreeNode curr = (TreeNode) obj[0];
                int level = (int) obj[1];
                maxDepth = level;

                if (curr.left != null) {
                    queue.addLast(new Object[]{curr.left, level + 1});
                }
                if (curr.right != null) {
                    queue.addLast(new Object[]{curr.right, level + 1});
                }
            }
        }

        return maxDepth;
    }

    int bfsIter2(TreeNode root) {
        int maxDepth = 0;

        if (root != null) {
            Deque<TreeNode> queue = new ArrayDeque<>() {{
                this.addLast(root);
            }};

            while (!queue.isEmpty()) {
                int levelSize = queue.size();

                while (levelSize-- > 0) {
                    TreeNode curr = queue.removeFirst();
                    if (curr.left != null) {
                        queue.addLast(curr.left);
                    }
                    if (curr.right != null) {
                        queue.addLast(curr.right);
                    }
                }

                maxDepth++;
            }
        }

        return maxDepth;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
