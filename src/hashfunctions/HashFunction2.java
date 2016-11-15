package hashfunctions;

public class HashFunction2 extends HashFunction {

    public long hash(long k) {
        k ^= (k >>> 20) ^ (k >>> 12);
        return (k ^ (k >>> 7) ^ (k >>> 4));
    }
}
