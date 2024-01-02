import org.example.leetcode.editor.cn.TreeNode;
import org.junit.Assert;
import org.junit.Test;
import org.example.leetcode.editor.cn._105_从前序与中序遍历序列构造二叉树;

public class _105_从前序与中序遍历序列构造二叉树Test {


    @Test
    public void _105_从前序与中序遍历序列构造二叉树Test1() {
        _105_从前序与中序遍历序列构造二叉树 obj = new _105_从前序与中序遍历序列构造二叉树();
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        TreeNode root = obj.buildTree(preorder, inorder);
        Assert.assertEquals(true, root != null);
    }
}
