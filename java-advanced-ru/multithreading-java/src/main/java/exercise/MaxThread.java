package exercise;

// BEGIN
public class MaxThread extends Thread {

    private final int[] nums;
    private int maxNum;

    public MaxThread(int[] nums) {
        this.nums = nums;
    }

    public int getMaxNum() {
        return this.maxNum;
    }

    @Override
    public void run() {
        maxNum = nums[0];
        for(var num : nums) {
            if (num > maxNum) {
                maxNum = num;
            }
        }
    }
}
// END
