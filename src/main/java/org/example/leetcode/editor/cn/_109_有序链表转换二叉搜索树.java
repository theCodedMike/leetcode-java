package org.example.leetcode.editor.cn;
//给定一个单链表的头节点 head ，其中的元素 按升序排序 ，将其转换为高度平衡的二叉搜索树。
//
// 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差不超过 1。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入: head = [-10,-3,0,5,9]
//输出: [0,-3,9,-10,null,5]
//解释: 一个可能的答案是[0，-3,9，-10,null,5]，它表示所示的高度平衡的二叉搜索树。
// 
//
// 示例 2: 
//
// 
//输入: head = []
//输出: []
// 
//
// 
//
// 提示: 
//
// 
// head 中的节点数在[0, 2 * 10⁴] 范围内 
// -10⁵ <= Node.val <= 10⁵ 
// 
//
// Related Topics 树 二叉搜索树 链表 分治 二叉树 👍 880 👎 0


import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

//leetcode submit region begin(Prohibit modification and deletion)
public class _109_有序链表转换二叉搜索树 {
    public TreeNode sortedListToBST(ListNode head) {
        //return this.divideAndConquer(head);

        return this.optimizeDivideAndConquer(head);
    }

    BiFunction<ListNode, ListNode, ListNode> getMid = (left, right) -> {
        ListNode slow = left;
        ListNode fast = left;

        while (fast != right && fast.next != right) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    };
    BiFunction<ListNode, ListNode, TreeNode> convert1 = (left, right) -> {
        if (left == right) {
            return null;
        }

        ListNode mid = this.getMid.apply(left, right);
        TreeNode root = new TreeNode(mid.val);

        root.left = this.convert1.apply(left, mid);
        root.right = this.convert1.apply(mid.next, right);

        return root;
    };

    /**
     * 时间复杂度：O(nlog(n))
     * 空间复杂度：O(log(n))
     */
    TreeNode divideAndConquer(ListNode head) {
        return this.convert1.apply(head, null);
    }



    @FunctionalInterface
    interface TriFunction<A, B, C, D> {
        D apply(A a, B b, C c);
    }
    Function<ListNode, Integer> getSize = (head) -> {
        int size = 0;
        while (head != null){
            size++;
            head = head.next;
        }
        return size;
    };
    TriFunction<ListNode[], Integer, Integer, TreeNode> convert2 = (head, left, right) -> {
        if (Objects.equals(left, right)) {
            return null;
        }

        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(0);

        root.left = this.convert2.apply(head, left, mid);

        root.val = head[0].val;
        head[0] = head[0].next;

        root.right = this.convert2.apply(head, mid + 1, right);

        return root;
    };
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(log(n))
     */
    TreeNode optimizeDivideAndConquer(ListNode head) {
        int size = this.getSize.apply(head);
        return this.convert2.apply(new ListNode[]{head}, 0, size);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
