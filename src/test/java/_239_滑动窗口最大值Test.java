import org.example.leetcode.editor.cn._239_滑动窗口最大值;
import org.junit.Assert;
import org.junit.Test;

public class _239_滑动窗口最大值Test {


    @Test
    public void maxSlidingWindow1() {
        _239_滑动窗口最大值 obj = new _239_滑动窗口最大值();
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] res = obj.maxSlidingWindow(nums, k);
        Assert.assertArrayEquals(res, new int[]{3, 3, 5, 5, 6, 7});
    }

    @Test
    public void maxSlidingWindow2() {
        _239_滑动窗口最大值 obj = new _239_滑动窗口最大值();
        int[] nums = new int[]{1};
        int k = 1;
        int[] res = obj.maxSlidingWindow(nums, k);
        Assert.assertArrayEquals(res, new int[]{1});
    }

    @Test
    public void maxSlidingWindow3() {
        _239_滑动窗口最大值 obj = new _239_滑动窗口最大值();
        int[] nums = new int[]{1, -1};
        int k = 1;
        int[] res = obj.maxSlidingWindow(nums, k);
        Assert.assertArrayEquals(res, new int[]{1, -1});
    }

}
