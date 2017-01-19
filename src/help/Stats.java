package help;


public class Stats {
    public int maxChainLength;
    public int[] chainsLength;
    public double avgChainLength;
    public int emptyBuckets;
    public int notEmptyBuckets;
    public int collisionCount;

    public Stats(int M) {
        this.chainsLength = new int[M];
    }

    public void calc() {
        int chainCount = 0;
        for (int i : chainsLength) {
            avgChainLength += i;
            if (i > maxChainLength) {
                maxChainLength = i;
            }
            if (i > 1) {
                collisionCount += (i - 1);
                chainCount++;
            }
        }
        avgChainLength /= chainCount;
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