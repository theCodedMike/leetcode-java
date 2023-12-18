import org.junit.Test;
import org.example.leetcode.editor.cn._117_填充每个节点的下一个右侧节点指针II;
import org.example.leetcode.editor.cn._116_填充每个节点的下一个右侧节点指针.Node;

public class _117_填充每个节点的下一个右侧节点指针IITest {


    @Test
    public void _117_填充每个节点的下一个右侧节点指针Test() {
        _117_填充每个节点的下一个右侧节点指针II obj = new _117_填充每个节点的下一个右侧节点指针II();
        //         2
        //      /     \
        //     1       3
        //    / \     / \
        //   0   7   9   1
        //  /   / \     / \
        // 2   1   0   8   8
        //        /
        //       7
        //
        // 注意：先序递归时，必须先右后左，因为如果先左后右，那么处理节点7时，7 -> 9这里就断了，因为节点3位于右半侧，还没有处理。
        //      此时只能处理节点7的左右孩子，无法处理右孩子的next指针(即节点0要指向节点8)，这是致命的。所以必须先右后左。
        Node root = Node.nodeWithChildren(2,
                Node.nodeWithChildren(1,
                        Node.nodeWithLeft(0,
                                Node.node(2)),
                        Node.nodeWithChildren(7,
                                Node.node(1),
                                Node.nodeWithLeft(0,
                                        Node.node(7)))),
                Node.nodeWithChildren(3,
                        Node.node(9),
                        Node.nodeWithChildren(1,
                                Node.node(8),
                                Node.node(8))));
        obj.connect(root);
    }

}
