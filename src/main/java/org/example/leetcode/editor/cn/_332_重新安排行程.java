package org.example.leetcode.editor.cn;
//给你一份航线列表 tickets ，其中 tickets[i] = [fromi, toi] 表示飞机出发和降落的机场地点。请你对该行程进行重新规划排序。
//
//
// 所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。如果存在多种有效的行程，请你按字典排序返回最小的行程组合。 
//
//
// 
// 例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前。 
// 
//
// 假定所有机票至少存在一种合理的行程。且所有的机票 必须都用一次 且 只能用一次。 
//
// 
//
// 示例 1： 
// 
// 
//输入：tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
//输出：["JFK","MUC","LHR","SFO","SJC"]
// 
//
// 示例 2： 
// 
// 
//输入：tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL",
//"SFO"]]
//输出：["JFK","ATL","JFK","SFO","ATL","SFO"]
//解释：另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"] ，但是它字典排序更大更靠后。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= tickets.length <= 300 
// tickets[i].length == 2 
// fromi.length == 3 
// toi.length == 3 
// fromi 和 toi 由大写英文字母组成 
// fromi != toi 
// 
//
// Related Topics 深度优先搜索 图 欧拉回路 👍 889 👎 0


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
public class _332_重新安排行程 {
    public List<String> findItinerary(List<List<String>> tickets) {
        //return this.backtracking(tickets);

        return this.hierholzer(tickets);
    }

    @FunctionalInterface
    interface QuadrConsumer<A, B, C, D> {
        void accept(A a, B b, C c, D d);
    }

    QuadrConsumer<List<List<String>>, boolean[], List<List<String>>, List<String>> dfs1 =
            (tickets, used, path, res) -> {
                if (!res.isEmpty()) {
                    return;
                }
                if (path.size() == tickets.size()) {
                    for (int i = 0, size = path.size(); i < size; i++) {
                        res.add(path.get(i).get(0));
                        if (i == size - 1) {
                            res.add(path.get(i).get(1));
                        }
                    }
                    return;
                }

                for (int i = 0, size = tickets.size(); i < size; i++) {
                    if (used[i]) {
                        continue;
                    }
                    if (!path.isEmpty() && path.getLast().get(1) != tickets.get(i).get(0)) {
                        continue;
                    }
                    if (path.isEmpty() && tickets.get(i).get(0) != "JFK") {
                        continue;
                    }

                    used[i] = true;
                    path.addLast(tickets.get(i));

                    this.dfs1.accept(tickets, used, path, res);

                    used[i] = false;
                    path.removeLast();
                }
            };

    List<String> backtracking(List<List<String>> tickets) {
        tickets.sort(Comparator.comparing((List<String> t) -> t.getFirst()).thenComparing(List::getLast));
        boolean[] used = new boolean[tickets.size()];
        List<String> res = new ArrayList<>();

        this.dfs1.accept(tickets, used, new ArrayList<>(), res);

        return res;
    }


    @FunctionalInterface
    interface TriConsumer<A, B, C> {
        void accept(A a, B b, C c);
    }

    TriConsumer<String, Map<String, PriorityQueue<String>>, List<String>> dfs2 =
            (curr, map, res) -> {
                while (map.containsKey(curr) && !map.get(curr).isEmpty()) {
                    String next = map.get(curr).poll();
                    this.dfs2.accept(next, map, res);
                }

                res.add(curr);
            };

    List<String> hierholzer(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for (List<String> t : tickets) {
            String from = t.getFirst();
            String to = t.getLast();
            PriorityQueue<String> minHeap = map.getOrDefault(from, new PriorityQueue<>());
            minHeap.add(to);
            map.put(from, minHeap);
        }
        List<String> res = new ArrayList<>();

        this.dfs2.accept("JFK", map, res);

        Collections.reverse(res);
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
