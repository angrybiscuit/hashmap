import hashfunctions.HashFunction;
import hashfunctions.HashFunction1;
import hashfunctions.HashFunctionTest;
import hashmaps.ChainHashMap;
import hashmaps.HashMap;
import hashmaps.InnerChainHashMap;
import help.FileReader;
import help.Stats;

public class Program {

    public static void main(String[] args) {
        //mapper();
        //iMapper();

        FileReader f = new FileReader();
        long[] arr = f.readFile("numbers.txt");

        HashMap map = new ChainHashMap(arr.length, new HashFunction1(), "bigtestHashmap.txt");
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i]);
        }
        System.out.println(map.displayToTXT());

    }
    public static void mapTester(String fileToRead, String fileToWrite, HashFunction hashFunction, boolean innerMap) {
        FileReader f = new FileReader();
        long[] arr = f.readFile(fileToRead);
        HashMap map;

        if (innerMap) {
            map = new InnerChainHashMap(arr.length, hashFunction, fileToWrite);
        } else {
            map = new ChainHashMap(arr.length, hashFunction, fileToWrite);
        }

        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i]);
        }
        Stats stats = map.displayToTXT();
        System.out.println(stats);
    }

}
