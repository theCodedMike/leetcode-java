package org.example.leetcode.editor.cn;
//给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并
//返回这颗 二叉树 。 
//
// 
//
// 示例 1: 
// 
// 
//输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
//输出：[3,9,20,null,null,15,7]
// 
//
// 示例 2: 
//
// 
//输入：inorder = [-1], postorder = [-1]
//输出：[-1]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= inorder.length <= 3000 
// postorder.length == inorder.length 
// -3000 <= inorder[i], postorder[i] <= 3000 
// inorder 和 postorder 都由 不同 的值组成 
// postorder 中每一个值都在 inorder 中 
// inorder 保证是树的中序遍历 
// postorder 保证是树的后序遍历 
// 
//
// Related Topics 树 数组 哈希表 分治 二叉树 👍 1159 👎 0


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

//leetcode submit region begin(Prohibit modification and deletion)
public class _106_从中序与后序遍历序列构造二叉树 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        //return this.recur1(inorder, postorder);
        //return this.recur2(inorder, postorder);
        return this.iter1(inorder, postorder);
    }

    BiFunction<List<Integer>, List<Integer>, TreeNode> helper1 = (postorder, inorder) -> {
        int size = postorder.size();
        if (size == 0) {
            return null;
        }

        Integer rootVal = postorder.getLast();
        TreeNode root = new TreeNode(rootVal);
        if (size == 1) {
            return root;
        }

        int idxAtInorder = inorder.indexOf(rootVal);
        List<Integer> leftInorder = inorder.subList(0, idxAtInorder);
        List<Integer> rightInorder = inorder.subList(idxAtInorder + 1, size);
        List<Integer> leftPostorder = postorder.subList(0, leftInorder.size());
        List<Integer> rightPostorder = postorder.subList(leftInorder.size(), size - 1);
        root.left = this.helper1.apply(leftPostorder, leftInorder);
        root.right = this.helper1.apply(rightPostorder, rightInorder);

        return root;
    };
    TreeNode recur1(int[] _inorder, int[] _postorder) {
        List<Integer> inorder = Arrays.stream(_inorder).boxed().collect(Collectors.toList());
        List<Integer> postorder = Arrays.stream(_postorder).boxed().collect(Collectors.toList());
        return this.helper1.apply(postorder, inorder);
    }

    @FunctionalInterface
    interface SeptFunction<A, B, C, D, E, F, G, H> {
        H apply(A a, B b, C c, D d, E e, F f, G g);
    }

    SeptFunction<int[], Integer, Integer, int[], Integer, Integer, Map<Integer, Integer>, TreeNode> helper2 =
            (postorder, postorderLIdx, postorderRIdx, inorder, inorderLIdx, inorderRIdx, map) -> {
        int size = postorderRIdx - postorderLIdx;
        if (size == 0) {
            return null;
        }
        int rootVal = postorder[postorderRIdx - 1];
        TreeNode root = new TreeNode(rootVal);
        if (size == 1) {
            return root;
        }

        int idxAtInorder = map.get(rootVal);
        int leftInorderSize = idxAtInorder - inorderLIdx;

        root.left = this.helper2.apply(postorder, postorderLIdx, postorderLIdx + leftInorderSize, inorder, inorderLIdx, idxAtInorder, map);
        root.right = this.helper2.apply(postorder, postorderLIdx + leftInorderSize, postorderRIdx - 1, inorder, idxAtInorder + 1, inorderRIdx, map);

        return root;
    };
    TreeNode recur2(int[] inorder, int[] postorder) {
        int size = inorder.length;
        Map<Integer, Integer> map = new HashMap<>(size);
        for (int i = 0; i < size; i++) {
            map.put(inorder[i], i);
        }
        return this.helper2.apply(postorder, 0, size, inorder, 0, size, map);
    }

    TreeNode iter1(int[] inorder, int[] postorder) {
        int size = postorder.length;
        if (size == 0) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[size - 1]);
        Deque<TreeNode> stack = new ArrayDeque<>() {{
            this.push(root);
        }};
        int inorderIdx = size - 1;

        for (int i = size - 2; i >= 0; i--) {
            int postorderVal = postorder[i];
            TreeNode top = stack.peek();
            if (top.val != inorder[inorderIdx]) {
                top.right = new TreeNode(postorderVal);
                stack.push(top.right);
            } else {
                do {
                    top = stack.pop();
                    inorderIdx--;
                } while (!stack.isEmpty() && stack.peek().val == inorder[inorderIdx]);

                top.left = new TreeNode(postorderVal);
                stack.push(top.left);
            }
        }

        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
