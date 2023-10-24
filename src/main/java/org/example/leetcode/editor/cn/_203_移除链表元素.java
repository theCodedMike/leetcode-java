package org.example.leetcode.editor.cn;
//给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,2,6,3,4,5,6], val = 6
//输出：[1,2,3,4,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [], val = 1
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [7,7,7,7], val = 7
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 列表中的节点数目在范围 [0, 10⁴] 内 
// 1 <= Node.val <= 50 
// 0 <= val <= 50 
// 
//
// Related Topics 递归 链表 👍 1344 👎 0

/**
 * Definition for singly-linked list.
 */
//leetcode submit region begin(Prohibit modification and deletion)
class _203_移除链表元素 {
    public ListNode removeElements(ListNode head, int val) {
        return this.iterationHelper(head, val);
        //return this.recursionHelper(head, val);
    }

    public ListNode iterationHelper(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(-1, head);
        ListNode p = dummy;
        while (p.next != null) {
            ListNode curr = p.next;
            if (curr.val == val) {
                p.next = curr.next;
            } else {
                p = curr;
            }
        }

        return dummy.next;
    }

    public ListNode recursionHelper(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        head.next = this.recursionHelper(head.next, val);
        return head.val == val ? head.next : head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
