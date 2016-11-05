package hashmaps;

import hashfunctions.HashFunction;
import help.Stats;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class InnerChainHashMap extends HashMap{
    protected Entry[] table;   //Array of Entry.
    protected String fileName;
    protected final long DELETED = 0x7fffffffffffffffL;

    public InnerChainHashMap(int M, HashFunction hashFunction, String filename) {
        super(M, hashFunction);
        this.fileName = filename;
        table = new Entry[M];
    }

    static class Entry {
        long key;
        int next;
        final int index;

        public Entry(long key, int next, int index){
            this.key = key;
            this.next = next;
            this.index = index;
        }

        @Override
        public String toString() {
            return "{" +
                    "index=" + index +
                    ", key=" + key +
                    ", next=" + next +
                    '}';
        }
    }

    public void put(long newKey){

        //calculate index of key.
        int hash = hash(newKey);
        //create new entry.

        //if table location does not contain any entry, store entry there.
        if (table[hash] == null || table[hash].key == DELETED) {
            table[hash] = new Entry(newKey, M, hash);
        } else {
            Entry current = table[hash];

            while (current.next != M) { //we have reached last entry of bucket.
                if (current.key == newKey) {
                    table[current.index] = new Entry(newKey, current.next, current.index);
                    return;
                } else if (current.key == DELETED) {
                    current.key = newKey;
                }
                current = table[current.next];
            }

            for (int i = M-1; i > 0; i--) {
                if (table[i] == null) {
                    table[i] = new Entry(newKey, M, i);
                    current.next = i;
                    return;
                }
            }
        }
    }

    public boolean remove(long deleteKey) {

        int hash = hash(deleteKey);

        if (table[hash] == null) {
            return false;
        } else {
            Entry current = table[hash];
            Entry previous;
            do {
                if(current.key == deleteKey){
                    current.key = DELETED;
                    return true;
                }
                previous = current;
                current = table[current.next];
            } while (previous.next != M);

            return false;
        }

    }

    public Stats displayToTXT() {

        Stats stats = new Stats(M);

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(fileName, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < M; i++) {
            writer.println(table[i]);
        }
        stats.calc();
        writer.println(stats);
        writer.close();
        return stats;
    }
    public void display() {

        Stats stats = new Stats(M);

        for (int i = 0; i < M; i++) {
            System.out.println(table[i]);
        }
        stats.calc();
        System.out.println(stats);
    }


}
