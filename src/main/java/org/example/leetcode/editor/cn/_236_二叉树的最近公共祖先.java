package org.example.leetcode.editor.cn;
//给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。” 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//输出：3
//解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
// 
//
// 示例 2： 
// 
// 
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//输出：5
//解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
// 
//
// 示例 3： 
//
// 
//输入：root = [1,2], p = 1, q = 2
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [2, 10⁵] 内。 
// -10⁹ <= Node.val <= 10⁹
// 所有 Node.val 互不相同 。 
// p != q 
// p 和 q 均存在于给定的二叉树中。 
// 
//
// Related Topics 树 深度优先搜索 二叉树 👍 2565 👎 0


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
public class _236_二叉树的最近公共祖先 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //return this.dfsRecur(root, p, q);

        return this.storeParentNode(root, p, q);
    }

    @FunctionalInterface
    interface TriFunction<A, B, C, D> {
        D apply(A a, B b, C c);
    }

    TriFunction<TreeNode, Integer, Integer, TreeNode> postorder = (root, p, q) -> {
        if (root == null || root.val == p || root.val == q) {
            return root;
        }

        TreeNode l_res = this.postorder.apply(root.left, p, q);
        TreeNode r_res = this.postorder.apply(root.right, p, q);

        if (l_res != null && r_res != null) {
            return root;
        }

        return l_res != null ? l_res : r_res;
    };
    TreeNode dfsRecur(TreeNode root, TreeNode p, TreeNode q) {
        return this.postorder.apply(root, p.val, q.val);
    }

    TreeNode storeParentNode(TreeNode root, TreeNode p, TreeNode q) {
        Deque<TreeNode> queue = new ArrayDeque<>() {{
            this.addLast(root);
        }};
        Map<Integer, Object[]> map = new HashMap<>() {{
            this.put(root.val, new Object[]{null, false});
        }};

        while (!queue.isEmpty()) {
            TreeNode curr = queue.removeFirst();

            if (curr.left != null) {
                map.put(curr.left.val, new Object[]{curr, false});
                queue.addLast(curr.left);
            }
            if (curr.right != null) {
                map.put(curr.right.val, new Object[]{curr, false});
                queue.addLast(curr.right);
            }
        }

        while (p != null) {
            int key = p.val;
            Object[] objs = map.get(key);
            objs[1] = true;
            p = (TreeNode) objs[0];
            map.put(key, objs);
        }

        while (q != null) {
            int key = q.val;
            Object[] objs = map.get(key);
            if ((boolean) objs[1]) {
                return q;
            }
            q = (TreeNode) objs[0];
        }

        return null;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
