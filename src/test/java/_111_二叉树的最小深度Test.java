import org.example.leetcode.editor.cn.TreeNode;
import org.junit.Assert;
import org.junit.Test;
import org.example.leetcode.editor.cn._111_二叉树的最小深度;

public class _111_二叉树的最小深度Test {
    //    3
    //   / \
    //  9   20
    //     /  \
    //    15   7
    @Test
    public void _111_二叉树的最小深度Test1() {
        _111_二叉树的最小深度 obj = new _111_二叉树的最小深度();
        TreeNode root = TreeNode._newWithChildren(3,
                TreeNode._new(9),
                TreeNode._newWithChildren(20,
                        TreeNode._new(15),
                        TreeNode._new(7)
                )
        );
        int res = obj.minDepth(root);
        Assert.assertEquals(res, 2);
    }

    //  2
    //   \
    //    3
    //     \
    //      4
    //       \
    //        5
    //         \
    //          6
    @Test
    public void _111_二叉树的最小深度Test2() {
        _111_二叉树的最小深度 obj = new _111_二叉树的最小深度();
        TreeNode root = TreeNode._newWithRight(2,
                TreeNode._newWithRight(3,
                        TreeNode._newWithRight(4,
                                TreeNode._newWithRight(5,
                                        TreeNode._new(6)
                                )
                        )
                )
        );
        int res = obj.minDepth(root);
        Assert.assertEquals(res, 5);
    }
}
