package org.example.leetcode.editor.cn;
//给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
//
//树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [1,null,3,2,4,null,5,6]
//输出：[[1],[3,2,4],[5,6]]
// 
//
// 示例 2： 
//
// 
//
// 
//输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,
//null,13,null,null,14]
//输出：[[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
// 
//
// 
//
// 提示： 
//
// 
// 树的高度不会超过 1000 
// 树的节点总数在 [0, 10^4] 之间 
// 
//
// Related Topics 树 广度优先搜索 👍 424 👎 0


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)

public class _429_N叉树的层序遍历 {
    public List<List<Integer>> levelOrder(Node root) {
        //return this.dfsRecurPreOrder(root);
        return this.dfsIterPreOrder1(root);
        //return this.dfsIterPreOrder2(root);
        //return this.dfsIterPreOrder3(root);

        //return this.bfsIter1(root);
        //return this.bfsIter2(root);
    }

    @FunctionalInterface
    interface TriConsumer<A, B, C> {
        void accept(A a, B b, C c);
    }

    TriConsumer<Node, Integer, List<List<Integer>>> preOrder = (root, level, res) -> {
        if (root == null) {
            return;
        }
        if (level == res.size()) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(root.val);

        for (Node child : root.children) {
            this.preOrder.accept(child, level + 1, res);
        }
    };

    List<List<Integer>> dfsRecurPreOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        this.preOrder.accept(root, 0, res);
        return res;
    }

    List<List<Integer>> dfsIterPreOrder1(Node _root) {
        List<List<Integer>> res = new ArrayList<>();

        Deque<Object[]> stack = new ArrayDeque<>();
        Object[] root = new Object[]{_root, 0};
        while (root[0] != null || !stack.isEmpty()) {
            while (root[0] != null) {
                Node curr = (Node) root[0];
                int level = (int) root[1];

                if (level == res.size()) {
                    res.add(new ArrayList<>());
                }
                res.get(level).add(curr.val);

                root = new Object[]{this.isBlank(curr.children) ? null : curr.children.get(0), level + 1};
                stack.push(new Object[]{curr, level});
            }

            Object[] objs = stack.pop();
            Node curr = (Node) objs[0];
            int level = (int) objs[1];
            if (this.isBlank(curr.children) || curr.children.size() == 1) {
                root = new Object[]{null, level + 1};
            } else {
                Node right = curr.children.remove(1);
                if (curr.children.size() > 1) {
                    stack.push(new Object[]{ curr, level});
                }
                root = new Object[]{right, level + 1};
            }

        }

        return res;
    }

    boolean isBlank(List<Node> children) {
        return children == null || children.isEmpty();
    }

    List<List<Integer>> dfsIterPreOrder2(Node _root) {
        List<List<Integer>> res = new ArrayList<>();

        Deque<Object[]> stack = new ArrayDeque<>();
        Object[] root = new Object[]{_root, 0};
        while (root[0] != null || !stack.isEmpty()) {
            if (root[0] != null) {
                Node curr = (Node) root[0];
                int level = (int) root[1];

                if (level == res.size()) {
                    res.add(new ArrayList<>());
                }
                res.get(level).add(curr.val);

                root = new Object[]{this.isBlank(curr.children) ? null : curr.children.get(0), level + 1};
                stack.push(new Object[]{curr, level});
            } else {
                Object[] objs = stack.pop();
                Node curr = (Node) objs[0];
                int level = (int) objs[1];
                if (this.isBlank(curr.children) || curr.children.size() == 1) {
                    root = new Object[]{null, level + 1};
                } else {
                    Node right = curr.children.remove(1);
                    if (curr.children.size() > 1) {
                        stack.push(new Object[]{ curr, level});
                    }
                    root = new Object[]{right, level + 1};
                }
            }
        }

        return res;
    }

    List<List<Integer>> dfsIterPreOrder3(Node _root) {
        List<List<Integer>> res = new ArrayList<>();

        if (_root != null) {
            Deque<Object[]> stack = new ArrayDeque<>() {{
                this.push(new Object[]{_root, 0});
            }};
            while (!stack.isEmpty()) {
                Object[] root = stack.pop();
                Object curr = root[0];
                int level = (int) root[1];

                switch (curr) {
                    case Node node -> {
                        Collections.reverse(node.children);
                        for (Node n : node.children) {
                            stack.push(new Object[]{n, level + 1});
                        }
                        stack.push(new Object[]{node.val, level});
                    }
                    case Integer val -> {
                        if (level == res.size()) {
                            res.add(new ArrayList<>());
                        }
                        res.get(level).add(val);
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + curr);
                }
            }
        }

        return res;
    }

    List<List<Integer>> bfsIter1(Node root) {
        List<List<Integer>> res = new ArrayList<>();

        if (root != null) {
            Deque<Node> queue = new ArrayDeque<>() {{
                this.addLast(root);
            }};
            while (!queue.isEmpty()) {
                int levelSize = queue.size();

                List<Integer> levelList = new ArrayList<>();
                for (int i = 0; i < levelSize; i++) {
                    Node curr = queue.removeFirst();
                    levelList.add(curr.val);

                    for (Node child : curr.children) {
                        queue.addLast(child);
                    }

                }
                res.add(levelList);
            }
        }

        return res;
    }

    List<List<Integer>> bfsIter2(Node root) {
        List<List<Integer>> res = new ArrayList<>();

        if (root != null) {
            Deque<Object[]> queue = new ArrayDeque<>() {{
                this.addLast(new Object[]{root, 0});
            }};
            while (!queue.isEmpty()) {
                Object[] objs = queue.removeFirst();
                Node curr = (Node) objs[0];
                int level = (int) objs[1];

                if (level == res.size()) {
                    res.add(new ArrayList<>());
                }
                res.get(level).add(curr.val);

                for (Node child : curr.children) {
                    queue.addLast(new Object[]{child, level + 1});
                }
            }
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
