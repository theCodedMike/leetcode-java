package org.example.leetcode.editor.cn;
//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
//
// 请注意 ，必须在不复制数组的情况下原地对数组进行操作。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [0,1,0,3,12]
//输出: [1,3,12,0,0]
// 
//
// 示例 2: 
//
// 
//输入: nums = [0]
//输出: [0] 
//
// 
//
// 提示: 
// 
//
// 
// 1 <= nums.length <= 10⁴ 
// -2³¹ <= nums[i] <= 2³¹ - 1 
// 
//
// 
//
// 进阶：你能尽量减少完成的操作次数吗？ 
//
// Related Topics 数组 双指针 👍 2186 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void moveZeroes(int[] nums) {
        //this.copyArray(nums);
        this.twoPointers(nums);
    }

    public void copyArray(int[] nums) {
        // 计数0的个数
        int zeroCount = 0;
        for (int num: nums) {
            if (num == 0) {
                zeroCount++;
            }
        }

        // 复制一个数组来保存新的数值
        int[] copyArray = new int[nums.length];
        int idx = 0;
        for (int num: nums) {
            if (num != 0) {
                copyArray[idx++]  = num;
            }
        }

        // 将0填充在末尾
        while (zeroCount-- != 0) {
            copyArray[idx++] = 0;
        }

        // 合并结果
        for (int i = 0; i < nums.length; i++) {
            nums[i] = copyArray[i];
        }
    }

    public void twoPointers(int[] nums) {
        for (int slow = 0, fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                int tmp = nums[slow];
                nums[slow++] = nums[fast];
                nums[fast] = tmp;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
