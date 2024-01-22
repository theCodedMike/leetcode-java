package org.example.leetcode.editor.cn;
//给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
//
// 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。 
//
// 
//
// 示例 1： 
// 
// 
//输入：p = [1,2,3], q = [1,2,3]
//输出：true
// 
//
// 示例 2： 
// 
// 
//输入：p = [1,2], q = [1,null,2]
//输出：false
// 
//
// 示例 3： 
// 
// 
//输入：p = [1,2,1], q = [1,1,2]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 两棵树上的节点数目都在范围 [0, 100] 内 
// -10⁴ <= Node.val <= 10⁴ 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 1121 👎 0


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.BiFunction;

//leetcode submit region begin(Prohibit modification and deletion)
public class _100_相同的树 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        //return this.dfsRecur(p, q);
        //return this.dfsIter(p, q);

        return this.bfsIter(p, q);
    }

    BiFunction<TreeNode, TreeNode, Boolean> compare = (p, q) -> {
        if (p == null && q == null) {
            return true;
        } else if (p != null && q != null) {
            if (p.val != q.val) {
                return false;
            }

            return this.compare.apply(p.left, q.left)
                    && this.compare.apply(p.right, q.right);
        } else {
            return false;
        }
    };

    boolean dfsRecur(TreeNode p, TreeNode q) {
        return this.compare.apply(p, q);
    }

    boolean dfsIter(TreeNode _p, TreeNode _q) {
        Deque<TreeNode[]> stack = new ArrayDeque<>() {{
            this.push(new TreeNode[]{_p, _q});
        }};
        while (!stack.isEmpty()) {
            TreeNode[] nodes = stack.pop();
            TreeNode p = nodes[0];
            TreeNode q = nodes[1];

            if (p != null && q != null) {
                if (p.val != q.val) {
                    return false;
                }

                stack.push(new TreeNode[]{p.right, q.right});
                stack.push(new TreeNode[]{p.left, q.left});
            } else {
                if (!(p == null && q == null)) {
                    return false;
                }
            }
        }

        return true;
    }

    boolean bfsIter(TreeNode _p, TreeNode _q) {
        Deque<TreeNode[]> queue = new ArrayDeque<>() {{
            this.addLast(new TreeNode[]{_p, _q});
        }};
        while (!queue.isEmpty()) {
            TreeNode[] nodes = queue.removeFirst();
            TreeNode p = nodes[0];
            TreeNode q = nodes[1];

            if (p != null && q != null) {
                if (p.val != q.val) {
                    return false;
                }

                queue.addLast(new TreeNode[]{p.left, q.left});
                queue.addLast(new TreeNode[]{p.right, q.right});
            } else {
                if (!(p == null && q == null)) {
                    return false;
                }
            }
        }

        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
