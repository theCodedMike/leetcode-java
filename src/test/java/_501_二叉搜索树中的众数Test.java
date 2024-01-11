import org.example.leetcode.editor.cn.TreeNode;
import org.example.leetcode.editor.cn._501_二叉搜索树中的众数;
import org.junit.Assert;
import org.junit.Test;

public class _501_二叉搜索树中的众数Test {


    @Test
    public void _501_二叉搜索树中的众数Test1() {
        //  1
        //   \
        //    2
        //   /
        //  2
        _501_二叉搜索树中的众数 obj = new _501_二叉搜索树中的众数();
        TreeNode root = TreeNode._newWithRight(1, TreeNode._newWithLeft(2, TreeNode._new(2)));
        int[] res = obj.findMode(root);
        Assert.assertArrayEquals(res, new int[]{2});
    }

    @Test
    public void _501_二叉搜索树中的众数Test2() {
        //       6
        //    /    \
        //   2      8
        //  / \    / \
        // 0   4  7   9
        //    / \
        //   2   6
        _501_二叉搜索树中的众数 obj = new _501_二叉搜索树中的众数();
        TreeNode root = TreeNode._newWithChildren(6,
                TreeNode._newWithChildren(2,
                        TreeNode._new(0),
                        TreeNode._newWithChildren(4,
                                TreeNode._new(2),
                                TreeNode._new(6))),
                TreeNode._newWithChildren(8,
                        TreeNode._new(7),
                        TreeNode._new(9))
        );
        int[] res = obj.findMode(root);
        Assert.assertArrayEquals(res, new int[]{2, 6});
    }
}
