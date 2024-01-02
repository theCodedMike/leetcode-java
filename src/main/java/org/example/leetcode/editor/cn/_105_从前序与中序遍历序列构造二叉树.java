package org.example.leetcode.editor.cn;
//给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并
//返回其根节点。 
//
// 
//
// 示例 1: 
// 
// 
//输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//输出: [3,9,20,null,null,15,7]
// 
//
// 示例 2: 
//
// 
//输入: preorder = [-1], inorder = [-1]
//输出: [-1]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= preorder.length <= 3000 
// inorder.length == preorder.length 
// -3000 <= preorder[i], inorder[i] <= 3000 
// preorder 和 inorder 均 无重复 元素 
// inorder 均出现在 preorder 
// preorder 保证 为二叉树的前序遍历序列 
// inorder 保证 为二叉树的中序遍历序列 
// 
//
// Related Topics 树 数组 哈希表 分治 二叉树 👍 2170 👎 0


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

//leetcode submit region begin(Prohibit modification and deletion)
public class _105_从前序与中序遍历序列构造二叉树 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //return this.recur1(preorder, inorder);
        //return this.recur2(preorder, inorder);
        return this.iter1(preorder, inorder);
    }

    BiFunction<List<Integer>, List<Integer>, TreeNode> helper1 = (preorder, inorder) -> {
        int size = preorder.size();
        if (size == 0) {
            return null;
        }

        Integer rootVal = preorder.getFirst();
        TreeNode root = new TreeNode(rootVal);
        if (size == 1) {
            return root;
        }

        int idxAtInorder = inorder.indexOf(rootVal);
        List<Integer> leftInorder = inorder.subList(0, idxAtInorder);
        List<Integer> rightInorder = inorder.subList(idxAtInorder + 1, inorder.size());
        preorder = preorder.subList(1, preorder.size());
        List<Integer> leftPreOrder = preorder.subList(0, leftInorder.size());
        List<Integer> rightPreOrder = preorder.subList(leftInorder.size(), preorder.size());

        root.left = this.helper1.apply(leftPreOrder, leftInorder);
        root.right = this.helper1.apply(rightPreOrder, rightInorder);

        return root;
    };

    TreeNode recur1(int[] _preorder, int[] _inorder) {
        List<Integer> preorder = Arrays.stream(_preorder).boxed().collect(Collectors.toList());
        List<Integer> inorder = Arrays.stream(_inorder).boxed().collect(Collectors.toList());
        return this.helper1.apply(preorder, inorder);
    }


    @FunctionalInterface
    interface SeptFunction<A, B, C, D, E, F, G, H> {
        H apply(A a, B b, C c, D d, E e, F f, G g);
    }

    SeptFunction<int[], Integer, Integer, int[], Integer, Integer, Map<Integer, Integer>, TreeNode> helper2 = (preorder, preorderLIdx, preorderRIdx, inorder, inorderLIdx, inorderRIdx, map) -> {
        int size = preorderRIdx - preorderLIdx;
        if (size == 0) {
            return null;
        }
        int rootVal = preorder[preorderLIdx];
        TreeNode root = new TreeNode(rootVal);
        if (size == 1) {
            return root;
        }

        int idxAtInorder = map.get(rootVal);
        int leftInorderSize = idxAtInorder - inorderLIdx;
        int rightInorderSize = inorderRIdx - idxAtInorder - 1;

        root.left = this.helper2.apply(preorder, preorderLIdx + 1, preorderLIdx + 1 + leftInorderSize, inorder, inorderLIdx, idxAtInorder, map);
        root.right = this.helper2.apply(preorder, preorderRIdx - rightInorderSize, preorderRIdx, inorder, idxAtInorder + 1, inorderRIdx, map);
        return root;
    };

    TreeNode recur2(int[] preorder, int[] inorder) {
        int size = preorder.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            map.put(inorder[i], i);
        }

        return this.helper2.apply(preorder, 0, size, inorder, 0, size, map);
    }

    TreeNode iter1(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new ArrayDeque<>() {{
            this.push(root);
        }};
        int inorderIdx = 0;

        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode top = stack.peek();
            if (top.val != inorder[inorderIdx]) {
                top.left = new TreeNode(preorderVal);
                stack.push(top.left);
            } else {
                do {
                    top = stack.pop();
                    inorderIdx++;
                } while (!stack.isEmpty() && stack.peek().val == inorder[inorderIdx]);

                top.right = new TreeNode(preorderVal);
                stack.push(top.right);
            }
        }

        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
