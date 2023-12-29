package org.example.leetcode.editor.cn;
//给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
//
// 假设二叉树中至少有一个节点。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入: root = [2,1,3]
//输出: 1
// 
//
// 示例 2: 
//
// 
//
// 
//输入: [1,2,3,4,null,5,6,null,null,7]
//输出: 7
// 
//
// 
//
// 提示: 
//
// 
// 二叉树的节点个数的范围是 [1,10⁴] 
// 
// -2³¹ <= Node.val <= 2³¹ - 1 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 554 👎 0


import java.util.ArrayDeque;
import java.util.Deque;

//leetcode submit region begin(Prohibit modification and deletion)
public class _513_找树左下角的值 {
    public int findBottomLeftValue(TreeNode root) {
        //return this.dfsRecur(root);
        //return this.dfsIter(root);
        //return this.bfsIter1(root);
        return this.bfsIter2(root);
    }

    @FunctionalInterface
    interface TriConsumer<X, Y, Z> {
        void accept(X x, Y y, Z z);
    }

    TriConsumer<TreeNode, Integer, int[]> recur = (root, level, val) -> {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null && level > val[1]) {
            val[0] = root.val;
            val[1] = level;
        }

        this.recur.accept(root.left, level + 1, val);
        this.recur.accept(root.right, level + 1, val);
    };

    /**
     * 深度优先搜索 - 递归(前序遍历)
     */
    int dfsRecur(TreeNode root) {
        int[] val = new int[] {0, Integer.MIN_VALUE};

        this.recur.accept(root, 0, val);

        return val[0];
    }

    /**
     * 深度优先搜索 - 迭代(前序遍历)
     */
    int dfsIter(TreeNode root) {
        int val = 0;
        int val_level = Integer.MIN_VALUE;
        Deque<Object[]> stack = new ArrayDeque<>() {{
            this.push(new Object[]{root, 0});
        }};

        while (!stack.isEmpty()) {
            Object[] objs = stack.pop();
            TreeNode curr = (TreeNode) objs[0];
            int level = (int) objs[1];

            if (curr.left == null && curr.right == null && level > val_level) {
                val = curr.val;
                val_level = level;
            }

            if (curr.right != null) {
                stack.push(new Object[]{curr.right, level + 1});
            }
            if (curr.left != null) {
                stack.push(new Object[]{curr.left, level + 1});
            }
        }

        return val;
    }

    /**
     * 广度优先搜索 - 迭代(层序遍历)
     */
    int bfsIter1(TreeNode root) {
        int val = 0;
        Deque<TreeNode> queue = new ArrayDeque<>() {{
            this.addLast(root);
        }};

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode curr = queue.removeFirst();
                if (i == 0) {
                    val = curr.val;
                }

                if (curr.left != null) {
                    queue.addLast(curr.left);
                }
                if (curr.right != null) {
                    queue.addLast(curr.right);
                }
            }
        }

        return val;
    }

    /**
     * 广度优先搜索 - 迭代(层序遍历)
     */
    int bfsIter2(TreeNode root) {
        int val = 0;
        int prevLevel = -1;
        Deque<Object[]> queue = new ArrayDeque<>() {{
            this.addLast(new Object[]{root, 0});
        }};

        while (!queue.isEmpty()) {
            Object[] objs = queue.removeFirst();
            TreeNode curr = (TreeNode) objs[0];
            int level = (int) objs[1];

            if (prevLevel != level) {
                val = curr.val;
            }
            prevLevel = level;

            if (curr.left != null) {
                queue.addLast(new Object[]{curr.left, level + 1});
            }
            if (curr.right != null) {
                queue.addLast(new Object[]{curr.right, level + 1});
            }
        }

        return val;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
