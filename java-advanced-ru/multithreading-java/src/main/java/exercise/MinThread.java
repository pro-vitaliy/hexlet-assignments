package exercise;

// BEGIN
public class MinThread extends Thread {

    private final int[] nums;
    private int minNum;

    public MinThread(int[] nums) {
        this.nums = nums;
    }

    @Override
    public void run() {
        minNum = nums[0];
        for (var i = 1; i < nums.length; i++) {
            if (minNum > nums[i]) {
                this.minNum = nums[i];
            }
        }
    }

    public int getMinNum() {
        return this.minNum;
    }
}
// END
