package org.example.leetcode.editor.cn;
//给你两棵二叉树： root1 和 root2 。
//
// 想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。你需要将这两棵树合并成一棵新二叉树。合并的规则是：如果两个节点重叠
//，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点。 
//
// 返回合并后的二叉树。 
//
// 注意: 合并过程必须从两个树的根节点开始。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
//输出：[3,4,5,5,4,null,7]
// 
//
// 示例 2： 
//
// 
//输入：root1 = [1], root2 = [1,2]
//输出：[2,2]
// 
//
// 
//
// 提示： 
//
// 
// 两棵树中的节点数目在范围 [0, 2000] 内 
// -10⁴ <= Node.val <= 10⁴ 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 1369 👎 0

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.BiFunction;

//leetcode submit region begin(Prohibit modification and deletion)
public class _617_合并二叉树 {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        //return this.dfsRecurCreateNew(root1, root2);
        //return this.dfsIterCreateNew(root1, root2);
        //return this.dfsRecurReuse(root1, root2);
        //return this.dfsIterReuse(root1, root2);
        //return this.bfsIterCreateNew(root1, root2);
        return this.bfsIterReuse(root1, root2);
    }

    BiFunction<TreeNode, TreeNode, TreeNode> merge1 = (root1, root2) -> {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }

        TreeNode root = new TreeNode(root1.val + root2.val);
        root.left = this.merge1.apply(root1.left, root2.left);
        root.right = this.merge1.apply(root1.right, root2.right);

        return root;
    };

    /**
     * 深度优先搜索，递归，创建一个新节点
     */
    TreeNode dfsRecurCreateNew(TreeNode root1, TreeNode root2) {
        return this.merge1.apply(root1, root2);
    }

    /**
     * 深度优先搜索，迭代，创建一个新节点
     */
    TreeNode dfsIterCreateNew(TreeNode root1, TreeNode root2) {
        TreeNode root = null;
        Deque<Object[]> stack = new ArrayDeque<>() {{
            this.push(new Object[]{
                    null, root1, root2, false
            });
        }};
        while (!stack.isEmpty()) {
            Object[] objs = stack.pop();
            TreeNode parent = (TreeNode) objs[0];
            TreeNode r1 = (TreeNode) objs[1];
            TreeNode r2 = (TreeNode) objs[2];
            boolean isLeft = (boolean) objs[3];

            TreeNode node = this.createNode.apply(r1, r2, stack);
            if (parent == null) {
                root = node;
            } else {
                if (isLeft) {
                    parent.left = node;
                } else {
                    parent.right = node;
                }
            }
        }

        return root;
    }

    BiFunction<TreeNode, TreeNode, TreeNode> merge2 = (root1, root2) -> {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }

        root1.val += root2.val;
        root1.left = this.merge2.apply(root1.left, root2.left);
        root1.right = this.merge2.apply(root1.right, root2.right);

        return root1;
    };

    /**
     * 深度优先搜索，递归，重复使用root1
     */
    TreeNode dfsRecurReuse(TreeNode root1, TreeNode root2) {
        return this.merge2.apply(root1, root2);
    }

    /**
     * 深度优先搜索，迭代，重复使用root1
     */
    TreeNode dfsIterReuse(TreeNode root1, TreeNode root2) {
        // 确保root1不为null
        if (root1 == null) {
            return root2;
        }

        Deque<TreeNode[]> stack = new ArrayDeque<>() {{
            this.push(new TreeNode[]{root1, root2});
        }};
        while (!stack.isEmpty()) {
            TreeNode[] nodes = stack.pop();
            TreeNode r1 = nodes[0];
            TreeNode r2 = nodes[1];

            if (r1 == null || r2 == null) {
                continue;
            }

            r1.val += r2.val;
            if (r1.left == null) {
                r1.left = r2.left;
            } else {
                stack.push(new TreeNode[]{r1.left, r2.left});
            }

            if (r1.right == null) {
                r1.right = r2.right;
            } else {
                stack.push(new TreeNode[]{r1.right, r2.right});
            }
        }

        return root1;
    }


    @FunctionalInterface
    interface TriFunction<A, B, C, D> {
        D apply(A a, B b, C c);
    }

    TriFunction<TreeNode, TreeNode, Deque<Object[]>, TreeNode> createNode = (r1, r2, container) -> {
        if (r1 == null) {
            return r2;
        }
        if (r2 == null) {
            return r1;
        }
        TreeNode node = new TreeNode(r1.val + r2.val);
        if (r1.left != null || r2.left != null) {
            container.addLast(new Object[]{node, r1.left, r2.left, true});
        }
        if (r1.right != null || r2.right != null) {
            container.addLast(new Object[]{node, r1.right, r2.right, false});
        }

        return node;
    };

    /**
     * 广度优先搜索，迭代，创建一个新节点
     */
    TreeNode bfsIterCreateNew(TreeNode root1, TreeNode root2) {
        TreeNode root = null;

        Deque<Object[]> queue = new ArrayDeque<>() {{
            this.addLast(new Object[]{null, root1, root2, false});
        }};
        while (!queue.isEmpty()) {
            Object[] objs = queue.removeFirst();
            TreeNode parent = (TreeNode) objs[0];
            TreeNode r1 = (TreeNode) objs[1];
            TreeNode r2 = (TreeNode) objs[2];
            boolean isLeft = (boolean) objs[3];

            TreeNode node = this.createNode.apply(r1, r2, queue);
            if (parent == null) {
                root = node;
            } else {
                if (isLeft) {
                    parent.left = node;
                } else {
                    parent.right = node;
                }
            }
        }

        return root;
    }

    /**
     * 广度优先搜索，迭代，重复使用root1
     */
    TreeNode bfsIterReuse(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }

        Deque<TreeNode[]> queue = new ArrayDeque<>() {{
            this.addLast(new TreeNode[]{root1, root2});
        }};
        while (!queue.isEmpty()) {
            TreeNode[] nodes = queue.removeFirst();
            TreeNode r1 = nodes[0];
            TreeNode r2 = nodes[1];
            if (r1 == null || r2 == null) {
                continue;
            }

            r1.val += r2.val;
            if (r1.left == null) {
                r1.left = r2.left;
            } else {
                queue.addLast(new TreeNode[]{r1.left, r2.left});
            }
            if (r1.right == null) {
                r1.right = r2.right;
            } else {
                queue.addLast(new TreeNode[]{r1.right, r2.right});
            }
        }

        return root1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
