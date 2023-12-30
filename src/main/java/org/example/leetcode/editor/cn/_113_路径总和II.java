package org.example.leetcode.editor.cn;
//给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
//
// 叶子节点 是指没有子节点的节点。 
//
// 
// 
// 
// 
// 
//
// 示例 1： 
// 
// 
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//输出：[[5,4,11,2],[5,8,4,5]]
// 
//
// 示例 2： 
// 
// 
//输入：root = [1,2,3], targetSum = 5
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1,2], targetSum = 0
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点总数在范围 [0, 5000] 内 
// -1000 <= Node.val <= 1000 
// -1000 <= targetSum <= 1000 
// 
//
// Related Topics 树 深度优先搜索 回溯 二叉树 👍 1073 👎 0


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
public class _113_路径总和II {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        //return this.dfsRecur1(root, targetSum);
        //return this.dfsIter1(root, targetSum);
        //return this.dfsRecur2(root, targetSum);
        return this.bfsIter(root, targetSum);
    }

    @FunctionalInterface
    interface QuintConsumer<A, B, C, D, E> {
        void accept(A a, B b, C c, D d, E e);
    }

    QuintConsumer<TreeNode, Integer, Integer, List<Integer>, List<List<Integer>>> recur1 = (root, targetSum, sum, path, paths) -> {
        if (root == null) {
            return;
        }

        int currSum = sum + root.val;
        path.add(root.val);

        if (root.left == null && root.right == null && currSum == targetSum) {
            paths.add(path);
        } else {
            if (root.left != null) {
                this.recur1.accept(root.left, targetSum, currSum, new ArrayList<>(path), paths);
            }
            if (root.right != null) {
                this.recur1.accept(root.right, targetSum, currSum, path, paths);
            }
        }
    };

    List<List<Integer>> dfsRecur1(TreeNode root, int targetSum) {
        List<List<Integer>> paths = new ArrayList<>();

        this.recur1.accept(root, targetSum, 0, new ArrayList<>(), paths);

        return paths;
    }

    List<List<Integer>> dfsIter1(TreeNode root, int targetSum) {
        List<List<Integer>> paths = new ArrayList<>();

        if (root != null) {
            Deque<Object[]> stack = new ArrayDeque<>() {{
                this.push(new Object[]{root, 0, new ArrayList<>()});
            }};

            while (!stack.isEmpty()) {
                Object[] objs = stack.pop();
                TreeNode curr = (TreeNode) objs[0];
                int sum = (int) objs[1];
                List<Integer> path = (List<Integer>) objs[2];

                int currSum = sum + curr.val;
                path.add(curr.val);

                if (curr.left == null && curr.right == null && currSum == targetSum) {
                    paths.add(path);
                } else {
                    if (curr.right != null) {
                        stack.push(new Object[]{curr.right, currSum, new ArrayList<>(path)});
                    }
                    if (curr.left != null) {
                        stack.push(new Object[]{curr.left, currSum, path});
                    }
                }
            }
        }

        return paths;
    }

    @FunctionalInterface
    interface TriFunction<A, B, C, D> {
        D apply(A a, B b, C c);
    }

    TriFunction<TreeNode, Integer, Integer, List<List<Integer>>> recur2 = (root, targetSum, sum) -> {
        if (root == null) {
            return new ArrayList<>();
        }

        int currVal = root.val;
        int currSum = sum + currVal;
        if (root.left == null && root.right == null) {
            List<List<Integer>> paths = new ArrayList<>();

            if (currSum == targetSum) {
                paths.add(new ArrayList<>() {{
                    this.add(currVal);
                }});
            }

            return paths;
        } else {
            List<List<Integer>> l_paths = this.recur2.apply(root.left, targetSum, currSum);
            List<List<Integer>> r_paths = this.recur2.apply(root.right, targetSum, currSum);

            l_paths.forEach(p -> p.addFirst(currVal));
            r_paths.forEach(p -> p.addFirst(currVal));
            l_paths.addAll(r_paths);

            return l_paths;
        }
    };

    List<List<Integer>> dfsRecur2(TreeNode root, int targetSum) {
        return this.recur2.apply(root, targetSum, 0);
    }

    List<List<Integer>> bfsIter(TreeNode root, int targetSum) {
        List<List<Integer>> paths = new ArrayList<>();

        if (root != null) {
            Deque<Object[]> queue = new ArrayDeque<>() {{
                this.addLast(new Object[]{root, 0, new ArrayList<>()});
            }};

            while (!queue.isEmpty()) {
                Object[] objs = queue.removeFirst();
                TreeNode curr = (TreeNode) objs[0];
                int sum = (int) objs[1];
                List<Integer> path = (List<Integer>) objs[2];

                int currSum = sum + curr.val;
                path.add(curr.val);

                if (curr.left == null && curr.right == null && currSum == targetSum) {
                    paths.add(path);
                } else {
                    if (curr.left != null) {
                        queue.addLast(new Object[]{curr.left, currSum, new ArrayList<>(path)});
                    }
                    if (curr.right != null) {
                        queue.addLast(new Object[]{curr.right, currSum, path});
                    }
                }
            }
        }

        return paths;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
