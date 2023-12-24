package org.example.leetcode.editor.cn;
//给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
//
// 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层
//为第 h 层，则该层包含 1~ 2ʰ 个节点。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [1,2,3,4,5,6]
//输出：6
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数目范围是[0, 5 * 10⁴] 
// 0 <= Node.val <= 5 * 10⁴ 
// 题目数据保证输入的树是 完全二叉树 
// 
//
// 
//
// 进阶：遍历树来统计节点是一种时间复杂度为 O(n) 的简单解决方案。你可以设计一个更快的算法吗？ 
//
// Related Topics 位运算 树 二分查找 二叉树 👍 1071 👎 0


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.BiPredicate;
import java.util.function.Function;

//leetcode submit region begin(Prohibit modification and deletion)
public class _222_完全二叉树的节点个数 {
    public int countNodes(TreeNode root) {
        //return this.dfsRecur(root);
        //return this.bfsIter(root);
        return this.binarySearch(root);
    }

    Function<TreeNode, Integer> count = root -> {
        if (root == null) {
            return 0;
        }

        return this.count.apply(root.left) + this.count.apply(root.right) + 1;
    };
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(log(n))
     */
    int dfsRecur(TreeNode root) {
        return this.count.apply(root);
    }

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    int bfsIter(TreeNode root) {
        int count = 0;

        if (root != null) {
            Deque<TreeNode> queue = new ArrayDeque<>() {{
                this.addLast(root);
            }};
            while (!queue.isEmpty()) {
                TreeNode curr = queue.removeFirst();
                count++;

                if (curr.left != null) {
                    queue.addLast(curr.left);
                }
                if (curr.right != null) {
                    queue.addLast(curr.right);
                }
            }
        }

        return count;
    }

    Function<TreeNode, Integer> calc_level = root -> {
        int level = 0;
        TreeNode curr = root;
        while (curr != null) {
            level++;
            curr = curr.left;
        }
        return level;
    };

    BiPredicate<TreeNode, Integer> exist = (root, expectedCount) -> {
        String path = Integer.toBinaryString(expectedCount);
        TreeNode curr = root;
        for (int i = 1, size = path.length(); i < size; i++) {
            if (path.charAt(i) == '1') {
                curr = curr.right;
            } else {
                curr = curr.left;
            }
            if (curr == null) {
                return false;
            }
        }

        return true;
    };
    /**
     * Time Complexity: O(log(n) * log(n))
     * Space Complexity: O(1)
     */
    int binarySearch(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Integer level = this.calc_level.apply(root);
        int minCount = (int) Math.pow(2, level - 1);
        int maxCount = (int) Math.pow(2, level);

        while (minCount < maxCount) {
            int mid = (minCount + maxCount) / 2;
            if (this.exist.test(root, mid)) {
                minCount = mid + 1;
            } else {
                maxCount = mid;
            }
        }

        return minCount - 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
