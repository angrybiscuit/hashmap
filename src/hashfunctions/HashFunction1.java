package hashfunctions;

public class HashFunction1 extends HashFunction {
    private double a = 0.00000000002;

    @Override
    public long hash(long k) {
        return (long) (M * ((k * a) % 1));
    }
}