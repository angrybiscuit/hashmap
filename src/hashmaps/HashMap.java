package hashmaps;

import help.Returner;
import help.Stats;
import hashfunctions.HashFunction;

public abstract class HashMap {
    protected int M;  //Initial capacity of table
    protected HashFunction hash;

    public HashMap(int M, HashFunction hash) {
        this.M = M;
        this.hash = hash;
        this.hash.setM(this.M);
    }
    public abstract void setFileName(String fileName);
    public abstract Returner put(long newKey);
    public abstract Returner get(long key);
    public abstract Returner remove(long deleteKey);
    public abstract Stats displayToTXT();
    public abstract void display();

    public int hash(long k) {
        return (int)(hash.hash(k) % M);
    }
}
