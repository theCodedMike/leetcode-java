package org.example.leetcode.editor.cn;
//给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
//
// 叶子节点 是指没有子节点的节点。 
//
// 示例 1： 
// 
// 
//输入：root = [1,2,3,null,5]
//输出：["1->2->5","1->3"]
// 
//
// 示例 2： 
//
// 
//输入：root = [1]
//输出：["1"]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数目在范围 [1, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// Related Topics 树 深度优先搜索 字符串 回溯 二叉树 👍 1077 👎 0


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

//leetcode submit region begin(Prohibit modification and deletion)
public class _257_二叉树的所有路径 {
    public List<String> binaryTreePaths(TreeNode root) {
        //return this.dfsRecur(root);
        //return this.dfsIter(root);
        return this.bfsIter(root);
    }

    @FunctionalInterface
    interface TriConsumer<A, B, C> {
        void accept(A a, B b, C c);
    }

    Function<List<List<Integer>>, List<String>> convert = paths -> paths.stream().map(v -> {
        List<String> s = v.stream().map(String::valueOf).collect(Collectors.toList());
        return String.join("->", s);
    }).collect(Collectors.toList());

    TriConsumer<TreeNode, List<Integer>, List<List<Integer>>> recur = (root, vals_list, paths) -> {
        if (root == null) {
            return;
        }

        vals_list.add(root.val);
        if (root.left == null && root.right == null) {
            paths.add(vals_list);
        } else {
            if (root.left != null) {
                this.recur.accept(root.left, new ArrayList<>(vals_list), paths);
            }
            if (root.right != null) {
                this.recur.accept(root.right, vals_list, paths);
            }
        }
    };

    /**
     * Time Complexity: O(n^2)
     * Space Complexity: O(n^2)
     */
    List<String> dfsRecur(TreeNode root) {
        List<List<Integer>> paths = new ArrayList<>();

        this.recur.accept(root, new ArrayList<>(), paths);

        return this.convert.apply(paths);
    }

    /**
     * Time Complexity: O(n^2)
     * Space Complexity: O(n^2)
     */
    List<String> dfsIter(TreeNode root) {
        List<List<Integer>> paths = new ArrayList<>();

        if (root != null) {
            Deque<Object[]> stack = new ArrayDeque<>() {{
                this.push(new Object[]{root, new ArrayList<Integer>()});
            }};

            while (!stack.isEmpty()) {
                Object[] objs = stack.pop();
                TreeNode curr = (TreeNode) objs[0];
                List<Integer> vals_list = (List<Integer>) objs[1];
                vals_list.add(curr.val);

                if (curr.left == null && curr.right == null) {
                    paths.add(vals_list);
                } else {
                    if (curr.right != null) {
                        stack.push(new Object[]{curr.right, new ArrayList<>(vals_list)});
                    }
                    if (curr.left != null) {
                        stack.push(new Object[]{curr.left, vals_list});
                    }
                }
            }
        }

        return this.convert.apply(paths);
    }

    /**
     * Time Complexity: O(n^2)
     * Space Complexity: O(n^2)
     */
    List<String> bfsIter(TreeNode root) {
        List<List<Integer>> paths = new ArrayList<>();

        if (root != null) {
            Deque<Object[]> queue = new ArrayDeque<>() {{
                this.addLast(new Object[]{root, new ArrayList<Integer>()});
            }};

            while (!queue.isEmpty()) {
                Object[] objs = queue.removeFirst();
                TreeNode curr = (TreeNode) objs[0];
                List<Integer> vals_list = (List<Integer>) objs[1];
                vals_list.add(curr.val);

                if (curr.left == null && curr.right == null) {
                    paths.add(vals_list);
                } else {
                    if (curr.left != null) {
                        queue.addLast(new Object[]{curr.left, new ArrayList<>(vals_list)});
                    }
                    if (curr.right != null) {
                        queue.addLast(new Object[]{curr.right, vals_list});
                    }
                }
            }
        }

        return this.convert.apply(paths);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
