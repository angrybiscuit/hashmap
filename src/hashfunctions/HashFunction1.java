package hashfunctions;

public class HashFunction1 extends HashFunction {
    private double a =  0.0000002;
    private int m = 10000;

    @Override
    public long hash(long k) {
        return (long) (m * ((k * a) % 1));
    }
}
