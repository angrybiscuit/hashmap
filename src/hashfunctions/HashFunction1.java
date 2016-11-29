package hashfunctions;

public class HashFunction1 extends HashFunction {
    public double a = 0.000000000002; //0000000

    public void setM(int M) {
        this.M = M;
    }

    @Override
    public long hash(long k) {
        return (long) (M * ((k * a) % 1));
    }
}