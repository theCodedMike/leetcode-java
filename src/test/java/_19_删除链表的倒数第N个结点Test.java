import org.example.leetcode.editor.cn.ListNode;
import org.example.leetcode.editor.cn._19_删除链表的倒数第N个结点;
import org.junit.Test;

public class _19_删除链表的倒数第N个结点Test {

    @Test
    public void _19_删除链表的倒数第N个结点Test() {
        _19_删除链表的倒数第N个结点 obj = new _19_删除链表的倒数第N个结点();
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        int n = 2;
        ListNode res = obj.removeNthFromEnd(head, 2);
    }
}
