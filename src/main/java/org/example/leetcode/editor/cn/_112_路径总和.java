package org.example.leetcode.editor.cn;
//给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和
// targetSum 。如果存在，返回 true ；否则，返回 false 。 
//
// 叶子节点 是指没有子节点的节点。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
//输出：true
//解释：等于目标和的根节点到叶节点路径如上图所示。
// 
//
// 示例 2： 
// 
// 
//输入：root = [1,2,3], targetSum = 5
//输出：false
//解释：树中存在两条根节点到叶子节点的路径：
//(1 --> 2): 和为 3
//(1 --> 3): 和为 4
//不存在 sum = 5 的根节点到叶子节点的路径。 
//
// 示例 3： 
//
// 
//输入：root = [], targetSum = 0
//输出：false
//解释：由于树是空的，所以不存在根节点到叶子节点的路径。
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数目在范围 [0, 5000] 内 
// -1000 <= Node.val <= 1000 
// -1000 <= targetSum <= 1000 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 1304 👎 0


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
public class _112_路径总和 {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        //return this.dfsRecur1(root, targetSum);
        //return this.dfsIter1(root, targetSum);
        //return this.dfsRecur2(root, targetSum);
        //return this.dfsIter2(root, targetSum);
        //return bfsIter1(root, targetSum);
        return this.bfsIter2(root, targetSum);
    }

    @FunctionalInterface
    interface TriConsumer<A, B, C> {
        void accept(A a, B b, C c);
    }

    TriConsumer<TreeNode, List<Integer>, List<List<Integer>>> recur1 = (root, path, paths) -> {
        if (root == null) {
            return;
        }

        path.add(root.val);
        if (root.left == null && root.right == null) {
            paths.add(path);
        } else {
            if (root.left != null) {
                this.recur1.accept(root.left, new ArrayList<>(path), paths);
            }
            if (root.right != null) {
                this.recur1.accept(root.right, path, paths);
            }
        }
    };

    /**
     * 1. 找出所有的路径
     * 2. 判断每条路径之和是否等于targetSum
     */
    boolean dfsRecur1(TreeNode root, int targetSum) {
        List<List<Integer>> paths = new ArrayList<>();

        this.recur1.accept(root, new ArrayList<>(), paths);

        return paths.stream().anyMatch(p ->
                p.stream().mapToInt(Integer::intValue).sum() == targetSum
        );
    }

    boolean dfsIter1(TreeNode root, int targetSum) {
        List<List<Integer>> paths = new ArrayList<>();

        if (root != null) {
            Deque<Object[]> stack = new ArrayDeque<>() {{
                this.push(new Object[]{root, new ArrayList<>()});
            }};

            while (!stack.isEmpty()) {
                Object[] objs = stack.pop();
                TreeNode curr = (TreeNode) objs[0];
                List<Integer> path = (List<Integer>) objs[1];

                path.add(curr.val);
                if (curr.left == null && curr.right == null) {
                    paths.add(path);
                } else {
                    if (curr.right != null) {
                        stack.push(new Object[]{curr.right, new ArrayList<>(path)});
                    }
                    if (curr.left != null) {
                        stack.push(new Object[]{curr.left, path});
                    }
                }
            }
        }

        return paths.stream().anyMatch(p ->
                p.stream().mapToInt(Integer::intValue).sum() == targetSum
        );
    }


    @FunctionalInterface
    interface TriPredicate<A, B, C> {
        boolean test(A a, B b, C c);
    }
    TriPredicate<TreeNode, Integer, Integer> recur2 = (root, sum, targetSum) -> {
        if (root == null) {
            return false;
        }

        int currSum = sum + root.val;

        if (root.left == null && root.right == null) {
            return currSum == targetSum;
        } else if (root.left != null && root.right != null) {
            return this.recur2.test(root.left, currSum, targetSum) || this.recur2.test(root.right, currSum, targetSum);
        } else if (root.left != null) {
            return this.recur2.test(root.left, currSum, targetSum);
        } else {
            return this.recur2.test(root.right, currSum, targetSum);
        }
    };

    /**
     * 累加每条路径，遇到叶子节点时与targetSum进行比较
     */
    boolean dfsRecur2(TreeNode root, int targetSum) {
        return this.recur2.test(root, 0, targetSum);
    }

    boolean dfsIter2(TreeNode root, int targetSum) {
        if (root != null) {
            Deque<Object[]> stack = new ArrayDeque<>() {{
                this.push(new Object[]{root, 0});
            }};

            while (!stack.isEmpty()) {
                Object[] objs = stack.pop();
                TreeNode curr = (TreeNode) objs[0];
                int sum = (int) objs[1];

                int currSum = sum + curr.val;
                if (curr.left == null && curr.right == null && currSum == targetSum) {
                    return true;
                }

                if (curr.right != null) {
                    stack.push(new Object[]{curr.right, currSum});
                }
                if (curr.left != null) {
                    stack.push(new Object[]{curr.left, currSum});
                }
            }
        }

        return false;
    }

    /**
     * 1. 找出所有的路径
     * 2. 判断每条路径之和是否等于targetSum
     */
    boolean bfsIter1(TreeNode root, int targetSum) {
        List<List<Integer>> paths = new ArrayList<>();

        if (root != null) {
            Deque<Object[]> queue = new ArrayDeque<>() {{
                this.addLast(new Object[]{root, new ArrayList<Integer>()});
            }};

            while (!queue.isEmpty()) {
                Object[] objs = queue.removeFirst();
                TreeNode curr = (TreeNode) objs[0];
                List<Integer> path = (List<Integer>) objs[1];

                path.add(curr.val);
                if (curr.left == null && curr.right == null) {
                    paths.add(path);
                } else {
                    if (curr.left != null) {
                        queue.addLast(new Object[]{curr.left, new ArrayList<>(path)});
                    }
                    if (curr.right != null) {
                        queue.addLast(new Object[]{curr.right, path});
                    }
                }
            }
        }

        return paths.stream().anyMatch(p ->
                p.stream().mapToInt(Integer::intValue).sum() == targetSum
        );
    }

    /**
     * 累加每条路径，遇到叶子节点时与targetSum进行比较
     */
    boolean bfsIter2(TreeNode root, int targetSum) {
        if (root != null) {
            Deque<Object[]> queue = new ArrayDeque<>() {{
                this.addLast(new Object[]{root, 0});
            }};

            while (!queue.isEmpty()) {
                Object[] objs = queue.removeFirst();
                TreeNode curr = (TreeNode) objs[0];
                int sum = (int) objs[1];

                int currSum = sum + curr.val;
                if (curr.left == null && curr.right == null && currSum == targetSum) {
                    return true;
                }

                if (curr.left != null) {
                    queue.addLast(new Object[]{curr.left, currSum});
                }
                if (curr.right != null) {
                    queue.addLast(new Object[]{curr.right, currSum});
                }
            }
        }

        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
