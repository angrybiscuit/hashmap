package hashmaps;

import hashfunctions.HashFunction;
import help.Returner;
import help.Stats;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

public class InnerChainHashMap extends HashMap{
    protected Entry[] table;   //Array of Entry.
    protected String fileName;
    protected final long DELETED = Long.MAX_VALUE;

    public InnerChainHashMap(int M, HashFunction hashFunction, String filename) {
        super(M, hashFunction);
        this.fileName = filename;
        table = new Entry[M];
    }

    public InnerChainHashMap(int M, HashFunction hashFunction) {
        super(M, hashFunction);
        this.fileName = "InnerChainHashMap.txt";
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

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Returner put(long newKey){

        Returner r = new Returner();
        r.success = true;
        //calculate index of key.
        int hash = hash(newKey);
        //create new entry.

        //if table location does not contain any entry, store entry there.
        if (table[hash] == null || table[hash].key == DELETED) {
            table[hash] = new Entry(newKey, M, hash);
        } else {
            Entry current = table[hash];

            while (current.next != M) { //we have reached last entry of chain.
                r.operationCount++;
                if (current.key == newKey) { // override
                    table[current.index] = new Entry(newKey, current.next, current.index);
                    return r;
                } else if (current.key == DELETED) { // found deleted one so insert there
                    current.key = newKey;
                    return r;
                }
                current = table[current.next];
            }

            for (int i = M-1; i > 0; i--) { //find a place to put new key from end
                r.operationCount++;
                if (table[i] == null) {
                    table[i] = new Entry(newKey, M, i);
                    current.next = i;
                    return r;
                }
            }
            r.success = false;
        }
        return r;
    }

    public Returner get(long key) {
        Returner r = new Returner();

        //calculate index of key.
        int hash = hash(key);
        //create new entry.

        //if table location does not contain any entry, store entry there.
        if (table[hash] == null || table[hash].key == DELETED) {
            return r;
        } else {
            Entry current = table[hash];

            while (current.next != M) { //we have reached last entry of chain.
                r.operationCount++;
                if (current.key == key) { // found what we looked for
                    r.success = true;
                    return r;
                }
                current = table[current.next];
            }
        }
        return r;
    }

    public Returner remove(long deleteKey) {

        Returner r = new Returner();
        int hash = hash(deleteKey);

        if (table[hash] == null) {
            return r;
        } else {
            Entry current = table[hash];
            Entry previous;
            do {
                r.operationCount++;
                if (current.key == deleteKey) {
                    current.key = DELETED;
                    r.success = true;
                    return r;
                }
                previous = current;
                current = table[current.next];
            } while (previous.next != M);

            return r;
        }

    }

    private String dispEntry(Entry e) {
        if (e == null) return "{null}";

        String s = "{key=";
        if (e.key != DELETED) {
            s += e.key+", next=";
        } else {
            s += "DELETED, next=";
        }
        if (e.next == M) {
            s += "M}";
        } else {
            s += e.next+"}";
        }
        return s;
    }

    public Stats displayToTXT() {

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(fileName, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < M; i++) {
            if (table[i] != null)
                writer.println(dispEntry(table[i]));
            else {
                writer.println("{null}");
            }
        }

        Stats stats =  calcChains(); //todo calcChains is insane
        writer.println(stats);
        writer.close();
        return stats;
    }

    public void display() {
        for (int i = 0; i < M; i++) {
            System.out.println(dispEntry(table[i]));
        }
        System.out.println(calcChains());
    }



    private Stats calcChains() {

        Stats stats = new Stats(M);
        int e;

        for (int i = 0; i < M; i++) {
            if (table[i] != null && table[i].next == M) {
                stats.chainsLength[i]++;
                e = getPrevEntry(i);
                while (e != M) {
                    stats.chainsLength[i]++;
                    e = getPrevEntry(e);
                }
            } else if (table[i] == null) {
                stats.emptyBuckets++;
            }
        }
        stats.calc();
        return stats;
    }
    private int getPrevEntry(int entryIndex) {
        for (int i = M-1; i >= 0; i--) {
            if (table[i] != null && table[i].next == entryIndex) {
                return i;
            }
        }
        return M;
    }


}
