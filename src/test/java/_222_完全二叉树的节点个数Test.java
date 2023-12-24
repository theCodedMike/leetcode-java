import org.junit.Assert;
import org.junit.Test;
import org.example.leetcode.editor.cn._222_完全二叉树的节点个数;
import org.example.leetcode.editor.cn.TreeNode;
public class _222_完全二叉树的节点个数Test {


    @Test
    public void _222_完全二叉树的节点个数Test1() {
        _222_完全二叉树的节点个数 obj = new _222_完全二叉树的节点个数();
        TreeNode root = TreeNode._newWithChildren(1,
                TreeNode._newWithChildren(2,
                        TreeNode._new(4),
                        TreeNode._new(5)),
                TreeNode._newWithLeft(3,
                        TreeNode._new(6)
                )
        );
        int count = obj.countNodes(root);
        Assert.assertEquals(count, 6);
    }
}
