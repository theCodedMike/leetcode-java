import org.junit.Assert;
import org.junit.Test;
import org.example.leetcode.editor.cn._17_电话号码的字母组合;

import java.util.HashSet;
import java.util.List;

public class _17_电话号码的字母组合Test {


    @Test
    public void 电话号码的字母组合Test1() {
        _17_电话号码的字母组合 obj = new _17_电话号码的字母组合();
        List<String> res = obj.letterCombinations("23");

        HashSet<String> set = new HashSet<>() {{
            this.add("ad");
            this.add("ae");
            this.add("af");
            this.add("bd");
            this.add("be");
            this.add("bf");
            this.add("cd");
            this.add("ce");
            this.add("cf");
        }};

        for (String s : res) {
            Assert.assertTrue(set.contains(s));
        }
    }
}
