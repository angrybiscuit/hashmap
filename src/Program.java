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

        mapTester("test.txt", "testHashmap.txt", new HashFunctionTest(), false);

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
