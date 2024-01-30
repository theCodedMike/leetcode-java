import org.example.leetcode.editor.cn._40_组合总和II;
import org.junit.Test;

import java.util.List;

public class _40_组合总和IITest {

    @Test
    public void 组合总和IITest() {
        _40_组合总和II obj = new _40_组合总和II();
        int[] candidates = new int[]{10,1,2,7,6,1,5};
        int target = 8;
        List<List<Integer>> res = obj.combinationSum2(candidates, target);
        System.out.println(res);
    }
}
