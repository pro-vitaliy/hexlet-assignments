package exercise;

import java.util.Map;
import java.util.logging.Logger;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] nums) {

        var minThread = new MinThread(nums);
        var maxThread = new MaxThread(nums);

        minThread.start();
        maxThread.start();

        try {
            minThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            maxThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return Map.of(
                "min", minThread.getMinNum(),
                "max", maxThread.getMaxNum()
        );
    }
    // END
}
