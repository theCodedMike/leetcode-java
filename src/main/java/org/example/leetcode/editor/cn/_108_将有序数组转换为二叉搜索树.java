package org.example.leetcode.editor.cn;
//给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
//
// 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。 
//
// 
//
// 示例 1： 
// 
// 
//输入：nums = [-10,-3,0,5,9]
//输出：[0,-3,9,-10,null,5]
//解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
//
// 
//
// 示例 2： 
// 
// 
//输入：nums = [1,3]
//输出：[3,1]
//解释：[1,null,3] 和 [3,1] 都是高度平衡二叉搜索树。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁴ 
// -10⁴ <= nums[i] <= 10⁴ 
// nums 按 严格递增 顺序排列 
// 
//
// Related Topics 树 二叉搜索树 数组 分治 二叉树 👍 1463 👎 0


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

//leetcode submit region begin(Prohibit modification and deletion)
public class _108_将有序数组转换为二叉搜索树 {
    public TreeNode sortedArrayToBST(int[] nums) {
        //return this.recurHelper1(nums);
        //return this.recurHelper2(nums);

        //return this.iterHelper1(nums);
        return this.iterHelper2(nums);
    }

    Function<List<Integer>, TreeNode> convert1 = (nums) -> {
        int size = nums.size();
        if (size == 0) {
            return null;
        }

        int rootIdx = size / 2;
        TreeNode root = new TreeNode(nums.get(rootIdx));
        if (size == 1) {
            return root;
        }

        root.left = this.convert1.apply(nums.subList(0, rootIdx));
        root.right = this.convert1.apply(nums.subList(rootIdx + 1, size));

        return root;
    };

    TreeNode recurHelper1(int[] _nums) {
        List<Integer> nums = Arrays.stream(_nums).boxed().collect(Collectors.toList());
        return this.convert1.apply(nums);
    }

    @FunctionalInterface
    interface TriFunction<A, B, C, D> {
        D apply(A a, B b, C c);
    }
    TriFunction<int[], Integer, Integer, TreeNode> convert2 = (nums, from, to) -> {
        if (Objects.equals(from, to)) {
            return null;
        }

        int rootIdx = (from + to) / 2;
        TreeNode root = new TreeNode(nums[rootIdx]);
        if (to - from == 1) {
            return root;
        }

        root.left = this.convert2.apply(nums, from, rootIdx);
        root.right = this.convert2.apply(nums, rootIdx + 1, to);

        return root;
    };

    TreeNode recurHelper2(int[] nums) {
        return this.convert2.apply(nums, 0, nums.length);
    }

    TreeNode iterHelper1(int[] nums) {
        if (nums.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(nums[0]);
        Deque<Object[]> queue = new ArrayDeque<>() {{
            this.addLast(new Object[]{root, 0, nums.length});
        }};
        while (!queue.isEmpty()) {
            Object[] objs = queue.removeFirst();
            TreeNode curr = (TreeNode) objs[0];
            int lIdx = (int) objs[1];
            int rIdx = (int) objs[2];
            int mid = (lIdx + rIdx) / 2;

            curr.val = nums[mid];
            if (lIdx < mid) {
                TreeNode lNode = new TreeNode(nums[0]);
                curr.left = lNode;
                queue.addLast(new Object[]{lNode, lIdx, mid});
            }
            if (mid + 1 < rIdx) {
                TreeNode rNode = new TreeNode(nums[0]);
                curr.right = rNode;
                queue.addLast(new Object[]{rNode, mid + 1, rIdx});
            }
        }

        return root;
    }

    TreeNode iterHelper2(int[] nums) {
        if (nums.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(nums[0]);
        Deque<Object[]> stack = new ArrayDeque<>() {{
            this.push(new Object[]{root, 0, nums.length});
        }};
        while (!stack.isEmpty()) {
            Object[] objs = stack.pop();
            TreeNode curr = (TreeNode) objs[0];
            int lIdx = (int) objs[1];
            int rIdx = (int) objs[2];
            int mid = (lIdx + rIdx) / 2;

            curr.val = nums[mid];
            if (lIdx < mid) {
                TreeNode lNode = new TreeNode(nums[0]);
                curr.left = lNode;
                stack.push(new Object[]{lNode, lIdx, mid});
            }
            if (mid + 1 < rIdx) {
                TreeNode rNode = new TreeNode(nums[0]);
                curr.right = rNode;
                stack.push(new Object[]{rNode, mid + 1, rIdx});
            }
        }

        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
