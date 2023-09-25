import org.example.leetcode.editor.en.TwoSum;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TwoSumTest {
    @Test
    public void twoSumTest() {
        TwoSum twoSum = new TwoSum();
        int[] nums = new int[]{2,7,11,15};
        int target = 9;
        int[] res = twoSum.twoSum(nums, target);

        int[] expected = new int[]{0, 1};
        assertEquals(expected[0], res[0]);
        assertEquals(expected[1], res[1]);
    }
}
