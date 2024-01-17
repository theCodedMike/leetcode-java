import org.example.leetcode.editor.cn.TreeNode;
import org.junit.Assert;
import org.junit.Test;
import org.example.leetcode.editor.cn._450_删除二叉搜索树中的节点;

public class _450_删除二叉搜索树中的节点Test {

    @Test
    public void 删除二叉搜索树中的节点Test1() {
        //    0
        _450_删除二叉搜索树中的节点 obj = new _450_删除二叉搜索树中的节点();
        TreeNode root = TreeNode._new(0);
        TreeNode res = obj.deleteNode(root, 0);
        Assert.assertNull(res);
    }

    @Test
    public void 删除二叉搜索树中的节点Test2() {
        //        5                    5
        //      /  \         3       /  \
        //     3    6       ——>     4    6
        //    / \    \             /      \
        //   2   4    7           2        7
        _450_删除二叉搜索树中的节点 obj = new _450_删除二叉搜索树中的节点();
        TreeNode root = TreeNode._newWithChildren(5,
                TreeNode._newWithChildren(3, TreeNode._new(2), TreeNode._new(4)),
                TreeNode._newWithRight(6, TreeNode._new(7)));
        TreeNode res = obj.deleteNode(root, 3);
        Assert.assertNotNull(res);
    }
}
