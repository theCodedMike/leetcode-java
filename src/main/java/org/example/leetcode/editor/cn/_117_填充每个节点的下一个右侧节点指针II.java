package org.example.leetcode.editor.cn;
//给定一个二叉树：
//
// 
//struct Node {
//  int val;
//  Node *left;
//  Node *right;
//  Node *next;
//} 
//
// 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL 。 
//
// 初始状态下，所有 next 指针都被设置为 NULL 。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [1,2,3,4,5,null,7]
//输出：[1,#,2,3,#,4,5,7,#]
//解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化输出按层序遍历顺序（由 next 指
//针连接），'#' 表示每层的末尾。 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 树中的节点数在范围 [0, 6000] 内 
// -100 <= Node.val <= 100 
// 
//
// 进阶： 
//
// 
// 你只能使用常量级额外空间。 
// 使用递归解题也符合要求，本题中递归程序的隐式栈空间不计入额外空间复杂度。 
// 
//
// 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 链表 二叉树 👍 819 👎 0

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Consumer;

import org.example.leetcode.editor.cn._116_填充每个节点的下一个右侧节点指针.Node;

//leetcode submit region begin(Prohibit modification and deletion)

public class _117_填充每个节点的下一个右侧节点指针II {
    public Node connect(Node root) {
        //return this.bfsIter1(root);
        //return this.bfsIter2(root);
        //return this.useNextPointerIter(root);
        return this.useNextPointerRecur(root);
    }

    Node bfsIter1(Node root) {
        if (root != null) {
            Deque<Node> queue = new ArrayDeque<>() {{
                this.addLast(root);
            }};
            while (!queue.isEmpty()) {
                int levelSize = queue.size();
                Node prev = null;

                for (int i = 0; i < levelSize; i++) {
                    Node curr = queue.removeFirst();
                    if (i > 0) {
                        prev.next = curr;
                    }
                    prev = curr;

                    if (curr.left != null) {
                        queue.addLast(curr.left);
                    }
                    if (curr.right != null) {
                        queue.addLast(curr.right);
                    }
                }
            }
        }

        return root;
    }

    Node bfsIter2(Node root) {
        if (root != null) {
            Deque<Object[]> queue = new ArrayDeque<>() {{
                this.addLast(new Object[]{root, 0});
            }};
            Node prev = null;
            int prevLevel = -1;
            while (!queue.isEmpty()) {
                Object[] objs = queue.removeFirst();
                Node curr = (Node) objs[0];
                int level = (int) objs[1];
                if (prevLevel == level) {
                    prev.next = curr;
                }
                prev = curr;
                prevLevel = level;

                if (curr.left != null) {
                    queue.addLast(new Object[]{curr.left, level + 1});
                }
                if (curr.right != null) {
                    queue.addLast(new Object[]{curr.right, level + 1});
                }
            }
        }

        return root;
    }

    Node useNextPointerIter(Node root) {
        Node leftmost = root;
        while (leftmost != null) {
            Node curr = leftmost;
            while (curr != null) {
                if (curr.left != null && curr.right != null) {
                    curr.left.next = curr.right;
                }
                if ((curr.left != null || curr.right != null) && curr.next != null) {
                    Node childOfCurr = curr.right != null ? curr.right : curr.left;
                    Node next = curr.next;
                    while (next != null) {
                        if (next.left != null || next.right != null) {
                            Node childOfNext = next.left != null ? next.left : next.right;
                            childOfCurr.next = childOfNext;
                            break;
                        } else {
                            next = next.next;
                        }
                    }
                }

                curr = curr.next;
            }

            leftmost = leftmost.left != null ? leftmost.left : (leftmost.right != null ? leftmost.right : leftmost.next);
        }

        return root;
    }

    Node useNextPointerRecur(Node root) {
        this.preOrder.accept(root);
        return root;
    }

    Consumer<Node> preOrder = (curr) -> {
        if (curr == null) {
            return;
        }
        if (curr.left != null && curr.right != null) {
            curr.left.next = curr.right;
        }
        if ((curr.left != null || curr.right != null) && curr.next != null) {
            Node childOfCurr = curr.right != null ? curr.right : curr.left;
            Node next = curr.next;
            while (next != null) {
                if (next.left != null || next.right != null) {
                    Node childOfNext = next.left != null ? next.left : next.right;
                    childOfCurr.next = childOfNext;
                    break;
                } else {
                    next = next.next;
                }
            }
        }
        this.preOrder.accept(curr.right);
        this.preOrder.accept(curr.left);
    };
}
//leetcode submit region end(Prohibit modification and deletion)
