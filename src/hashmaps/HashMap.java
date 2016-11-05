package hashmaps;

import help.Stats;
import hashfunctions.HashFunction;

public abstract class HashMap {
    protected int M;  //Initial capacity of table
    protected HashFunction hash;

    public HashMap(int M, HashFunction hash) {
        this.M = M;
        this.hash = hash;
    }

    public abstract void put(long newKey);
    public abstract boolean remove(long deleteKey);
    public abstract Stats displayToTXT();
    public abstract void display();

    public int hash(long k) {
        return (int)(hash.hash(k) % M);
    }
}
