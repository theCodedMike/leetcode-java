package org.example.leetcode.editor.cn;
//给你一个含重复值的二叉搜索树（BST）的根节点 root ，找出并返回 BST 中的所有 众数（即，出现频率最高的元素）。
//
// 如果树中有不止一个众数，可以按 任意顺序 返回。 
//
// 假定 BST 满足如下定义： 
//
// 
// 结点左子树中所含节点的值 小于等于 当前节点的值 
// 结点右子树中所含节点的值 大于等于 当前节点的值 
// 左子树和右子树都是二叉搜索树 
// 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [1,null,2,2]
//输出：[2]
// 
//
// 示例 2： 
//
// 
//输入：root = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数目在范围 [1, 10⁴] 内 
// -10⁵ <= Node.val <= 10⁵ 
// 
//
// 
//
// 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内） 
//
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 726 👎 0


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;

//leetcode submit region begin(Prohibit modification and deletion)
public class _501_二叉搜索树中的众数 {
    public int[] findMode(TreeNode root) {
        //return this.useHashmapRecur(root);
        //return this.useHashmapIter(root);

        //return this.inorderTraversalRecur(root);
        //return this.inorderTraversalIter(root);

        //return this.morrisInorderIter1(root);
        return this.morrisInorderIter2(root);
    }

    BiConsumer<TreeNode, Map<Integer, Integer>> preorder = (root, counter) -> {
        if (root == null) {
            return;
        }

        counter.put(root.val, counter.getOrDefault(root.val, 0) + 1);
        this.preorder.accept(root.left, counter);
        this.preorder.accept(root.right, counter);
    };

    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    int[] useHashmapRecur(TreeNode root) {
        Map<Integer, Integer> counter = new HashMap<>();

        this.preorder.accept(root, counter);

        Integer maxFreq = counter.values().stream().max(Comparator.naturalOrder()).orElse(0);
        return counter.entrySet().stream()
                .filter(e -> Objects.equals(e.getValue(), maxFreq))
                .map(Map.Entry::getKey).mapToInt(Integer::intValue)
                .toArray();
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    int[] useHashmapIter(TreeNode root) {
        Map<Integer, Integer> counter = new HashMap<>();

        if (root != null) {
            Deque<TreeNode> stack = new ArrayDeque<>() {{
                this.push(root);
            }};
            while (!stack.isEmpty()) {
                TreeNode curr = stack.pop();
                counter.put(curr.val, counter.getOrDefault(curr.val, 0) + 1);

                if (curr.right != null) {
                    stack.push(curr.right);
                }
                if (curr.left != null) {
                    stack.push(curr.left);
                }
            }
        }

        Integer maxFreq = counter.values().stream().max(Comparator.naturalOrder()).orElse(0);
        return counter.entrySet().stream()
                .filter(e -> Objects.equals(e.getValue(), maxFreq))
                .map(Map.Entry::getKey).mapToInt(Integer::intValue)
                .toArray();
    }

    /**
     * val: 正在遍历的节点的值
     * params:
     *   0: 当前节点的值（当前值）
     *   1: 当前值出现的频率
     *   2: 频率的最大值
     * res: 最终生成的结果
     */
    TriConsumer<Integer, int[], List<Integer>> update = (val, params, res) -> {
        if (val == params[0]) {
            params[1]++;
        } else {
            params[0] = val;
            params[1] = 1;
        }

        if (params[1] > params[2]) {
            res.clear();
            params[2] = params[1];
        }
        if (params[1] == params[2]) {
            res.add(val);
        }
    };


    @FunctionalInterface
    interface TriConsumer<A, B, C> {
        void accept(A a, B b, C c);
    }

    TriConsumer<TreeNode, int[], List<Integer>> inorder = (root, params, res) -> {
        if (root == null) {
            return;
        }
        this.inorder.accept(root.left, params, res);

        this.update.accept(root.val, params, res);

        this.inorder.accept(root.right, params, res);
    };

    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    int[] inorderTraversalRecur(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        int[] params = {Integer.MIN_VALUE, 0, 0};
        this.inorder.accept(root, params, res);
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    int[] inorderTraversalIter(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root != null) {
            int[] params = new int[]{Integer.MIN_VALUE, 0, 0};
            Deque<Object> stack = new ArrayDeque<>() {{
                this.push(root);
            }};
            while (!stack.isEmpty()) {
                Object obj = stack.pop();
                switch (obj) {
                    case TreeNode curr -> {
                        if (curr.right != null) {
                            stack.push(curr.right);
                        }
                        stack.push(curr.val);
                        if (curr.left != null) {
                            stack.push(curr.left);
                        }
                    }
                    case Integer val -> {
                        this.update.accept(val, params, res);
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + obj);
                }
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    int[] morrisInorderIter1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        int[] params = new int[]{Integer.MIN_VALUE, 0, 0};
        TreeNode prev = null;

        while (root != null) {
            if (root.left != null) {
                prev = root.left;
                while (prev.right != null && prev.right != root) {
                    prev = prev.right;
                }

                if (prev.right == null) {
                    prev.right = root;
                    root = root.left;
                } else {
                    prev.right = null;
                    this.update.accept(root.val, params, res);
                    root = root.right;
                }
            } else {
                this.update.accept(root.val, params, res);
                root = root.right;
            }
        }

        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    int[] morrisInorderIter2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        int[] params = new int[]{Integer.MIN_VALUE, 0, 0};
        TreeNode prev = null;

        while (root != null) {
            if (root.left == null) {
                this.update.accept(root.val, params, res);
                root = root.right;
                continue;
            }

            prev = root.left;
            while (prev.right != null && prev.right != root) {
                prev = prev.right;
            }
            if (prev.right == null) {
                prev.right = root;
                root = root.left;
            } else {
                prev.right = null;
                this.update.accept(root.val, params, res);
                root = root.right;
            }
        }

        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
