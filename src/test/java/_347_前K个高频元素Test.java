import org.example.leetcode.editor.cn._347_前K个高频元素;
import org.junit.Assert;
import org.junit.Test;

public class _347_前K个高频元素Test {
    @Test
    public void topKFrequent1() {
        _347_前K个高频元素 obj = new _347_前K个高频元素();
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        int k = 2;
        int[] res = obj.topKFrequent(nums, k);
        Assert.assertArrayEquals(res, new int[]{2, 1}); // new int[]{2, 1}也可以，与顺序无关
    }

    @Test
    public void topKFrequent2() {
        _347_前K个高频元素 obj = new _347_前K个高频元素();
        int[] nums = new int[]{1};
        int k = 1;
        int[] res = obj.topKFrequent(nums, k);
        Assert.assertArrayEquals(res, new int[]{1});
    }
}
