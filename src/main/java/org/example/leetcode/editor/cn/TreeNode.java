package org.example.leetcode.editor.cn;

/**
 * Definition for a binary tree node.
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode _new(int val) {
        return new TreeNode(val);
    }

    public static TreeNode _newWithLeft(int val, TreeNode left) {
        return new TreeNode(val, left ,null);
    }

    public static TreeNode _newWithRight(int val, TreeNode right) {
        return new TreeNode(val, null ,right);
    }

    public static TreeNode _newWithChildren(int val, TreeNode left, TreeNode right) {
        return new TreeNode(val, left ,right);
    }
}

