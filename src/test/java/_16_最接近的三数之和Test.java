import org.example.leetcode.editor.cn._16_最接近的三数之和;
import org.junit.Assert;
import org.junit.Test;

public class _16_最接近的三数之和Test {

    @Test
    public void threeSumClosest1() {
        _16_最接近的三数之和 obj = new _16_最接近的三数之和();
        int[] nums = new int[]{-1, 2, 1, -4};
        int target = 1;
        int res = obj.threeSumClosest(nums, target);
        Assert.assertEquals(res, 2);
    }

    @Test
    public void threeSumClosest2() {
        _16_最接近的三数之和 obj = new _16_最接近的三数之和();
        int[] nums = new int[]{0, 0, 0};
        int target = 1;
        int res = obj.threeSumClosest(nums, target);
        Assert.assertEquals(res, 0);
    }
}
