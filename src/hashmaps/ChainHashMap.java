package hashmaps;

import hashfunctions.HashFunction;
import help.Stats;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class ChainHashMap extends HashMap{
    protected Entry[] table;   //Array of Entry.
    protected String fileName;

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

    public void put(long newKey){

        //calculate index of key.
        int hash=hash(newKey);
        //create new entry.
        Entry newEntry = new Entry(newKey, null);

        //if table location does not contain any entry, store entry there.
        if(table[hash] == null){
            table[hash] = newEntry;
        }else{
            Entry previous = null;
            Entry current = table[hash];

            while(current != null){ //we have reached last entry of bucket.
                if(current.key == newKey){
                    if(previous == null){  //node has to be insert on first of bucket.
                        newEntry.next = current.next;
                        table[hash] = newEntry;
                        return;
                    }
                    else{
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

    public boolean remove(long deleteKey) {

        int hash=hash(deleteKey);

        if (table[hash] == null) {
            return false;
        } else {
            Entry previous = null;
            Entry current = table[hash];

            while (current != null) { //we have reached last entry node of bucket.
                if (current.key == deleteKey) {
                    if (previous == null) {  //delete first entry node.
                        table[hash] = table[hash].next;
                        return true;
                    } else {
                        previous.next = current.next;
                        return true;
                    }
                }
                previous = current;
                current = current.next;
            }
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

        for(int i = 0; i < M; i++){
            if(table[i] != null){
                Entry entry=table[i];
                while(entry != null){
                    stats.chainsLength[i]++;
                    writer.print("{"+entry.key+"}->");
                    entry = entry.next;
                }
                writer.print("{null}");
                writer.println();
            } else {
                stats.emptyBuckets++;
                writer.println("{null}->{null}");
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
                System.out.println("{null}->{null}");
            }
        }
        stats.calc();
        System.out.println(stats);
    }

}
