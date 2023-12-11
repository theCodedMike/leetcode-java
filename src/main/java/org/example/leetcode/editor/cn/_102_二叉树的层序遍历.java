package org.example.leetcode.editor.cn;
//给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：[[3],[9,20],[15,7]]
// 
//
// 示例 2： 
//
// 
//输入：root = [1]
//输出：[[1]]
// 
//
// 示例 3： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 2000] 内 
// -1000 <= Node.val <= 1000 
// 
//
// Related Topics 树 广度优先搜索 二叉树 👍 1850 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class _102_二叉树的层序遍历 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        //return this.recursionImpl(root);
        //return this.iterationImpl1(root);
        return this.iterationImpl2(root);
    }

    @FunctionalInterface
    interface TriConsumer<X, Y, Z> {
        void accept(X x, Y y, Z z);
    }

    TriConsumer<TreeNode, Integer, List<List<Integer>>> recurImpl = (root, level, res) -> {
        if (root == null) {
            return;
        }

        if (level == res.size()) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(root.val);
        if (root.left != null) {
            this.recurImpl.accept(root.left, level + 1, res);
        }
        if (root.right != null) {
            this.recurImpl.accept(root.right, level + 1, res);
        }
    };

    List<List<Integer>> recursionImpl(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        this.recurImpl.accept(root, 0, res);
        return res;
    }

    List<List<Integer>> iterationImpl1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        if (root != null) {
            Deque<Object[]> queue = new ArrayDeque<>() {{
                this.addLast(new Object[]{0, root});
            }};

            while (!queue.isEmpty()) {
                Object[] pop = queue.removeFirst();
                int level = (int) pop[0];
                TreeNode curr = (TreeNode) pop[1];

                if (level == res.size()) {
                    res.add(new ArrayList<>());
                }
                res.get(level).add(curr.val);
                if (curr.left != null) {
                    queue.addLast(new Object[]{level + 1, curr.left});
                }
                if (curr.right != null) {
                    queue.addLast(new Object[]{level + 1, curr.right});
                }
            }
        }

        return res;
    }

    List<List<Integer>> iterationImpl2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        if (root != null) {
            Deque<TreeNode> queue = new ArrayDeque<>() {{
                this.addLast(root);
            }};

            while (!queue.isEmpty()) {
                int levelSize = queue.size();
                List<Integer> levelList = new ArrayList<>();

                for (int i = 0; i < levelSize; i++) {
                    TreeNode curr = queue.removeFirst();
                    levelList.add(curr.val);

                    if (curr.left != null) {
                        queue.addLast(curr.left);
                    }
                    if (curr.right != null) {
                        queue.addLast(curr.right);
                    }
                }

                res.add(levelList);
            }
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
