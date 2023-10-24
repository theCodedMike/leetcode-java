package org.example.leetcode.editor.cn;
//给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,2,3,4]
//输出：[2,1,4,3]
// 
//
// 示例 2： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 100] 内 
// 0 <= Node.val <= 100 
// 
//
// Related Topics 递归 链表 👍 2065 👎 0

/**
 * Definition for singly-linked list.
 */


//leetcode submit region begin(Prohibit modification and deletion)

class _24_两两交换链表中的节点 {
    public ListNode swapPairs(ListNode head) {
        //return this.iterationHelper(head);
        return this.recursionHelper(head);
    }

    ListNode iterationHelper(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode temp = dummy;

        while (temp.next != null) {
            ListNode curr = temp.next;
            ListNode next = curr.next;
            if (next == null) {
                break;
            }
            curr.next = next.next;
            next.next = curr;
            temp.next = next;
            temp = curr;
        }

        return dummy.next;
    }

    ListNode recursionHelper(ListNode curr) {
        if (curr == null) {
            return null;
        }
        ListNode next = curr.next;
        if (next == null) {
            return curr;
        }
        curr.next = this.recursionHelper(next.next);
        next.next = curr;
        return next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
