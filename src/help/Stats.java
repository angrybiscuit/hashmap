package help;

import java.util.Arrays;

public class Stats {
    public int maxChainLength;
    public int[] chainsLength;
    public int avgChainLength;
    public int emptyBuckets;
    public int notEmptyBuckets;
    public int collisionCount;

    public Stats(int M) {
        this.chainsLength = new int[M];
    }

    public void calc() {
        System.out.println(Arrays.toString(chainsLength));
        for (int i : chainsLength) {
            avgChainLength += i;
            if (i > maxChainLength) {
                maxChainLength = i;
            }
            if (i > 1) {
                collisionCount += i;
            }
        }
        avgChainLength /= chainsLength.length;
        notEmptyBuckets = chainsLength.length - emptyBuckets;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "maxChainLength=" + maxChainLength +
                ", avgChainLength=" + avgChainLength +
                ", emptyBuckets=" + emptyBuckets +
                ", notEmptyBuckets=" + notEmptyBuckets +
                ", collisionCount=" + collisionCount +
                '}';
    }
}