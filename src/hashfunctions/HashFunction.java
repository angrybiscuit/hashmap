package hashfunctions;

public abstract class HashFunction {
    protected int M;

    public void setM(int M) {
        this.M = M;
    }

    public abstract long hash(long k);
}
