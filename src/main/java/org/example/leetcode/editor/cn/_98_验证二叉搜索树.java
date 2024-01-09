package org.example.leetcode.editor.cn;
//给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
//
// 有效 二叉搜索树定义如下： 
//
// 
// 节点的左子树只包含 小于 当前节点的数。 
// 节点的右子树只包含 大于 当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
// 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [2,1,3]
//输出：true
// 
//
// 示例 2： 
// 
// 
//输入：root = [5,1,4,null,null,3,6]
//输出：false
//解释：根节点的值是 5 ，但是右子节点的值是 4 。
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目范围在[1, 10⁴] 内 
// -2³¹ <= Node.val <= 2³¹ - 1 
// 
//
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 2248 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.function.BiPredicate;

public class _98_验证二叉搜索树 {
    public boolean isValidBST(TreeNode root) {
        //return this.dfsRecur1(root);
        //return this.dfsIter1(root);
        //return this.bfsIter1(root);

        //return this.dfsRecur2(root);
        return this.dfsIter2(root);
    }


    @FunctionalInterface
    interface TriPredicate<A, B, C> {
        boolean test(A a, B b, C c);
    }

    TriPredicate<TreeNode, Long, Long> determine1 = (root, min, max) -> {
        if (root == null) {
            return true;
        }

        long currVal = root.val;
        if (!(min < currVal && currVal < max)) {
            return false;
        }

        return this.determine1.test(root.left, min, currVal) && this.determine1.test(root.right, currVal, max);
    };

    /**
     * 夹逼定理
     */
    boolean dfsRecur1(TreeNode root) {
        return this.determine1.test(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    boolean dfsIter1(TreeNode root) {
        if (root != null) {
            Deque<Object[]> stack = new ArrayDeque<>() {{
                this.push(new Object[]{root, Long.MIN_VALUE, Long.MAX_VALUE});
            }};

            while (!stack.isEmpty()) {
                Object[] objs = stack.pop();
                TreeNode curr = (TreeNode) objs[0];
                long min = (long) objs[1];
                long max = (long) objs[2];

                long currVal = curr.val;
                if (!(min < currVal && currVal < max)) {
                    return false;
                }
                if (curr.right != null) {
                    stack.push(new Object[]{curr.right, currVal, max});
                }
                if (curr.left != null) {
                    stack.push(new Object[]{curr.left, min, currVal});
                }
            }
        }

        return true;
    }

    boolean bfsIter1(TreeNode root) {
        if (root != null) {
            Deque<Object[]> queue = new ArrayDeque<>() {{
                this.addLast(new Object[]{root, Long.MIN_VALUE, Long.MAX_VALUE});
            }};

            while (!queue.isEmpty()) {
                Object[] objs = queue.removeFirst();
                TreeNode curr = (TreeNode) objs[0];
                long min = (long) objs[1];
                long max = (long) objs[2];

                long currVal = curr.val;
                if (!(min < currVal && currVal < max)) {
                    return false;
                }
                if (curr.left != null) {
                    queue.addLast(new Object[]{curr.left, min, currVal});
                }
                if (curr.right != null) {
                    queue.addLast(new Object[]{curr.right, currVal, max});
                }
            }
        }

        return true;
    }


    /**
     * 中序遍历是有序的
     */
    boolean dfsRecur2(TreeNode root) {
        List<Integer> vals = new ArrayList<>();

        return this.determine2.test(root, vals);
    }

    BiPredicate<TreeNode, List<Integer>> determine2 = (root, vals) -> {
        if (root == null) {
            return true;
        }

        if (!this.determine2.test(root.left, vals)) {
            return false;
        }

        int currVal = root.val;
        if (!vals.isEmpty() && currVal <= vals.getLast()) {
            return false;
        }
        vals.add(currVal);

        return this.determine2.test(root.right, vals);
    };

    boolean dfsIter2(TreeNode root) {
        if (root != null) {
            List<Integer> vals = new ArrayList<>();
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
                        if (!vals.isEmpty() && currVal <= vals.getLast()) {
                            return false;
                        }

                        vals.add(currVal);
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + obj);
                }
            }
        }

        return true;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
