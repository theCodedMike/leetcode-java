import org.example.leetcode.editor.cn._332_重新安排行程;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _332_重新安排行程Test {

    @Test
    public void reconstructItinerary1() {
        _332_重新安排行程 obj = new _332_重新安排行程();
        // MUC -> LHR -> SFO
        //  ↑             ↓
        // JFK           SJC
        List<List<String>> tickets = new ArrayList<>() {{
            this.add(new ArrayList<>() {{
                this.add("MUC");
                this.add("LHR");
            }});
            this.add(new ArrayList<>() {{
                this.add("JFK");
                this.add("MUC");
            }});
            this.add(new ArrayList<>() {{
                this.add("SFO");
                this.add("SJC");
            }});
            this.add(new ArrayList<>() {{
                this.add("LHR");
                this.add("SFO");
            }});
        }};
        List<String> res = obj.findItinerary(tickets);
        Assert.assertEquals(res, Arrays.asList("JFK", "MUC", "LHR", "SFO", "SJC"));
    }

    @Test
    public void reconstructItinerary2() {
        _332_重新安排行程 obj = new _332_重新安排行程();
        // SFO
        //  ↑  ↖↘
        // JFK ⇄ ATL
        List<List<String>> tickets = new ArrayList<>() {{
            this.add(new ArrayList<>() {{
                this.add("JFK");
                this.add("SFO");
            }});
            this.add(new ArrayList<>() {{
                this.add("JFK");
                this.add("ATL");
            }});
            this.add(new ArrayList<>() {{
                this.add("SFO");
                this.add("ATL");
            }});
            this.add(new ArrayList<>() {{
                this.add("ATL");
                this.add("JFK");
            }});
            this.add(new ArrayList<>() {{
                this.add("ATL");
                this.add("SFO");
            }});
        }};
        List<String> res = obj.findItinerary(tickets);
        Assert.assertEquals(res, Arrays.asList("JFK", "ATL", "JFK", "SFO", "ATL", "SFO"));
    }
}
