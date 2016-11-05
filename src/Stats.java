
public class Stats {
    public int maxChainLength;
    public int[] chainsLength;
    public int avgChainLength;
    public int emptyBuckets;
    public int notEmptyBuckets;

    Stats(int M) {
        this.chainsLength = new int[M];
    }

    public void calc() {
        for (int i : chainsLength) {
            avgChainLength += i;
            if (i > maxChainLength) {
                maxChainLength = i;
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
                '}';
    }
}