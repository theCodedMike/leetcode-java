import org.junit.Test;
import org.example.leetcode.editor.cn._49_字母异位词分组;
import static org.junit.Assert.assertEquals;
import java.util.List;

public class _49_字母异位词分组Test {

    @Test
    public void _49_字母异位词分组Test() {
        _49_字母异位词分组 test = new _49_字母异位词分组();
        String[] strs1 = new String[] {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> res1 = test.groupAnagrams(strs1);
        System.out.println(res1);

        String[] strs2 = new String[] {""};
        List<List<String>> res2 = test.groupAnagrams(strs2);
        assertEquals(res2.get(0).get(0), "");

        String[] strs3 = new String[] {"a"};
        List<List<String>> res3 = test.groupAnagrams(strs3);
        assertEquals(res3.get(0).get(0), "a");
    }
}
