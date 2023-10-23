package org.example.leetcode.editor.cn;
//给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
//
// 
// 
// 
// 
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,2,3,4,5]
//输出：[5,4,3,2,1]
// 
//
// 示例 2： 
// 
// 
//输入：head = [1,2]
//输出：[2,1]
// 
//
// 示例 3： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目范围是 [0, 5000] 
// -5000 <= Node.val <= 5000 
// 
//
// 
//
// 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？ 
//
// Related Topics 递归 链表 👍 3395 👎 0

/**
 * Definition for singly-linked list.
 */
public class ListNode {
     int val;
     ListNode next;
}


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public ListNode reverseList(ListNode head) {
        return this.iterationHelper(head);
        //return this.recursionHelper(null, head);
    }

    public ListNode iterationHelper(ListNode head) {
        ListNode newHead = null;

        ListNode curr = head;
        while (head != null) {
            curr = head;
            head = head.next;
            curr.next = newHead;
            newHead = curr;
        }

        return newHead;
    }

    public ListNode recursionHelper(ListNode prev, ListNode curr) {
        if (curr == null) {
            return prev;
        } else {
            ListNode next = curr.next;
            curr.next = prev;
            return this.recursionHelper(curr, next);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
