package org.example.leetcode.editor.cn;
//给定一个不重复的整数数组 nums 。 最大二叉树 可以用下面的算法从 nums 递归地构建:
//
// 
// 创建一个根节点，其值为 nums 中的最大值。 
// 递归地在最大值 左边 的 子数组前缀上 构建左子树。 
// 递归地在最大值 右边 的 子数组后缀上 构建右子树。 
// 
//
// 返回 nums 构建的 最大二叉树 。 
//
// 
//
// 示例 1： 
// 
// 
//输入：nums = [3,2,1,6,0,5]
//输出：[6,3,5,null,2,0,null,null,1]
//解释：递归调用如下所示：
//- [3,2,1,6,0,5] 中的最大值是 6 ，左边部分是 [3,2,1] ，右边部分是 [0,5] 。
//    - [3,2,1] 中的最大值是 3 ，左边部分是 [] ，右边部分是 [2,1] 。
//        - 空数组，无子节点。
//        - [2,1] 中的最大值是 2 ，左边部分是 [] ，右边部分是 [1] 。
//            - 空数组，无子节点。
//            - 只有一个元素，所以子节点是一个值为 1 的节点。
//    - [0,5] 中的最大值是 5 ，左边部分是 [0] ，右边部分是 [] 。
//        - 只有一个元素，所以子节点是一个值为 0 的节点。
//        - 空数组，无子节点。
// 
//
// 示例 2： 
// 
// 
//输入：nums = [3,2,1]
//输出：[3,null,2,null,1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 1000 
// 0 <= nums[i] <= 1000 
// nums 中的所有整数 互不相同 
// 
//
// Related Topics 栈 树 数组 分治 二叉树 单调栈 👍 760 👎 0


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

//leetcode submit region begin(Prohibit modification and deletion)
public class _654_最大二叉树 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        //return this.recur1(nums);
        //return this.recur2(nums);
        //return this.monotonicStack1(nums);
        return this.monotonicStack2(nums);
    }

    Function<List<Integer>, int[]> getMaxAndIdx = nums -> {
        int[] res = new int[]{Integer.MIN_VALUE, 0};
        for (int i = 0, size = nums.size(); i < size; i++) {
            if (nums.get(i) > res[0]) {
                res[0] = nums.get(i);
                res[1] = i;
            }
        }
        return res;
    };
    Function<List<Integer>, TreeNode> helper1 = nums -> {
        int size = nums.size();
        if (size == 0) {
            return null;
        }

        int[] valAndIdx = this.getMaxAndIdx.apply(nums);
        int maxVal = valAndIdx[0];
        int maxIdx = valAndIdx[1];
        TreeNode root = new TreeNode(maxVal);
        if (size == 1) {
            return root;
        }

        root.left = this.helper1.apply(nums.subList(0, maxIdx));
        root.right = this.helper1.apply(nums.subList(maxIdx + 1, size));

        return root;
    };

    /**
     * Time complexity: O(n^2)
     * Space complexity: O(n)
     */
    TreeNode recur1(int[] _nums) {
        List<Integer> nums = Arrays.stream(_nums).boxed().collect(Collectors.toList());
        return this.helper1.apply(nums);
    }


    @FunctionalInterface
    interface TriFunction<A, B, C, D> {
        D apply(A a, B b, C c);
    }

    TriFunction<List<Integer>, Integer, Integer, TreeNode> helper2 = (nums, lIdx, rIdx) -> {
        int size = rIdx - lIdx;
        if (size == 0) {
            return null;
        }

        int[] valAndIdx = this.getMaxAndIdx.apply(nums.subList(lIdx, rIdx));
        int maxVal = valAndIdx[0];
        int maxIdx = valAndIdx[1] + lIdx;
        TreeNode root = new TreeNode(maxVal);
        if (size == 1) {
            return root;
        }

        root.left = this.helper2.apply(nums, lIdx, maxIdx);
        root.right = this.helper2.apply(nums, maxIdx + 1, rIdx);

        return root;
    };

    /**
     * Time complexity: O(n^2)
     * Space complexity: O(n)
     */
    TreeNode recur2(int[] _nums) {
        List<Integer> nums = Arrays.stream(_nums).boxed().collect(Collectors.toList());
        return this.helper2.apply(nums, 0, _nums.length);
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    TreeNode monotonicStack1(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return null;
        }

        Deque<Integer> stack = new ArrayDeque<>(len);
        TreeNode[] tree = new TreeNode[len];
        int[] left = new int[len];
        int[] right = new int[len];
        Arrays.fill(left, -1);
        Arrays.fill(right, -1);

        for (int i = 0; i < len; i++) {
            tree[i] = new TreeNode(nums[i]);

            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                right[stack.pop()] = i;
            }
            if (!stack.isEmpty()) {
                left[i] = stack.peek();
            }

            stack.push(i);
        }

        TreeNode root = null;
        for (int i = 0; i < len; i++) {
            if (left[i] == -1 && right[i] == -1) {
                root = tree[i];
            } else if (right[i] == -1 || (left[i] != -1 && nums[left[i]] < nums[right[i]])) {
                tree[left[i]].right = tree[i];
            } else {
                tree[right[i]].left = tree[i];
            }
        }

        return root;
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    TreeNode monotonicStack2(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return null;
        }

        Deque<Integer> stack = new ArrayDeque<>(len);
        TreeNode[] tree = new TreeNode[len];

        for (int i = 0; i < len; i++) {
            tree[i] = new TreeNode(nums[i]);

            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                tree[i].left = tree[stack.pop()];
            }
            if (!stack.isEmpty()) {
                tree[stack.peek()].right = tree[i];
            }

            stack.push(i);
        }

        // 这里要取栈底的元素
        return tree[stack.getLast()];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
