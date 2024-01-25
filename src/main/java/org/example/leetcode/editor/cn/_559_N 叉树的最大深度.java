package org.example.leetcode.editor.cn;
//给定一个 N 叉树，找到其最大深度。
//
// 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。 
//
// N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [1,null,3,2,4,null,5,6]
//输出：3
// 
//
// 示例 2： 
//
// 
//
// 
//输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,
//null,13,null,null,14]
//输出：5
// 
//
// 
//
// 提示： 
//
// 
// 树的深度不会超过 1000 。 
// 树的节点数目位于 [0, 10⁴] 之间。 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 👍 380 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Function;

public class _559_N叉树的最大深度 {
    public int maxDepth(Node root) {
        //return this.dfsRecur(root);
        //return this.dfsIter(root);

        //return this.bfsIter1(root);
        return this.bfsIter2(root);
    }

    Function<Node, Integer> recur = (root) -> {
        if (root == null) {
            return 0;
        }

        int maxDepth = 0;
        for (Node n : root.children) {
            maxDepth = Math.max(maxDepth, this.recur.apply(n));
        }

        return maxDepth + 1;
    };
    int dfsRecur(Node root) {
        return this.recur.apply(root);
    }

    int dfsIter(Node root) {
        int maxDepth = 0;

        if (root != null) {
            Deque<Object[]> stack = new ArrayDeque<>() {{
                this.push(new Object[]{root, 1});
            }};
            while (!stack.isEmpty()) {
                Object[] objs = stack.pop();
                Node curr = (Node) objs[0];
                int depth = (int) objs[1];

                maxDepth = Math.max(maxDepth, depth);

                for (Node n : curr.children) {
                    stack.push(new Object[]{n, depth + 1});
                }
            }
        }

        return maxDepth;
    }

    int bfsIter1(Node root) {
        int maxDepth = 0;

        if (root != null) {
            Deque<Object[]> queue = new ArrayDeque<>() {{
                this.addLast(new Object[]{root, 1});
            }};
            while (!queue.isEmpty()) {
                Object[] objs = queue.removeFirst();
                Node curr = (Node) objs[0];
                int depth = (int) objs[1];

                maxDepth = Math.max(maxDepth, depth);

                for (Node n : curr.children) {
                    queue.addLast(new Object[]{n, depth + 1});
                }
            }
        }

        return maxDepth;
    }

    int bfsIter2(Node root) {
        int maxDepth = 0;

        if (root != null) {
            Deque<Node> queue = new ArrayDeque<>() {{
                this.addLast(root);
            }};
            while (!queue.isEmpty()) {
                int size = queue.size();

                for (int i = 0; i < size; i++) {
                    Node curr = queue.removeFirst();
                    for (Node n : curr.children) {
                        queue.addLast(n);
                    }
                }

                maxDepth++;
            }
        }

        return maxDepth;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
