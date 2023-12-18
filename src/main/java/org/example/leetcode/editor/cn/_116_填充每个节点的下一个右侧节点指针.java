package org.example.leetcode.editor.cn;
//给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
//
// 
//struct Node {
//  int val;
//  Node *left;
//  Node *right;
//  Node *next;
//} 
//
// 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。 
//
// 初始状态下，所有 next 指针都被设置为 NULL。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [1,2,3,4,5,6,7]
//输出：[1,#,2,3,#,4,5,6,7,#]
//解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 
//next 指针连接，'#' 标志着每一层的结束。
// 
//
// 
// 
//
// 示例 2: 
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
// 树中节点的数量在
// [0, 2¹² - 1] 范围内 
// -1000 <= node.val <= 1000 
// 
//
// 
//
// 进阶： 
//
// 
// 你只能使用常量级额外空间。 
// 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 链表 二叉树 👍 1078 👎 0


import java.util.ArrayDeque;
import java.util.function.Consumer;

//leetcode submit region begin(Prohibit modification and deletion)

public class _116_填充每个节点的下一个右侧节点指针 {
    // Definition for a Node
    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}
        public Node(int _val) {
            val = _val;
        }
        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }

        public static Node node(int val) {
            return new Node(val);
        }
        public static Node nodeWithChildren(int val, Node left, Node right) {
            return new Node(val, left, right, null);
        }
        public static Node nodeWithLeft(int val, Node left) {
            return new Node(val, left, null, null);
        }
        public static Node nodeWithRight(int val, Node right) {
            return new Node(val, null, right, null);
        }
    };

    public Node connect(Node root) {
        //return this.bfsIter1(root);
        //return this.bfsIter2(root);
        //return this.useNextPointerIter(root);
        return this.useNextPointerRecur(root);
    }

    Consumer<Node> preOrder = (root) -> {
        if (root == null || root.left == null) {
            return;
        }
        root.left.next = root.right;
        if (root.next != null) {
            root.right.next = root.next.left;
        }
        this.preOrder.accept(root.left);
        this.preOrder.accept(root.right);
    };

    Node useNextPointerRecur(Node root) {
        this.preOrder.accept(root);
        return root;
    }

    Node useNextPointerIter(Node root) {
        Node leftmost = root;
        while (leftmost != null) {
            Node curr = leftmost;
            while (curr != null) {
                if (curr.left != null) {
                    curr.left.next = curr.right;
                    if (curr.next != null) {
                        curr.right.next = curr.next.left;
                    }
                }

                curr = curr.next;
            }

            leftmost = leftmost.left;
        }

        return root;
    }

    Node bfsIter1(Node root) {
        if (root != null) {
            ArrayDeque<Node> queue = new ArrayDeque<>() {{
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
            ArrayDeque<Object[]> queue = new ArrayDeque<>() {{
                this.addLast(new Object[]{root, 0});
            }};

            Node prev = null;
            int prevLevel = -1;
            while (!queue.isEmpty()) {
                Object[] objs = queue.removeFirst();
                Node curr = (Node) objs[0];
                int level = (int) objs[1];

                if (level == prevLevel) {
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

}
//leetcode submit region end(Prohibit modification and deletion)
