package org.example.leetcode.editor.cn;
//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,2,3,4,5], n = 2
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1], n = 1
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2], n = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中结点的数目为 sz 
// 1 <= sz <= 30 
// 0 <= Node.val <= 100 
// 1 <= n <= sz 
// 
//
// 
//
// 进阶：你能尝试使用一趟扫描实现吗？ 
//
// Related Topics 链表 双指针 👍 2714 👎 0

/**
 * Definition for singly-linked list.
 */
import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)

public class _19_删除链表的倒数第N个结点 {

    static class Tuple {
        ListNode data;
        int level;

        Tuple(ListNode data, int level) {
            this.data = data;
            this.level = level;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        //return this.calcLengthOfLinkedList(head, n);
        //return this.useStack(head, n);
        //return this.twoPointers(head, n);

        return this.recursionHelper(head, n).data;
    }

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    ListNode calcLengthOfLinkedList(ListNode head, int n) {
        // 计算长度
        int len = 0;
        for (ListNode p = head; p != null; ++len) {
            p = p.next;
        }
        // 将指针移动到待删除节点的前一个节点上
        ListNode dummy = new ListNode(-1, head);
        ListNode p = dummy;
        for (int i = 0; i < len - n; i++) {
            p = p.next;
        }
        p.next = p.next.next;

        return dummy.next;
    }

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    ListNode useStack(ListNode head, int n) {
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            ListNode curr = head;
            stack.push(curr);
            head = head.next;
        }

        int i = 0;
        ListNode new_head = null;
        while (!stack.isEmpty()) {
            ListNode node = stack.pop();
            ++i;
            if (i != n) {
                node.next = new_head;
                new_head = node;
            }
        }

        return new_head;
    }

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    ListNode twoPointers(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);
        ListNode fast = dummy;
        ListNode slow = dummy;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;

        return dummy.next;
    }

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    Tuple recursionHelper(ListNode curr, int n) {
        if (curr == null) {
            return new Tuple(null, 0);
        }
        Tuple res = this.recursionHelper(curr.next, n);
        curr.next = res.data;
        return res.level + 1 == n ? new Tuple(curr.next, res.level + 1) : new Tuple(curr, res.level + 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
