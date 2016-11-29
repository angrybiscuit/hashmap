package hashmaps;

import hashfunctions.HashFunction;
import help.Returner;
import help.Stats;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class ChainHashMap extends HashMap{
    protected Entry[] table;   //Array of Entry.
    protected String fileName;
    public int override;

    public ChainHashMap(int M, HashFunction hash, String filename) {
        super(M, hash);
        this.fileName = filename;
        table = new Entry[M];
    }

    public ChainHashMap(int M, HashFunction hash) {
        super(M, hash);
        this.fileName = "ChainHashMap.txt";
        table = new Entry[M];
    }

    static class Entry {
        long key;
        Entry next;

        public Entry(long key, Entry next){
            this.key = key;
            this.next = next;
        }
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Returner put(long newKey) {
        Returner r = new Returner();
        r.success = true;
        //calculate index of key.
        int hash=hash(newKey);
        //create new entry.
        Entry newEntry = new Entry(newKey, null);

        //if table location does not contain any entry, store entry there.
        if (table[hash] == null) {
            table[hash] = newEntry;
        } else {
            Entry previous = null;
            Entry current = table[hash];

            while (current != null) { //we have reached last entry of bucket.
                r.operationCount++;
                if (current.key == newKey) {
                    if (previous == null) {  //node has to be insert on first of bucket.
                        newEntry.next = current.next;
                        table[hash] = newEntry;
                    } else {
                        newEntry.next = current.next;
                        previous.next = newEntry;
                    }
                }
                previous = current;
                current = current.next;
            }
            previous.next = newEntry;
        }
        return r;
    }

    public void add(long newKey) {
        //calculate index of key.
        int hash=hash(newKey);
        //create new entry.
        Entry newEntry = new Entry(newKey, null);

        //if table location does not contain any entry, store entry there.
        if (table[hash] == null) {
            table[hash] = newEntry;
        } else {
            Entry previous = null;
            Entry current = table[hash];
            while (current != null) { //we have reached last entry of bucket.
                if (current.key == newKey) {
                    override++;
                    if (previous == null) {  //node has to be insert on first of bucket.
                        newEntry.next = current.next;
                        table[hash] = newEntry;
                        return;
                    } else {
                        newEntry.next = current.next;
                        previous.next = newEntry;
                        return;
                    }
                }
                previous = current;
                current = current.next;
            }
            previous.next = newEntry;
        }
    }

    public Returner get(long key) {
        int hash = hash(key);
        Returner returner = new Returner();

        if (table[hash] == null) {
            return returner;
        } else {
            Entry temp = table[hash];
            while (temp != null) {
                returner.operationCount++;
                if (temp.key == key) {
                    returner.success = true;
                    return returner;
                }
                temp = temp.next; //return value corresponding to key.
            }
            return returner;   //returns null if key is not found.
        }
    }

    public Returner remove(long deleteKey) { //todo implement Returner to remove
        Returner r = new Returner();
        int hash = hash(deleteKey);

        if (table[hash] == null) {
            return r;
        } else {
            Entry previous = null;
            Entry current = table[hash];

            while (current != null) { //we have reached last entry node of bucket.
                r.operationCount++;
                if (current.key == deleteKey) {
                    r.success = true;
                    if (previous == null) {  //delete first entry node.
                        table[hash] = table[hash].next;
                    } else {
                        previous.next = current.next;
                    }
                    return r;
                }
                previous = current;
                current = current.next;
            }
            return r;
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

        for(int i = 0; i < M; i++){
            if(table[i] != null){
                Entry entry = table[i];
                while(entry != null){
                    stats.chainsLength[i]++;
                    writer.print("{"+entry.key+"}->");
                    entry = entry.next;
                }
                writer.print("{null}");
                writer.println();
            } else {
                stats.emptyBuckets++;
                writer.println("{null}");
            }
        }
        stats.calc();
        writer.println(stats);
        writer.close();
        return stats;
    }
    public void display() {

        Stats stats = new Stats(M);

        for (int i = 0; i < M; i++) {
            if (table[i] != null) {
                Entry entry = table[i];
                while (entry != null) {
                    stats.chainsLength[i]++;
                    System.out.print("{"+entry.key+"}->");
                    entry=entry.next;
                }
                System.out.print("{null}");
                System.out.println();
            } else {
                stats.emptyBuckets++;
                System.out.println("{null}");
            }
        }
        stats.calc();
        System.out.println(stats);
    }

}
