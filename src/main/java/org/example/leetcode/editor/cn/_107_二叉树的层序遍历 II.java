package org.example.leetcode.editor.cn;
//给你二叉树的根节点 root ，返回其节点值 自底向上的层序遍历 。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：[[15,7],[9,20],[3]]
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
// Related Topics 树 广度优先搜索 二叉树 👍 757 👎 0

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
public class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        //return this.recursionImpl(root);
        //return this.iterationImpl1(root);
        return this.iterationImpl2(root);
    }

    @FunctionalInterface
    interface TriConsumer<U, V, W> {
        void accept(U u, V v, W w);
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
        Collections.reverse(res);
        return res;
    }

    List<List<Integer>> iterationImpl1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        if (root != null) {
            Deque<Object[]> queue = new ArrayDeque<>() {{
                this.add(new Object[]{root, 0});
            }};

            while (!queue.isEmpty()) {
                Object[] obj = queue.removeFirst();
                TreeNode curr = (TreeNode) obj[0];
                Integer level = (Integer) obj[1];
                if (level == res.size()) {
                    res.add(new ArrayList<>());
                }
                res.get(level).add(curr.val);

                if (curr.left != null) {
                    queue.addLast(new Object[]{curr.left, level + 1});
                }
                if (curr.right != null) {
                    queue.addLast(new Object[]{curr.right, level + 1});
                }
            }

            Collections.reverse(res);
        }

        return res;
    }

    List<List<Integer>> iterationImpl2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        if (root != null) {
            Deque<TreeNode> queue = new ArrayDeque<>() {{
                this.add(root);
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

            Collections.reverse(res);
        }

        return res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
